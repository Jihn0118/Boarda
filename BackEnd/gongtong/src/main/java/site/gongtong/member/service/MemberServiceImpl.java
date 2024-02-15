package site.gongtong.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.gongtong.member.dto.SignUpRequest;
import site.gongtong.member.model.Member;
import site.gongtong.member.model.MemberDto;
import site.gongtong.member.repository.MemberRepository;
import site.gongtong.s3.FileFolder;
import site.gongtong.s3.FileService;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final FileService fileService;

    public boolean canUseId(String id) {
        return !memberRepository.existsById(id);
    }

    public boolean canUseNickname(String nickname) {
        return !memberRepository.existsByNickname(nickname);
    }

    public Member signup(SignUpRequest req, MultipartFile file) {

        String str = fileService.uploadFile(file, FileFolder.MEMBER_IMAGES);

        Member signupMember = Member.builder()
                .id(req.getId())
                .password(encoder.encode(req.getPassword()))
                .nickname(req.getNickname())
                .birth(req.getBirth())
                .gender(req.getGender())
                .profileImage(str)
                .build();

        Member resultMember = memberRepository.save(signupMember);

        return resultMember;
    }

    public Member login(String id, String password) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isEmpty()) {
            return null;
        }

        Member member = optionalMember.get();
        if (!member.getPassword().equals(password)) {
            return null;
        }

        return member;
    }

    public MemberDto getLoginMemberById(String id) { //인증, 인가 시 사용
        if (id == null) return null; //로그아웃 상태
        Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isEmpty()) return null;

        //Member을 MemberDto로 바꿔서 리턴
        Member member = memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        return MemberDto.of(member);

    }

    public void deleteMember(Integer num) {
        memberRepository.deleteById(num);
    }
}