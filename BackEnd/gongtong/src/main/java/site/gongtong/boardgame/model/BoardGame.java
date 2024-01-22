package site.gongtong.boardgame.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BoardGame {

    @Id
    private Long id;
}
