package umc.study.service.RestaurantService;

import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.web.dto.RestaurantRequestDTO;

public interface RestaurantCommandService {
    Restaurant addRestaurant(RestaurantRequestDTO.addRestaurantDTO request, Region region);
}
