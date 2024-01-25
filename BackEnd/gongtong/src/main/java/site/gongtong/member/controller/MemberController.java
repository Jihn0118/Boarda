package site.gongtong.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MemberRepository;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

//    @GetMapping("/checkid")
//    public String checkId(@RequestParam String id) { //sns x
        //아이디 중복 체크 수행
        //- id리턴 (-> 이 아이디로 생성 불가)
        //- "can"
//        HttpStatus status = HttpStatus.ACCEPTED;

//        boolean alreadyExistId = memberService.existsById(id);

//        return "여기는 체크아이디";
        // 파라미터로 아이디 값(문자열)을 받음
        // -> 서비스로 가서 레포지토리 select id 해봄
        // -> 레포지토리에서 역시 select id 해봄
        // db 직접 가서 확인
        // -> 있으면 :true 뱉, 없으면: false뱉 (alreadyExistId) (레포지토리)
        // -> 있으면 :true 뱉, 없으면: false뱉 (alreadyExistId) (서비스)
        // -> true가 오면 ; 못 쓰는 거임 ("이미 있다" 뱉) ->false가 오면: 쓸 수 있는 거임("써도 된다") 뱉
//    }

//    @GetMapping("/checknickname")
//    public String checkNickname(@RequestParam String nickname) {
//        //- nickname리턴 (안 됨)
//        //- "can"
//        return "";
//    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Member member) { //sns x
        Member savedMember = memberRepository.save(member);
        ResponseEntity response = null;
        try {
            if (savedMember.getNum() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("회원가입이 완료되었습니다");
            }
        } catch (Exception e) {
            response =ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원가입이 실패했습니다. 에러 원인은 " + e.getMessage());
        }
        return response;
    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestParam String id, @RequestParam String password) { //sns x
//
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        resultMap.put("message", "구색갖추기");
//        HttpStatus status = HttpStatus.ACCEPTED;
//
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }xx

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