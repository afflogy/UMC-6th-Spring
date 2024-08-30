package umc.study.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    @NoArgsConstructor
    public static class AddMissionDTO {
        private String condition;
        private String content;
        private LocalDate deadline;
    }
}
