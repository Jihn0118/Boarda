package site.gongtong.member.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 추가이유 : JPA에게 엔티티의 생명주기 이벤트를 감지할 리스너를 지정합니다. 주로 엔티티의 생성, 수정 시간을 자동으로 관리할 때 사용합니다.
// created_at 만드려고
@EntityListeners(AuditingEntityListener.class)

public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer follower; // 나를 좋아요
    private Integer following; // 내가 좋아요
    //팔로우 = F, 차단 = B
    private Character flag; // 팔로우/차단 상태

    @Column(name = "created_at")
    private LocalDateTime createAt; // 팔로우/차단 일시
}
