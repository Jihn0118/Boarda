package site.gongtong.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.member.dto.EditProfileDto;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MyPageCustomRepository;
import site.gongtong.review.model.Review;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
    private final MyPageCustomRepository myPageRepository;

    @Override
    public int idToNum(String id) {
        return myPageRepository.MemberidToNum(id);
    }

    @Override
    public List<Review> getReviewListByNum(int num) {
        return myPageRepository.findAllReviews(num);
    }

    @Override
    public Member findById(String id) {
        return myPageRepository.findById(id);
    }

    @Override
    public Member findByNickname(String nickname) {
        return myPageRepository.findByNickname(nickname);
    }

    @Override
    public Long modifyProfile(EditProfileDto editProfileDto) {
        // 여기서 dto -> 멤버
        Member member = dtoToEntity(editProfileDto);

        return myPageRepository.modifyProfile(member);
    }

    @Override
    public int setPwd(String id, String newEncodedPwd) {
        //1. id로 해당 유저 찾기
        Member member = myPageRepository.findById(id);
        //예외처리 - id에 해당하는 member가 없으면 0리턴
        if (member == null) return 0;

        //2. id에 해당하는 비번 바꾸기...
        return myPageRepository.modifyPwd(id, newEncodedPwd);
    }

    @Override
    public int deleteMember(String id) { //회원 탈퇴
        return myPageRepository.delete(id);
    }

    //dto -> entity 전환
    public Member dtoToEntity(EditProfileDto editProfileDto) {

        Member member = Member.builder()
                .num(editProfileDto.getNum())
                .id(editProfileDto.getId())
                .nickname(editProfileDto.getNickname())
                .birth(editProfileDto.getBirth())
                .gender(editProfileDto.getGender())
                .profileImage(editProfileDto.getProfileImage())
                .build();

        return member;
    }

}
