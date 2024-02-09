//package site.gongtong.member.model;
//
//import lombok.*;
//import lombok.experimental.Delegate;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
///**
// * 시큐리티 용 Details Dto... => 개인 정보 + 사용가능한지(시간만료, enable 등)
// */
//
//@Slf4j
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
//public class SecurityMemberDetailsDto implements UserDetails {
//
//    @Delegate
//    private MemberDto memberDto;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority(memberDto.getRoleType().toString()));
//    }
//
////    @Override
////    public String getPassword() {
////        return memberDto.getPassword();
////    }
//
//    @Override
//    public String getUsername() {
//        return memberDto.getId();
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
//    public SecurityMemberDetailsDto toSecurityMemberDetailsDto(MemberDetails memberDetails) {
//        // MemberDetails 객체에서 필요한 정보를 추출하여 SecurityMemberDetailsDto 객체를 생성하여 반환
//
//        SecurityMemberDetailsDto tmpDto = MemberDto.builder()
//                .num(memberDetails.getNum())
//                .id(memberDetails.getUsername())
//                .nickname(memberDetails.getNickname())
//                .birth(memberDetails.getBirth())
//                .gender(memberDetails.getGender())
//                .profileImage(memberDetails.getProfileImage())
//                .build();
//
////        SecurityMemberDetailsDto dto = new SecurityMemberDetailsDto();
//
//        return tmpDto;
//        }
//}
