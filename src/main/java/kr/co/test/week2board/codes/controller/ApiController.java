package kr.co.test.week2board.codes.controller;

//import kr.co.test.week2board.codes.dto.CategoryDTO2;
import kr.co.test.week2board.codes.model.CategoryResponseVO;
import kr.co.test.week2board.codes.service.CodesService;
import kr.co.test.week2board.response.GlobalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/codes")
@RequiredArgsConstructor
public class ApiController {
    private final CodesService codesService;

    @RequestMapping(value="/categoryAll", method= RequestMethod.GET)
    public GlobalResponse<Map<String, Object>> categoryAll() {
        Map<String, Object> codes = new HashMap<>();

        List<CategoryResponseVO> categoryList = codesService.categoryAll();
        codes.put("category", categoryList);

        return GlobalResponse.apiResponse(codes);
    }
}
