package kr.co.test.week2board.board.controller;

import kr.co.test.week2board.board.dto.BoardDTO;
import kr.co.test.week2board.board.dto.SearchFilterDTO;
import kr.co.test.week2board.board.service.ModuleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardWebController {

    private final ModuleServiceImpl moduleServiceImpl;

    /**
     * 메인 페이지
     * @return index 페이지
     */
    @GetMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 글 추가 페이지 진입시 필요정보
     * @param model 뷰에 전달할 데이터
     *              BoardResponseDTO - 카테고리리스트
     * @return save 페이지
     */
    @GetMapping("/save")
    public String loadSaveData(Model model){
        model.addAttribute("response", moduleServiceImpl.loadSaveData());

        return "save";
    }

    /**
     * 글 작성 처리
     * @param boardDTO 작성하려는 글 정보
     * @return 작성된 글의 상세 페이지로 리다이렉션
     */
    @PostMapping("/save")
    public String save(BoardDTO boardDTO){
        long id = moduleServiceImpl.save(boardDTO);

        return "redirect:/board/"+boardDTO.getId();
    }

    /**
     * 게시글 목록 조회
     * @param searchFilter 검색 조건
     * @param model 뷰에 전달할 데이터
     *              listResponseDTO - 게시판 리스트, 카테고리 리스트
     * @return 게시글 목록 페이지
     */
    @GetMapping("/list")
    public String findAll(SearchFilterDTO searchFilter, Model model){
        model.addAttribute("response", moduleServiceImpl.list(searchFilter));

        return "list";
    }

    /**
     * 게시글 상세 조회
     * @param id 조회하려는 게시글 ID
     * @param model 뷰에 전달할 데이터
     *              listResponseDTO - 게시판 리스트, 카테고리 리스트
     * @return 상세 조회 페이지
     */
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model){
        model.addAttribute("response", moduleServiceImpl.findById(id));

        return "detail";
    }

    /**
     * 게시글 수정 페이지
     * @param id 수정하려는 게시글 ID
     * @param model 뷰에 전달할 데이터
     *              listResponseDTO - 게시글 상세, 카테고리 리스트
     * @return 수정 페이지
     */
    @GetMapping("/update/{id}")
    public String loadUpdateData(@PathVariable("id") Long id, Model model){
        model.addAttribute("response", moduleServiceImpl.loadUpdateData(id));

        return "update";
    }

    /**
     * 게시글 수정 처리
     * @param boardDTO 수정된 게시글 정보
     * @param model 뷰에 전달할 데이터
     *              listResponseDTO - 게시글 상세
     * @return 수정된 게시글의 상세 페이지
     */
    @PostMapping("/update/{id}")
    public String updateById(BoardDTO boardDTO, Model model){
        model.addAttribute("response", moduleServiceImpl.updateById(boardDTO));

        return "detail";
    }

    /**
     * 게시글 삭제 처리
     * @param id 삭제하려는 게시글 ID
     * @return 게시글 목록 페이지로 리다이렉션
     */
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        moduleServiceImpl.delete(id);

        return "redirect:/board/list";
    }

    /**
     * 파일 다운로드 (새창)
     * @param uuid 파일고유이름
     * @return 
     */
//    @GetMapping("/download/{uuid}")
//    public ResponseEntity<Resource> download(@PathVariable String uuid){
//
//        return moduleServiceImpl.attachById(uuid);
//    }

}
