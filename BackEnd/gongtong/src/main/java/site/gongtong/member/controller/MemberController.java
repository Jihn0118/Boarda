package site.gongtong.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import site.gongtong.member.config.MemberDetails;
import site.gongtong.member.dto.SignUpRequest;
import site.gongtong.member.model.Member;
import site.gongtong.member.service.MemberDetailsService;
import site.gongtong.member.service.MemberService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    MemberDetailsService memberDetailsService;
    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/checkid")
    public ResponseEntity<String> checkId(@RequestParam String id) { //sns x
        //아이디 중복 체크 수행
        //- id리턴 (-> 이 아이디로 생성 불가)
        //- "can"
        ResponseEntity<String> response = null;
        if(!memberService.canUseId(id)){ //사용 불가 아이디
            response = ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(id); //이 내용은 사용자에게 숨기기?
        }
        else { //사용 가능 아이디
            response = ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("사용 가능한 아이디입니다");
        }

        return response;
    }

    @GetMapping("/checknickname")
    public ResponseEntity<String> checkNickname(@RequestParam String nickname) { //sns x
        //아이디 중복 체크 수행
        //- id리턴 (-> 이 아이디로 생성 불가)
        //- "can"
        ResponseEntity<String> response = null;
        if(!memberService.canUseNickname(nickname)){ //사용 불가 닉네임
            response = ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(nickname); //이 내용은 사용자에게 숨기기?
        }
        else { //사용 가능 닉네임
            response = ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("사용 가능한 닉네임입니다");
        }

        return response;

    }

    @GetMapping("/login")
    public String login(){ return "login"; }
    @GetMapping("/main")
    public String main(){  return "main"; }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult, Model model) { //sns x

        Member savedMember = null;
        ResponseEntity<String> response = null;
        
        // 1 id 중복체크
        if(checkId(signUpRequest.getId()).getStatusCode() == HttpStatus.CONFLICT) {
            response = ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("사용할 수 없는 아이디입니다");
            return response;
        }
        // 2 닉네임 중복체크
        if(checkNickname(signUpRequest.getNickname()).getStatusCode() == HttpStatus.CONFLICT) {
            response = ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("사용할 수 없는 닉네임입니다");
            return response;
        }
        // 3 password와 passwordCheck가 같은지 체크
        if(signUpRequest.getPassword() != signUpRequest.getPasswordCheck()) { //다르면 걍 리턴
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호와 비밀번호 확인이 일치하지 않습니다");
            return response;
        }

        
        //위의 내용 거치고 옴
        try {
            String hashPwd = passwordEncoder.encode(signUpRequest.getPassword());
            signUpRequest.setPassword(hashPwd);
            savedMember = memberService.signup(signUpRequest); //내용 db에 세이브?

            if (savedMember.getNum() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("회원가입이 완료되었습니다");
            }
        } catch (Exception e) {
            response =ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원가입이 실패했습니다. 에러 원인: " + e.getMessage());
        }
        return response;
    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestParam String id, @RequestParam String password) { //sns x
//
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        resultMap = null;
//        HttpStatus status = HttpStatus.ACCEPTED;
//        MemberDetails loginMember ;
//        String input_password = password;
//
//        try {
//            loginMember = memberDetailsService.loadUserByUsername(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        if(loginMember == null) { //없는 아이디
//            resultMap.put("message", "아이디 또는 비밀번호가 잘못 입력되었습니다.");
//            log.info("아이디 없음");
//            status = HttpStatus.UNAUTHORIZED;
//        }
//        else if(!passwordEncoder.matches(loginMember.getPassword(), passwordEncoder.encode(password))) { //틀린 비밀번호
//            resultMap.put("message", "아이디 또는 비밀번호가 잘못 입력되었습니다.");
//            log.info("비번 없음");
//            status = HttpStatus.UNAUTHORIZED;
//        }
//        //잘 되면 resultMap에 유저 정보 넣고
//        resultMap.put("loginMember", loginMember);
//        log.info("회원정보 찾음. ");
//        log.debug("유저 정보: {}",loginMember);
//        //토큰은 어떻게 붙이지?
//
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }

    @RequestMapping("/")
    public MemberDetails getMemberDetailsAfterLogin(Authentication authentication) {
        /* findById 만 사용해서 구현할 수 있을까 (Option<> 리턴) */
        MemberDetails member = memberDetailsService.loadUserByUsername(authentication.getName());
        if(member != null) {
            return member;
        } else {
            return null;
        }
    }



//    @PostMapping("/findpwd")
//    public String findpwd(@RequestParam String id) { //sns x
//        //있으면
//        //임시 비번 이메일 ! 헐!
//
//        //없으면
//        //invalid 쏘기
//        return"";
//    }

//    public sendEmailWithTempPwd(){} //sns x

//    @PutMapping("/modifypwd")
//    public String modifypwd(@RequestParam String id, @RequestParam String newPassword) { //sns x
//        //오케이
//
//        //기존과 동일하면
//        //기존과 동일하면 안 됨
//        return "";
//    }

//    public checkNewPwd() {} //sns x
}