package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.SoCongChungTemp} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoCongChungTempDTO implements Serializable {

    private String id;

    private String idHopDong;

    private Long idMaster;

    private String soCc;

    private LocalDate ngayThaoTac;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHopDong() {
        return idHopDong;
    }

    public void setIdHopDong(String idHopDong) {
        this.idHopDong = idHopDong;
    }

    public Long getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public String getSoCc() {
        return soCc;
    }

    public void setSoCc(String soCc) {
        this.soCc = soCc;
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
        if (!(o instanceof SoCongChungTempDTO)) {
            return false;
        }

        SoCongChungTempDTO soCongChungTempDTO = (SoCongChungTempDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, soCongChungTempDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoCongChungTempDTO{" +
            "id='" + getId() + "'" +
            ", idHopDong='" + getIdHopDong() + "'" +
            ", idMaster=" + getIdMaster() +
            ", soCc='" + getSoCc() + "'" +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            "}";
    }
}
