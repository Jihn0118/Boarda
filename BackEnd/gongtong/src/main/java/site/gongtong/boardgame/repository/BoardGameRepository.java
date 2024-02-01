package site.gongtong.boardgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.boardgame.model.BoardGame;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Integer> {
}
