package site.gongtong.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
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

        System.out.println("repo usernum: " + user_num);

        return jpaQueryFactory
                .selectFrom(review)
                .where(review.member.num.eq(user_num)
                        .and(review.isRemoved.eq(false)))
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
    public Member findByNickname(String nickname) {
        QMember member = QMember.member;

        return jpaQueryFactory
                .selectFrom(member)
                .where(member.nickname.eq(nickname))
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

}
