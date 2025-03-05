package kr.co.test.week2board.board.service;

import kr.co.test.week2board.board.model._BoardDTO;
import kr.co.test.week2board.board.model._BoardResponseDTO;
import kr.co.test.week2board.board.model._SearchFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class _ModuleServiceImpl {
    private final _BoardService boardService;

    /**
     * 저장 api
     * @return 게시글 id
     */
    public long save(_BoardDTO boardDTO) {
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
    public _BoardResponseDTO loadSaveData() {
        _BoardResponseDTO boardResponseDTO = new _BoardResponseDTO();
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
    public _BoardResponseDTO list(_SearchFilterDTO searchFilter) {

        _SearchFilterDTO filters = new _SearchFilterDTO(searchFilter);
        // Todo : 안함 매퍼에서 처리로

        _BoardResponseDTO boardResponseDTO = new _BoardResponseDTO();

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

    public _BoardResponseDTO findById(Long id) {
        try {
            boardService.updateViewCnt(id); // 조회수 증가
            return wrapBoardResponseWithCategory(boardService.findById(id), false);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("게시글 조회 중 문제가 발생하였습니다.", e);
        }
    }

    public _BoardResponseDTO loadUpdateData(Long id) {
        try {
            return wrapBoardResponseWithCategory(boardService.findById(id), true);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    public _BoardResponseDTO updateById(_BoardDTO boardDTO) {
        List<_BoardDTO> boardDTOList = new ArrayList<>();
        _BoardResponseDTO boardResponseDTO = new _BoardResponseDTO();

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

    private _BoardResponseDTO wrapBoardResponseWithCategory(_BoardDTO board, boolean includeCategory) {
        _BoardResponseDTO response = new _BoardResponseDTO();
        List<_BoardDTO> boardList = new ArrayList<>();

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
