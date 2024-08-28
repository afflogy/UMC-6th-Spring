package umc.study.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    // 1) 사용자 회원가입 API
    @Getter
    public static class JoinDTO{
        @NotBlank
        @Schema(description = "사용자 이름")
        String name;

        @NotBlank
        @Schema(description = "사용자 연락처")
        String phon_number;

        @NotBlank
        @Schema(description = "사용자 성별")
        Integer gender;

        @NotBlank
        @Size(min = 5, max = 12)
        @Schema(description = "사용자 주소")
        String address;

        @ExistCategories
        @Schema(description = "사용자 선호 음식")
        List<Long> preferCategory;
    }
}
