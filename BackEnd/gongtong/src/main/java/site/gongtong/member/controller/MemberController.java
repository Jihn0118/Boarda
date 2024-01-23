package site.gongtong.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.gongtong.member.model.Member;

@Controller
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/checkid")
    public String checkId(@RequestParam String id) {
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
    public String signUp(@RequestBody Member member) {
        // Member 객체 (id, password, nickname, birth, gender, image)
        return "";
        // 성공적으로 회원가입이 이루어졌을 경우
//        return ResponseEntity.ok("회원가입이 성공적으로 완료되었습니다.");
    }
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