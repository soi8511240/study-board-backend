package kr.co.test.week2board.board.repository;


import kr.co.test.week2board.board.model.ListRequestDTO;
import kr.co.test.week2board.board.model.ListResponseVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardApiRepository {

    private final SqlSessionTemplate sql;

    public List<ListResponseVO> findAll(ListRequestDTO listRequest) {
        return sql.selectList("BoardApi.findAll", listRequest);
    }
}
