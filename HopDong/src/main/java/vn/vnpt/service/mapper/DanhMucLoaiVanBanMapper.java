package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiVanBan;
import vn.vnpt.service.dto.DanhMucLoaiVanBanDTO;

/**
 * Mapper for the entity {@link DanhMucLoaiVanBan} and its DTO {@link DanhMucLoaiVanBanDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucLoaiVanBanMapper extends EntityMapper<DanhMucLoaiVanBanDTO, DanhMucLoaiVanBan> {}
