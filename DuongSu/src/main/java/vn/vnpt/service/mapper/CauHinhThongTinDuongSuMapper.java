package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.CauHinhThongTinDuongSu;
import vn.vnpt.service.dto.CauHinhThongTinDuongSuDTO;

/**
 * Mapper for the entity {@link CauHinhThongTinDuongSu} and its DTO {@link CauHinhThongTinDuongSuDTO}.
 */
@Mapper(componentModel = "spring")
public interface CauHinhThongTinDuongSuMapper extends EntityMapper<CauHinhThongTinDuongSuDTO, CauHinhThongTinDuongSu> {}
