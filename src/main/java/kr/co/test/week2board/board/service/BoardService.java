package kr.co.test.week2board.board.service;

import kr.co.test.week2board.board.dto.BoardDTO;
import kr.co.test.week2board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public String save(BoardDTO boardDTO) {
        boardRepository.save(boardDTO);

        return "success";
    }

    public List<BoardDTO> findAll() {

        return boardRepository.findAll();
    }
}
