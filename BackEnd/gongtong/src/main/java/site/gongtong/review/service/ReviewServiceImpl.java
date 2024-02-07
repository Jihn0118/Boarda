package site.gongtong.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.gongtong.Image.model.Image;
import site.gongtong.Image.repository.ImageRepository;
import site.gongtong.boardgame.repository.BoardGameRepository;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.cafe.repository.CafeCustomRepository;
import site.gongtong.cafe.repository.CafeRepository;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MemberCustomRepository;
import site.gongtong.member.repository.MemberRepository;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.repository.MoimCustomRepository;
import site.gongtong.review.model.Review;
import site.gongtong.review.model.ReviewDto;
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
    private final CafeRepository cafeRepository;
    private final CafeCustomRepository cafeCustomRepository;
    private final ReviewCustomRepository reviewCustomRepository;
    private final BoardGameRepository boardGameRepository;
    private final MoimCustomRepository moimRepository;
    private final MemberCustomRepository memberCustomRepository;
    private final FileService fileService;
    private final ImageRepository imageRepository;

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
    public Long deleteReview(int reviewId, int userNum) {
        return reviewCustomRepository.deleteReview(reviewId, userNum);
    }

    @Override
    @Transactional
    public Integer createReview(ReviewDto reviewDto, List<MultipartFile> files) {
        // TODO 보드게임 리스트도 받아서 같이 저장해줘야 함
        Moim moim = moimRepository.findById(reviewDto.getMoimId());
        Cafe cafe = cafeCustomRepository.findById(reviewDto.getCafeId());
        Member member = memberCustomRepository.findMemberByNum(reviewDto.getUserNum());

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
            return 0;       // error
        }

        List<Image> imageList = new ArrayList<>();

         //리스트 돌면서 이미지 => image 객체리스트로 변경해서 saveAll();
        for(int i = 0; i < files.size(); i++){
            MultipartFile file = files.get(i);

            String str = fileService.uploadFile(file, FileFolder.REVIEW_IMAGES);

            Image image = Image.builder()
                    .review(resultReview)
                    .flag('R')
                    .name(str)
                    .article(null)
                    .build();
            System.out.println(image.toString());

            imageList.add(image);
        }

        List<Image> resultImageList = imageRepository.saveAll(imageList);

        if(resultImageList.isEmpty()){
            return 3;   // 이미지 에러
        }

        return 1;
    }
}
