package site.gongtong.member.model;

import jakarta.persistence.Entity;
import lombok.*;
import site.gongtong.member.model.constant.RoleType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDto {
    private Integer num; //고유 번호
    private String id; // 로그인 아이디
    private String password;
    private String nickname;
    private String birth;
    private Character gender;
    private String profileImage;
    private RoleType roleType; //USER 혹은 GUEST

    public static MemberDto of(String id) {
        return new MemberDto(null, id, null, null, null, null, null, null);
    }

    public static MemberDto of(Integer num, String id,
                               String password, String nickname,
                               String birth, Character gender,
                               String profileImage, RoleType roleType) {
        return new MemberDto(num,id,password,nickname,birth,gender,profileImage,roleType);
    }
}
