package umc.study.service.MissionService;


import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

public interface MissionCommandService {
    MissionResponseDTO.AddResultDTO addMission(Long restaurantId, MissionRequestDTO.AddMissionDTO missionDTO);
    void joinMission(Long memberId, Long missionId);
}
