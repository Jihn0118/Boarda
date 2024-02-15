package site.gongtong.member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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
    private Integer num;  //멤버 pK

    @Column(unique = true)
    private String id; //로그인 아이디 = 이메일 고유값

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //@JsonIgnore
    private String password; //로그인 비밀번호

    @Column(unique = true)
    private String nickname; //사용 닉네임 고유값

    private String birth; //생년월일

    private Character gender; //'W' 또는 'M'

    private String profileImage; //주소값


}
