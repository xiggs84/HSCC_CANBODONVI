package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucHuyen;
import vn.vnpt.service.dto.DanhMucHuyenDTO;

/**
 * Mapper for the entity {@link DanhMucHuyen} and its DTO {@link DanhMucHuyenDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucHuyenMapper extends EntityMapper<DanhMucHuyenDTO, DanhMucHuyen> {}
