package kr.co.test.week2board.board.controller;

import kr.co.test.week2board.board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardWebController {

    @GetMapping("/")
    public String index(){
        log.warn(getClass().getName());
//        logger.log(Level. FINER,"logger");

        return "index";
    }

    @GetMapping("/save")
    public String save(){
        return "save";
    }

    @PostMapping("/save")
    public String save(BoardDTO boardDTO){
        log.warn(boardDTO.toString());

        return "index";
    }
}
