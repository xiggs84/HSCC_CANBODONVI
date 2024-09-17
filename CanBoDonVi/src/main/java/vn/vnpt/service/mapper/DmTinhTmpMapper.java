package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DmTinhTmp;
import vn.vnpt.service.dto.DmTinhTmpDTO;

/**
 * Mapper for the entity {@link DmTinhTmp} and its DTO {@link DmTinhTmpDTO}.
 */
@Mapper(componentModel = "spring")
public interface DmTinhTmpMapper extends EntityMapper<DmTinhTmpDTO, DmTinhTmp> {}
