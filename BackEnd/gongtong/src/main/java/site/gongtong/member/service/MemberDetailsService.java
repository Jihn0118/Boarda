package site.gongtong.member.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import site.gongtong.member.config.MemberDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MemberRepository;

@Service
public class MemberDetailsService implements UserDetailsService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public MemberDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("Cannot find the input Member");
                });
        return new MemberDetails(member);
    }
}
