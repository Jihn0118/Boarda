package site.gongtong.member.controller;

import com.querydsl.core.Tuple;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.gongtong.member.dto.EditProfileDto;
import site.gongtong.member.dto.FollowListDto;
import site.gongtong.member.dto.PasswordChangeDto;
import site.gongtong.member.dto.ProfileDto;
import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;
import site.gongtong.member.model.MemberDetails;
import site.gongtong.member.service.FollowService;
import site.gongtong.member.service.MemberDetailsService;
import site.gongtong.member.service.MemberService;
import site.gongtong.member.service.MyPageService;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.repository.MoimRepository;
import site.gongtong.moim.service.MoimService;
import site.gongtong.review.model.Review;
import site.gongtong.security.jwt.TokenUtils;

import java.security.SecureRandom;
import java.util.*;
@RestController
@RequestMapping("/mypage")
@Slf4j
@RequiredArgsConstructor
public class MyPageController {

    private final MemberDetailsService memberDetailsService;
    private final MyPageService myPageService;
    private final FollowService followService;
    private final PasswordEncoder passwordEncoder;
    private final MemberController memberController;
    private final MemberService memberService;
    private final MoimService moimService;

    @GetMapping("/profile") //토큰으로 본인인지 확인 필요 -> 프론트?
    public ResponseEntity<ProfileDto> viewProfile(@RequestParam(value = "id") String id,
                                                  HttpServletRequest request) {

        MemberDetails dbMember = null;

        ProfileDto profileDto = new ProfileDto();
        List<Review> reviews; //리뷰 리스트

        List<Moim> dbMoims ;

        try {
            dbMember = memberDetailsService.loadUserByUsername(id);
            // 정상 처리
            if(dbMember != null) {
                //멤버 프로필 내용 넣기
                profileDto.setMember(mapToMember(dbMember, isSameId(fetchToken(request), id)));

                //리스트 뽑기
                reviews = myPageService.getReviewListByNum(myPageService.idToNum(id));

                for(int i = 0; i< reviews.size(); i++){
                    log.info(reviews.get(i).toString());
                }
                profileDto.setReviews(reviews);

                dbMoims = moimService.getMyMoimList(myPageService.idToNum(id));
                profileDto.setMoimList(dbMoims);
//                if(dbMoim!=null){
//                    profileDto.setMoim(dbMoim);
//                }
                Moim dbMoim = null;
                if(dbMoims.size() >= 1) {
                    dbMoim = dbMoims.get(0);
                    profileDto.setMoim(dbMoim);
                } else {
                    profileDto.setMoim(dbMoim);
                }
            }
        } catch (Exception e) { //로그인 멤버 찾아오다가 오류
            e.printStackTrace();
            return new ResponseEntity<>((ProfileDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    public static Member mapToMember(MemberDetails dbMember, boolean issameId) {
        Member showMember = new Member();
        showMember.setNickname(dbMember.getNickname());
        showMember.setProfileImage(dbMember.getProfileImage());
        showMember.setNum(dbMember.getNum()); // 마이페이지에는 안 나오게 하면 됨.


        if(issameId) { //나==id 일 때만
            showMember.setId(dbMember.getUsername());
            showMember.setBirth(dbMember.getBirth());
            showMember.setGender(dbMember.getGender());
        } 

        return showMember;
    }


    @PutMapping("/profile")
    public ResponseEntity<String> modifyProfile(@RequestParam(name = "id") String id,
                                                @RequestBody EditProfileDto editProfileDto,
                                                HttpServletRequest request) {
        //본인 아니면 리턴
        if(!isSameId(fetchToken(request), id)) {
            return new ResponseEntity<>("권한 없음", HttpStatus.UNAUTHORIZED);
        }
        else {
            log.info("profile modify start!!");

            //read only는 원래 값 그대로 넣기 (id기반으로 Member 찾아서 넣기)
            Member member = myPageService.findById(id);
            editProfileDto.setNum(member.getNum());
            editProfileDto.setId(member.getId());
            editProfileDto.setBirth(member.getBirth());
            editProfileDto.setGender(member.getGender());

            //프사, 닉변은 빈값 아니면 하기. (비번은 따로)
            if (editProfileDto.getProfileImage().equals("")) {
                editProfileDto.setProfileImage(member.getProfileImage());
            } else {
                editProfileDto.setProfileImage(editProfileDto.getProfileImage());
            }
            if (editProfileDto.getNickname().equals("")) {
                editProfileDto.setNickname(member.getNickname());
            } else {
                editProfileDto.setNickname(editProfileDto.getNickname());
            }
            // ㄴ editDto완성

            try {
                if (myPageService.modifyProfile(editProfileDto) > 0) {
                    return new ResponseEntity<>("프로필 수정 성공 -db확인", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("프로필 수정 안 됨 - 내용이 같음", HttpStatus.OK);
                }
            } catch (Exception e) {
//            resultMap.put("message", e.getMessage());
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    //비번 찾기
    @PostMapping("/forgetpwd")
    public ResponseEntity<Integer> forgetPwd(@RequestParam(name = "id") String id) {
        //1. 임시 비번 만들기
        //1-랜덤 문자열 생성
        String newRawPwd = getRandomPwd(10);
          System.out.println("tmp rawPwd: "+newRawPwd);
        //2-위의 문자열 bcrypt로 암호화하기
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String newEncodedPwd = encoder.encode(newRawPwd); //암호화된 문자열
//          System.out.println("tmp encodedPwd: "+newEncodedPwd);

        //2. db에 비번 바꾸기
        try {
            if (myPageService.setPwd(id, newEncodedPwd) > 0) { //성공!
                //비번바꾸기 성공하면 1!
                return new ResponseEntity<>(1, HttpStatus.OK);
            } else { //실패ㅠ = 0
                //모종의 이유로 실패 - 디비는 갔다 옴
                return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()); //뭐가 잘못된 건지 찎어보기
            //내부 이유로 실패 - 2
            return new ResponseEntity<>(2, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //랜덤 문자 만들기
    public String getRandomPwd(int length) {
        char[] rndAllCharacters = new char[]{
                //number
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                //uppercase
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                //lowercase
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                //special symbols
                '@', '$', '!', '%', '*', '?', '&'
        };
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        int rndAllCharactersLength = rndAllCharacters.length;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(rndAllCharacters[random.nextInt(rndAllCharactersLength)]);
        }

        return stringBuilder.toString();
    }


    //비번 변경
    @PutMapping("/modifypwd")
    public ResponseEntity<Integer> modifyPwd(@RequestBody PasswordChangeDto passwordChangeDto,
                                             HttpServletRequest request) {
        //토큰에서 추출한 아이디와 dto의 아이디가 같은지 확인, 다르면 return
        if(!isSameId(fetchToken(request), passwordChangeDto.getId())){
            return new ResponseEntity<>(-1, HttpStatus.UNAUTHORIZED);
        }

        //1. 입력된 id 기반으로 해당 유저 entity 찾기
        Member member;
        try {
            member = myPageService.findById(passwordChangeDto.getId());
            if(member == null)
                return new ResponseEntity<>(0, HttpStatus.NOT_FOUND); // id에 해당하는 유저 없음

            //2. member의 비번 - 입력된 현재비번 동일성 여부
            if( !passwordEncoder.matches(passwordChangeDto.getCurPwd(), member.getPassword()) )
                return new ResponseEntity<>(2, HttpStatus.UNAUTHORIZED); // 현재 비밀번호 불일치 시 땡~!!

            //3. db에 새로운 비밀번호 encode해서 대체하기
            String newPwd = passwordChangeDto.getNewRawPwd();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String newEncodedPwd = encoder.encode(newPwd);
            try {
                if (myPageService.setPwd(passwordChangeDto.getId(), newEncodedPwd) > 0) {
                    return new ResponseEntity<>(1, HttpStatus.OK); //성공
                }
            } catch (Exception e) {
                System.out.println(e.getMessage()); //뭐가 잘못된 건지 찍어보기
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(3, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(3, HttpStatus.INTERNAL_SERVER_ERROR); // 모종의 이유로 실패
    }

    //회원 탈퇴
    @DeleteMapping("/profile")
    public ResponseEntity<Integer> deleteMember(@RequestParam String id,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {

        String jwt = fetchToken(request);
        if (!isSameId(jwt, id)) {
            log.info("id and loggedInUserId is not same");
            return new ResponseEntity<>(0, HttpStatus.UNAUTHORIZED); // 권한 없음 에러 반환
        }

        try {
            // 회원 정보 삭제
            int num = myPageService.idToNum(id);
            memberService.deleteMember(num);

            // 로그아웃 처리
            memberController.logout(request, response);

            // 회원탈퇴 성공 응답 반환
            log.info("good");
            return new ResponseEntity<>(1, HttpStatus.OK);
        } catch (Exception e) {
            // 회원탈퇴 처리 중 오류 발생 시 에러 응답 반환
            log.info("i dont know what it is eroor");
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //팔로우 하기
    @PostMapping("/follow")
    public ResponseEntity<Integer> registFollow (@RequestParam(name = "nickname") String yourNickname,
                                                 @RequestParam(name = "flag") char flag,
                                                 HttpServletRequest request) {
        Member memMe;
        Member memYou;
        String jwt = fetchToken(request);
        String myId = TokenUtils.getUserIdFromToken(jwt);

        try {
            //1. 아이디, 닉네임 기반 멤버 찾아오기
            memMe = myPageService.findById(myId); //-> 여기가 팔로워
            memYou = myPageService.findByNickname(yourNickname);
            if(memMe==null || memYou==null) {
//                log.info("follow; null Object input error!");
                return new ResponseEntity<>(0, HttpStatus.NOT_FOUND); //해당 유저 찾을 수 없으면 안 됨
            }
            if(memMe==memYou) {
//                log.info("follow; same Object cannot have relationship!");
                return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST); //팔로우==팔로잉은 안 됨
            }

            //2. id 뽑아서, 디비에 관계 저장
            //이미 있는 관계는 패스
            if( followService.existRelation(memMe.getNum(), memYou.getNum()) > 0 ) { //팔로워 팔로잉
//                log.info("follow; Already existed relationship!");
                return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST); //이미 있는 관계가 또 들어오면 무시
            }
            //이미 있는 관계가 아니면 수행하기
            Follow newRelation = followService.save(memMe, flag, memYou);
            if (newRelation == null) new ResponseEntity<>(2, HttpStatus.INTERNAL_SERVER_ERROR); //객체 안 만들어짐

        } catch (Exception e) {
            e.printStackTrace(); //예상치 못한 다중 테이블 참조 오류를 발생
            return new ResponseEntity<>(2, HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        log.info("follow making; GOODDDD!");
        return new ResponseEntity<>(1, HttpStatus.OK); //성공!
    }
    //팔로우 취소하기
    @DeleteMapping("/follow")
    public ResponseEntity<Integer> deleteFollow (@RequestParam (name = "id") String followId,
                                                 HttpServletRequest request) {

        int myNum = myPageService.idToNum(TokenUtils.getUserIdFromToken(fetchToken(request)));
        int yourNum = myPageService.idToNum(followId);

        Follow wannaDeleteFollow = followService.findBy2Nums(myNum, yourNum); //팔로워 팔로잉
        if (wannaDeleteFollow == null) {
            log.info("follow delete: there's no relation~!");
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR); //그런 팔로우 관계는 없다
        }

        followService.deleteFollow(wannaDeleteFollow);
        log.info("follow Delete: successful~!");
        return new ResponseEntity<>(1, HttpStatus.OK); //팔로우 끊기 완료
    }

    //팔로우&차단 목록
    @GetMapping("/follow")
    public ResponseEntity<List<FollowListDto>> getFollowList (HttpServletRequest request) {
        String id = TokenUtils.getUserIdFromToken(fetchToken(request));
        int userNum = -1;

        try {
            userNum = myPageService.idToNum(id);

        } catch (Exception e) {
            log.info("No user~~!!");
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED); // 존재x 유저
        }

        List<Tuple> followList = null;
        List<FollowListDto> followListDto = new ArrayList<>();

        try {
            followList = followService.getFollowList(userNum);
            for(Tuple t : followList) {
                Member member = t.get(0, Member.class);
                Follow follow = t.get(1, Follow.class);

                // Member와 flag를 사용하여 FollowListDto 객체를 생성
                FollowListDto dto = new FollowListDto(member, follow);

                // 생성한 dto를 리스트에 추가
                followListDto.add(dto);

            }
            return new ResponseEntity<>(followListDto, HttpStatus.OK); // 성공
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 어떤이유
        }
    }

    //쿠키에서 JWT 추츨하기
    public String fetchToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String jwt = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        return jwt;
    }

    //JWT에서 추출한 id값과 파라미터로 들어온 id값이 같은지 확인
    public boolean isSameId(String jwt, String id) {
        // JWT 검증 및 클레임에서 현재 로그인한 사용자의 ID 추출
        String loggedInUserId = TokenUtils.getUserIdFromToken(jwt);
        return id.equals(loggedInUserId);
    }
}

