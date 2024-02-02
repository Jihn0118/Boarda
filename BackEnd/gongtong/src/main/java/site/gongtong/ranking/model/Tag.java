package site.gongtong.ranking.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import site.gongtong.ranking.config.TagEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(TagEntityListener.class)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tagId;

    private int reviewId;
    private int gameId;
}