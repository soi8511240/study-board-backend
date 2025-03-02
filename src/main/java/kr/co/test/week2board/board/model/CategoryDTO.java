package kr.co.test.week2board.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDTO {
    private String id;
    private String name;
    private String useYn;
    private String description;
    private String createdAt;
    private String updatedAt;
}
