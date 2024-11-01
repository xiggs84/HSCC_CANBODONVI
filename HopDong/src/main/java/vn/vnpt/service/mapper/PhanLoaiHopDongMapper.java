package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.PhanLoaiHopDong;
import vn.vnpt.service.dto.PhanLoaiHopDongDTO;

/**
 * Mapper for the entity {@link PhanLoaiHopDong} and its DTO {@link PhanLoaiHopDongDTO}.
 */
@Mapper(componentModel = "spring")
public interface PhanLoaiHopDongMapper extends EntityMapper<PhanLoaiHopDongDTO, PhanLoaiHopDong> {}
