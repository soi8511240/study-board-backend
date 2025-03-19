package kr.co.test.week2board.codes.service;

import kr.co.test.week2board.codes.model.CategoryResponseVO;
import kr.co.test.week2board.codes.repository.CodesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodesService {
    private final CodesRepository codesRepository;

    public List<CategoryResponseVO> categoryAll() {
        return codesRepository.categoryAll();
    }
}
