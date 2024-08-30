package umc.study.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    @NoArgsConstructor
    public static class AddMissionDTO {
        @Schema(description = "미션 상태")
        private String condition;

        @Schema(description = "미션 내용")
        private String content;

        @Schema(description = "미션 종료일")
        private LocalDateTime deadline;
    }
}
