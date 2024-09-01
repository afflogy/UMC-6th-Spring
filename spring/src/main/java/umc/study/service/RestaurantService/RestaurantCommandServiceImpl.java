package umc.study.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.RestaurantHandler;
import umc.study.converter.RestaurantConverter;
import umc.study.domain.Mission;
import umc.study.domain.Region;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;
import umc.study.repository.MissionRepository;
import umc.study.repository.RestaurantRepository;
import umc.study.repository.ReviewRepository;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantCommandServiceImpl implements RestaurantCommandService {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Restaurant addRestaurant(RestaurantRequestDTO.addRestaurantDTO request, Region region) {
        Restaurant newRestaurant = RestaurantConverter.toAddRestaurant(request, region);
        return restaurantRepository.save(newRestaurant);
    }

    @Override
    public Review writeReview(Long memberId, Long restaurantId, RestaurantRequestDTO.ReviewDTO info) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        Review review = RestaurantConverter.toReview(info, restaurant);

        return reviewRepository.save(review);
    }

    public RestaurantResponseDTO.ReviewPreViewListDTO getReviewList(Long restaurantId, Integer page) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        Page<Review> RestaurantPage = reviewRepository.findAllByRestaurant(restaurant, PageRequest.of(page, 10));
        return RestaurantConverter.reviewPreViewListDTO(RestaurantPage);
    }

    public RestaurantResponseDTO.MissionPreViewListDTO getMissionList(Long restaurantId, Integer page) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        Page<Mission> RestaurantPage = missionRepository.findAllByRestaurant(restaurant, PageRequest.of(page, 10));
        return RestaurantConverter.missionPreViewListDTO(RestaurantPage);
    }
}
