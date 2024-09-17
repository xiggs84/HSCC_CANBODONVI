package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.CapQuanLy;
import vn.vnpt.service.dto.CapQuanLyDTO;

/**
 * Mapper for the entity {@link CapQuanLy} and its DTO {@link CapQuanLyDTO}.
 */
@Mapper(componentModel = "spring")
public interface CapQuanLyMapper extends EntityMapper<CapQuanLyDTO, CapQuanLy> {}
