package site.gongtong.ranking.repository;

import site.gongtong.boardgame.model.BoardGame;

public interface GameRanking {
    Integer getGameId();
    Long getTagCount();
    BoardGame getGame();
}
