package kr.co.nexmore.onimani.repository;

import kr.co.nexmore.onimani.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findOptionalByKakaoId(Long KakaoId);

    Optional<Member> findOptionalByNickname(String nickname);

    @EntityGraph(attributePaths = {"friends"})
    Optional<Member> findOptionalEntityGraphByNickname(@Param("nickname") String nickname);
}
