package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.SoChungThuc;
import vn.vnpt.service.dto.SoChungThucDTO;

/**
 * Mapper for the entity {@link SoChungThuc} and its DTO {@link SoChungThucDTO}.
 */
@Mapper(componentModel = "spring")
public interface SoChungThucMapper extends EntityMapper<SoChungThucDTO, SoChungThuc> {}
