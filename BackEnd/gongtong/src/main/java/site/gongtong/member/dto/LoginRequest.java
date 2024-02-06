package site.gongtong.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.gongtong.member.model.Member;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest { //로그인 리퀘스트용
    @NotNull
    private String id;

    @NotNull(message = "사용할 비밀번호를 입력해 주세요.")
    private String password;

    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .password(this.password)
                .build();
    }
}
