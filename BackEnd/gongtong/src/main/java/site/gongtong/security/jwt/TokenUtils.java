package site.gongtong.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.gongtong.member.model.MemberDto;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. JWT 생성
 * 2. JWT 검증(유효성 검증, 관련 정보 추출 등)
 */

@Slf4j
@Component
public class TokenUtils {
    //나중에 환경변수에 넣어놓고, 거기서 가져오기
    private static final String jwtSecretKey = "boardgameboardawitha106teaminssafyleggo";
    // jwtSecretKey를 바이트 배열로 변환하고, 이를 사용하여 HMAC-SHA256 알고리즘에 사용할 키를 생성한다.
    private static final Key key = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    private static final String JWT_TYPE = "JWT";       //헤더 들어갈 내용
    private static final String ALGORITHM = "HS256";    //헤더 들어갈 내용
    private static final String LOGIN_ID = "loginId";   // 고유 아이디 = pk
    private static final String NICKNAME = "nickname";  // 보여지는 이름 = 닉네임

    /**
     * 사용자 pk(=num)를 기준으로 JWT '토큰을 발급'
     */
    public static String generateJwtToken(MemberDto memberDto) {
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())                      //어떤 알고리즘 사용, 토큰의 타입
                .setClaims(createClaims(memberDto))
                .setSubject(String.valueOf(memberDto.getId()))     //로그인 아이디
                .setIssuer("boarda.site")                     //iss(Issuer) 클레임을 설정하는 데 사용 (발급자 식별)
                .signWith(key, SignatureAlgorithm.HS256)        //헤더, 클레임 기반으로 시그니처 구성
                .setExpiration(createExpiredDate());            //토큰의 만료시간을 설정

        return builder.compact();
    }

    /**
     * -
     * 멤버 정보를 기반으로 '클래임을 생성해주는 메서드'
     *
     * @param memberDto
     * @return Map<String, Object>
     */
    private static Map<String, Object> createClaims(MemberDto memberDto) {
        // 공개 클래임에 멤버의 닉네임과 아이디(이메일)을 설정해서 정보를 조회
        Map<String, Object> claims = new HashMap<>();

        claims.put(LOGIN_ID, memberDto.getId());
        claims.put(NICKNAME, memberDto.getNickname());

        return claims;
    }

    /**
     * -
     * 사용된 암호 알고리즘, 토큰 타입 기반 'JWT의 헤더값을 생성'
     */
    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", JWT_TYPE);
        header.put("alg", ALGORITHM);
        header.put("regDate", System.currentTimeMillis()); // - 만들어진 시간
        return header;
    }

    /**
     * 토큰 기반 '사용자 정보 반환해줌' (능)
     * 토큰의 만료시간, 변조 여부, 널 여부를 체크
     */
    public static boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);


            log.info("expireTime : " + claims.getExpiration());//ok
            log.info("id : " + claims.getSubject());

            return true; //유효한 토큰 - 만료 시간, 사용자 아이디 로그
        } catch (ExpiredJwtException expiredJwtException) { //유효하지 않은 토큰 - 기간 만료
            log.error("Token Expired", expiredJwtException);
            return false;
        } catch (JwtException jwtException) {               //유효하지 않은 토큰 - 변조된 토큰
            log.error("Token Tampered", jwtException);
            return false;
        } catch (NullPointerException npe) {                //유효하지 않은 토큰 - null
            log.error("Token is null", npe);
            return false;
        }
    }

    /**
     * -
     * 토큰의 만료 기간 지정 함수 : 24시간
     *
     * @return Date
     */
    private static Date createExpiredDate() {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(Duration.ofHours(24));
        return Date.from(expiryDate);
    }

    /**
     * 토큰에서 'Claims 정보만 가져오기'
     *
     * @return Claims : Claims
     */
    private static Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder() //메서드를 호출하여 JWT 파서를 빌드
                .setSigningKey(key) //메서드를 사용하여 JWT를 서명한 키를 설정 (이 키는 토큰 검증에 사용)
                .build()            //JWT 파서를 빌드
                .parseClaimsJws(token) //주어진 JWT 토큰을 파싱. 서명이 유효한 JWT인 경우에만 클레임을 추출
                .getBody();
    }

    /**
     * 토큰을 기반 '사용자 정보를 반환받음' (수)
     *
     * @return String : 사용자 아이디
     */
    public static String getUserIdFromToken(String token) { //외부로 가지고 나갈 수 있게 public
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    public static String fetchToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String jwt = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        return jwt;
    }

    //JWT에서 추출한 id값과 파라미터로 들어온 id값이 같은지 확인
    public static boolean isSameId(String jwt, String id) {
        // JWT 검증 및 클레임에서 현재 로그인한 사용자의 ID 추출
        String loggedInUserId = TokenUtils.getUserIdFromToken(jwt);
        return id.equals(loggedInUserId);
    }
}
