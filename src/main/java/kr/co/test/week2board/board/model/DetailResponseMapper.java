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
public interface DetailResponseMapper {
    DetailResponseMapper INSTANCE = Mappers.getMapper(DetailResponseMapper.class);

//    @Mapping(source="boardModel", target="")
    @Mapping(source="attachList", target="attachFiles")
    DetailResponseVO toDetailResponseVO(BoardModel boardModel, List<AttachDTO> attachList);

}
