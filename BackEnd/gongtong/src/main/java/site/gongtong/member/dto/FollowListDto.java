package site.gongtong.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowListDto {
    private Integer num;
    private String id;
    private String nickname;
    private String birth;
    private Character gender;
    private String profileImage;
    private Character flag; //follow에서 가져오기

    @Builder
    public FollowListDto(Member member, Follow follow) {
        this.num = member.getNum();
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.birth = member.getBirth();
        this.gender = member.getGender();
        this.profileImage = member.getProfileImage();
        this.flag = follow.getFlag();
    }
}
