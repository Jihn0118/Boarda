package site.gongtong.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.review.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
