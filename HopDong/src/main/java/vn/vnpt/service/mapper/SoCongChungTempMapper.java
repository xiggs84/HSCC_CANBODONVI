package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.SoCongChungTemp;
import vn.vnpt.service.dto.SoCongChungTempDTO;

/**
 * Mapper for the entity {@link SoCongChungTemp} and its DTO {@link SoCongChungTempDTO}.
 */
@Mapper(componentModel = "spring")
public interface SoCongChungTempMapper extends EntityMapper<SoCongChungTempDTO, SoCongChungTemp> {}
