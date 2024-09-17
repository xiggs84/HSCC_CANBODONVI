package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.NhiemVu;
import vn.vnpt.service.dto.NhiemVuDTO;

/**
 * Mapper for the entity {@link NhiemVu} and its DTO {@link NhiemVuDTO}.
 */
@Mapper(componentModel = "spring")
public interface NhiemVuMapper extends EntityMapper<NhiemVuDTO, NhiemVu> {}
