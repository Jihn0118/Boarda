package site.gongtong.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest { //회원가입 리퀘스트용
    @NotNull(message = "사용할 아이디를 입력해 주세요.")
    private String id;

    @NotNull(message = "사용할 비밀번호를 입력해 주세요.")
    private String password;

    @NotNull(message = "사용할 닉네임을 입력해 주세요.")
    private String nickname;

    @NotNull(message = "생년월일을 입력해주세요.")
    private String birth;

    @NotNull(message = "성별을 입력해 주세요.")
    private Character gender;

//    private String profile_image;

}
