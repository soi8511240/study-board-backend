package kr.co.test.week2board.board.controller;

import kr.co.test.week2board.board.dto.BoardDTO;
import kr.co.test.week2board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
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

    /**
     * 글 추가
     * @param boardDTO
     * @return
     */
    @PostMapping("/save")
    public String save(BoardDTO boardDTO){
        log.warn(boardDTO.toString());

        boardService.boardSave(boardDTO);

        return "list";
    }

    /**
     * 게시판 전체리스트
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        log.info(boardDTOList.toString());

        model.addAttribute("boardList", boardDTOList);

        return "list";
    }

    /**
     * 게시판 - 상세
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        boardService.updateViewCnt(id);

        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);

        return "detail";
    }


}
