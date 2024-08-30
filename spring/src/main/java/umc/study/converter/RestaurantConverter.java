package umc.study.converter;

import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

import java.time.LocalDateTime;

public class RestaurantConverter {

    public static RestaurantResponseDTO.addRestaurantResultDTO toAddRestaurantResultDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.addRestaurantResultDTO.builder()
                .restaurantId(restaurant.getId())
                .regionName(restaurant.getRegion().getRegion_state())
                .createdAt(restaurant.getCreatedAt())
                .build();
    }

    public static Restaurant toAddRestaurant(RestaurantRequestDTO.addRestaurantDTO info, Region region) {
        return Restaurant.builder()
                .name(info.getName())
                .address(info.getAddress())
                .region(region)
                .build();
    }

    public static RestaurantResponseDTO.writeReviewDTO toWriteReviewResultDTO(Review review) {
        return RestaurantResponseDTO.writeReviewDTO.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(RestaurantRequestDTO.ReviewDTO info, Restaurant restaurant) {
        return Review.builder()
                .title(info.getTitle())
                .restaurant(restaurant)
                .score(info.getScore())
                .content(info.getContent())
                .build();
    }
}
