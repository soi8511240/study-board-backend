package kr.co.test.week2board.board.service;

import jakarta.validation.Valid;
import kr.co.test.week2board.board.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModuleService {

    private final BoardService boardService;
    BoardMapper mapper = BoardMapper.INSTANCE;

    /**
     * 리스트
     * @param listRequest
     * @return
     */
    public ListsResponse retrieveAll(ListsRequest listRequest) {
        ListsEntity listsEntity = mapper.toListsEntity(listRequest);

        return boardService.retrieveAll(listsEntity);
    }

    /**
     * 상세
     * @param id
     * @return
     */
    public DetailResponse retrieveDetail(Long id) {
        return boardService.retrieveDetail(id);
    }

    /**
     * 글 등록
     * @param insertRequest
     * @return
     */
    public long insertBoard(@Valid InsertRequest insertRequest) throws IOException {
        InsertEntity insertEntity = mapper.toInsertBoardEntity(insertRequest);

        return boardService.insertBoard(insertEntity);
    }

    /**
     * 암호 비교
     * @param id
     * @param password
     * @return
     */
    public boolean matchedPassword(long id, String password) {
        String storedPassword = boardService.matchedPassword(id);

        return Objects.equals(storedPassword,password);
    }

    /**
     * 글 수정
     * @param updateRequest
     * @return
     */
    public long updateById(@Valid UpdateRequest updateRequest) {
        return boardService.updateById(updateRequest);
    }

    public long removeById(Long id) {
        return boardService.removeById(id);
    }
}
