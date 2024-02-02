package site.gongtong.ranking.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.gongtong.ranking.model.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query(value = "SELECT gameId, COUNT(tagId) as tagCount FROM Tag GROUP BY gameId ORDER BY tagCount DESC", nativeQuery = true)
    List<Object[]> getGameRanking();
}
