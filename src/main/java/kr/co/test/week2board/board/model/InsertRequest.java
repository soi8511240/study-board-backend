package kr.co.test.week2board.board.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class InsertRequest {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String password;
    private String useYn;
    private String replyYn;
    private String createdAt;
    private String updatedAt;
    private Long viewCnt;
    private String categoryCode;
    private String categoryName;
    private String attachYn;
    private List<MultipartFile> attachFiles;
}
