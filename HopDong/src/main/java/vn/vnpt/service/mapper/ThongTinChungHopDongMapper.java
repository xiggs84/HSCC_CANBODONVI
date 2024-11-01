package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.ThongTinChungHopDong;
import vn.vnpt.service.dto.ThongTinChungHopDongDTO;

/**
 * Mapper for the entity {@link ThongTinChungHopDong} and its DTO {@link ThongTinChungHopDongDTO}.
 */
@Mapper(componentModel = "spring")
public interface ThongTinChungHopDongMapper extends EntityMapper<ThongTinChungHopDongDTO, ThongTinChungHopDong> {}
