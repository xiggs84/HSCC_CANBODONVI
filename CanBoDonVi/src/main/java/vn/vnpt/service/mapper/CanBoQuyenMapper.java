package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.CanBoQuyen;
import vn.vnpt.service.dto.CanBoQuyenDTO;

/**
 * Mapper for the entity {@link CanBoQuyen} and its DTO {@link CanBoQuyenDTO}.
 */
@Mapper(componentModel = "spring")
public interface CanBoQuyenMapper extends EntityMapper<CanBoQuyenDTO, CanBoQuyen> {}
