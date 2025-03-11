package kr.co.test.week2board.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Schema(description = "게시글 작성 DTO 입니다.")
public class InsertRequestDTO {

    @NotBlank(message = "제목은 필수 값입니다.")
    @Schema(description = "제목")
    private String title;
    @NotBlank(message = "게시글은 필수 값입니다.")
    @Schema(description = "게시글 내용")
    private String content;
    @NotBlank
    @NotBlank(message = "글쓴이은 필수 값입니다.")
    @Schema(description = "글쓴이")
    private String writer;
    @Schema(description = "카테고리 분류 코드")
    private String categoryCode;


    @Schema(description = "암호")
//    @Pattern(
//            regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
//            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8 ~20자의 비밀번호여야 합니다."
//    )
    private String password;
//    private List<MultipartFile> attachFiles;
}
