package kr.co.test.week2board.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class _BoardResponseDTO {
    private Long totalCnt;
    private int currentPage;

    private _SearchFilterDTO search;

    private List<_BoardDTO> board;
    private List<_CategoryDTO> category;
    private List<_SearchFilterDTO> filter;
    private List<_AttachDTO> attach;
}
