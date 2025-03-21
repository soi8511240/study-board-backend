package kr.co.test.week2board.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "게시글 목록 VO")
public record ListsResponse(
    @Schema(description = "게시글 목록 글갯수")
    Long totalCnt,

    @Schema(description = "게시글 목록", contentSchema = ListsModel.class)
    List<ListsModel> boardLists
) {}

