package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucVaiTro;
import vn.vnpt.service.dto.DanhMucVaiTroDTO;

/**
 * Mapper for the entity {@link DanhMucVaiTro} and its DTO {@link DanhMucVaiTroDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucVaiTroMapper extends EntityMapper<DanhMucVaiTroDTO, DanhMucVaiTro> {}
