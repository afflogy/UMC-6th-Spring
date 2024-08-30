package umc.study.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class RestaurantRequestDTO {

    @Getter
    public static class addRestaurantDTO {
        @NotBlank
        @Schema(description = "가게 이름")
        String name;

        @NotBlank
        @Schema(description = "가게 연락처")
        String phon_number;

        @NotBlank
        @Size(min = 5, max = 50)
        @Schema(description = "가게 주소")
        String address;

        @NotBlank
        @Schema(description = "지역")
        Long region;
    }

    @Getter
    public static class ReviewDTO {
        @NotBlank
        @Schema(description = "가게 아이디")
        Long restaurant;

        @NotBlank
        @Schema(description = "리뷰 제목")
        String title;

        @NotNull
        @Schema(description = "리뷰 점수")
        Float score;

        @NotBlank
        @Size(min = 30, max = 100)
        @Schema(description = "리뷰 내용")
        String content;
    }
}
