package site.gongtong.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

/**
 * 사용자 인증 실패
 */

@Slf4j
@Configuration
@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
        //[1] 클라이언트로 전달할 응답값 구성
        JSONObject jsonObject = new JSONObject();
        String failMessage = "";

        //[2] 발생한 exception에 대해 확인
        if (exception instanceof AuthenticationServiceException) {
            failMessage = "로그인 정보가 일치하지 않습니다.";
        } else {
            failMessage = "인증 실패";
        }

        //[3] 응답값을 구성하고 전달
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        log.info(failMessage);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("memberInfo", null);
        resultMap.put("resultCode", 9999);
        resultMap.put("failMessage", failMessage);

        jsonObject = new JSONObject(resultMap);
        //response.getOutputStream().print(jsonObject.toString());
        try (PrintStream printStream = new PrintStream(response.getOutputStream(), true, "UTF-8")) {
            printStream.print(jsonObject);
        }
    }
}
