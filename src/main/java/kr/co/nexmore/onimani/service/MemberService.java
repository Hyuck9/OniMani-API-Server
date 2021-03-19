package kr.co.nexmore.onimani.service;

import kr.co.nexmore.onimani.entity.Member;
import kr.co.nexmore.onimani.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        Optional<Member> findMember = memberRepository.findOptionalByKakaoId(member.getKakaoId());

        if (findMember.isPresent()) {
            throw new IllegalStateException("");
        }
        memberRepository.save(member);
        return member.getId();
    }

}
