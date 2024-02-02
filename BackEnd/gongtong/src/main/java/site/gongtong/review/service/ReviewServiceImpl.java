package site.gongtong.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import site.gongtong.boardgame.model.BoardGame;
import site.gongtong.boardgame.repository.BoardGameRepository;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.cafe.repository.CafeCustomRepository;
import site.gongtong.cafe.repository.CafeRepository;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MemberRepository;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.repository.MoimCustomRepository;
import site.gongtong.moim.repository.MoimRepository;
import site.gongtong.review.model.Review;
import site.gongtong.review.model.ReviewDto;
import site.gongtong.review.repository.ReviewCustomRepository;
import site.gongtong.review.repository.ReviewRepository;
import site.gongtong.review.repository.TagRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final TagRepository tagRepository;
    private final CafeRepository cafeRepository;
    private final CafeCustomRepository cafeCustomRepository;
    private final ReviewCustomRepository reviewCustomRepository;
    private final BoardGameRepository boardGameRepository;
    private final MoimCustomRepository moimRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Cafe> getCafeList() {
        return cafeRepository.findAll();
    }

    @Override
    public List<Review> getReviews(int userNum) {
        return reviewRepository.findReviewsByMember_NumOrderByCreatedAtDesc(userNum);
    }

    @Override
    @Transactional
    public Long deleteReview(int reviewId) {
        return reviewCustomRepository.deleteReview(reviewId);
    }

    @Override
    public void createReview(ReviewDto reviewDto) {
        // TODO 보드게임 리스트도 받아서 같이 저장해줘야 함
        Moim moim = moimRepository.findById(reviewDto.getMoimId());
        Cafe cafe = cafeCustomRepository.findById(reviewDto.getCafeId());
        Member member = memberRepository.findMemberByNum(reviewDto.getUserNum());

        Review review = Review.builder()
                .content(reviewDto.getContent())
                .rate(reviewDto.getRate())
                .moim(moim)
                .member(member)
                .cafe(cafe)
                .build();

        reviewRepository.save(review);
    }
}
