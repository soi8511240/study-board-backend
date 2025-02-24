package kr.co.test.week2board.board.controller;

import kr.co.test.week2board.board.dto.BoardDTO;
import kr.co.test.week2board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardWebController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(){
        log.warn(getClass().getName());

        return "index";
    }

    @GetMapping("/save")
    public String save(){
        return "save";
    }

    @PostMapping("/save")
    public String save(BoardDTO boardDTO){
        log.warn(boardDTO.toString());

        boardService.save(boardDTO);

        return "index";
    }

    @GetMapping("/list")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();

        log.warn(boardDTOList.toString());

        model.addAttribute("boardList", boardDTOList);

        return "list";
    }
}
