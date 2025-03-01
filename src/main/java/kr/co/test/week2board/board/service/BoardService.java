package kr.co.test.week2board.board.service;

import kr.co.test.week2board.Constants;
import kr.co.test.week2board.board.dto.AttachDTO;
import kr.co.test.week2board.board.dto.BoardDTO;
import kr.co.test.week2board.board.dto.CategoryDTO;
import kr.co.test.week2board.board.dto.SearchFilterDTO;
import kr.co.test.week2board.board.repository.BoardRepository;
import kr.co.test.week2board.board.util.BinaryAttach;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BinaryAttach binaryAttach;

    public Long save(BoardDTO boardDTO) throws IOException {
        long id; // sql result 카운터
        if (boardDTO.getAttachFiles().get(0).isEmpty()) {
            log.info("첨부파일 없음");
            // 첨부파일 없을 때
            boardDTO.setAttachYn("N");
            id = boardRepository.save(boardDTO);
        }else{
            log.info("첨부파일 있음");
            // 첨부파일 있을 때
            boardDTO.setAttachYn("Y");
            id = boardRepository.save(boardDTO);

            log.info("log key:{}",boardDTO);
            for (MultipartFile attachFile : boardDTO.getAttachFiles()){
                String fileName = attachFile.getOriginalFilename();
                String storeFileName = System.currentTimeMillis() + fileName; // Todo: 더 다양한 케이스 ,uuid 추천

                AttachDTO attachDTO = new AttachDTO();
                attachDTO.setOriginalFileName(fileName);
                attachDTO.setStoredFileName(storeFileName);
                attachDTO.setBoardId(boardDTO.getId());

                // 첨부파일 경로
                String filePath = Constants.IMAGE_PATH + storeFileName;
                log.info(filePath);

                attachFile.transferTo(new File(filePath));
                boardRepository.saveFile(attachDTO);
            }
        }
        return id;
    }

    public List<BoardDTO> findAll(SearchFilterDTO searchFilterDTO) {
        return boardRepository.findAll(searchFilterDTO);
    }

    public void updateViewCnt(Long id) {
        boardRepository.updateViewCnt(id);
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    public List<CategoryDTO> categoryAll() {
        return boardRepository.categoryAll();
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public Long countBoards(SearchFilterDTO filters) {
        return boardRepository.countBoards(filters);
    }

    public List<AttachDTO> getAttachList(Long id) {
        return boardRepository.getAttachList(id);
    }

    public AttachDTO attachById(String uuid) {
        return boardRepository.attachById(uuid);
    }
}
