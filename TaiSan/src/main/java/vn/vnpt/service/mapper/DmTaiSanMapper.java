package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DmTaiSan;
import vn.vnpt.service.dto.DmTaiSanDTO;

/**
 * Mapper for the entity {@link DmTaiSan} and its DTO {@link DmTaiSanDTO}.
 */
@Mapper(componentModel = "spring")
public interface DmTaiSanMapper extends EntityMapper<DmTaiSanDTO, DmTaiSan> {}
