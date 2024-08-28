package umc.study.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.RestaurantConverter;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.repository.RestaurantRepository;
import umc.study.web.dto.RestaurantRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantCommandServiceImpl implements RestaurantCommandService {
    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public Restaurant addRestaurant(RestaurantRequestDTO.addRestaurantDTO request, Region region) {
        Restaurant newRestaurant = RestaurantConverter.toAddRestaurant(request, region);
        return restaurantRepository.save(newRestaurant);
    }
}
