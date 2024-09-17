package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.LoaiDonVi;
import vn.vnpt.service.dto.LoaiDonViDTO;

/**
 * Mapper for the entity {@link LoaiDonVi} and its DTO {@link LoaiDonViDTO}.
 */
@Mapper(componentModel = "spring")
public interface LoaiDonViMapper extends EntityMapper<LoaiDonViDTO, LoaiDonVi> {}
