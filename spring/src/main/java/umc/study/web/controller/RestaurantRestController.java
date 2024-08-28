package umc.study.web.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.RestaurantConverter;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.repository.RestaurantRepository;
import umc.study.service.RegionService.RegionCommandService;
import umc.study.service.RestaurantService.RestaurantCommandService;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantRestController {
    private final RestaurantCommandService restaurantCommandService;
    private final RegionCommandService regionCommandService;
    private final RestaurantRepository restaurantRepository;

    @PostMapping("/")
    public ApiResponse<RestaurantResponseDTO.addRestaurantResultDTO> addRestaurants(
            @RequestBody @Valid RestaurantRequestDTO.addRestaurantDTO request) {
        Region region = regionCommandService.findById(request.getRegion());
        Restaurant restaurant = restaurantCommandService.addRestaurant(request, region);
        return ApiResponse.onSuccess(RestaurantConverter.toAddRestaurantResultDTO(restaurant));
    }
}
