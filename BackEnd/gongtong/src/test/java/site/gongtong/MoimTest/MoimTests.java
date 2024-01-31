package site.gongtong.MoimTest;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import site.gongtong.config.QuerydslConfig;
import site.gongtong.member.model.Member;
import site.gongtong.member.model.QMember;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
@Import(QuerydslConfig.class)
@Transactional
public class MoimTests {
    @PersistenceContext
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void init() {
        jpaQueryFactory = new JPAQueryFactory(em);

        Member m1 = Member.builder()
                .birth("980118")
                .id("wlsgh980118@gmail.com")
                .nickname("닠네임")
                .profileImage("김진호이미지.png")
                .gender('M')
                .build();

        Member m2 = Member.builder()
                .birth("980118")
                .id("wlsgh980118@gmail.com2")
                .nickname("닠네임2")
                .profileImage("김진호이미지.png")
                .gender('M')
                .build();
        Member m3 = Member.builder()
                .birth("980118")
                .id("wlsgh980118@gmail.com3")
                .nickname("닠네임3")
                .profileImage("이미지이미지.png")
                .gender('M')
                .build();


        em.persist(m1);
        em.persist(m2);
        em.persist(m3);
    }

    @DisplayName("Querydsl test >> 올바르게 insert되었는지 확인")
    @Test
    void selectBeforeInsertDataWithQuerydsl(){
        //given
        QMember member = new QMember("member1");
        //QChampion champion = new QChampion("champion");

        //when
        Member r1 = jpaQueryFactory.select(member)
                .from(member)
                .where(member.nickname.eq("닠네임"))
                .fetchOne();

        Member r2 = jpaQueryFactory.select(member)
                .from(member)
                .where(member.id.eq("wlsgh980118@gmail.com2"))
                .fetchOne();

        Member r3 = jpaQueryFactory.select(member)
                .from(member)
                .where(member.nickname.eq("닠네임3"))
                .fetchOne();



        //then
        assert r1 != null;
        assertThat(r1.getNickname()).isEqualTo("닠네임");
        assert r2 != null;
        assertThat(r2.getId()).isEqualTo("wlsgh980118@gmail.com2");
        assert r3 != null;
        assertThat(r3.getProfileImage()).isEqualTo("이미지이미지.png");
    }

}
