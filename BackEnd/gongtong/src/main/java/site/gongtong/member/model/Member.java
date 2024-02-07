package site.gongtong.member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private String password;

    @Column(unique = true)
    private String nickname;

    private String birth;

    private Character gender;

    private String profileImage;

}
