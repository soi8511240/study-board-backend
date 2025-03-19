package kr.co.test.week2board.codes.repository;

import kr.co.test.week2board.codes.model.CategoryResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CodesRepository {
    private final SqlSessionTemplate sql;

    public List<CategoryResponseVO> categoryAll() {
        log.info("CodesRepository.categoryAll()");
        return sql.selectList("Codes.categoryAll");
    }
}
