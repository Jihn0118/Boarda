package site.gongtong.boardgame.service;

import site.gongtong.boardgame.model.BoardGame;

import java.util.List;
import java.util.Optional;


public interface BoardGameService {
    List<BoardGame> getGameList(int time, int num, String keyword);

    BoardGame getGameInfo(Integer gameId);
}
