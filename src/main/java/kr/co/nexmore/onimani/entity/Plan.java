package kr.co.nexmore.onimani.entity;

import kr.co.nexmore.onimani.entity.base.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Plan extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "plan_id")
    private Long id;

    private String title;

    private String placeName;

    private LocalDateTime planDate;

    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<PlanMember> planMembers = new ArrayList<>();
}
