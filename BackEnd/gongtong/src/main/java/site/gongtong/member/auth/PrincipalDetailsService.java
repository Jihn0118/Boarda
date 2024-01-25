//package site.gongtong.member.auth;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import site.gongtong.member.model.Member;
//import site.gongtong.member.repository.MemberRepository;
//
//@Service
//@RequiredArgsConstructor
//public class PrincipalDetailsService implements UserDetailsService {
//    private MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//        Member member = memberRepository.findByLoginId(id)
//                .orElseThrow(() -> {
//                    return new UsernameNotFoundException("없는 유저");
//                });
//        return new PrincipalDetails(member);
//    }
//
//
//}
