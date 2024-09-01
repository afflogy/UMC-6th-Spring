package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static RestaurantResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewPage) {
        List<RestaurantResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewPage.getContent().stream()
                .map(RestaurantConverter::reviewPreViewDTO).collect(Collectors.toList());

        return RestaurantResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewPage.isLast())
                .isFirst(reviewPage.isFirst())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static RestaurantResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        return RestaurantResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getContent())
                .build();
    }

    public static RestaurantResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionPage) {
        List<RestaurantResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionPage.getContent().stream()
                .map(RestaurantConverter::missionPreViewDTO).collect(Collectors.toList());

        return RestaurantResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionPage.isLast())
                .isFirst(missionPage.isFirst())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }

    public static RestaurantResponseDTO.MissionPreViewDTO missionPreViewDTO(Mission mission) {
        return RestaurantResponseDTO.MissionPreViewDTO.builder()
                .missionId(mission.getId())
                .body(mission.getContent())
                .createdAt(mission.getCreatedAt())
                .deadline(mission.getDeadline())
                .build();
    }
}
