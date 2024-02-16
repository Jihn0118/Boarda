package site.gongtong.security.filter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import site.gongtong.member.model.MemberDto;

/**
 * 사용자의 로그인 요청을 처리하고, 인증을 시도
 */

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 사용자가 로그인을 시도할 때 호출.
     * <p>
     * HTTP 요청에서 사용자 이름과 비밀번호를 추출하여 UsernamePasswordAuthenticationToken 객체를 생성,
     * 이를 AuthenticationManager에 전달하여 인증을 시도
     * <p>
     * 인증이 성공 -> 인증된 사용자의 정보와 권한을 담은 Authentication 객체를 반환,
     * 인증이 실패 -> AuthenticationException을 던짐
     */

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authRequest = null;

        try {
            authRequest = getAuthRequest(request); //정보 추출하여 Token 객체를 생성하는 역할
            /**
             * 보안 인증(authentication) 객체에 -> 추가적인 세부 정보(details)를 설정하는 데 사용
             *
             * request는 사용자의 요청(request) 객체이며, authRequest는 인증 요청(authentication request) 객체
             *
             * 추가된 세부 정보는 나중에 필요한 경우 보안 컨텍스트(SecurityContext)에서 가져와서 사용될 수 있
             */
            setDetails(request, authRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Authentication 객체를 반환
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * HTTP 요청에서 사용자 이름과 비밀번호를 추출
     * -> UsernamePasswordAuthenticationToken 객체를 생성하는 역할
     * <p>
     * HTTP 요청의 입력 스트림에서 JSON 형태의 사용자 이름과 비밀번호를 읽어 UserDto 객체를 생성
     * -> 이를 기반으로 UsernamePasswordAuthenticationToken 객체를 생성
     */
    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) throws Exception {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); //추가된 날짜 및 시간 API인 Java Time API를 Jackson 라이브러리와 통합하기 위해 사용
            objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

            MemberDto member = objectMapper.readValue(request.getInputStream(), MemberDto.class); //json -> java객체 역직렬화
            log.info("1.CustomAuthenticationFilter :: loginId: " + member.getId() + "userPw: " + member.getPassword());

            /**
             * ID, PW를 기반으로 UsernamePasswordAuthenticationToken 토큰을 발급한다.
             * UsernamePasswordAuthenticationToken 객체가 처음 생성될 때 authenticated 필드는 기본적으로 false로 설정된다.
             */
            return new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword());
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
