package kr.co.test.week2board.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Schema(description = "게시글 목록 VO")
public class ListsResponseVO {

    @Schema(description = "게시글 목록 글갯수")
    private Long totalCnt;
    @Schema(description = "게시글 목록", contentSchema = ListsBoardVO.class)
    private List<ListsBoardVO> boardLists;

    public ListsResponseVO(Long totalCnt, List<ListsBoardVO> boardLists) {
        this.totalCnt = totalCnt;
        this.boardLists = boardLists;
    }
}
