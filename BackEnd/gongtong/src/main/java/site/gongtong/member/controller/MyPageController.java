//package site.gongtong.member.controller;
//
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class MyPageController {
//}

/**
 * @Controller
 * @RequestMapping("/view")
 * public class ViewController {
 *
 *     @GetMapping("/login")
 *     public String loginPage() {
 *         return "login";
 *     }
 *
 *     @GetMapping("/join")
 *     public String joinPage() {
 *         return "join";
 *     }
 *
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