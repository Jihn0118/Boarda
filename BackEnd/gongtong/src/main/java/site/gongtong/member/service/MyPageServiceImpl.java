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
    private final MyPageCustomRepository myPageCustomRepository;

    @Override
    public int idToNum(String id) {
        return myPageCustomRepository.MemberidToNum(id);
    }

    @Override
    public List<Review> getReviewListByNum(int num) {
        return myPageCustomRepository.findAllReviews(num);
    }

//    @Override
//    public Member updateNickname(String id, EditProfileDto editProfileDto) {
//        return myPageCustomRepository.setNickname(id, editProfileDto.getNickname());
//    }

    @Override
    public Member findById(String id) {
        return myPageCustomRepository.findById(id);
    }

    @Override
    public Long modifyProfile(EditProfileDto editProfileDto) {
        // 여기서 dto -> 멤버
        Member member = dtoToEntity(editProfileDto);

        System.out.println("member: "+member.toString());
        System.out.println("editDto: "+editProfileDto);
        return myPageCustomRepository.modifyProfile(member);
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
}
