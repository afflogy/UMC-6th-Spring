package umc.study.service.RestaurantService;

import org.springframework.data.domain.Page;
import umc.study.domain.Restaurant;
import umc.study.domain.Review;

import java.util.Optional;

public interface RestaurantQueryService {
    Optional<Restaurant> findRestaurant(Long value);
    Page<Review> getReviewList(Long restaurantId, Integer page);
}
