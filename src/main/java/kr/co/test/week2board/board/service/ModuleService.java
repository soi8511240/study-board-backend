package kr.co.test.week2board.board.service;

import jakarta.annotation.Resource;
import kr.co.test.week2board.board.dto.BoardDTO;
import kr.co.test.week2board.board.dto.BoardResponseDTO;
import kr.co.test.week2board.board.dto.SearchFilterDTO;
import org.springframework.http.ResponseEntity;

public interface ModuleService {
    long             save(BoardDTO boardDTO);
    void             delete(Long id);
    BoardResponseDTO loadSaveData();
    BoardResponseDTO list(SearchFilterDTO searchFilter);
    BoardResponseDTO findById(Long id);
    BoardResponseDTO loadUpdateData(Long id);
    BoardResponseDTO updateById(BoardDTO boardDTO);

//    ResponseEntity<Resource> attachById(String uuid);
}