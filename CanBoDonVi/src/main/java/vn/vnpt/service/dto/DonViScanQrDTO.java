package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DonViScanQr} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DonViScanQrDTO implements Serializable {

    private Long idLuotQuet;

    private Long idCongDan;

    private LocalDate ngayThaoTac;

    public Long getIdLuotQuet() {
        return idLuotQuet;
    }

    public void setIdLuotQuet(Long idLuotQuet) {
        this.idLuotQuet = idLuotQuet;
    }

    public Long getIdCongDan() {
        return idCongDan;
    }

    public void setIdCongDan(Long idCongDan) {
        this.idCongDan = idCongDan;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DonViScanQrDTO)) {
            return false;
        }

        DonViScanQrDTO donViScanQrDTO = (DonViScanQrDTO) o;
        if (this.idLuotQuet == null) {
            return false;
        }
        return Objects.equals(this.idLuotQuet, donViScanQrDTO.idLuotQuet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idLuotQuet);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DonViScanQrDTO{" +
            "idLuotQuet=" + getIdLuotQuet() +
            ", idCongDan=" + getIdCongDan() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            "}";
    }
}
