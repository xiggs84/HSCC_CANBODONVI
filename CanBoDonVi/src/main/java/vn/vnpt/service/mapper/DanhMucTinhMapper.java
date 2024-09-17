package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucTinh;
import vn.vnpt.service.dto.DanhMucTinhDTO;

/**
 * Mapper for the entity {@link DanhMucTinh} and its DTO {@link DanhMucTinhDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucTinhMapper extends EntityMapper<DanhMucTinhDTO, DanhMucTinh> {}
