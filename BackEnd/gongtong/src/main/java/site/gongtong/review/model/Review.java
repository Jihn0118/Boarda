package site.gongtong.review.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.member.model.Member;
import site.gongtong.moim.model.Moim;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Character status;

    private String content;

    private Float rate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "moim_id")
    private Moim moim;

    @ManyToOne
    @JoinColumn(name="cafe_id")
    private Cafe cafe;
}
