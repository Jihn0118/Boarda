package site.gongtong.moim.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MemberRepository;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimMember;
import site.gongtong.moim.repository.MoimMemberRepository;
import site.gongtong.moim.repository.MoimRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoimServiceImpl implements MoimService {
    private final MoimRepository moimRepository;
    private final MoimMemberRepository moimMemberRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Moim> getMoimList(String location) {
        return moimRepository.getMoimsByLocation(location);
    }

    @Override
    public List<Moim> getDeadlineList() {
        return moimRepository.getMoimsDeadLine();
    }


    // 모임멤버 테이블에서 내 userNum 검색해서 모임id 리스트 가져오고
    // 가져온 걸로 모임 레포 뒤져서 진행중인 모임 있으면 Integer 리턴
    @Override
    public Integer checkRoom(int userNum) {
        return moimMemberRepository.countMoimsByMemberIdAndStatus(userNum);
    }

    @Override
    public void createRoom(Moim moim, int userNum) {
        Moim saveMoim = moimRepository.save(moim);
        Member member = memberRepository.findByNum(userNum);

        if(saveMoim != null && member != null){
            MoimMember saveMoimMember = MoimMember.builder()
                    .member(member)
                    .moim(saveMoim)
                    .build();

            moimMemberRepository.save(saveMoimMember);
        } else {
            System.out.println("에러");
            // exception 던져서 에외 화면 나오게?
        }
    }
}
