package site.gongtong.moim.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MemberRepository;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimMember;
import site.gongtong.moim.repository.MoimMemberRepository;
import site.gongtong.moim.repository.MoimRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoimServiceImpl implements MoimService {
    private final MoimRepository moimRepository;
    private final MoimMemberRepository moimMemberRepository;
    private final MemberRepository memberRepository;

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

        Member member = memberRepository.findMemberByNum(userNum);

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

    @Override
    public boolean joinRoom(int moimId, String memberId) {
        // 모임 id 가지고 카운트 세서 moim의 최대 인원과 비교해서 -1 차이면 status 바꾸고,
        // 최대 인원과 같으면 참여 불가
        // else 참여
        int count = moimMemberRepository.countMoimMemberByMoim_Id(moimId);

        Moim moim = moimRepository.getMoimById(moimId);

        // TODO 이미 참여한 사람인 지 확인하는 로직 필요!!!
        if(moim == null){
            return false;
        } else if(count == moim.getNumber()) {
            return false;
        } else if(count + 1 == moim.getNumber()){
            moim.setNumber(count + 1);
            moim.setStatus('S');
            moimRepository.save(moim);
            // ToDo: 꽉 찼으니 모임 멤버 모두에게 알림 보내는 로직도 추가해야 함
        }

        Member addMember = memberRepository.findMemberById(memberId);

        if(addMember != null){
            MoimMember addMoimMember = MoimMember.builder()
                    .moim(moim)
                    .member(addMember)
                    .build();

            moimMemberRepository.save(addMoimMember);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Moim> getSortedMoimList(String location,int sorting) {

        List<Moim> list = new ArrayList<>();

        // 마감임박순 정렬
        if(sorting == 2){           // 마감임박순 정렬
            //list = moimRepository.
        } else if(sorting == 3){    // 모집일시 정렬
            list = moimRepository.findByLocationAndStatusOrderByDatetime(location, 'P');
        } else {                    // 최신순 정렬
            list = moimRepository.findByLocationAndStatusOrderByIdDesc(location, 'P');
        }

        return list;
    }
}
