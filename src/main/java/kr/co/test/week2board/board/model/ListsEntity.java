package kr.co.test.week2board.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.test.week2board.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
@Schema(description = "게시글 목록 QUERY를 위한 Entity")
public class ListsEntity {
    @Schema(description = "카테고리 아이디")
    private String categoryId;
    @Schema(description = "검색 키워드")
    private String keyword;
    @Schema(description = "날짜조회 시작일")
    private String fromDt;
    @Schema(description = "날짜조회 종료일")
    private String toDt;

    @Schema(description = "페이징 번호")
    private int currentPage;
    @Schema(description = "페이징 건수")
    private int fetchCnt = Constants.FETCH_COUNT;
    @Schema(description = "리스트 시작지점")
    private int offset;
}
