package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.QuanHeMaster;
import vn.vnpt.service.dto.QuanHeMasterDTO;

/**
 * Mapper for the entity {@link QuanHeMaster} and its DTO {@link QuanHeMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuanHeMasterMapper extends EntityMapper<QuanHeMasterDTO, QuanHeMaster> {}
