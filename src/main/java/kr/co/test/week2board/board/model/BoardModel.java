package kr.co.test.week2board.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class BoardModel {
    @Schema(description = "아이디")
    private Long id;
    @Schema(description = "제목")
    private String title;
    @Schema(description = "게시글 내용")
    private String content;
    @Schema(description = "글쓴이")
    private String writer;
    @Schema(description = "게시일")
    private String createdAt;
    @Schema(description = "수정일")
    private String updatedAt;
    @Schema(description = "조회수")
    private Long viewCnt;
    @Schema(description = "카테고리 분류 이름")
    private String categoryName;
    @Schema(description = "카테고리 분류 ID")
    private String categoryCode;
    @Schema(description = "파일첨부 유무")
    private String attachYn;
}
