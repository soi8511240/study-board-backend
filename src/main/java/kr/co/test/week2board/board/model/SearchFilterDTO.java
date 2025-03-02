package kr.co.test.week2board.board.model;

import kr.co.test.week2board.Constants;
import kr.co.test.week2board.board.util.CommonUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchFilterDTO {
    private String categoryId;
    private String keyword;
    private String fromDt;
    private String toDt;

    private int currentPage;
    private int fetchCnt;
    private int offset;

    public SearchFilterDTO(){
        this.categoryId = "";
        this.keyword = "";
        this.fromDt = CommonUtil.getOneYearAgo();
        this.toDt = CommonUtil.getToday();
        this.currentPage = 1;
        this.fetchCnt = Constants.FETCH_COUNT;
        this.offset = 0;
    }

    public SearchFilterDTO(SearchFilterDTO dto){
        this.categoryId = dto.getCategoryId().isEmpty() ? "" : dto.getCategoryId();
        this.keyword = dto.getKeyword().isEmpty() ? "" : dto.getKeyword();
        this.fromDt = dto.getFromDt().isEmpty() ? CommonUtil.getOneYearAgo() : dto.getFromDt();
        this.toDt = dto.getToDt().isEmpty() ? CommonUtil.getToday() : dto.getToDt();
        this.currentPage = dto.getCurrentPage();
        this.fetchCnt = Constants.FETCH_COUNT;
        this.offset = (currentPage-1) * fetchCnt;
    }



}
