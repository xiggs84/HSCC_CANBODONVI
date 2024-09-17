package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DmXaTmp;
import vn.vnpt.service.dto.DmXaTmpDTO;

/**
 * Mapper for the entity {@link DmXaTmp} and its DTO {@link DmXaTmpDTO}.
 */
@Mapper(componentModel = "spring")
public interface DmXaTmpMapper extends EntityMapper<DmXaTmpDTO, DmXaTmp> {}
