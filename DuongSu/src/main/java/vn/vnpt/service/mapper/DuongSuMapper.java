package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DuongSu;
import vn.vnpt.service.dto.DuongSuDTO;

/**
 * Mapper for the entity {@link DuongSu} and its DTO {@link DuongSuDTO}.
 */
@Mapper(componentModel = "spring")
public interface DuongSuMapper extends EntityMapper<DuongSuDTO, DuongSu> {}
