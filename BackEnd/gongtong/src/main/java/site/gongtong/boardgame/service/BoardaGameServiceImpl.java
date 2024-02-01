package site.gongtong.boardgame.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.boardgame.model.BoardGame;
import site.gongtong.boardgame.repository.BoardGameRepository;
import site.gongtong.boardgame.repository.GameCustomRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardaGameServiceImpl implements BoardGameService{
    private final BoardGameRepository boardGameRepository;
    private final GameCustomRepository gameCustomRepository;

    @Override
    public List<BoardGame> getGameList(int time, int num, String keyword) {
        return gameCustomRepository.findWithConditions(time, num, keyword);
    }

    @Override
    public BoardGame getGameInfo(Integer gameId) {
        return gameCustomRepository.findGameInfo(gameId);
    }
}
