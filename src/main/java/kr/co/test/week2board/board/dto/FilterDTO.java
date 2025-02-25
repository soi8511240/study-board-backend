package kr.co.test.week2board.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilterDTO {
    private String categoryId;
    private String keyword;
    private String startDt;
    private String endDt;
}
