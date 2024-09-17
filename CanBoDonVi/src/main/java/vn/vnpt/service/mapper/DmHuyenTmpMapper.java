package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DmHuyenTmp;
import vn.vnpt.service.dto.DmHuyenTmpDTO;

/**
 * Mapper for the entity {@link DmHuyenTmp} and its DTO {@link DmHuyenTmpDTO}.
 */
@Mapper(componentModel = "spring")
public interface DmHuyenTmpMapper extends EntityMapper<DmHuyenTmpDTO, DmHuyenTmp> {}
