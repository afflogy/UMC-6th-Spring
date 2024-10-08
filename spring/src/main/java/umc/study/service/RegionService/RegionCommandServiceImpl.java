package umc.study.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.RestaurantHandler;
import umc.study.domain.Region;
import umc.study.repository.RegionRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class RegionCommandServiceImpl implements RegionCommandService {
    private final RegionRepository regionRepository;

    public Region findById(Long regionId) {
        return regionRepository.findById(Math.toIntExact(regionId))
                .orElseThrow(()->new RestaurantHandler(ErrorStatus.REGION_NOT_FOUND));

    }
}
