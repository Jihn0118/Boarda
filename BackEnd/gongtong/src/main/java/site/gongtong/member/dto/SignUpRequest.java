package site.gongtong.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.gongtong.member.model.Member;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest { //회원가입 리퀘스트용
    @NotNull(message = "사용할 아이디를 입력해 주세요.")
    private String id;

    @NotNull(message = "사용할 비밀번호를 입력해 주세요.")
    private String password;
    private String passwordCheck;

    @NotNull(message = "사용할 닉네임을 입력해 주세요.")
    private String nickname;

    @NotNull(message = "생년월일을 입력해주세요.")
    private String birth;

    @NotNull(message = "성별을 입력해 주세요.")
    private Character gender;

    private String profile_image;

    //비밀번호 암호화
    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .id(this.id)
                .password(encodedPassword)
                .nickname(this.nickname)
                .birth(this.birth)
                .gender(this.gender)
                .profileImage(this.profile_image)
                .build();
    }
}
