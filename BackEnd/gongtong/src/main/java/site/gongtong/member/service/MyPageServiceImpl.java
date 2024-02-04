package site.gongtong.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.member.dto.EditProfileDto;
import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.FollowRepository;
import site.gongtong.member.repository.MyPageCustomRepository;
import site.gongtong.member.repository.MyPageRepository;
import site.gongtong.review.model.Review;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
    private final MyPageRepository myPageRepository;

    @Override
    public int idToNum(String id) {
        return myPageRepository.MemberidToNum(id);
    }

    @Override
    public List<Review> getReviewListByNum(int num) {
        return myPageRepository.findAllReviews(num);
    }

//    @Override
//    public Member updateNickname(String id, EditProfileDto editProfileDto) {
//        return myPageCustomRepository.setNickname(id, editProfileDto.getNickname());
//    }

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

//        System.out.println("member: "+member.toString());
//        System.out.println("editDto: "+editProfileDto);
        return myPageRepository.modifyProfile(member);
    }

    @Override
    public int setPwd(String id, String newEncodedPwd) {
        //1. id로 해당 유저 찾기
        Member member = myPageRepository.findById(id);
            //예외처리 - id에 해당하는 member가 없으면 킬
        if(member == null) return 0; // 0: 못찾겠다...

        //2. id에 해당하는 비번 바꾸기...
        return myPageRepository.modifyPwd(id, newEncodedPwd);
    }

    @Override
    public int deleteMember(String id) { //회원 탈퇴
        return myPageRepository.delete(id);
    }

    //엔터티 -> dto 전환
//    public EditProfileDto entityToDto(Member member) {
//        EditProfileDto editProfileDto = new EditProfileDto();
//        editProfileDto.setId(member.getId());
//        editProfileDto.setProfileImage(member.getProfileImage());
//        editProfileDto.setNickname(member.getNickname());
//        editProfileDto.setNum(member.getNum());
////        editProfileDto.setPassword(member.getPassword());
//
//        return editProfileDto;
//    }
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public void updateAndFlush(Member member) {
//        // 엔티티를 업데이트
//        entityManager.merge(member);
//
//        // 변경사항을 데이터베이스에 동기화 (flush)
//        entityManager.flush();
//    }

    //dt0o->entity
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
