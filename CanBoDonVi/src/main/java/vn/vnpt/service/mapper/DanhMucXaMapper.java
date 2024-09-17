package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucXa;
import vn.vnpt.service.dto.DanhMucXaDTO;

/**
 * Mapper for the entity {@link DanhMucXa} and its DTO {@link DanhMucXaDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucXaMapper extends EntityMapper<DanhMucXaDTO, DanhMucXa> {}
