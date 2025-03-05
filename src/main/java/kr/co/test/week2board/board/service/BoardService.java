package kr.co.test.week2board.board.service;

import kr.co.test.week2board.board.model.DetailResponseVO;
import kr.co.test.week2board.board.model.ListsBoardVO;
import kr.co.test.week2board.board.model.ListsEntity;
import kr.co.test.week2board.board.model.ListsResponseVO;
import kr.co.test.week2board.board.repository.BoardApiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardApiRepository boardApiRepository;

    public ListsResponseVO retrieveAll(ListsEntity listsEntity) {
        List<ListsBoardVO> listsBoardVO = boardApiRepository.retrieveAll(listsEntity);
        long totalCnt = boardApiRepository.totalCnt(listsEntity);

        ListsResponseVO listsResponseVO = new ListsResponseVO(totalCnt, listsBoardVO);

        return listsResponseVO;
    }

    public DetailResponseVO retrieveDetail(Long id) {
        return boardApiRepository.retrieveDetail(id);
    }

}
