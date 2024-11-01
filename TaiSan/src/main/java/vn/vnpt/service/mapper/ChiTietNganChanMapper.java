package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.ChiTietNganChan;
import vn.vnpt.service.dto.ChiTietNganChanDTO;

/**
 * Mapper for the entity {@link ChiTietNganChan} and its DTO {@link ChiTietNganChanDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChiTietNganChanMapper extends EntityMapper<ChiTietNganChanDTO, ChiTietNganChan> {}
