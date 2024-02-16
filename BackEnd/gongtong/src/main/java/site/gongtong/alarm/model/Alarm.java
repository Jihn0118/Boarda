package site.gongtong.alarm.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.gongtong.member.model.Member;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String link;

    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    private Boolean isRead;

    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member member;
}
