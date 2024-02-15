package site.gongtong.boardgame.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.boardgame.model.BoardGame;
import site.gongtong.boardgame.model.BoardGameDetailDto;
import site.gongtong.boardgame.repository.GameCustomRepository;
import site.gongtong.review.model.ImageReviewDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardGameServiceImpl implements BoardGameService {
    private final GameCustomRepository gameCustomRepository;

    @Override
    public List<BoardGame> getGameList(int time, int num, String keyword) {
        return gameCustomRepository.findWithConditions(time, num, keyword);
    }

    @Override
    public BoardGameDetailDto getGameInfo(Integer gameId) {
        BoardGame boardGame = gameCustomRepository.findById(gameId);
        List<ImageReviewDto> imageReviewDtos = gameCustomRepository.getImagesAndReviewIdsByGameId(gameId);

        return BoardGameDetailDto.builder()
                .boardGame(boardGame)
                .imageReviews(imageReviewDtos)
                .build();
    }
}
