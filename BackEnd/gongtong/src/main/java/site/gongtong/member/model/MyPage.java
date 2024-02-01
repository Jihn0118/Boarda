package site.gongtong.member.model;


//import jakarta.persistence.*;
//import lombok.*;
//import site.gongtong.moim.model.Moim;
//
//import java.util.LinkedList;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//
//public class MyPage {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id; // MyPage 엔터티의 기본키
//
//    @OneToOne //JOIN
//    @JoinColumn(name = "MEMBER_ID") //FK
//    private Member member;
//
//    @OneToOne
//    @JoinTable(
//            name = "MOIMMEMBER", // 중간 테이블의 이름
//            joinColumns = @JoinColumn(name = "MEMBER_ID"), // 현재 엔터티(MyPage)의 외래 키
//            inverseJoinColumns = @JoinColumn(name = "MOIM_ID")) // 상대 엔터티(Moim)의 외래 키
//    private List<Moim> moims = new LinkedList<>();
//}
