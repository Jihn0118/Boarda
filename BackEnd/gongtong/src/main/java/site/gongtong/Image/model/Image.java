package site.gongtong.Image.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.gongtong.article.model.Article;
import site.gongtong.review.model.Review;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
// 추가이유 : JPA에게 엔티티의 생명주기 이벤트를 감지할 리스너를 지정합니다. 주로 엔티티의 생성, 수정 시간을 자동으로 관리할 때 사용합니다.
// created_at 만드려고
@EntityListeners(AuditingEntityListener.class)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Character flag;     // R 리뷰, A 게시글

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

}
