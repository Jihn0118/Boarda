package site.gongtong.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.article.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
