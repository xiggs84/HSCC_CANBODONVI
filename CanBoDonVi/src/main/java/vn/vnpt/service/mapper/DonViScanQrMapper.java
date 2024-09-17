package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DonViScanQr;
import vn.vnpt.service.dto.DonViScanQrDTO;

/**
 * Mapper for the entity {@link DonViScanQr} and its DTO {@link DonViScanQrDTO}.
 */
@Mapper(componentModel = "spring")
public interface DonViScanQrMapper extends EntityMapper<DonViScanQrDTO, DonViScanQr> {}
