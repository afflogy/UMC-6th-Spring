package umc.study.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
}
