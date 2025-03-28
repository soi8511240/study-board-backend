package kr.co.test.week2board.codes.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseVO {
    private String id;
    private String name;
    private String useYn;
    private String description;
    private String createdAt;
    private String updatedAt;
}
