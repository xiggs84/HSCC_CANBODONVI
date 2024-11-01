package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.SoCongChung;
import vn.vnpt.service.dto.SoCongChungDTO;

/**
 * Mapper for the entity {@link SoCongChung} and its DTO {@link SoCongChungDTO}.
 */
@Mapper(componentModel = "spring")
public interface SoCongChungMapper extends EntityMapper<SoCongChungDTO, SoCongChung> {}
