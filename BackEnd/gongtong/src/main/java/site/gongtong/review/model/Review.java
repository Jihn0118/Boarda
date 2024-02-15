package site.gongtong.review.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.gongtong.Image.model.Image;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.member.model.Member;
import site.gongtong.moim.model.Moim;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "is_removed")
    private Boolean isRemoved;

    private Character status;

    private String content;

    private Float rate;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "moim_id")
    private Moim moim;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER)
    private List<Image> images;
}
