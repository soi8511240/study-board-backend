package kr.co.test.week2board.board.service;

import kr.co.test.week2board.board.model.ListsEntity;
import kr.co.test.week2board.board.model.ListsRequestDTO;
import kr.co.test.week2board.board.model.ListsResponseVO;
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
    public ListsResponseVO listAll(ListsRequestDTO listRequest) {
        ListsEntity listsEntity = new ListsEntity();

        listsEntity.setCategoryId(listRequest.getCategoryId());
        listsEntity.setKeyword(listRequest.getKeyword());
        listsEntity.setToDt(listRequest.getToDt());
        listsEntity.setFromDt(listRequest.getFromDt());
        listsEntity.setCurrentPage(listRequest.getCurrentPage());

        return boardService.listAll(listsEntity);
    }
}
