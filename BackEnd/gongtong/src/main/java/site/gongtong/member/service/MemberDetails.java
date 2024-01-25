//package site.gongtong.member.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import site.gongtong.member.model.Member;
//import site.gongtong.member.repository.MemberRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class MemberDetails implements UserDetailsService {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String userName,nickname,birth,profile_image = null;
//        Character gender = null;
//        String password=null;
////        List<GrantedAuthority> authorities = null; //한 인간이 여러 권한 가질 수..
//        List<Member> member = memberRepository.findById(username);
//        if(member.size() == 0) {
//            throw new UsernameNotFoundException(username+"은(는) 없는 사용자입니다.");
//        } else {
//            userName = member.get(0).getId();
//            password = member.get(0).getPassword();
//            nickname = member.get(0).getNickname();
//            birth = member.get(0).getBirth();
//            gender = member.get(0).getGender();
//            profile_image = member.get(0).getProfile_image();
//        }
//
//        return new User(userName, password, );
//    }
//}
