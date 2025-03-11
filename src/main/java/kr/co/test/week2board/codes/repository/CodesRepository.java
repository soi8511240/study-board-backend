package kr.co.test.week2board.codes.repository;

import kr.co.test.week2board.codes.dto.CategoryDTO2;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CodesRepository {
    private final SqlSessionTemplate sql;

//    public List<CategoryDTO2> categoryAll() {
//        return sql.selectList("Codes.categoryAll");
//    }
}
