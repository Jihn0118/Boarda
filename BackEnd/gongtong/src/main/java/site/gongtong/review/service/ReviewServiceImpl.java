package site.gongtong.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.gongtong.Image.model.Image;
import site.gongtong.Image.repository.ImageRepository;
import site.gongtong.boardgame.model.BoardGame;
import site.gongtong.boardgame.repository.GameCustomRepository;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.cafe.repository.CafeCustomRepository;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MemberCustomRepository;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.repository.MoimCustomRepository;
import site.gongtong.review.model.Review;
import site.gongtong.review.model.ReviewDto;
import site.gongtong.review.model.Tag;
import site.gongtong.review.repository.ReviewCustomRepository;
import site.gongtong.review.repository.ReviewRepository;
import site.gongtong.review.repository.TagRepository;
import site.gongtong.s3.FileFolder;
import site.gongtong.s3.FileService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final TagRepository tagRepository;
    private final CafeCustomRepository cafeCustomRepository;
    private final ReviewCustomRepository reviewCustomRepository;
    private final GameCustomRepository boardGameRepository;
    private final MoimCustomRepository moimRepository;
    private final MemberCustomRepository memberCustomRepository;
    private final FileService fileService;
    private final ImageRepository imageRepository;

    @Override
    public List<String> getGameNameList() {
        return boardGameRepository.findNameList();
    }

    @Override
    public List<Review> getReviews(String memberId) {
        return reviewCustomRepository.findReviewsWithImagesByUserId(memberId);
    }

    @Override
    @Transactional
    public Long deleteReview(int reviewId, String memberId) {
        return reviewCustomRepository.deleteReview(reviewId, memberId);
    }

    @Override
    @Transactional
    public Integer createReview(ReviewDto reviewDto,
                                List<String> gameNameList,
                                List<MultipartFile> files, String memberId) {
        // TODO 보드게임 리스트도 받아서 같이 저장해줘야 함
        Moim moim = moimRepository.findById(reviewDto.getMoimId());
        Cafe cafe = cafeCustomRepository.findById(reviewDto.getCafeId());
        Member member = memberCustomRepository.findMemberById(memberId);

        Review review = Review.builder()
                .content(reviewDto.getContent())
                .rate(reviewDto.getRate())
                .moim(moim)
                .member(member)
                .cafe(cafe)
                .isRemoved(false)
                .status('Y')
                .build();

        Review resultReview = reviewRepository.save(review);

        if (resultReview == null) {
            System.out.println("리뷰 저장 실패");
            return 1;       // error
        }

        // 보드게임 아이디 받아와서 게임과 리뷰로 태그 만들기
        List<BoardGame> gameList = boardGameRepository.findAllByTitle(gameNameList);
        List<Tag> tagList = new ArrayList<>();

        for (BoardGame game : gameList) {
            Tag tag = Tag.builder()
                    .game(game)
                    .review(resultReview)
                    .cafe(cafe)
                    .build();
            tagList.add(tag);
        }
        List<Tag> resultTagList = tagRepository.saveAll(tagList);

        if (!tagList.isEmpty() && resultTagList.isEmpty()) {
            System.out.println("태그 저장 실패");
            return 2;   // 태그 저장 에러
        }
        // 보드게임 이미지 만들기
        List<Image> imageList = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);

            String str = fileService.uploadFile(file, FileFolder.REVIEW_IMAGES);

            Image image = Image.builder()
                    .review(resultReview)
                    .flag('R')
                    .name(str)
                    .build();

            imageList.add(image);
        }

        List<Image> resultImageList = imageRepository.saveAll(imageList);

        if (!imageList.isEmpty() && resultImageList.isEmpty()) {
            System.out.println("이미지 저장 실패");
            return 3;   // 이미지 에러
        }

        return 0;
    }

}
