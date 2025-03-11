package kr.co.test.week2board.board.service;

import jakarta.validation.Valid;
import kr.co.test.week2board.board.model.*;
import kr.co.test.week2board.board.repository.BoardApiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardApiRepository boardApiRepository;

    /**
     * 리스트 서비스
     * @param listsEntity
     * @return
     */
    public ListsResponseVO retrieveAll(ListsEntity listsEntity) {
        List<ListsBoardVO> listsBoardVO = boardApiRepository.retrieveAll(listsEntity);
        long totalCnt = boardApiRepository.totalCnt(listsEntity);

        ListsResponseVO listsResponseVO = new ListsResponseVO(totalCnt, listsBoardVO);

        return listsResponseVO;
    }

    /**
     * 상세 서비스
     * @param id
     * @return
     */
    public DetailResponseVO retrieveDetail(Long id) {
        return boardApiRepository.retrieveDetail(id);
    }

    /**
     * 글 등록 서비스
     * @param insertEntity
     * @return
     */
    public long insertBoard(InsertEntity insertEntity) {
        return boardApiRepository.insert(insertEntity);
    }

    /**
     * 비밀번호 비교 서비스
     * @param id
     * @return
     */
    public PasswordVO matchedPassword(long id) {
        return boardApiRepository.matchedPassword(id);
    }

    /**
     * 글 수정 서비스
     * @param updateRequestDTO
     * @return
     */
    public long updateById(@Valid UpdateRequestDTO updateRequestDTO) {
        return boardApiRepository.updateById(updateRequestDTO);
    }

    /**
     * 글 삭제 서비스
     * @param id
     * @return
     */
    public long removeById(Long id) {
        return boardApiRepository.removeById(id);
    }


//        public Long save(_BoardDTO boardDTO) throws IOException {
//            long id; // sql result 카운터
//            if (boardDTO.getAttachFiles().get(0).isEmpty()) {
//                log.info("첨부파일 없음");
//                // 첨부파일 없을 때
//                boardDTO.setAttachYn("N");
//                id = boardRepository.save(boardDTO);
//            }else{
//                log.info("첨부파일 있음");
//                // 첨부파일 있을 때
//                boardDTO.setAttachYn("Y");
//                id = boardRepository.save(boardDTO);
//
//                log.info("log key:{}",boardDTO);
//                for (MultipartFile attachFile : boardDTO.getAttachFiles()){
//                    String fileName = attachFile.getOriginalFilename();
//                    String storeFileName = System.currentTimeMillis() + fileName; // Todo: 더 다양한 케이스 ,uuid 추천
//
//                    _AttachDTO attachDTO = new _AttachDTO();
//                    attachDTO.setOriginalFileName(fileName);
//                    attachDTO.setStoredFileName(storeFileName);
//                    attachDTO.setBoardId(boardDTO.getId());
//
//                    // 첨부파일 경로
//                    String filePath = Constants.IMAGE_PATH + storeFileName;
//                    log.info(filePath);
//
//                    attachFile.transferTo(new File(filePath));
//                    boardRepository.saveFile(attachDTO);
//                }
//            }
//            return id;
//        }
}
