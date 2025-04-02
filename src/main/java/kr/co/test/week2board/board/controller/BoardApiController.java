package kr.co.test.week2board.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.test.week2board.board.model.*;
import kr.co.test.week2board.board.service.ModuleService;
import kr.co.test.week2board.response.GlobalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Board-Controller", description = "게시글 API 엔드포인트")
@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final ModuleService moduleService;

    @RequestMapping(value="/lists", method=RequestMethod.GET)
    @Operation(summary = "게시글 전체 조회", description = "게시글 전체를 조회합니다.")
    public GlobalResponse<ListsResponse> findAll(ListsRequest listRequest) {
        ListsResponse response = moduleService.retrieveAll(listRequest);

        return GlobalResponse.apiResponse(response);
    }

    @RequestMapping(value="/detail", method=RequestMethod.GET)
    @Operation(summary = "게시글 단건 상세", description = "게시글 단건 상세 내용를 조회합니다.")
    public GlobalResponse<DetailResponse> findById(@RequestParam Long id) {
        //ㅅtodo: 빈선언 안해도됨. 오히려 취약점
        DetailResponse response = moduleService.retrieveDetail(id);

        return GlobalResponse.apiResponse(response);
    }

    @RequestMapping(value = "/insert")
    @Operation(summary = "게시글 작성", description = "게시글 작성에 사용하는 API입니다.")
    public GlobalResponse<Long> insertCode(@RequestBody @Valid InsertRequest insertRequest) throws IOException {
        long response = moduleService.insertBoard(insertRequest);

        return GlobalResponse.apiResponse(response);
    }

    @RequestMapping(value="/isMatchPassword", method=RequestMethod.POST)
    @Operation(summary = "비밀번호 비교", description = "입력된 비밀번호를 비교하는 API입니다.")
    public GlobalResponse<Boolean> isMatchPassword(@RequestParam Long id, @RequestParam String password) {
        boolean response = moduleService.matchedPassword(id,password);

        return GlobalResponse.apiResponse(response);
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    @Operation(summary = "게시글 수정", description = "게시글 수정 API입니다.")
    public GlobalResponse<Long> updateBoardById(@RequestBody @Valid UpdateRequest updateRequest) {
        long response = moduleService.updateById(updateRequest);

        return GlobalResponse.apiResponse(response);
    }

    @RequestMapping(value="/remove", method=RequestMethod.GET)
    @Operation(summary = "게시글 삭제", description = "게시글 삭제 API입니다.")
    public GlobalResponse<Long> removeBoardById(@RequestParam Long id) {
        long response = moduleService.removeById(id);

        return GlobalResponse.apiResponse(response);
    }
}
