package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.NoiCapGttt;
import vn.vnpt.service.dto.NoiCapGtttDTO;

/**
 * Mapper for the entity {@link NoiCapGttt} and its DTO {@link NoiCapGtttDTO}.
 */
@Mapper(componentModel = "spring")
public interface NoiCapGtttMapper extends EntityMapper<NoiCapGtttDTO, NoiCapGttt> {}
