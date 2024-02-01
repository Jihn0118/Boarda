package site.gongtong.review.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@EntityListeners(AuditingEntityListener.class)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private LocalDateTime created_at;

    private float rate;

    @Column(name="member_id")
    private Integer memberId;

    @Column(name="cafe_id")
    private Integer cafeId;


    @Column(name="moim_id")
    private Integer moimId;

}
