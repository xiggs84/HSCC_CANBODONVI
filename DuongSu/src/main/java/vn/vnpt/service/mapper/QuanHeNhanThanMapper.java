package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.QuanHeNhanThan;
import vn.vnpt.service.dto.QuanHeNhanThanDTO;

/**
 * Mapper for the entity {@link QuanHeNhanThan} and its DTO {@link QuanHeNhanThanDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuanHeNhanThanMapper extends EntityMapper<QuanHeNhanThanDTO, QuanHeNhanThan> {}
