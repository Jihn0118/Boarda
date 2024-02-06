package site.gongtong.chat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="chatting_room")
public class ChattingRoom {
    @Id
    private Long id;
}
