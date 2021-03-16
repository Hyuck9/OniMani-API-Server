package kr.co.nexmore.onimani.entity;

import kr.co.nexmore.onimani.entity.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "kakaoId", "nickname", "email", "profileImageUrl", "thumbnailImageUrl", "location"})
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private Long kakaoId;

    private String nickname;

    private String email;

    private String profileImageUrl;

    private String thumbnailImageUrl;

    @Embedded
    private Location location;

    @OneToOne(mappedBy = "member")
    private MemberFriend memberFriend;

    @OneToMany(mappedBy = "friend")
    private List<MemberFriend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PlanMember> planMembers = new ArrayList<>();

    //== 연관관계 편의 메서드 ==//
    public void addFriend(Member friend) {
        if (this.id.equals(friend.getId())) {
            return;
        }
        MemberFriend memberFriend = MemberFriend.createMemberFriend(this, friend);
        friends.add(memberFriend);
        friend.getFriends().add(MemberFriend.createMemberFriend(friend, this));
    }

    //== 생성 메서드 ==//
    public static Member createMember(Long kakaoId, String nickname, String email, String profileUrl, String thumbnailUrl) {
        Member member = new Member();
        member.kakaoId = kakaoId;
        member.nickname = nickname;
        member.email = email;
        member.profileImageUrl = profileUrl;
        member.thumbnailImageUrl = thumbnailUrl;
        return member;
    }
    public static Member createMember(Long kakaoId, String nickname, String profileUrl, String thumbnailUrl) {
        return createMember(kakaoId, nickname, null, profileUrl, thumbnailUrl);
    }
    public static Member createMember(Long kakaoId, String nickname) {
        return createMember(kakaoId, nickname, null, null, null);
    }
}
