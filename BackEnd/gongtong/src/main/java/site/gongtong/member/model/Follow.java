package site.gongtong.member.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "follower")
    private Member follower; //나를 좋아하는 사람들

    @ManyToOne
    @JoinColumn(name = "following")
    private Member following; //내가 좋아하는 사람들

    //팔로우 = F, 차단 = B
    private Character flag; // 팔로우/차단 상태 int

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt; // 팔로우/차단 일시

    public Follow(Member follower, Character flag, Member following) {
        this.follower = follower;
        this.flag = flag;
        this.following = following;
    }

}