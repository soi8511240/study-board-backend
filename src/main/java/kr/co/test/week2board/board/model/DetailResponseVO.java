package kr.co.test.week2board.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class DetailResponseVO {
    // todo: 게시일 시간 등등 백엔드에서 데이터가공이 필요한 부분에 대해서는
    // 한다면 쿼리 resultType을 entity로 두고
    // record response vo객체를 만들면서 데이터 가공을 해야하는가?
    // todo: 파일첨부모델을 불변성으로 처리하면 좋은것인가?
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
    @Schema(description = "파일첨부 유무")
    private String attachYn;
}
