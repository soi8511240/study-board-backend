package kr.co.test.week2board.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ListResponseDTO {
    private List<BoardDTO> board;
    private List<CategoryDTO> category;
}
