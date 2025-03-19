package kr.co.test.week2board.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.test.week2board.board.model.*;
import kr.co.test.week2board.board.service.ModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Board-Controller", description = "게시글 API 엔드포인트")
//@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final ModuleService moduleService;

    @RequestMapping(value="/lists", method=RequestMethod.GET)
    @Operation(
            summary = "게시글 전체 조회",
            description = "게시글 전체를 조회합니다.",
            requestBody = @RequestBody(
                content = @Content(schema = @Schema( allOf = ListsRequestDTO.class))
            ),
            responses = {
                @ApiResponse(
                    responseCode = "200",
                    description = "성공",
                    content = @Content(schema = @Schema(implementation = ListsResponseVO.class))
                )
            }
    )
    public ResponseEntity<ListsResponseVO> findAll(ListsRequestDTO listRequest) {
        ListsResponseVO responseLists = null;

        responseLists = moduleService.retrieveAll(listRequest);

        return ResponseEntity.ok(responseLists);
    }

    @Operation(summary = "게시글 단건 상세", description = "게시글 단건 상세 내용를 조회합니다.")
    @RequestMapping(value="/detail", method=RequestMethod.GET)
    public DetailResponseVO findById(@RequestParam Long id) {
        DetailResponseVO response = null;

        response = moduleService.retrieveDetail(id);

        return response;
    }

    @RequestMapping(value = "/insert", method=RequestMethod.POST)
    @Operation(summary = "게시글 작성", description = "게시글 작성에 사용하는 API입니다.")
    public long insertCode(@RequestBody @Valid InsertRequestDTO insertRequestDTO) throws IOException {
        long response = 0L;

        response = moduleService.insertBoard(insertRequestDTO);

        return response;
    }

    @RequestMapping(value="/isMatchPassword", method=RequestMethod.POST)
    @Operation(summary = "비밀번호 비교", description = "입력된 비밀번호를 비교하는 API입니다.")
    public boolean isMatchPassword(@RequestParam Long id, @RequestParam String password) {
        boolean response = false;

        response = moduleService.matchedPassword(id,password);

        return response;
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    @Operation(summary = "게시글 수정", description = "게시글 수정 API입니다.")
    public long updateBoardById(@RequestBody @Valid UpdateRequestDTO updateRequestDTO) {
        long response = 0L;

        response = moduleService.updateById(updateRequestDTO);

        return response;
    }

    @RequestMapping(value="/remove", method=RequestMethod.GET)
    @Operation(summary = "게시글 삭제", description = "게시글 삭제 API입니다.")
    public long removeBoardById(@RequestParam Long id) {
        long response = 0L;

        response = moduleService.removeById(id);

        return response;
    }
}
