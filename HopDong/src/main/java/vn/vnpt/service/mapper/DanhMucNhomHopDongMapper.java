package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucNhomHopDong;
import vn.vnpt.service.dto.DanhMucNhomHopDongDTO;

/**
 * Mapper for the entity {@link DanhMucNhomHopDong} and its DTO {@link DanhMucNhomHopDongDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucNhomHopDongMapper extends EntityMapper<DanhMucNhomHopDongDTO, DanhMucNhomHopDong> {}
