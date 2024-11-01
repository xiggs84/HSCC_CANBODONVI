package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.LoaiHopDongCongChung;
import vn.vnpt.service.dto.LoaiHopDongCongChungDTO;

/**
 * Mapper for the entity {@link LoaiHopDongCongChung} and its DTO {@link LoaiHopDongCongChungDTO}.
 */
@Mapper(componentModel = "spring")
public interface LoaiHopDongCongChungMapper extends EntityMapper<LoaiHopDongCongChungDTO, LoaiHopDongCongChung> {}
