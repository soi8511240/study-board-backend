package kr.co.test.week2board.codes.controller;

import kr.co.test.week2board.codes.dto.CategoryDTO2;
import kr.co.test.week2board.codes.service.CodesService;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {
    private final CodesService codesService;

    @RequestMapping(value="/commonCodes", method= RequestMethod.GET)
    public Map<String, Object> categoryAll() {
        Map<String, Object> codes = new HashMap<>();

        try {
            List<CategoryDTO2> categoryDTO2List = codesService.categoryAll();
            codes.put("category", categoryDTO2List);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return codes;
    }
}
