package site.gongtong.member.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.member.dto.EditProfileDto;
import site.gongtong.member.model.Member;
import site.gongtong.member.model.QMember;
import site.gongtong.review.model.QReview;
import site.gongtong.review.model.Review;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageCustomRepositoryImpl implements MyPageCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int MemberidToNum(String user_id) {
        QMember member = QMember.member;

        return jpaQueryFactory
                .select(member.num)
                .from(member)
                .where(member.id.eq(user_id))
                .fetchOne();
    }

    @Override
    public List<Review> findAllReviews(int user_num) {
        QReview review = QReview.review;

        return jpaQueryFactory
                .selectFrom(review)
                .where(review.memberId.eq(user_num))
                .fetch();
    }

    @Override
    public Member findById(String id) {
        QMember member = QMember.member;

        return jpaQueryFactory
                .selectFrom(member)
                .where(member.id.eq(id))
                .fetchOne();
    }

    @Override
    public Long modifyProfile(Member inputMember) {
        //성공이면 1 반환, 실패면 0 반환
        QMember member = QMember.member;
        return jpaQueryFactory
                .update(member)
                .set(member.nickname, inputMember.getNickname())
                .set(member.profileImage, inputMember.getProfileImage())
                .where(member.id.eq(inputMember.getId())) //id로 해당 유저만 변경하기
                .execute();
    }

    @Override
    public int modifyPwd(String id, String newEncodedPwd) {
        //성공이면 1 반환, 실패면 0 반환
        QMember member = QMember.member;
        return (int) jpaQueryFactory
                .update(member)
                .set(member.password, newEncodedPwd)
                .where(member.id.eq(id)) //id로 해당 유저만 변경하기
                .execute();
    }

    @Override
    public int delete(String id) {
        QMember member = QMember.member;

        return (int) jpaQueryFactory
                .delete(member)
                .where(member.id.eq(id))
                .execute();
    }

//    @Override
//    public Member save(EditProfileDto editProfileDto) {//패스워드, 닉넴, 프사
//        QMember member = QMember.member;
//
//        return jpaQueryFactory
//                .update(member) // updatedField에는 실제 필드명을 넣어야 합니다.
//                .set(member.nickname, editProfileDto.getNickname())
//                .set(member.profileImage, editProfileDto.getProfileImage())
//                .where(member.id.eq(editProfileDto.getId()))
//                .execute();
//    }

//    @Override
//    public Member setPassword(String id, String password) {
//        QMember member = QMember.member;
//
//        jpaQueryFactory
//                .update(member)
//                .set(member.password, password)
//                .where(member.id.eq(id))
//                .execute();
//
//        return jpaQueryFactory
//                .selectFrom(member)
//                .where(member.id.eq(id))
//                .fetchOne();
//    }

//    @Override
//    public Member setProfileImage(String id, String profileImage) {
//        QMember member = QMember.member;
//
//        jpaQueryFactory
//                .update(member)
//                .set(member.profileImage, profileImage)
//                .where(member.id.eq(id))
//                .execute();
//
//        return jpaQueryFactory
//                .selectFrom(member)
//                .where(member.id.eq(id))
//                .fetchOne();
//    }

//    @Override
//    public Member setNickname(String id, String nickname) {
//        QMember member = QMember.member;
//
//        jpaQueryFactory
//                .update(member)
//                .set(member.nickname, nickname)
//                .where(member.id.eq(id))
//                .execute();
//
//        return jpaQueryFactory
//                .selectFrom(member)
//                .where(member.id.eq(id))
//                .fetchOne();
//    }

}
