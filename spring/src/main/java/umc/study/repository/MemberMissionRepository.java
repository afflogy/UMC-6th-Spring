package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.MemberMission;
import umc.study.domain.enums.MemberMissionStatus;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<umc.study.domain.mapping.MemberMission> findAllByMemberAndStatus(Member member, MemberMissionStatus status, Pageable pageable);

    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);
}