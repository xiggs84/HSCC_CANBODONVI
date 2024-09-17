package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucCapQuanLy;
import vn.vnpt.service.dto.DanhMucCapQuanLyDTO;

/**
 * Mapper for the entity {@link DanhMucCapQuanLy} and its DTO {@link DanhMucCapQuanLyDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucCapQuanLyMapper extends EntityMapper<DanhMucCapQuanLyDTO, DanhMucCapQuanLy> {}
