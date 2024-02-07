package site.gongtong.member.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import site.gongtong.member.model.Member;

import java.util.Collection;

public class MemberDetails implements UserDetails {

    private MemberDto member;

    public MemberDetails(MemberDto member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; //권한 따로 없음
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getId();
    }
    public String getNickname() {
        return member.getNickname();
    }
    public String getBirth() {
        return member.getBirth();
    }

    public Character getGender() {
        return member.getGender();
    }

    //글 불러올 때 필요
    public Integer getNum() { return member.getNum(); }

    public String getProfileImage() {
        return member.getProfileImage();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //세션 만료x 사용자 계정 자체의 만료 여부o
        //적당한 주기로 로그인을 해서 계정을 계속 쓸 수 있냐
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true; //계정 자체가 존재하냐
    }

}
