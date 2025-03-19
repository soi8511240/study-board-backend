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

    /**
     * 리스트
     * @param listRequest
     * @return
     */
    public ListsResponseVO retrieveAll(ListsRequestDTO listRequest) {
        ListsEntityMapper mapper = ListsEntityMapper.INSTANCE;
        ListsEntity listsEntity = mapper.toListsEntity(listRequest);

        return boardService.retrieveAll(listsEntity);
    }

    /**
     * 상세
     * @param id
     * @return
     */
    public DetailResponseVO retrieveDetail(Long id) {
        return boardService.retrieveDetail(id);
    }

    /**
     * 글 등록
     * @param insertRequestDTO
     * @return
     */
    public long insertBoard(@Valid InsertRequestDTO insertRequestDTO) throws IOException {
        InsertEntityMapper mapper = InsertEntityMapper.INSTANCE;
        InsertEntity insertEntity = mapper.toInsertBoardEntity(insertRequestDTO);

        return boardService.insertBoard(insertEntity);
    }

    /**
     * 암호 비교
     * @param id
     * @param password
     * @return
     */
    public boolean matchedPassword(long id, String password) {
        String storedPassword = boardService.matchedPassword(id).getPassword();

        return Objects.equals(storedPassword,password);
    }

    /**
     * 글 수정
     * @param updateRequestDTO
     * @return
     */
    public long updateById(@Valid UpdateRequestDTO updateRequestDTO) {
        return boardService.updateById(updateRequestDTO);
    }

    public long removeById(Long id) {
        return boardService.removeById(id);
    }
}
