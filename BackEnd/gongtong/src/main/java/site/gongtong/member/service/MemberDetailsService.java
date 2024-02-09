package site.gongtong.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.gongtong.member.model.Member;
import site.gongtong.member.model.MemberDetails;
import site.gongtong.member.repository.MemberRepository;

@Service
public class MemberDetailsService implements UserDetailsService {
    @Autowired
    MemberRepository memberRepository;

    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("Cannot find the input Member");
                });
        return new MemberDetails(member);
    }
}
