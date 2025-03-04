package kr.co.test.week2board.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.test.week2board.board.model.DetailResponseVO;
import kr.co.test.week2board.board.model.ListsRequestDTO;
import kr.co.test.week2board.board.model.ListsResponseVO;
import kr.co.test.week2board.board.service.ModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ListsResponseVO findAll(ListsRequestDTO listRequest) {
        ListsResponseVO responseLists = null;

        try {
            responseLists = moduleService.retrieveAll(listRequest);
        } catch (Exception e) {
            log.error("=========================== {}",e.getMessage());
        }

        return responseLists;
    }

    @Operation(
            summary = "게시글 단건 상세",
            description = "게시글 단건 상세 내용를 조회합니다.",
            requestBody = @RequestBody(
                    content = @Content(schema = @Schema( requiredProperties = {"id"}))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(schema = @Schema(implementation = DetailResponseVO.class))
                    )
            }
    )
    @RequestMapping(value="{id}", method=RequestMethod.GET)
    public DetailResponseVO findById(@RequestParam Long id) {
        DetailResponseVO response = null;

        try {
            response = moduleService.retrieveDetail(id);
        } catch (Exception e) {
            log.error("=========================== {}",e.getMessage());
        }

        return response;
    }

//    @RequestMapping(value="{id}", method=RequestMethod.GET)
//    public DetailResponseVO findById(Long id) {
//        DetailResponseVO response = null;
//
//        try {
//            response = moduleService.detailOne(id);
//        } catch (Exception e) {
//            log.error("=========================== {}",e.getMessage());
//        }
//
//        return response;
//    }

}
