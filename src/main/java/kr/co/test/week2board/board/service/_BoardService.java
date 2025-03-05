package kr.co.test.week2board.board.service;

import kr.co.test.week2board.Constants;
import kr.co.test.week2board.board.model.*;
import kr.co.test.week2board.board.repository.BoardApiRepository;
import kr.co.test.week2board.board.repository._BoardRepository;
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
public class _BoardService {
    private final _BoardRepository boardRepository;
    private final BoardApiRepository boardApiRepository;
    private final BinaryAttach binaryAttach;

    public Long save(_BoardDTO boardDTO) throws IOException {
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

                _AttachDTO attachDTO = new _AttachDTO();
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

    public List<_BoardDTO> findAll(_SearchFilterDTO searchFilterDTO) {
        return boardRepository.findAll(searchFilterDTO);
    }

    public void updateViewCnt(Long id) {
        boardRepository.updateViewCnt(id);
    }

    public _BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    public List<_CategoryDTO> categoryAll() {
        return boardRepository.categoryAll();
    }

    public void update(_BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public Long countBoards(_SearchFilterDTO filters) {
        return boardRepository.countBoards(filters);
    }

    public List<_AttachDTO> getAttachList(Long id) {
        return boardRepository.getAttachList(id);
    }

    public _AttachDTO attachById(String uuid) {
        return boardRepository.attachById(uuid);
    }

    public ListsResponseVO retrieveAll(ListsEntity listsEntity) {
        return null;
    }
}
