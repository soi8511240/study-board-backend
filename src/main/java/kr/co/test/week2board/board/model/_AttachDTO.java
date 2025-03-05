package kr.co.test.week2board.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class _AttachDTO {
    private Long id;
    private Long boardId;
    private String originalFileName;
    private String storedFileName;
}
