package site.gongtong.review.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.review.model.Review;
import site.gongtong.review.model.ReviewDto;
import site.gongtong.review.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    //TODO 보드게임 리스트도 가져와야 하나?
    @GetMapping("/cafelist")
    public ResponseEntity<List<Cafe>> getCafeList(){
        log.info("카페 리스트 들어옴!!!");
        List<Cafe> cafeList = reviewService.getCafeList();

        return new ResponseEntity<>(cafeList, HttpStatus.OK);
    }

    @GetMapping("/myreview")
    public ResponseEntity<List<Review>> getMyReview(@RequestParam(name = "user_num") int userNum){
        log.info("내 리뷰리스트 보기 들어옴!!!");
        List<Review> reviews = reviewService.getReviews(userNum);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/regist")
    //public ResponseEntity<Integer> createReview(@RequestBody ReviewDto reviewDto){
    public ResponseEntity<Integer> createReview(@RequestPart(name="review") ReviewDto reviewDto,
                                                @RequestPart(name="images")List<MultipartFile> files){
        log.info("리뷰 등록 들어옴!!!");

        int result = reviewService.createReview(reviewDto, files);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/delete")
    public ResponseEntity<Long> deleteReview(@RequestParam(name="reviewId") int reviewId, @RequestParam(name="userNum") int userNum){
        log.info("리뷰 삭제 들어옴!!!");
        Long deletedReviewNum = reviewService.deleteReview(reviewId, userNum);

        return new ResponseEntity<>(deletedReviewNum, HttpStatus.OK);
    }

}
