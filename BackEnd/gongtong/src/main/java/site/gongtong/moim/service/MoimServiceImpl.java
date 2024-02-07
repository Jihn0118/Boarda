package site.gongtong.moim.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MemberCustomRepository;
import site.gongtong.member.repository.MemberRepository;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimMember;
import site.gongtong.moim.repository.MoimCustomRepository;
import site.gongtong.moim.repository.MoimMemberCustomRepository;
import site.gongtong.moim.repository.MoimMemberRepository;
import site.gongtong.moim.repository.MoimRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimServiceImpl implements MoimService {
    private final MoimRepository moimRepository;
    private final MoimMemberRepository moimMemberRepository;
    private final MoimCustomRepository moimCustomRepository;
    private final MoimMemberCustomRepository moimMemberCustomRepository;
    private final MemberRepository memberRepository;
    private final MemberCustomRepository memberCustomRepository;

    @Override
    public List<Moim> getDeadlineList() {
        return moimCustomRepository.findAllDeadLine();
    }

    // 모임멤버 테이블에서 내 userNum 검색해서 모임id 리스트 가져오고
    // 가져온 걸로 모임 레포 뒤져서 진행중인 모임 있으면 Integer 리턴
    @Override
    public Integer checkRoom(int userNum) {
        return moimMemberCustomRepository.countMoimsByMemberIdAndStatus(userNum);
    }

    @Override
    public Integer createRoom(Moim moim, int userNum) {
        Member member = memberCustomRepository.findMemberByNum(userNum);

        if(member == null){
            return 1;
        }

        moim.setLeaderNickname(member.getNickname());

        Moim saveMoim = moimRepository.save(moim);

        if (saveMoim != null) {
            MoimMember saveMoimMember = MoimMember.builder()
                    .member(member)
                    .moim(saveMoim)
                    .build();

            moimMemberRepository.save(saveMoimMember);
            return 0;
        } else {
            System.out.println("에러");
            return 2;
            // exception 던져서 에외 화면 나오게?
        }
    }

    @Override
    public Integer joinRoom(int moimId, String memberId) {
        int count = moimMemberCustomRepository.countMoimMemberByMoimId(moimId);

        Moim moim = moimCustomRepository.findById(moimId);

        Member addMember = memberCustomRepository.findById(memberId);

        if (moim == null) {
            return 1;
        } else if(addMember == null){
            return 2;
        }

        MoimMember existedMoimMember = moimMemberCustomRepository.findMoimMemberByMoimAndMember(moim, addMember);

        if (count == moim.getNumber()) {
            return 3;
        } else if (existedMoimMember != null) {// 이미 모임에 참여한 사람인 지 확인
            return 4;
        } else if (count + 1 == moim.getNumber()) {
            moim.setStatus('S');
            // ToDo: 꽉 찼으니 모임 멤버 모두에게 알림 보내는 로직도 추가해야 함
        }
        moim.setCurrentNumber(count + 1);
        moimRepository.save(moim);

        MoimMember addMoimMember = MoimMember.builder()
                .moim(moim)
                .member(addMember)
                .build();

        moimMemberRepository.save(addMoimMember);

        return 0;
    }

    @Override
    public List<Moim> getSortedMoimList(String location, int sorting) {

        List<Moim> list = new ArrayList<>();

        // 마감임박순 정렬
        if (sorting == 2) {           // 마감임박순 정렬
            list = moimCustomRepository.getMoimWithMemberCountOrder();
        } else if (sorting == 3) {    // 모집일시 정렬
            list = moimCustomRepository.findByLocationAndStatusOrderByDatetime(location, 'P');
        } else {                    // 최신순 정렬
            list = moimCustomRepository.findByLocationAndStatusOrderByIdDesc(location, 'P');
        }

        return list;
    }

}
