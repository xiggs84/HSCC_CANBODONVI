package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.HopDongCongChung;
import vn.vnpt.service.dto.HopDongCongChungDTO;

/**
 * Mapper for the entity {@link HopDongCongChung} and its DTO {@link HopDongCongChungDTO}.
 */
@Mapper(componentModel = "spring")
public interface HopDongCongChungMapper extends EntityMapper<HopDongCongChungDTO, HopDongCongChung> {}
