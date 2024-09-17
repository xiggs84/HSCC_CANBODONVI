package vn.vnpt.service.mapper;

import org.mapstruct.*;
import vn.vnpt.domain.DmNoiCapGpdkx;
import vn.vnpt.service.dto.DmNoiCapGpdkxDTO;

/**
 * Mapper for the entity {@link DmNoiCapGpdkx} and its DTO {@link DmNoiCapGpdkxDTO}.
 */
@Mapper(componentModel = "spring")
public interface DmNoiCapGpdkxMapper extends EntityMapper<DmNoiCapGpdkxDTO, DmNoiCapGpdkx> {}
