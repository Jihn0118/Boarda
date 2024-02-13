package site.gongtong.Image.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.gongtong.review.model.Review;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Character flag;     // R 리뷰, A 게시글

    @ManyToOne
    @JoinColumn(name = "review_id")
    @JsonIgnore // 양방향 참조 JSON 직렬화 과정에서 무한 순환 참조 발생 방지
    private Review review;

}
