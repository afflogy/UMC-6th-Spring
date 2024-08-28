package umc.study.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    // 1) 사용자 회원가입 API
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        @Schema(description = "사용자 아이디")
        Long memberId;

        @Schema(description = "가입한 시간")
        LocalDateTime createdAt;
    }
}
