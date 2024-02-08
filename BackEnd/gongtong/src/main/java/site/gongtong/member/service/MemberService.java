package site.gongtong.member.service;

import site.gongtong.member.dto.SignUpRequest;
import site.gongtong.member.model.Member;

public interface MemberService {
    Boolean canUseId(String id);

    Boolean canUseNickname(String nickname);

    Member signup(SignUpRequest req);

    Member login(String id, String password);

    Member getLoginMemberById(String id);

}
