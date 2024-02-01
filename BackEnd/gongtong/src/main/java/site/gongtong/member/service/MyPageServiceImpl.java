package site.gongtong.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.member.repository.MyPageCustomRepository;
import site.gongtong.review.model.Review;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
    private final MyPageCustomRepository myPageCustomRepository;

    @Override
    public int idToNum(String id) {
        return myPageCustomRepository.MemberidToNum(id);
    }

    @Override
    public List<Review> getReviewListByNum(int num) {
        return myPageCustomRepository.findAllReviews(num);
    }
}
