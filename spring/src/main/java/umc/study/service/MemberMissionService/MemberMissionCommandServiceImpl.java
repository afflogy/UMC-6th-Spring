package umc.study.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.TempHandler;
import umc.study.domain.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.web.dto.MemberMissionResponseDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public MemberMissionResponseDTO.MissionCompleteDTO completeMission(Long memberId, Long missionId) {
        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.MISSION_NOT_FOUND));

        // 미션 상태를 완료로 변경
        memberMission.completeMission();

        // 변경된 상태 저장
        memberMissionRepository.save(memberMission);

        return MemberMissionResponseDTO.MissionCompleteDTO.builder()
                .memberId(memberId)
                .missionId(missionId)
                .status(memberMission.getStatus().toString())
                .build();
    }
}
