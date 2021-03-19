package kr.co.nexmore.onimani.repository;

import kr.co.nexmore.onimani.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void save() {
        // given
        Member member1 = Member.createMember(1L, "test1");
        Member member2 = Member.createMember(2L, "test2");
        Member member3 = Member.createMember(3L, "test3");
        Member member4 = Member.createMember(4L, "test4");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);

        em.flush();
        em.clear();

        // when
        Member findMember1 = memberRepository.findOptionalByNickname("test1").get();
        Member findMember2 = memberRepository.findOptionalByNickname("test2").get();
        Member findMember3 = memberRepository.findOptionalByNickname("test3").get();
        Member findMember4 = memberRepository.findOptionalByNickname("test4").get();

        findMember1.addFriend(findMember2);
        findMember1.addFriend(findMember3);
        findMember1.addFriend(findMember4);
        findMember2.addFriend(findMember3);

        em.flush();
        em.clear();

        Member reFindMember1 = memberRepository.findOptionalEntityGraphByNickname("test1").get();
        Member reFindMember2 = memberRepository.findOptionalEntityGraphByNickname("test2").get();

        // then
        assertThat(reFindMember1.getFriends().size()).isEqualTo(3);
        assertThat(reFindMember2.getFriends().size()).isEqualTo(2);
    }

}