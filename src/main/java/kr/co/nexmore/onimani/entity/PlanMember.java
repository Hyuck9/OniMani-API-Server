package kr.co.nexmore.onimani.entity;

import kr.co.nexmore.onimani.entity.base.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class PlanMember extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "plan_member_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private PlanMemberStatus status;
}
