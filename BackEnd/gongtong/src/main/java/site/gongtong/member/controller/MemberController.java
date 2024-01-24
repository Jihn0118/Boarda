package site.gongtong.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.gongtong.member.model.Member;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/checkid")
    public String checkId(@RequestParam String id) { //sns x
        //아이디 중복 체크 수행
        //- id리턴 (안 됨)
        //- "can"
        return "";
    }

    @GetMapping("/checknickname")
    public String checkNickname(@RequestParam String nickname) {
        //- nickname리턴 (안 됨)
        //- "can"
        return "";
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody Member member) { //sns x
        // Member 객체 (id, password, nickname, birth, gender, image)
        return "";
        // 성공적으로 회원가입이 이루어졌을 경우
//        return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam String id, @RequestParam String password) { //sns x

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("message", "구색갖추기");
        HttpStatus status = HttpStatus.ACCEPTED;

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 소셜 로그인 처리 메서드
    @GetMapping("/login/social/{provider}") //http://localhost:8080/member/login/social/kakao
    public String socialLogin(@PathVariable String provider, Model model) {
        if ("kakao".equals(provider)) {
            // Kakao 소셜 로그인 로직 구현

        } else if ("google".equals(provider)) {
            // Google 소셜 로그인 로직 구현
            // ...
        } else if ("facebook".equals(provider)) {
            // Facebook 소셜 로그인 로직 구현
            // ...
        }
        // ...

        return "";
    }

    @PostMapping("/findpwd")
    public String findpwd(@RequestParam String id) { //sns x
        //있으면
        //임시 비번 이메일 ! 헐!

        //없으면
        //invalid 쏘기
        return"";
    }

    public sendEmailWithTempPwd(){} //sns x

    @PutMapping("/modifypwd")
    public String modifypwd(@RequestParam String id, @RequestParam String newPassword) { //sns x
        //오케이

        //기존과 동일하면
        //기존과 동일하면 안 됨
        return "";
    }

    public checkNewPwd() {} //sns x
}

/**

 * public class ViewController {
 *     @GetMapping("/dashboard")
 *     public String dashboardPage(@AuthenticationPrincipal User user, Model model) {
 *         model.addAttribute("loginId", user.getUsername());
 *         model.addAttribute("loginRoles", user.getAuthorities());
 *         return "dashboard";
 *     }
 *
 *     @GetMapping("/setting/admin")
 *     @AdminAuthorize
 *     public String adminSettingPage() {
 *         return "admin_setting";
 *     }
 *
 *     @GetMapping("/setting/user")
 *     @UserAuthorize
 *     public String userSettingPage() {
 *         return "user_setting";
 *     }
 * }
 */