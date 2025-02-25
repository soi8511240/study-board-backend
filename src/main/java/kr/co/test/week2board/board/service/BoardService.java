package kr.co.test.week2board.board.service;

import kr.co.test.week2board.board.dto.BoardDTO;
import kr.co.test.week2board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public String boardSave(BoardDTO boardDTO) {
        try{
            boardRepository.save(boardDTO);
        }catch (Exception e){
            log.error(e.getMessage());
            return "fail";
        }

        return "success";
    }

    public List<BoardDTO> findAll() {
        return boardRepository.findAll();
    }

    public void updateViewCnt(Long id) {
        boardRepository.updateViewCnt(id);
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }
}
