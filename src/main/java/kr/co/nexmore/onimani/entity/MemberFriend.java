package kr.co.nexmore.onimani.entity;

import kr.co.nexmore.onimani.entity.base.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class MemberFriend extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "member_friend_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "friend_id")
    private Member friend;

    //==생성 메서드==//
    public static MemberFriend createMemberFriend(Member member, Member friend) {
        MemberFriend memberFriend = new MemberFriend();
        memberFriend.member = member;
        memberFriend.friend = friend;
        return memberFriend;
    }

}
