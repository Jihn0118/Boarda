package site.gongtong.boardgame.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.gongtong.boardgame.model.BoardGame;
import site.gongtong.boardgame.model.BoardGameDetailDto;
import site.gongtong.boardgame.service.BoardGameService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/game")
@Slf4j
public class BoardGameController {
    private final BoardGameService gameService;

    @GetMapping("/list")
    public ResponseEntity<List<BoardGame>> getGameList(@RequestParam(name = "time", defaultValue = "0") int time,
                                                       @RequestParam(name = "num", defaultValue = "0") int num,
                                                       @RequestParam(name = "keyword") String keyword) {
        log.info("보드게임 리스트 들어옴!");
        List<BoardGame> gameList = gameService.getGameList(time, num, keyword);

        return new ResponseEntity<>(gameList, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<BoardGameDetailDto> getGameInfo(@RequestParam(name = "game_id", defaultValue = "0") Integer gameId) {
        log.info("보드게임 상세정보 들어옴!");

        BoardGameDetailDto game = gameService.getGameInfo(gameId);

        if (game != null) {
            return new ResponseEntity<>(game, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
