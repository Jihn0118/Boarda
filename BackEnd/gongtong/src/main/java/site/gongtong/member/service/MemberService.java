package site.gongtong.member.service;

import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Id
    private Long id;
}
