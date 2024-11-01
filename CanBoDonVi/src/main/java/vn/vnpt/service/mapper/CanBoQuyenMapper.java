package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.CanBoQuyen;
import vn.vnpt.domain.DanhMucDonVi;
import vn.vnpt.service.dto.CanBoQuyenDTO;
import vn.vnpt.service.dto.DanhMucDonViDTO;

/**
 * Mapper for the entity {@link CanBoQuyen} and its DTO {@link CanBoQuyenDTO}.
 */
@Mapper(componentModel = "spring")
public interface CanBoQuyenMapper extends EntityMapper<CanBoQuyenDTO, CanBoQuyen> {
    @Mapping(target = "danhMucDonVi", source = "danhMucDonVi", qualifiedByName = "danhMucDonViIdDonVi")
    CanBoQuyenDTO toDto(CanBoQuyen s);

    @Named("danhMucDonViIdDonVi")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "idDonVi", source = "idDonVi")
    DanhMucDonViDTO toDtoDanhMucDonViIdDonVi(DanhMucDonVi danhMucDonVi);
}
