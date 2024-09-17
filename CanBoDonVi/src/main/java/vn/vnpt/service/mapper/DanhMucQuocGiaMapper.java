package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucQuocGia;
import vn.vnpt.service.dto.DanhMucQuocGiaDTO;

/**
 * Mapper for the entity {@link DanhMucQuocGia} and its DTO {@link DanhMucQuocGiaDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucQuocGiaMapper extends EntityMapper<DanhMucQuocGiaDTO, DanhMucQuocGia> {}
