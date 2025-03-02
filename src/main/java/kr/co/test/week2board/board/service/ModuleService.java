package kr.co.test.week2board.board.service;

import kr.co.test.week2board.Constants;
import kr.co.test.week2board.board.model.ListRequestDTO;
import kr.co.test.week2board.board.model.ListResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final BoardService boardService;

    /**
     * 게시판 리스트
     * @param listRequest
     * @return
     */
    public List<ListResponseVO> listAll(ListRequestDTO listRequest) {
        return boardService.listAll(listRequest);
    }
}
