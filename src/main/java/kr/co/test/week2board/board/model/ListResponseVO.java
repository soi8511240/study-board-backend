package kr.co.test.week2board.board.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ListResponseVO {
    private Long id;
    private String title;
    private String writer;
    private String createdAt;
    private String updatedAt;
    private Long viewCnt;
    private String categoryName;
    private String attachYn;
}
