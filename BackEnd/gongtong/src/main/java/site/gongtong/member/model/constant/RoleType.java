package site.gongtong.member.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    /**
     * GUEST : 로그인 x
     * USER : 로그인 o
     */
    GUEST, USER
}
