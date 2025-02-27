package kr.co.test.week2board.board.repository;

import kr.co.test.week2board.board.dto.AttachDTO;
import kr.co.test.week2board.board.dto.BoardDTO;
import kr.co.test.week2board.board.dto.CategoryDTO;
import kr.co.test.week2board.board.dto.SearchFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final SqlSessionTemplate sql;

    public int save(BoardDTO boardDTO) {
        return sql.insert("Board.save", boardDTO);
    }

    public List<BoardDTO> findAll(SearchFilterDTO searchFilterDTO) {
        return sql.selectList("Board.findAll", searchFilterDTO);
    }

    public void updateViewCnt(Long id) {
        sql.update("Board.updateViewCnt", id);
    }

    public BoardDTO findById(Long id) {
        return sql.selectOne("Board.findById", id);
    }

    public List<CategoryDTO> categoryAll() {
        return sql.selectList("Board.categoryAll");
    }

    public void update(BoardDTO boardDTO) {
        sql.update("Board.update", boardDTO);
    }

    public void delete(Long id) {
        sql.update("Board.remove", id);
    }

    public void saveFile(AttachDTO attachDTO) {
        sql.insert("Board.saveFile", attachDTO);
    }

    public Long countBoards(SearchFilterDTO filters) {
        return sql.selectOne("Board.countBoards", filters);
    }
}
