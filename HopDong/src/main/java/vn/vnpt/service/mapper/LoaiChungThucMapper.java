package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.LoaiChungThuc;
import vn.vnpt.service.dto.LoaiChungThucDTO;

/**
 * Mapper for the entity {@link LoaiChungThuc} and its DTO {@link LoaiChungThucDTO}.
 */
@Mapper(componentModel = "spring")
public interface LoaiChungThucMapper extends EntityMapper<LoaiChungThucDTO, LoaiChungThuc> {}
