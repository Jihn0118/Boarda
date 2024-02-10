package site.gongtong.ranking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;
import site.gongtong.boardgame.model.BoardGame;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.review.model.Review;
import site.gongtong.review.model.Tag;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ranking")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String flag;
    private Integer num;
    private LocalDateTime createdAt;
    private Integer cafeId;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private BoardGame game;

//    @ManyToOne
//    @JoinColumn(name = "cafe_id")
//    private Cafe cafe;

    @ManyToOne
    @JoinColumn(name="review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name="tag_id")
    private Tag tag;
}
