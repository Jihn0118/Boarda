package site.gongtong.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.review.repository.ReviewRepository;
import site.gongtong.review.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final TagRepository tagRepository;

}
