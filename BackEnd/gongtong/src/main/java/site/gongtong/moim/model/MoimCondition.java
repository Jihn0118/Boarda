package site.gongtong.moim.model;

import lombok.*;

import java.time.LocalDateTime;

// 방 만들기 할 때 필요한 요소를 담은 DTO
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MoimCondition {
    private String title;   // 모임 방 제목

    private String content; // 모임 방 본문

    private int number;     // 모임 최대 인원

    private String location;// 모임 지역

    private LocalDateTime datetime; // 모임 할 시간

    // private Integer currentNumber;   // 현재 모인 사람 수

    //private List<Integer> friends;    // 초대할 친구 id 리스트 Integer 리스트
}
