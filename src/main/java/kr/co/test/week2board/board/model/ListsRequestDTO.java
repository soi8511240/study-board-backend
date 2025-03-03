package kr.co.test.week2board.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "게시글 검색 조건 DTO")
public class ListsRequestDTO {

    @Schema(description = "카테고리 ID *100부터 시작*", example = "100")
    private String categoryId;
    @Schema(description = "검색 키워드")
    private String keyword;
    @Schema(description = "검색 날짜 시작일", type = "string", format = "yyyy-MM-dd")
    private String fromDt;
    @Schema(description = "검색 날짜 종료일", type = "string", format = "yyyy-MM-dd")
    private String toDt;

    @Schema(description = "이동할 페이징 번호")
    private int currentPage;
}
