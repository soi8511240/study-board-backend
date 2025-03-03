package kr.co.test.week2board.board.repository;


import kr.co.test.week2board.board.model.ListsBoardVO;
import kr.co.test.week2board.board.model.ListsEntity;
import kr.co.test.week2board.board.model.ListsResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardApiRepository {

    private final SqlSessionTemplate sql;


    public Long totalCnt(ListsEntity listsEntity) {
        return sql.selectOne("BoardApi.countAll", listsEntity);
    }

    public List<ListsBoardVO> findAll(ListsEntity listsEntity) {
        return sql.selectList("BoardApi.findAll", listsEntity);
    }

}
