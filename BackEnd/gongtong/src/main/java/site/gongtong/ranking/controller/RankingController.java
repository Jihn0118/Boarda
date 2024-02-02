package site.gongtong.ranking.controller;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/ranking")
public class RankingController {
    private final RedisTemplate<String, Object> redisTemplate;

    public RankingController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public Set<Object> getRanking() {
        ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();
        return ops.reverseRange("gameRanking", 0, -1); // 랭킹 정보를 높은 순서대로 가져옴
    }
}

//
//@RestController
//public class RankingController {
//
//    private final RankingService rankingService;
//
//    @Autowired
//    public RankingController(RankingService rankingService) {
//        this.rankingService = rankingService;
//    }
//
//    // 클라이언트가 /rankings?limit=10 형태로 요청을 보낼 경우
//    @GetMapping("/rankings")
//    public Set<Integer> getRankings(@RequestParam int limit) {
//        return rankingService.getTopRankings(limit);
//    }
//}

