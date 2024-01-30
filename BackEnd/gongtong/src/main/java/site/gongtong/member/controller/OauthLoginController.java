package site.gongtong.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/?")
@Slf4j
@RequiredArgsConstructor
public class OauthLoginController {

    // 소셜 로그인 처리 메서드
//    @GetMapping("/login/social/{provider}") //http://localhost:8080/member/login/social/kakao
//    public String socialLogin(@PathVariable String provider, Model model) {
//        if ("kakao".equals(provider)) {
//            // Kakao 소셜 로그인 로직 구현
//
//        } else if ("google".equals(provider)) {
//            // Google 소셜 로그인 로직 구현
//            // ...
//        } else if ("facebook".equals(provider)) {
//            // Facebook 소셜 로그인 로직 구현
//            // ...
//        }
//        // ...
//
//        return "";
//    }
}
