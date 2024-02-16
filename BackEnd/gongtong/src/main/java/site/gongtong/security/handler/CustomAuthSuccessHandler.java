package site.gongtong.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import site.gongtong.member.model.MemberDto;
import site.gongtong.security.jwt.TokenUtils;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

/**
 * 사용자가 인증에 성공했을 때 호출
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        log.info("3.CustomLoginSuccessHandler");

        // 1. 사용자와 관련된 정보를 모두 조회
        MemberDto memberDto = (MemberDto) authentication.getPrincipal();
        String memberId = memberDto.getUsername();
        String memberNickname = memberDto.getNickname();
        String memberImage = memberDto.getProfileImage();

        // 2. 조회한 데이터를 JsonObject 형태로 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
//        JSONObject memberDtoObject = new JSONObject(objectMapper.writeValueAsString(memberDto));

        HashMap<String, Object> responseMap = new HashMap<>();
        JSONObject jsonObject;

        //사용자 상태 정상 시

        //1. 일반 계정일 경우 데이터 세팅
//        responseMap.put("memberInfo", memberDtoObject);
        responseMap.put("memberId", memberId);
        responseMap.put("memberNickname", memberNickname);
        responseMap.put("memberImage", memberImage);
        responseMap.put("resultCode", 200);
        responseMap.put("failMessage", null);
        jsonObject = new JSONObject(responseMap);

        //JWT 토큰 생성
        String token = TokenUtils.generateJwtToken(memberDto);
        jsonObject.put("token", token); //성공

        //쿠키에 JWT 토큰 저장
        Cookie jwtCookie = new Cookie("jwt", token);
        jwtCookie.setHttpOnly(true); //JavaScript에서 쿠키에 접근할 수 없도록 설정
        jwtCookie.setPath("/"); //모든 경로에서 쿠케에 대한 접근 가능하도록 설정
        response.addCookie(jwtCookie); //응답에 쿠키 추가

        //4. 구성한 응답값을 전달
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (PrintStream printStream = new PrintStream(response.getOutputStream(), true, "UTF-8")) {
            printStream.print(jsonObject);
        }

    }
}
