package site.gongtong.moim.model;

import lombok.*;
import site.gongtong.member.model.Member;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MoimGroup {
    private Moim moim;
    private List<Member> memberList;
}
