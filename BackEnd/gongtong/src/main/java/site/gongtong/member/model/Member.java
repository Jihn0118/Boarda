package site.gongtong.member.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;
    @Column(unique = true)
    private String id; //아이디 = 이메일
    private String password;
    @Column(unique = true)
    private String nickname;
    private String birth;
    private char gender; //'W' 또는 'M'
    private String profile_image;

    protected Member() { }

    private Member(String id, //생성자도 private?
                   String password,
                   String nickname,
                   String birth,
                   char gender,
                   String profile_image) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.birth = birth;
        if(gender=='M' || gender=='W') {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("성별은 M, W 중에서 선택해 주세요");
        }
        this.profile_image = profile_image;
    }


}
