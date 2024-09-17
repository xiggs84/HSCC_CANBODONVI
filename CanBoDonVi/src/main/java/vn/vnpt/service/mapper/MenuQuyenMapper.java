package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.MenuQuyen;
import vn.vnpt.service.dto.MenuQuyenDTO;

/**
 * Mapper for the entity {@link MenuQuyen} and its DTO {@link MenuQuyenDTO}.
 */
@Mapper(componentModel = "spring")
public interface MenuQuyenMapper extends EntityMapper<MenuQuyenDTO, MenuQuyen> {}
