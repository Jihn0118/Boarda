package site.gongtong.member.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;

    @Column(unique = true)
    private String id; //아이디 = 이메일

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(unique = true)
    private String nickname;

    private String birth;

    private Character gender; //'W' 또는 'M'

    private String profileImage;

    //OAuth 로그인에 사용 -> 따로 만들어야 하나?/
//    private String provider;
//    private String providerId;

//    public void changeNickname(String nickname) {
//        this.nickname = nickname;
//    }
//    public void changeProfileImage(String profileImage) {
//        this.profileImage = profileImage;
//    }
//    public void changePassword(String password) {
//        this.password = password;
//    }

}
