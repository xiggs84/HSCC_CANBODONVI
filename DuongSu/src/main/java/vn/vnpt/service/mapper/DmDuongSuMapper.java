package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DmDuongSu;
import vn.vnpt.service.dto.DmDuongSuDTO;

/**
 * Mapper for the entity {@link DmDuongSu} and its DTO {@link DmDuongSuDTO}.
 */
@Mapper(componentModel = "spring")
public interface DmDuongSuMapper extends EntityMapper<DmDuongSuDTO, DmDuongSu> {}
