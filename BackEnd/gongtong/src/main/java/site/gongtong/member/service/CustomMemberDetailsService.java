package site.gongtong.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.gongtong.member.model.Member;
import site.gongtong.member.model.MemberDto;
import site.gongtong.member.model.constant.RoleType;
import site.gongtong.member.repository.MemberRepository;

/**
 * SecurityUserDetailsDto 객체가 생성
 * => 스프링 시큐리티에서 사용자의 상세 정보를 담는 역할
 * 스프링 시큐리티에서 사용자 정보를 불러오고, 인증 및 권한 부여 과정에서 중요한 역할
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public MemberDto loadUserByUsername(String id) throws UsernameNotFoundException {
        //1. memberRepository사용 : id로 유저 정보 받아오기
        Member infoById = memberRepository.findById(id)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Cannot find Member with input Id")
                );

        //2. member을 dto로 변환
        MemberDto memberDto = entityToDto(infoById);

        return memberDto;
    }

    //엔터티 -> dto 전환
    public MemberDto entityToDto(Member member) {
        return MemberDto.builder()
                .num(member.getNum())
                .id(member.getId())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .birth(member.getBirth())
                .gender(member.getGender())
                .profileImage(member.getProfileImage())
                .roleType(RoleType.USER) //로그인 하면 다 USER임
                .build();
    }

}
