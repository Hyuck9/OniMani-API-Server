package kr.co.nexmore.onimani.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext EntityManager em;

    @Test
    @Rollback(value = false)
    public void friendTest() throws Exception {
        // given
        Member member1 = Member.createMember(1L, "test1");
        Member member2 = Member.createMember(2L, "test2");
        Member member3 = Member.createMember(3L, "test3");
        Member member4 = Member.createMember(4L, "test4");

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        // when
        Member findMember1 = em.createQuery("select m from Member m where m.nickname = :nickname", Member.class)
                .setParameter("nickname", "test1").getSingleResult();

        findMember1.addFriend(member2);
        findMember1.addFriend(member3);
        findMember1.addFriend(member4);

        Member findMember2 = em.createQuery("select m from Member m where m.nickname = :nickname", Member.class)
                .setParameter("nickname", "test2").getSingleResult();

        // then
        assertThat(findMember1.getFriends().size()).isEqualTo(3);
        assertThat(findMember2.getFriends().size()).isEqualTo(1);
    }
}