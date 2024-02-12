package site.gongtong.member.service;

import org.springframework.web.multipart.MultipartFile;
import site.gongtong.member.dto.SignUpRequest;
import site.gongtong.member.model.Member;
import site.gongtong.member.model.MemberDto;

public interface MemberService {

    boolean canUseId(String id);

    boolean canUseNickname(String nickname);

    Member signup(SignUpRequest req, MultipartFile file);

    Member login(String id, String password);

    MemberDto getLoginMemberById(String id);

    void deleteMember(Integer num);
}