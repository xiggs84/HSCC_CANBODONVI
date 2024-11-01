package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucTuVietTat;
import vn.vnpt.service.dto.DanhMucTuVietTatDTO;

/**
 * Mapper for the entity {@link DanhMucTuVietTat} and its DTO {@link DanhMucTuVietTatDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucTuVietTatMapper extends EntityMapper<DanhMucTuVietTatDTO, DanhMucTuVietTat> {}
