package site.gongtong.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.chat.model.Chatting;

@Repository
public interface ChatRepository extends JpaRepository<Chatting, Long> {
}
