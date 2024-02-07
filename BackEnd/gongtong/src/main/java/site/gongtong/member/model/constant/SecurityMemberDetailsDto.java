package site.gongtong.member.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.gongtong.member.model.MemberDto;

import java.util.Collection;
import java.util.Collections;

/**
 * 시큐리티 용 Details Dto... => 개인 정보 + 사용가능한지(시간만료, enable 등)
 */

@Slf4j
@Getter
@AllArgsConstructor
public class SecurityMemberDetailsDto implements UserDetails {

    @Delegate
    private MemberDto memberDto;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(memberDto.getRoleType().toString()));
    }

//    @Override
//    public String getPassword() {
//        return memberDto.getPassword();
//    }

    @Override
    public String getUsername() {
        return memberDto.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
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
        return true;
    }
}
