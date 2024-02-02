package site.gongtong.ranking.service;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import site.gongtong.ranking.repository.TagRepository;

import java.util.List;

@Service
public class RankingService {
    private final TagRepository tagRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public RankingService(TagRepository tagRepository, RedisTemplate<String, Object> redisTemplate) {
        this.tagRepository = tagRepository;
        this.redisTemplate = redisTemplate;
    }

    public void updateRanking() {
        List<Object[]> rankingList = tagRepository.getGameRanking();
        ZSetOperations<String, Object> ops = redisTemplate.opsForZSet();
        ops.removeRange("gameRanking", 0, -1); // 기존의 랭킹 정보를 삭제
        for (Object[] ranking : rankingList) {
            ops.add("gameRanking", ranking[0], (Long) ranking[1]); // 새로운 랭킹 정보를 추가
        }
    }
}



//@Service
//public class RankingService {
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    public Set<Integer> getTopRankings(int limit) {
//        Set<Integer> topRankings = Collections.emptySet();
//        try {
//            topRankings = redisTemplate.opsForZSet().reverseRange("ranking", 0, limit - 1);
//        } catch (Exception e) {
//            System.err.println("Error while getting top rankings from Redis: " + e.getMessage());
//        }
//        return topRankings;
//    }
//}

