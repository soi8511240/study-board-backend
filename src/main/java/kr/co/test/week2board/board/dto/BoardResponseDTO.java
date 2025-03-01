package kr.co.test.week2board.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BoardResponseDTO {
    private Long totalCnt;
    private int currentPage;

    private SearchFilterDTO search;

    private List<BoardDTO> board;
    private List<CategoryDTO> category;
    private List<SearchFilterDTO> filter;
    private List<AttachDTO> attach;
}
