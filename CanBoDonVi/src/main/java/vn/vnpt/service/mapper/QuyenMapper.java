package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.Quyen;
import vn.vnpt.service.dto.QuyenDTO;

/**
 * Mapper for the entity {@link Quyen} and its DTO {@link QuyenDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuyenMapper extends EntityMapper<QuyenDTO, Quyen> {}
