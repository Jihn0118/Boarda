package site.gongtong.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.gongtong.member.dto.LoginRequest;
import site.gongtong.member.dto.SignUpRequest;
import site.gongtong.member.model.Member;
import site.gongtong.member.model.MemberDetails;
import site.gongtong.member.model.MemberDto;
import site.gongtong.member.service.MemberDetailsService;
import site.gongtong.member.service.MemberService;
import site.gongtong.security.handler.CustomAuthSuccessHandler;
import site.gongtong.security.jwt.TokenUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberDetailsService memberDetailsService;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthSuccessHandler customAuthSuccessHandler;

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

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest signUpRequest) { //sns x

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
        if(!signUpRequest.getPassword().equals(signUpRequest.getPasswordCheck())) { //다르면 걍 리턴
            response = ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호와 비밀번호 확인이 일치하지 않습니다");
            return response;
        }


        //위의 내용 거치고 옴
        try {
            savedMember = memberService.signup(signUpRequest); //내용 db에 세이브 (서비스 단에서 비번 인코딩함)

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

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(HttpServletRequest httprequest,
                                                     HttpServletResponse httpresponse,
                                                    Authentication authentication,
                                            @RequestBody LoginRequest loginRequest) { //sns x
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ResponseEntity<Map<String, Object>> response;

        MemberDetails dbMember;
        MemberDto dbDto;
        String token = null;

        try {
            dbMember = memberDetailsService.loadUserByUsername(loginRequest.getId());
            dbDto = memberService.getLoginMemberById(loginRequest.getId());
        } catch (Exception e) { //로그인 멤버 찾아오다가 오류
            e.printStackTrace();

            resultMap.put("message", e.getMessage());
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(resultMap);
            return response;
        }

        if (dbMember == null || !passwordEncoder.matches(loginRequest.getPassword(), dbMember.getPassword())) {
            resultMap.put("message", "아이디 혹은 패스워드가 잘못 되었습니다");
            response = ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(resultMap);
        }
        else {
            try {
                // Authentication 객체 생성
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(dbDto, null, dbMember.getAuthorities());
                // CustomAuthSuccessHandler에서 처리
                customAuthSuccessHandler.onAuthenticationSuccess( httprequest, httpresponse, authenticationToken);

                resultMap.put("message", "로그인 성공");
                resultMap.put("dbMember", dbMember);
                resultMap.put("token", token); //뺴기

                response = ResponseEntity
                        .status(HttpStatus.OK)
                        .body(resultMap);
                
            } catch (IOException e) {
                e.printStackTrace();
                response = ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(resultMap);
                return response;
            }

        }

        return response;
    }

    //JWT 토큰 만들기
    private String makeJWTtoken(MemberDto memberDto) {
        return TokenUtils.generateJwtToken(memberDto);
    }

    @RequestMapping("/")
    public MemberDetails getMemberDetailsAfterLogin(Authentication authentication) {
        //로그인한 사용자의 정보와 권한을 얻기 위한 것 (user인지 admin인지 등을 알기 위해...)
        /* findById 만 사용해서 구현할 수 있을까 (Option<> 리턴) */
        MemberDetails member = memberDetailsService.loadUserByUsername(authentication.getName());
        if(member != null) {
            return member;
        } else {
            return null;
        }
    }

//    @GetMapping("/logout")
//    public ResponseEntity<Integer> logout(@RequestParam String id, HttpServletRequest request) {
//
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader == null ) {
//            log.info("토큰 없음");
//            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST); //토큰 없음
//        }
//
//
//        if (getUserIdFromToken()) {
//            // 클라이언트에게 로그인이 필요하다는 메시지를 반환
//            Map<String, Object> response = new HashMap<>();
//            response.put("message", "로그인이 필요합니다.");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//
//        // JWT 토큰을 저장하는 쿠키의 값을 삭제
//        Cookie jwtCookie = new Cookie("jwt", null); //쿠키 값 없애기
//        jwtCookie.setMaxAge(0); //쿠키의 유효 기간을 0으로 설정
//
//        // 클라이언트에게 로그아웃이 성공적으로 처리되었음을 알리는 응답 반환
//
//        response.put("message", "로그아웃이 성공적으로 처리되었습니다.");
//        return ResponseEntity.ok().body(response);
//    }

}