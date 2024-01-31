package site.gongtong.boardgame.repository;

import site.gongtong.boardgame.model.BoardGame;

import java.util.List;

public interface GameCustomRepository {
    List<BoardGame> findWithConditions(int time, int num, String keyword);

    BoardGame findGameInfo(Integer gameId);
}
