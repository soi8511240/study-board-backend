package kr.co.test.week2board.board.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttachModel {
    long boardId;
    String originalFileName;
    String storedFileName;
    String fileUri;
    long size;
    int orderBy;
}
