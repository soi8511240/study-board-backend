package kr.co.test.week2board.board.service;

import jakarta.validation.Valid;
import kr.co.test.week2board.Constants;
import kr.co.test.week2board.board.model.*;
import kr.co.test.week2board.board.repository.BoardApiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardApiRepository boardApiRepository;
    BoardMapper mapper = BoardMapper.INSTANCE;

    /**
     * 리스트 서비스
     * @param listsEntity
     */
    public ListsResponse retrieveAll(ListsEntity listsEntity){
        List<ListsModel> boardModelList = boardApiRepository.retrieveAll(listsEntity);
        long totalCnt = boardApiRepository.totalCnt(listsEntity);

        return new ListsResponse(totalCnt, boardModelList);
    }

    /**
     * 상세 서비스
     * @param id
     */
    public DetailResponse retrieveDetail(Long id) {
        boardApiRepository.updateViewCnt(id);
        BoardModel boardModel = null;
        List<AttachModel> attachList = null;

        boardModel = boardApiRepository.retrieveDetail(id); // null 체크를 해야하는데.. Optional<>
//        if (boardModel.getAttachYn().equals("Y")) {
        if ("Y".equals(boardModel.getAttachYn())) {
            attachList = boardApiRepository.retrieveAttachList(id);
        }

        return mapper.toDetailResponseVO(boardModel, attachList);
    }

    /**
     * 글 등록 서비스
     * @param insertEntity
     */
    public long insertBoard(InsertEntity insertEntity) throws IOException {

        boardApiRepository.insert(insertEntity);

        long id = insertEntity.getId();

        log.info("id: {}", id);

        if (insertEntity.getAttachYn().equals("N")) {
            log.info("첨부파일 없음");

            return id;
        }

        log.info("첨부파일 있음");
        // 첨부파일 있을 때

        List<MultipartFile> attachFiles = insertEntity.getAttachFiles(); // Attach 파일 리스트 가져오기
        for (int i = 0; i < attachFiles.size(); i++) {
            MultipartFile attachFile = attachFiles.get(i); // i번째 파일 가져오기
            insertFile(attachFile, i , id); // 인덱스를 전달
        }

        // reply 검색

        return id;


        //비교 작업
        // 없는거 파일 지워.
        // resquest
        // id: file명
        // [1],[2],[3] // attachArray {index:1,fileName:''} // Object
        // [1],[3] // {fileName:'', strageName:'', index:1} // Multi
//        return boardApiRepository.insert(insertEntity);
    }

    private void insertFile(MultipartFile file, int index, long id) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String originalFileName = file.getOriginalFilename();

        // 파일 확장자 추출
        assert originalFileName != null;
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String fileName = file.getOriginalFilename();
        String storeFileName = uuid + "_" + System.currentTimeMillis() + extension;

        // 첨부파일 경로
        String filePath = Constants.ATTACH_PATH + storeFileName;

        file.transferTo(new File(filePath));

        AttachModel attachModel = new AttachModel();
        attachModel.setBoardId(id);
        attachModel.setOriginalFileName(fileName);
        attachModel.setStoredFileName(storeFileName);
        attachModel.setFileUri(Constants.ATTACH_URL);
        attachModel.setOrderBy(index);
        attachModel.setSize(file.getSize());

        boardApiRepository.saveFile(attachModel);
    };

    /**
     * 비밀번호 비교 서비스
     * @param id
     */
    public isCurrentPassword matchedPassword(long id) {
        return boardApiRepository.matchedPassword(id);
    }

    /**
     * 글 수정 서비스
     * @param updateRequest
     */
    public long updateById(@Valid UpdateRequest updateRequest) {
        return boardApiRepository.updateById(updateRequest);
    }

    /**
     * 글 삭제 서비스
     * @param id
     */
    public long removeById(Long id) {
//        boardApiRepository.removeByIdFromAttach(id);
//        boardApiRepository.removeByIdFromReply(id);
        return boardApiRepository.removeById(id);
    }


}
