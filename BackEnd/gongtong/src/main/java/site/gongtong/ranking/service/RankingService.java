package site.gongtong.ranking.service;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import site.gongtong.ranking.model.CafeRankingDTO;
import site.gongtong.ranking.model.GameRankingDTO;
import site.gongtong.review.repository.TagRepository;

import java.util.List;

@Service
public class RankingService {
    private final TagRepository tagRepository;

    // 게임 랭킹 정보를 저장할 List
    @Getter
    private List<GameRankingDTO> gameRanking;
    // 카페 랭킹 정보를 저장할 List
    @Getter
    private List<CafeRankingDTO> cafeRanking;

    public RankingService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
        updateRanking();  // 서비스가 생성될 때 랭킹 정보를 처음으로 생성합니다.
    }

    // 매일 자정에 랭킹 정보를 갱신합니다.
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateRanking() {
        gameRanking = tagRepository.findGameRanking(PageRequest.of(0, 10));
        cafeRanking = tagRepository.findCafeRanking(PageRequest.of(0, 10));
    }

}
