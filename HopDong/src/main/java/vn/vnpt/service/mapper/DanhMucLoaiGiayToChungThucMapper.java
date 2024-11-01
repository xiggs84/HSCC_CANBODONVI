package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiGiayToChungThuc;
import vn.vnpt.service.dto.DanhMucLoaiGiayToChungThucDTO;

/**
 * Mapper for the entity {@link DanhMucLoaiGiayToChungThuc} and its DTO {@link DanhMucLoaiGiayToChungThucDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucLoaiGiayToChungThucMapper extends EntityMapper<DanhMucLoaiGiayToChungThucDTO, DanhMucLoaiGiayToChungThuc> {}
