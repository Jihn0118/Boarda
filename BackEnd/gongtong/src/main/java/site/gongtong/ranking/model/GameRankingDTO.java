package site.gongtong.ranking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import site.gongtong.boardgame.model.BoardGame;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GameRankingDTO {
    private Integer gameId;
    private Long tagCount;
    private BoardGame game;

    public GameRankingDTO(Integer gameId, Long tagCount, BoardGame game) {
        this.gameId = gameId;
        this.tagCount = tagCount;
        this.game = game;
    }
}
