package site.gongtong.ranking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import site.gongtong.cafe.model.Cafe;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class CafeRankingDTO {
    private Integer cafeId;
    private Long tagCount;
    private Cafe cafe;

    public CafeRankingDTO(Integer cafeId, Long tagCount, Cafe cafe) {
        this.cafeId = cafeId;
        this.tagCount = tagCount;
        this.cafe = cafe;
    }
}
