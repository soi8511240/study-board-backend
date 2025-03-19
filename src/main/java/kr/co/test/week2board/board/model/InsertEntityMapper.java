package kr.co.test.week2board.board.model;

import kr.co.test.week2board.board.util.CommonUtil;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface InsertEntityMapper {
    InsertEntityMapper INSTANCE = Mappers.getMapper(InsertEntityMapper.class);

    // insertRequest DTO -> Entity 매핑
    InsertEntity toInsertBoardEntity(InsertRequestDTO insertRequestDTO);

    /**
     *  useYn, replyYn : Default 'Y' 설정
     *  createdAt, updatedAt, viewCnt, categoryName, attachYn 값 설정
     */
    @AfterMapping // or @BeforeMapping
    default void setDefault(InsertRequestDTO insertRequestDTO, @MappingTarget InsertEntity insertEntity) {
        insertEntity.setUseYn("Y");
        insertEntity.setReplyYn("N");
        insertEntity.setCreatedAt(CommonUtil.getToday());
        insertEntity.setUpdatedAt(CommonUtil.getToday());
        insertEntity.setViewCnt(0L);
        if (insertRequestDTO.getAttachFiles().get(0).isEmpty()){
            insertEntity.setAttachYn("N");
            return;
        }
        insertEntity.setAttachYn("Y");
    }
}
