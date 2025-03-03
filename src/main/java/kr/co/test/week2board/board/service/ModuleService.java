package kr.co.test.week2board.board.service;

import kr.co.test.week2board.Constants;
import kr.co.test.week2board.board.model.ListEntity;
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
        ListEntity listEntity = new ListEntity();

        listEntity.setCategoryId( listRequest.getCategoryId());
        listEntity.setKeyword( listRequest.getKeyword());
        listEntity.setToDt(listRequest.getToDt());
        listEntity.setFromDt(listRequest.getFromDt());
        listEntity.setCurrentPage(listRequest.getCurrentPage());

        return boardService.listAll(listEntity);
    }
}
