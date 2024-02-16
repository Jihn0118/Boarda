package site.gongtong.review.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.gongtong.ranking.model.CafeRankingDTO;
import site.gongtong.ranking.model.GameRankingDTO;
import site.gongtong.review.model.Tag;

import java.util.List;


@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("SELECT new site.gongtong.ranking.model.GameRankingDTO(t.game.id, count(t), t.game) " +
            "FROM Tag t " +
            "GROUP BY t.game.id " +
            "ORDER BY count(t) DESC")
    List<GameRankingDTO> findGameRanking(Pageable pageable);


    @Query("SELECT new site.gongtong.ranking.model.CafeRankingDTO(t.cafe.id, count(t), t.cafe) " +
            "FROM Tag t " +
            "GROUP BY t.cafe.id " +
            "ORDER BY count(t) DESC")
    List<CafeRankingDTO> findCafeRanking(Pageable pageable);

}

