package site.gongtong.moim.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "moim")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 추가이유 : JPA에게 엔티티의 생명주기 이벤트를 감지할 리스너를 지정합니다. 주로 엔티티의 생성, 수정 시간을 자동으로 관리할 때 사용합니다.
// created_at 만드려고
@EntityListeners(AuditingEntityListener.class)
public class Moim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //진행 = P, 성공 = S, 실패 = F
    private Character status;       // 모임 상태

    @Column(name = "leader_nickname")
    private String leaderNickname;

    private LocalDateTime datetime; // 모임 일시

    private String location;        // 모임 지역

    private Integer number;         // 모임 총 인원수

    private String title;           // 모임 제목

    private String content;         // 모임 본문

    @CreatedDate
    private LocalDateTime createdAt; // TODO createdAt으로 바꿔야함

    @Column(name = "current_number")
    private Integer currentNumber;   // 모임 현재 인원수
}


