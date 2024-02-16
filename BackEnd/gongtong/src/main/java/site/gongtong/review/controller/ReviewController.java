package site.gongtong.review.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.gongtong.review.model.Review;
import site.gongtong.review.model.ReviewDto;
import site.gongtong.review.service.ReviewService;
import site.gongtong.security.jwt.TokenUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/showgames")
    public ResponseEntity<List<String>> showGames() {
        log.info("보드게임 제목 리스트 들어옴!!!");
        List<String> gameNameList = reviewService.getGameNameList();

        return new ResponseEntity<>(gameNameList, HttpStatus.OK);
    }

    @GetMapping("/myreview")
    public ResponseEntity<List<Review>> getMyReview(HttpServletRequest request) {
        log.info("내 리뷰리스트 보기 들어옴!!!");
        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        List<Review> reviews = reviewService.getReviews(memberId);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/regist")
    public ResponseEntity<Integer> createReview(@RequestPart(name = "review") ReviewDto reviewDto,
                                                @RequestPart(name = "gamenames") List<String> gameNameList,
                                                @RequestPart(name = "images") List<MultipartFile> files,
                                                HttpServletRequest request) {
        log.info("리뷰 등록 들어옴!!!");

        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        int result = reviewService.createReview(reviewDto, gameNameList, files, memberId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<Long> deleteReview(@RequestParam(name = "reviewId") int reviewId, HttpServletRequest request) {
        log.info("리뷰 삭제 들어옴!!!");

        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        Long deletedReviewNum = reviewService.deleteReview(reviewId, memberId);

        return new ResponseEntity<>(deletedReviewNum, HttpStatus.OK);
    }

}
