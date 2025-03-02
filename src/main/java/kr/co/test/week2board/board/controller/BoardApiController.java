package kr.co.test.week2board.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.test.week2board.board.model.ListRequestDTO;
import kr.co.test.week2board.board.model.ListResponseVO;
import kr.co.test.week2board.board.service.ModuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Board-Controller", description = "게시글 API 엔드포인트")
//@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final ModuleService moduleService;

    /**
     * 코드 단건 조회
     *
     * @param listRequest
     * @return
     */
    @RequestMapping(value="/boards", method=RequestMethod.GET)
    @Operation(
            summary = "게시글 전체 조회",
            description = "게시글 전체를 조회합니다.",
            requestBody = @RequestBody(
                content = @Content(
                        schema = @Schema(
                                allOf = ListRequestDTO.class,
                                requiredProperties = {}
                        )
                )
            ),
            responses = {
                @ApiResponse(
                    responseCode = "200",
                    description = "성공",
                    content = @Content(schema = @Schema(implementation = ListResponseVO.class))
                )
            }
    )
    public List<ListResponseVO> findAll(ListRequestDTO listRequest) throws Exception {
        List<ListResponseVO> adminUserList = null;

        try {
            adminUserList = moduleService.listAll(listRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return adminUserList;
    }

}
