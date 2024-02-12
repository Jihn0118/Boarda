package site.gongtong.ranking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.gongtong.ranking.model.CafeRankingDTO;
import site.gongtong.ranking.model.GameRankingDTO;
import site.gongtong.ranking.service.RankingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ranking") // 중복된 경로를 묶어 처리하는 어노테이션
public class RankingController {
    private final RankingService rankingService;

    @GetMapping("/games")
    public ResponseEntity<List<GameRankingDTO>> getGameRanking() {
        List<GameRankingDTO> gameRankings = rankingService.getGameRanking();

        return gameRankings.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // 데이터가 없을 경우, 204 No Content 반환
                : new ResponseEntity<>(gameRankings, HttpStatus.OK); // 데이터가 있을 경우, 200 OK와 함께 데이터 반환
    }

    @GetMapping("/cafes")
    public ResponseEntity<List<CafeRankingDTO>> getCafeRanking() {

        List<CafeRankingDTO> cafeRankings = rankingService.getCafeRanking();

        return cafeRankings.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // 데이터가 없을 경우, 204 No Content 반환
                : new ResponseEntity<>(cafeRankings, HttpStatus.OK); // 데이터가 있을 경우, 200 OK와 함께 데이터 반환
    }
}



