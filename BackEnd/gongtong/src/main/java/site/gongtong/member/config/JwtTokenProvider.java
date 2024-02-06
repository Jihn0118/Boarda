//package site.gongtong.member.config;
//
////import io.jsonwebtoken.*;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import site.gongtong.member.service.MemberDetailsService;
//
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//
////@Component
//@Service
//@RequiredArgsConstructor
//public class JwtTokenProvider { //토큰 생성 등
////    private String secretKey = generateRandomString(); //signature에서 서명을 생성 및 검증할 때 사용되는 비밀 키
//    private String secretKey = "boardgameboardawitha106teaminssafyleggo"; //나중에 환경변수에 박아두고 가져오기
//
//    private long tokenValidTime = 60 * 60 * 1000L; //토큰 유효시간: 60분
//
//    private final MemberDetailsService memberDetailsService;
//
//    //객체 초기화, secretkey를 인코딩
//    protected void initSecretKey() {
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//    }
//
//    //JWT 토큰 생성
//    public String createToken(String id, List<String> roles) {
//        Claims claims = Jwts.claims().setSubject(id);
//        claims.put("roles", roles);
//        Date now = new Date();
//        String tmpJwtToken = Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + tokenValidTime))
//                .signWith(SignatureAlgorithm.HS256, secretKey) //안코딩을 두 번이나
//                .compact();
//        System.out.println("tmpJwtToekn: "+ tmpJwtToken);
//
//        return tmpJwtToken;
//    }
//
//    //토큰의 유효성, 만료일자 확인
//    public boolean validateToken(String jwtToken) {
//        try {
//            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
//            return !claims.getBody().getExpiration().before(new Date());
//        } catch ( ExpiredJwtException e) {
//            System.out.println("JWT token 만료");
//            return false;
//        } catch (Exception e) {
//            System.out.println("유효하지 않은 토큰");
//            return false;
//        }
//    }
//
//    // Request의 header에서 token값 가져오기
//    public String resolveToken(HttpServletRequest request) {
//        return request.getHeader("X-AUTH-TOKEN");
//    }
//
//    // 토큰에서 회원 정보 추출
//    public String getMemberPk(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
//                .getSubject() ;
////        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//    }
//
//    public Date extractExpiration(String token) {
////        return extractClaim(token, Claims::getExpiration);
//        return (Date) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//    }
//
////    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
////        final Claims claims = extractAllClaims(token);
////        return claimsResolver.apply(claims);
////    }
//
//    //JWT 토큰에서 인증 정보 조회
//    public Authentication getAuthentication(String token) {
//        MemberDetails memberDetails = memberDetailsService.loadUserByUsername(this.getMemberPk(token));
//        return new UsernamePasswordAuthenticationToken(memberDetails, "", memberDetails.getAuthorities()); //패스워드는 전달하지 않음
//    }
//
//    //소셜?
////    public SocialMemberInfo extractMemberId(String jwtToken) {
////        System.out.println("jwtToken: " + jwtToken); //
//////        jwtToken = jwtToken.substring(7);
////        jwtToken = jwtToken.replace("Bearer ", "");
////        System.out.println("jwtToken: " + jwtToken); //
////        Claims claims = Jwts.parser()
////                .setSigningKey(SECRET_KEY)
////                .parseClaimsJws(jwtToken)
////                .getBody();
////
////        SocialMemberInfo memberInfoByJwt = new SocialMemberInfo();
////        memberInfoByJwt.setId(Long.parseLong(claims.get("memberId", String.class)));
////        memberInfoByJwt.setLoginType(claims.get("loginType", String.class));
////
////        System.out.println("memberInfoByJwt");
////        System.out.println(memberInfoByJwt);
////        return memberInfoByJwt;
////    }
//}
