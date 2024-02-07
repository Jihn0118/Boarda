package site.gongtong.boardgame.service;

import site.gongtong.boardgame.model.BoardGame;
import site.gongtong.boardgame.model.BoardGameDetailDto;

import java.util.List;


public interface BoardGameService {
    List<BoardGame> getGameList(int time, int num, String keyword);

    BoardGameDetailDto getGameInfo(Integer gameId);
}
