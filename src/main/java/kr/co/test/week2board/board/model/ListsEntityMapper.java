package kr.co.test.week2board.board.model;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ListsEntityMapper {
    ListsEntityMapper INSTANCE = Mappers.getMapper(ListsEntityMapper.class);

    // RequestDto -> MessageBodyDto 매핑
    ListsEntity toListsEntity(ListsRequestDTO listRequest);

    @AfterMapping // or @BeforeMapping
    default void calcOffset(ListsRequestDTO ignoredListRequest, @MappingTarget ListsEntity listsEntity) {
        int offset = Math.max((listsEntity.getCurrentPage()-1)*listsEntity.getFetchCnt(), 0);

        listsEntity.setOffset(offset);
    }
}
