package site.gongtong.member.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import site.gongtong.member.dto.SignUpRequest;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MemberRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {
    MemberRepository memberRepository;
    PasswordEncoder encoder;

    public boolean canUseId(String id) {
        return !memberRepository.existsById(id);
    }

    public boolean canUseNickname(String nickname) {
        return !memberRepository.existsByNickname(nickname);
    }

    public Member signup(SignUpRequest req) {
        Member member = memberRepository.save(req.toEntity(encoder.encode(req.getPassword())));
        return member;
    }

    public Member login(String id, String password) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isEmpty()) {
            return null;
        }

        Member member = optionalMember.get();
        if(!member.getPassword().equals(password)) { //인코딩된 패스워드로 비교하지 않아도 돠는 거냐?
            return null;
        }
        
        return member;
    }

    public Member getLoginMemberById(String id) { //인증, 인가 시 사용
        if(id == null) return null; //로그아웃 상태
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isEmpty()) return null;

        return optionalMember.get();
    }
}