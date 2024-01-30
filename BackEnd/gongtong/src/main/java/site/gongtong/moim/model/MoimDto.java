package site.gongtong.moim.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class MoimDto {
    private int moimId;             // 방 번호

    private String leaderNickname;  // 모임 방 만든 멤버 닉네임

    private String title;           // 모임 방 제목

    private String content;         // 모임 방 본문

    private int number;             // 모임 최대 인원

    private String location;        // 모임 지역

    private LocalDateTime datetime; // 모임 할 시간

    private Integer currentNumber;   // 현재 모인 사람 수
}
