package umc.study.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO{
        @Schema(description = "리뷰 목록")
        List<MemberResponseDTO.ReviewPreViewDTO> reviewList;

        @Schema(description = "목록 크기")
        Integer listSize;

        @Schema(description = "전체 페이지")
        Integer totalPage;

        @Schema(description = "전체 데이터")
        Long totalElements;

        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO{

        @Schema(description = "작성자 닉네임")
        String ownerNickname;

        @Schema(description = "리뷰 별점")
        Float score;

        @Schema(description = "리뷰 내용")
        String body;

        @Schema(description = "리뷰 작성일")
        LocalDate createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewListDTO {
        @Schema(description = "미션 목록")
        List<MemberResponseDTO.MissionPreViewDTO> missionList;

        @Schema(description = "목록 크기")
        Integer listSize;

        @Schema(description = "전체 페이지")
        Integer totalPage;

        @Schema(description = "전체 데이터")
        Long totalElements;

        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreViewDTO {
        @Schema(description = "미션 아이디")
        Long missionId;

        @Schema(description = "미션 내용")
        String body;

        @Schema(description = "미션 종료일")
        LocalDate deadline;

        @Schema(description = "미션 시작일")
        LocalDateTime createdAt;
    }
}
