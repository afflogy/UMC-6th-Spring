package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.MemberStatus;
import umc.study.domain.mapping.MemberAgree;
import umc.study.domain.mapping.MemberMission;
import umc.study.domain.mapping.MemberPoint;
import umc.study.domain.mapping.MemberPrefer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "member_id")
   private Long id;

   @Column(nullable = false, length = 20)
   private String name;

   @Column(nullable = false, length = 20)
   private String phone_num;

   @Column(nullable = false, length = 40)
   private String address;

   private LocalDate inactDate;

   @Enumerated(EnumType.STRING)
   @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
   private MemberStatus status;

   // @Column(nullable = false, length=50)
   private String email;

   @Enumerated(EnumType.STRING)
   @Column(columnDefinition = "VARCHAR(10)")
   private Gender gender;

   private LocalDate inactiveDate;

   @ColumnDefault("0")
   private Integer point;

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
   private List<MemberAgree> memberAgreeList = new ArrayList<>();

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
   private List<MemberPrefer> memberPreferList = new ArrayList<>();

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
   private List<Review> reviewList = new ArrayList<>();

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
   private List<MemberPoint> memberPointList = new ArrayList<>();

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<MemberMission> memberMissionList = new ArrayList<>();

   public void addMemberMission(MemberMission memberMission) {
      this.memberMissionList.add(memberMission);
      if (memberMission.getMember() != this) {
         memberMission.setMember(this);
      }
   }
}
