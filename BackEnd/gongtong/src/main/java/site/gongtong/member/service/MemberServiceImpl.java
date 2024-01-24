package site.gongtong.member.service;

import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import site.gongtong.member.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl {
    
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder; //알아서 암호화(단)

    //id 중복체크
    public boolean checkid(){}

    //nickname 중복체크

    //회원가입 기능1,2,

    //로그인 기능 1

    //인증,인가 - Id를 입력받아서 member 리턴

    //?? - loginId
}
