package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DanhMucLoaiSoCongChung;
import vn.vnpt.service.dto.DanhMucLoaiSoCongChungDTO;

/**
 * Mapper for the entity {@link DanhMucLoaiSoCongChung} and its DTO {@link DanhMucLoaiSoCongChungDTO}.
 */
@Mapper(componentModel = "spring")
public interface DanhMucLoaiSoCongChungMapper extends EntityMapper<DanhMucLoaiSoCongChungDTO, DanhMucLoaiSoCongChung> {}
