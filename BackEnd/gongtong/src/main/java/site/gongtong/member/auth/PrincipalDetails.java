//package site.gongtong.member.auth;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import site.gongtong.member.model.Member;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Map;
//
//public class PrincipalDetails implements UserDetails {
//    private Member member;
//
//    public PrincipalDetails(Member member) {
//        this.member = member;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(() -> "USER");
//    }
//
//    @Override
//    public String getPassword() {
//        return member.getPassword();
//    }
//
//    @Override
//    public String getUsername() { //username = id
//        return member.getId();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    //Oauth
//    private Map<String, Object> attributes;
//
//    public PrincipalDetails(Member member, Map<String, Object> attributes) {
//        this.member = member;
//        this.attributes = attributes;
//    }
//
////    @Override
////    public String getName() {
////        return null;
////    }
////    @Override
////    public Map<String, Object> getAttributes() {
////        return attributes;
////    }
//}
