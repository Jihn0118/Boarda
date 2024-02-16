package site.gongtong.moim.model;

import jakarta.persistence.*;
import lombok.*;
import site.gongtong.member.model.Member;

@Entity(name = "moim_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoimMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mm_id")
    private Integer id;

    @ManyToOne //JOIN
    @JoinColumn(name = "MEMBER_ID") //FK
    private Member member;

    @ManyToOne // JOIN
    @JoinColumn(name = "MOIM_ID") //FK
    private Moim moim;
}
