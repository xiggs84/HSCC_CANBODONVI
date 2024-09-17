package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucDauSoCmnd;
import vn.vnpt.service.dto.DanhMucDauSoCmndDTO;

/**
 * Mapper for the entity {@link DanhMucDauSoCmnd} and its DTO {@link DanhMucDauSoCmndDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucDauSoCmndMapper extends EntityMapper<DanhMucDauSoCmndDTO, DanhMucDauSoCmnd> {}
