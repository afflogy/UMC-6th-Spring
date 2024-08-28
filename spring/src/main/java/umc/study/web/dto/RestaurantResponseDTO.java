package umc.study.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class RestaurantResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addRestaurantResultDTO {
        @Schema(description = "가게 아이디")
        Long restaurantId;

        @Schema(description = "가게 이름")
        String regionName;

        @Schema(description = "가입한 시간")
        LocalDateTime createdAt;
    }
}
