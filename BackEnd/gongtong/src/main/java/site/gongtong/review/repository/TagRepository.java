package site.gongtong.review.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.gongtong.boardgame.model.BoardGame;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.ranking.repository.CafeRanking;
import site.gongtong.ranking.repository.GameRanking;
import site.gongtong.review.model.Tag;

import java.util.List;


@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("SELECT t.game.id as gameId, count(t) as tagCount, t.game as game " +
            "FROM Tag t " +
            "GROUP BY t.game.id " +
            "ORDER BY tagCount DESC")
    List<GameRanking> findGameRanking(Pageable pageable);

    @Query("SELECT t.cafe.id as cafeId, count(t) as tagCount, t.cafe as cafe " +
            "FROM Tag t " +
            "GROUP BY t.cafe.id " +
            "ORDER BY tagCount DESC")
    List<CafeRanking> findCafeRanking(Pageable pageable);
}

