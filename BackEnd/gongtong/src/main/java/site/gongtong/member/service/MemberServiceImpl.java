package site.gongtong.member.service;

import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl {
    @Id
    private Long id;
}
