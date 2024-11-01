package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhSachHopDong;
import vn.vnpt.service.dto.DanhSachHopDongDTO;

/**
 * Mapper for the entity {@link DanhSachHopDong} and its DTO {@link DanhSachHopDongDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhSachHopDongMapper extends EntityMapper<DanhSachHopDongDTO, DanhSachHopDong> {}
