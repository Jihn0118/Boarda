package site.gongtong.member.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.gongtong.member.model.constant.RoleType;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDto implements UserDetails {
    @Id
    private Integer num; //고유 번호
    private String id; // 로그인 아이디
    private String password;
    private String nickname;
    private String birth;
    private Character gender;
    private String profileImage;
    private RoleType roleType; //USER 혹은 GUEST

    public static MemberDto of(Member member) {
        return MemberDto.builder()
                .num(member.getNum())
                .id(member.getId())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .birth(member.getBirth())
                .gender(member.getGender())
                .profileImage(member.getProfileImage())
                .roleType(RoleType.USER)
                .build();
    }

    //

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.getRoleType().toString()));
    }

    @Override
    public String getUsername() {
        return this.getId();
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
