package kr.co.test.week2board.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
    private int id;
    private String title;
    private String content;
    private String writer;
    private String password;
    private String useYn;
    private String attachYn;
    private String replyYn;
    private String createdAt;
    private String updatedAt;
    private int viewCnt;
    private String categoryCode;
    private String categoryName;
}
