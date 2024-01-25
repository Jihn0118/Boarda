//package site.gongtong.member.auth.oauth;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//import site.gongtong.member.auth.PrincipalDetails;
//import site.gongtong.member.model.Member;
//import site.gongtong.member.repository.MemberRepository;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
//    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder encoder;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//        log.info("getAttributes : {}", oAuth2User.getAttributes());
//
//        String provider = userRequest.getClientRegistration().getRegistrationId(); //구글 카카오 네이버
//        String providerId = oAuth2User.getAttribute("sub"); //거기서 사용하는 아이디 (어디든 sub)
//        String loginId = provider+"_"+providerId; //중복이 있을까봐 이렇게 저장한다 (신경 안 쓸거면 loginId == providerId인 거고, member dto도 변경)
//
//        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
//        //있는지 확인해서 -> 없으면 회원가입까지 시켜버리기
//        Member member;
//        if(optionalMember.isEmpty()) {
//            member = Member.builder()
//                    .id(loginId)
////                    .nickname(oAuth2User.getAttribute("name")) //이거 unique 값이어야 하는데??...
//                    .provider(provider)
//                    .providerId(providerId)
//                    .build();
//            //생년월일, 닉네임(유니크), 성별(?) 받아오기 (프론트에서 짜잔!)
//        } else {
//            //이미 있음 -> 로그인만 하면 됨
//            member = optionalMember.get();
//        }
//
//        return new PrincipalDetails(member, oAuth2User.getAttributes());
//    }
//}
