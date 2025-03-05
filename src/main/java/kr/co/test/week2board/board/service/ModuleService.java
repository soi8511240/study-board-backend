package kr.co.test.week2board.board.service;

import kr.co.test.week2board.board.model.ListsEntity;
import kr.co.test.week2board.board.model.ListsEntityMapper;
import kr.co.test.week2board.board.model.ListsRequestDTO;
import kr.co.test.week2board.board.model.ListsResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModuleService {

    private final BoardService boardService;

    /**
     * 게시판 리스트
     * @param listRequest
     * @return
     */
    public ListsResponseVO listAll(ListsRequestDTO listRequest) {
        ListsEntityMapper mapper = ListsEntityMapper.INSTANCE;
        ListsEntity listsEntity = mapper.toListsEntity(listRequest);

        return boardService.listAll(listsEntity);
    }
}
