package kr.co.test.week2board.board.model;

import kr.co.test.week2board.board.util.CommonUtil;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface BoardModelMapper {
    BoardModelMapper INSTANCE = Mappers.getMapper(BoardModelMapper.class);

    /**
     * BoardModel, AttachList 로 DetailResponse객체 생성
     * @param boardModel
     * @param attachList
     */
    //    @Mapping(source="boardModel", target="")
    @Mapping(source="attachList", target="attachFiles")
    DetailResponseVO toDetailResponseVO(BoardModel boardModel, List<AttachModel> attachList);

    /**
     * ListsRequestDTO -> ListsEntity 매핑
     * @param listRequest
     * @return
     */
    ListsEntity toListsEntity(ListsRequestDTO listRequest);
    @AfterMapping // or @BeforeMapping
    default void calcOffset(ListsRequestDTO listRequest, @MappingTarget ListsEntity listsEntity) {
        int offset = Math.max((listsEntity.getCurrentPage()-1)*listsEntity.getFetchCnt(), 0);

        listsEntity.setOffset(offset);
    }

    /**
     * insertRequest -> InsertEntity 매핑
     * @param insertRequestDTO
     * @return
     */
    InsertEntity toInsertBoardEntity(InsertRequestDTO insertRequestDTO);
    @AfterMapping // or @BeforeMapping
    default void setDefault(InsertRequestDTO insertRequestDTO, @MappingTarget InsertEntity insertEntity) {
        insertEntity.setUseYn("Y");
        insertEntity.setReplyYn("N");
        insertEntity.setCreatedAt(CommonUtil.getToday());
        insertEntity.setUpdatedAt(CommonUtil.getToday());
        insertEntity.setViewCnt(0L);
        if (insertRequestDTO.getAttachFiles() == null || insertRequestDTO.getAttachFiles().isEmpty()
                || insertRequestDTO.getAttachFiles().get(0).isEmpty()) {
            insertEntity.setAttachYn("N");
            return;
        }
        insertEntity.setAttachYn("Y");
    }
}
