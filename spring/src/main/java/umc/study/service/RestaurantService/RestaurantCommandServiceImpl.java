package umc.study.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.RestaurantHandler;
import umc.study.aws.s3.AmazonS3Manager;
import umc.study.converter.RestaurantConverter;
import umc.study.converter.ReviewConverter;
import umc.study.domain.*;
import umc.study.repository.*;
import umc.study.web.dto.RestaurantRequestDTO;
import umc.study.web.dto.RestaurantResponseDTO;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantCommandServiceImpl implements RestaurantCommandService {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ReviewConverter reviewConverter;

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

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), info.getReviewPicture());

        review.setMember(memberRepository.findById(memberId).get());
        review.setRestaurant(restaurantRepository.findById(restaurantId).get());

        reviewImageRepository.save(reviewConverter.toReviewImage(pictureUrl,review));
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
