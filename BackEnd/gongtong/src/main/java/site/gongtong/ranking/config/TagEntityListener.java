package site.gongtong.ranking.config;


import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import site.gongtong.ranking.model.Tag;
import site.gongtong.ranking.service.RankingService;

@Component
public class TagEntityListener {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RankingService rankingService;

    @PostPersist
    @PostUpdate
    public void handleTagChange(Tag tag) {
        rankingService.updateRanking();
    }
}
