package site.gongtong.boardgame.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;

@Entity(name = "boardgame")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;       // 게임 제목

    @Column(name = "min_num")
    private int minNum;         // 최소인원

    @Column(name = "max_num")
    private int maxNum;         // 최대인원

    @Column(name = "time")
    private int playTime;       // 플레이시간

    private int age;            // 사용연령

    private Float difficulty;   // 난이도

    private Year year;          // 출판년도

    private String image;       // 게임 이미지
}
