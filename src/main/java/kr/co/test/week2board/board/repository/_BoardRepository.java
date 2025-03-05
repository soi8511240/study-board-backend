package kr.co.test.week2board.board.repository;

import kr.co.test.week2board.board.model._AttachDTO;
import kr.co.test.week2board.board.model._BoardDTO;
import kr.co.test.week2board.board.model._CategoryDTO;
import kr.co.test.week2board.board.model._SearchFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class _BoardRepository {

    private final SqlSessionTemplate sql;

    public int save(_BoardDTO boardDTO) {
        return sql.insert("Board.save", boardDTO);
    }

    public List<_BoardDTO> findAll(_SearchFilterDTO searchFilterDTO) {
        return sql.selectList("Board.findAll", searchFilterDTO);
    }

    public void updateViewCnt(Long id) {
        sql.update("Board.updateViewCnt", id);
    }

    public _BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    public List<_CategoryDTO> categoryAll() {
        return sql.selectList("Board.categoryAll");
    }

    public void update(_BoardDTO boardDTO) {
        sql.update("Board.update", boardDTO);
    }

    public void delete(Long id) {
        sql.update("Board.remove", id);
    }

    public void saveFile(_AttachDTO attachDTO) {
        sql.insert("Board.saveFile", attachDTO);
    }

    public Long countBoards(_SearchFilterDTO filters) {
        return sql.selectOne("Board.countBoards", filters);
    }

    public List<_AttachDTO> getAttachList(Long id) {
        return sql.selectList("Board.attachAll", id);
    }

    public _AttachDTO attachById(String uuid) {
        return sql.selectOne("Board.attachById", uuid);
    }
}
