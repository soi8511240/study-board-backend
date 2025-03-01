package kr.co.test.week2board.board.service;

import jakarta.annotation.Resource;
import kr.co.test.week2board.Constants;
import kr.co.test.week2board.board.dto.AttachDTO;
import kr.co.test.week2board.board.dto.BoardDTO;
import kr.co.test.week2board.board.dto.BoardResponseDTO;
import kr.co.test.week2board.board.dto.SearchFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {
    private final BoardService boardService;

    /**
     * 저장 api
     * @return 게시글 id
     */
    @Override
    public long save(BoardDTO boardDTO) {
        long id = 0;
        try {
            id = boardService.save(boardDTO);
        }catch (IOException e){
            log.error(e.getMessage());
        }
        return id;
    }

    /**
     * 등록 페이지 
     * @return categoryList
     */
    @Override
    public BoardResponseDTO loadSaveData() {
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();
        try {
            boardResponseDTO.setCategory(boardService.categoryAll());
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return boardResponseDTO;
    }

    /**
     * 글목록
     * @return BoardResponseDTO
     */
    @Override
    public BoardResponseDTO list(SearchFilterDTO searchFilter) {

        SearchFilterDTO filters = new SearchFilterDTO(searchFilter);
        // Todo : 안함 매퍼에서 처리로

        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();

        try {
            boardResponseDTO.setTotalCnt(boardService.countBoards(filters));
            boardResponseDTO.setBoard(boardService.findAll(filters));
            boardResponseDTO.setCategory(boardService.categoryAll());
            boardResponseDTO.setSearch(filters);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return boardResponseDTO;
    }

    @Override
    public BoardResponseDTO findById(Long id) {
        try {
            boardService.updateViewCnt(id); // 조회수 증가
            return wrapBoardResponseWithCategory(boardService.findById(id), false);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("게시글 조회 중 문제가 발생하였습니다.", e);
        }
    }

    @Override
    public BoardResponseDTO loadUpdateData(Long id) {
        try {
            return wrapBoardResponseWithCategory(boardService.findById(id), true);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public BoardResponseDTO updateById(BoardDTO boardDTO) {
        List<BoardDTO> boardDTOList = new ArrayList<>();
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();

        Long id = boardDTO.getId();

        try {
            boardService.update(boardDTO);
            boardDTOList.add(boardService.findById(id));
            boardResponseDTO.setBoard(boardDTOList);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("게시글 수정 중 문제가 발생하였습니다.", e);
        }

        return boardResponseDTO;
    }

    @Override
    public void delete(Long id) {
        boardService.delete(id);
    }

//    @Override
//    public ResponseEntity<Resource> attachById(String uuid) {
//        try{
//            AttachDTO attach = boardService.attachById(uuid);
//            String attachName = attach.getOriginalFileName();
//            String attachPath = Constants.ATTACH_PATH;
//
//            Resource resource = new UrlResource(Paths.get(attachPath).toUri());
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + UriUtils.encode(filename, "UTF-8") + "\"")
//                    .body(resource);
//        }catch (Exception e){
//            return null;
//        }
//    }

    private BoardResponseDTO wrapBoardResponseWithCategory(BoardDTO board, boolean includeCategory) {
        BoardResponseDTO response = new BoardResponseDTO();
        List<BoardDTO> boardList = new ArrayList<>();

        boardList.add(board);
        response.setBoard(boardList);
        if (board.getAttachYn().equals("Y")) {
            response.setAttach(boardService.getAttachList(board.getId()));
        }
        if (includeCategory) {
            response.setCategory(boardService.categoryAll());
        }
        return response;
    }

}
