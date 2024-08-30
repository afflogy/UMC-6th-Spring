package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.RestaurantHandler;
import umc.study.domain.Mission;
import umc.study.domain.Restaurant;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.RestaurantRepository;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    @Override
    public MissionResponseDTO.AddResultDTO addMission(Long restaurantId, MissionRequestDTO.AddMissionDTO info) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        Mission mission = Mission.builder()
                .condition(info.getCondition())
                .content(info.getContent())
                .deadline(info.getDeadline())
                .restaurant(restaurant)
                .build();

        missionRepository.save(mission);

        return MissionResponseDTO.AddResultDTO.builder()
                .restaurantId(restaurant.getId())
                .content(mission.getContent())
                .deadline(mission.getDeadline().atStartOfDay())
                .createdAt(LocalDateTime.now())
                .build();
    }


}
