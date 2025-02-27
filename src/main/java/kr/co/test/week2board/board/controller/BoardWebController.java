package kr.co.test.week2board.board.controller;

import kr.co.test.week2board.board.dto.BoardDTO;
import kr.co.test.week2board.board.dto.SearchFilterDTO;
import kr.co.test.week2board.board.dto.BoardResponseDTO;
import kr.co.test.week2board.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
    public String save(Model model){
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();
        boardResponseDTO.setCategory(boardService.categoryAll());
        model.addAttribute("response", boardResponseDTO);

        return "save";
    }

    /**
     * 글 추가 API
     */
    @PostMapping("/save")
    public String save(BoardDTO boardDTO){

        try{
            boardService.save(boardDTO);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return "redirect:/board/list";
    }

    /**
     * 게시판 전체리스트
     * @param model
     * listResponseDTO - 게시판 리스트, 카테고리 리스트
     */
    @GetMapping("/list")
    public String findAll(SearchFilterDTO searchFilterDTO, Model model){
        SearchFilterDTO filters = new SearchFilterDTO(searchFilterDTO);

        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();
        boardResponseDTO.setTotalCnt(boardService.countBoards(filters));
        boardResponseDTO.setBoard(boardService.findAll(filters));
        boardResponseDTO.setCategory(boardService.categoryAll());
        boardResponseDTO.setSearch(filters);

        model.addAttribute("response", boardResponseDTO);

        return "list";
    }

    /**
     * 게시판 - 상세
     * @param id
     * @param model
     */
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        // count 올리기
        boardService.updateViewCnt(id);

        List<BoardDTO> boardDTOList = new ArrayList<>();
        boardDTOList.add(boardService.findById(id));

        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();
        boardResponseDTO.setBoard(boardDTOList);

        model.addAttribute("response", boardResponseDTO);

        return "detail";
    }

    /**
     * 게시판 - 수정
     */
    @GetMapping("/update/{id}")
    public String updateById(@PathVariable("id") Long id, Model model){
        List<BoardDTO> boardDTOList = new ArrayList<>();
        boardDTOList.add(boardService.findById(id));

        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();
        boardResponseDTO.setBoard(boardDTOList);
        boardResponseDTO.setCategory(boardService.categoryAll());

        model.addAttribute("response", boardResponseDTO);

        return "update";
    }

    /**
     * 게시판 - 수정
     */
    @PostMapping("/update/{id}")
    public String updateById(BoardDTO boardDTO, Model model){
        boardService.update(boardDTO);

        Long id = boardDTO.getId();

        List<BoardDTO> boardDTOList = new ArrayList<>();
        boardDTOList.add(boardService.findById(id));

        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();
        boardResponseDTO.setBoard(boardDTOList);

        model.addAttribute("response", boardResponseDTO);

        return "detail";
    }

    public String deleteById(@PathVariable Long id){
        boardService.delete(id);

        return "redirect:/board/list";
    }


}
