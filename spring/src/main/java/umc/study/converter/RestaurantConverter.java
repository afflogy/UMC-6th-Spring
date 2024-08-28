package umc.study.converter;

import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

public class RestaurantConverter {

    public static RestaurantResponseDTO.addRestaurantResultDTO toAddRestaurantResultDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.addRestaurantResultDTO.builder()
                .restaurantId(restaurant.getId())
                .regionName(restaurant.getRegion().getRegion_state())
                .createdAt(restaurant.getCreatedAt())
                .build();
    }

    public static Restaurant toAddRestaurant(RestaurantRequestDTO.addRestaurantDTO request, Region region) {
        return Restaurant.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
    }
}
