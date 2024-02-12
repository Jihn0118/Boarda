package site.gongtong.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.gongtong.member.model.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditProfileDto { //프론트에서 리드온리 처리할 거~
    private Integer num; //read-only
    private String id; //read-only
    private String nickname;
    private String birth; //read-only
    private Character gender; //read-only
    private String profileImage;

    @Builder
    public EditProfileDto(Member member) {
        this.num = member.getNum();
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.birth = member.getBirth();
        this.gender = member.getGender();
        this.profileImage = member.getProfileImage();
    }


}

