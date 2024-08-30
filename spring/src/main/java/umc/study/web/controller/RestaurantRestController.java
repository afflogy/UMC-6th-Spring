package umc.study.web.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.RestaurantConverter;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.repository.RestaurantRepository;
import umc.study.service.RegionService.RegionCommandService;
import umc.study.service.RestaurantService.RestaurantCommandService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistRestaurant;
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
            @RequestBody @Valid RestaurantRequestDTO.addRestaurantDTO info) {
        Region region = regionCommandService.findById(info.getRegion());
        Restaurant restaurant = restaurantCommandService.addRestaurant(info, region);
        return ApiResponse.onSuccess(RestaurantConverter.toAddRestaurantResultDTO(restaurant));
    }

    @PostMapping("/{restaurantId}/reviews")
    public ApiResponse<RestaurantResponseDTO.writeReviewDTO> writeReview(
            @RequestBody @Valid RestaurantRequestDTO.ReviewDTO info,
            @ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId,
            @ExistMember @RequestParam(name = "memberId") Long memberId) {
        Review review = restaurantCommandService.writeReview(memberId, restaurantId, info);
        return ApiResponse.onSuccess(RestaurantConverter.toWriteReviewResultDTO(review));
    }
}
