package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiHopDong;
import vn.vnpt.service.dto.DanhMucLoaiHopDongDTO;

/**
 * Mapper for the entity {@link DanhMucLoaiHopDong} and its DTO {@link DanhMucLoaiHopDongDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucLoaiHopDongMapper extends EntityMapper<DanhMucLoaiHopDongDTO, DanhMucLoaiHopDong> {}
