package umc.study.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddResultDTO {
        @Schema(description = "가게 아이디")
        Long restaurantId;

        @Schema(description = "미션 내용")
        String content;

        @Schema(description = "미션 데드라인")
        LocalDateTime deadline;

        @Schema(description = "미션 생성일")
        LocalDateTime createdAt;
    }
}
