package site.gongtong.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.gongtong.member.config.MemberDetails;
import site.gongtong.member.dto.ReviewDto;
import site.gongtong.member.model.Member;
import site.gongtong.member.service.MemberDetailsService;
import site.gongtong.member.service.MyPageService;
import site.gongtong.review.model.Review;

import java.util.*;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping("/mypage")
@Slf4j
@RequiredArgsConstructor
public class MyPageController {

    @Autowired
    MemberDetailsService memberDetailsService;
    @Autowired
    MyPageService myPageService;

    /*무조건 보여주기
        //{닉네임, 프사}
        //작성 피드들

        //본인일 때
        //{아이디, 비밀번호, 닉네임, 생일, 성별, 프사}
        //작성 피드들
        //팔로우 목록*/

    @GetMapping("/profile")
    public ResponseEntity<ReviewDto> viewProfile(@RequestParam String id) {

        log.info("mypage enter reque!!");

//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        ResponseEntity<Map<String, Object>> response = null;

        MemberDetails dbMember = null;
        ReviewDto reviewDto = new ReviewDto();
        //리뷰 리스트
        List<Review> reviews = new ArrayList<>();
        try {
            dbMember = memberDetailsService.loadUserByUsername(id);
            // 정상 처리
            if(dbMember != null) {
                //멤버 프로필 내용 넣기
                reviewDto.setMember(mapToMember(dbMember));

                //리스트 뽑기
                reviews = myPageService.getReviewListByNum(myPageService.idToNum(id));

                for(int i = 0; i< reviews.size(); i++){
                    log.info(reviews.get(i).toString());
                }
                if(reviews == null || reviews.size() == 0) {
                    return new ResponseEntity<ReviewDto>(reviewDto, HttpStatus.OK);
                } else {
                    System.out.println("size>??? "+reviews.size());
                    reviewDto.setReviews(reviews);
                }

//                response = ResponseEntity
//                        .status(HttpStatus.OK)
//                        .body(resultMap);
            }
        } catch (Exception e) { //로그인 멤버 찾아오다가 오류
            e.printStackTrace();
//            resultMap.put("message", e.getMessage());
            return new ResponseEntity<ReviewDto>((ReviewDto) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<ReviewDto>(reviewDto, HttpStatus.OK);
    }

    public static Member mapToMember(MemberDetails dbMember) {
        Member showMember = new Member();
        showMember.setNickname(dbMember.getNickname());
        showMember.setProfileImage(dbMember.getProfileImage());
        showMember.setNum(dbMember.getNum()); // 마이페이지에는 안 나오게 하면 됨.


        if(true) { //토큰으로 사용자 인증 후 넣을지 말지 저장
            showMember.setId(dbMember.getUsername());
            showMember.setBirth(dbMember.getBirth());
            showMember.setGender(dbMember.getGender());
        }

        return showMember;
    }


//    @PutMapping("/profile")
//    public ResponseEntity<String> modifyProfile(@RequestParam String id) {
//
//        log.info("프로필 수정 하기!!");
//
//        /*
//        수정 가능:
//        패스워드(비번 확인까지), 닉네임, 프사
//         */
//
//        return null;
//    }
}

