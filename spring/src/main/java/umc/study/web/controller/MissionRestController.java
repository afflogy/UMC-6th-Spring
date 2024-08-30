package umc.study.web.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
public class MissionRestController {
    private final MissionCommandService missionCommandService;

    @PostMapping("/addInRestaurant")
    public ResponseEntity<MissionResponseDTO.AddResultDTO> addMission(
            @RequestParam Long restaurantId,
            @RequestBody MissionRequestDTO.AddMissionDTO missionDTO) {
        MissionResponseDTO.AddResultDTO result = missionCommandService.addMission(restaurantId, missionDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/joinAndMember")
    public ResponseEntity<Void> joinMission(
            @RequestParam Long memberId,
            @RequestParam Long missionId) {
        missionCommandService.joinMission(memberId, missionId);
        return ResponseEntity.ok().build();
    }
}
