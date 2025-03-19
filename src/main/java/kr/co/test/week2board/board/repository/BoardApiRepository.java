package kr.co.test.week2board.board.repository;


import jakarta.validation.Valid;
import kr.co.test.week2board.board.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardApiRepository {

    private final SqlSessionTemplate sql;

    /**
     * 조회 카운트
     * @param listsEntity
     * @return
     */
    public Long totalCnt(ListsEntity listsEntity) { return sql.selectOne("BoardApi.countAll", listsEntity); }

    /**
     * 글 리스트 조회 
     * @param listsEntity
     * @return
     */
    public List<ListsBoardVO> retrieveAll(ListsEntity listsEntity) { return sql.selectList("BoardApi.findAll", listsEntity); }

    /**
     * 게시글 상세
     * @param id
     * @return
     */
    public BoardModel retrieveDetail(Long id) {
        return sql.selectOne("BoardApi.findById", id);
    }

    /**
     * 첨부파일리스트 가져오기
     * @param id
     * @return
     */
    public List<AttachDTO> retrieveAttachList(Long id) {
        return sql.selectList("BoardApi.getAttachList", id);
    }

    /**
     * 조회수 증가
     * @param id
     * @return
     */
    public void updateViewCnt(Long id) {
        sql.update("BoardApi.updateViewCnt", id);
    }

    /**
     * 등록
     * @param insertEntity
     * @return
     */
    public long insert(InsertEntity insertEntity) {
        return sql.insert("BoardApi.save", insertEntity);
    }

    /**
     * 암호 가져오기
     * @param id
     * @return
     */
    public PasswordVO matchedPassword(long id) {
        return sql.selectOne("BoardApi.findPasswordById", id);
    }

    /**
     * 글 수정
     * @param updateRequestDTO
     * @return
     */
    public long updateById(@Valid UpdateRequestDTO updateRequestDTO) {
        return sql.update("BoardApi.updateById", updateRequestDTO);
    }

    /**
     * 글 삭제
     * @param id
     * @return
     */
    public long removeById(Long id){
        return sql.update("BoardApi.removeById", id);
    }

    /**
     * 파일 저장
     * @param attachDTO
     * @return
     */
    public void saveFile(AttachDTO attachDTO) {
        sql.insert("BoardApi.saveFile", attachDTO);
    }

}
