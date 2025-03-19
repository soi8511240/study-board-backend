package kr.co.test.week2board.board.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UpdateRequestDTO (
    Long id,
    String title,
    String content,
    String writer,
    String categoryCode,
    String password,
    String useYn,
    String replyYn,
    String createdAt,
    String updatedAt,
    Long viewCnt,
    String categoryName,
    String attachYn,
    List<AttachDTO> attachLists,
    List<MultipartFile> attachMultiFiles
    ){
}
