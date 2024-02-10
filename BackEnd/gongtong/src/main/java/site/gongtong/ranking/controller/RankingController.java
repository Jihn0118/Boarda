package site.gongtong.ranking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.gongtong.ranking.repository.CafeRanking;
import site.gongtong.ranking.repository.GameRanking;
import site.gongtong.ranking.service.RankingService;

import java.util.List;

@RestController
public class RankingController {
    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/ranking/games")
    public List<GameRanking> getGameRanking() {
        return rankingService.getGameRanking();
    }

    @GetMapping("/ranking/cafes")
    public List<CafeRanking> getCafeRanking() {
        return rankingService.getCafeRanking();
    }
}



