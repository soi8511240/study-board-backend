package kr.co.test.week2board.board.model;

import kr.co.test.week2board.Constants;
import lombok.Data;

@Data
public class ListEntity {
    private String categoryId;
    private String keyword;
    private String fromDt;
    private String toDt;

    private int currentPage;
    private int fetchCnt = Constants.FETCH_COUNT;
}
