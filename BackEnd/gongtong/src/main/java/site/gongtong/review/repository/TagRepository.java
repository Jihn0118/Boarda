package site.gongtong.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.gongtong.review.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
