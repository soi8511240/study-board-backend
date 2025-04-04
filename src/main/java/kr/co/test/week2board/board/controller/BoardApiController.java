package kr.co.test.week2board.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.co.test.week2board.Constants;
import kr.co.test.week2board.board.model.*;
import kr.co.test.week2board.board.service.ModuleService;
import kr.co.test.week2board.response.GlobalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Tag(name = "Board-Controller", description = "게시글 API 엔드포인트")
@Slf4j
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardApiController {

    private final ModuleService moduleService;

    @GetMapping(value = "/lists")
    @Operation(summary = "게시글 전체 조회", description = "게시글 전체를 조회합니다.")
    public GlobalResponse<ListsResponse> findAll(ListsRequest listRequest) {
        ListsResponse response = moduleService.retrieveAll(listRequest);

        return GlobalResponse.apiResponse(response);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "게시글 단건 상세", description = "게시글 단건 상세 내용를 조회합니다.")
    public GlobalResponse<DetailResponse> findById(@RequestParam Long id) {
        DetailResponse response = moduleService.retrieveDetail(id);

        return GlobalResponse.apiResponse(response);
    }

    @PostMapping
    @Operation(summary = "게시글 작성", description = "게시글 작성에 사용하는 API입니다.")
    public GlobalResponse<Long> insertCode(@RequestBody @Valid InsertRequest insertRequest) throws IOException {
        long response = moduleService.insertBoard(insertRequest);

        return GlobalResponse.apiResponse(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "게시글 수정", description = "게시글 수정 API입니다.")
    public GlobalResponse<Long> updateBoardById(@RequestBody @Valid UpdateRequest updateRequest) {
        long response = moduleService.updateById(updateRequest);

        return GlobalResponse.apiResponse(response);
    }

    @PostMapping("/{id}/password-verification")
    @Operation(summary = "비밀번호 비교", description = "입력된 비밀번호를 비교하는 API입니다.")
    public GlobalResponse<Boolean> isMatchPassword(@RequestParam Long id, @RequestParam String password) {
        boolean isMatched = moduleService.matchedPassword(id, password);

        return GlobalResponse.apiResponse(isMatched);
    }

    @DeleteMapping(value = "/remove")
    @Operation(summary = "게시글 삭제", description = "게시글 삭제 API입니다.")
    public GlobalResponse<Long> removeBoardById(@RequestParam Long id) {
        long response = moduleService.removeById(id);

        return GlobalResponse.apiResponse(response);
    }

    @GetMapping("/download/{fileName:.+}")
    @Operation(summary = "파일 다운로드", description = "파일 다운로드 API입니다.")
    public void downloadFile(@PathVariable String fileName, HttpServletResponse response) {
        File file = new File(Constants.ATTACH_PATH + fileName);

        if (!file.exists()) {
            throw new RuntimeException("파일을 찾을 수 없습니다: " + fileName);
        }

        // 파일명 인코딩
        String encodedFilename;
        encodedFilename = URLEncoder.encode(fileName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        // 컨텐츠 타입 설정
        String contentType = "application/octet-stream";
        try {
            contentType = Files.probeContentType(file.toPath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
        } catch (IOException ignored) {
        }

        // 응답 헤더 설정
        response.setContentType(contentType);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"");
        response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));

        // 버퍼를 사용한 스트리밍 전송
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream()) {

            byte[] buffer = new byte[8192]; // 8KB 버퍼
            int bytesRead;

            // 버퍼 단위로 읽어서 출력 스트림에 쓰기
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 다운로드 중 오류 발생", e);
        }
    }
}

