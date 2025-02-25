package kr.co.test.week2board.codes.service;

import kr.co.test.week2board.codes.dto.CategoryDTO;
import kr.co.test.week2board.codes.repository.CodesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodesService {
    private final CodesRepository codesRepository;

    public List<CategoryDTO> categoryAll() {
        return codesRepository.categoryAll();
    }
}
