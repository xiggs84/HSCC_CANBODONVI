package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.NhiemVu} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NhiemVuDTO implements Serializable {

    private String idNhiemVu;

    private String tenNhiemVu;

    public String getIdNhiemVu() {
        return idNhiemVu;
    }

    public void setIdNhiemVu(String idNhiemVu) {
        this.idNhiemVu = idNhiemVu;
    }

    public String getTenNhiemVu() {
        return tenNhiemVu;
    }

    public void setTenNhiemVu(String tenNhiemVu) {
        this.tenNhiemVu = tenNhiemVu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NhiemVuDTO)) {
            return false;
        }

        NhiemVuDTO nhiemVuDTO = (NhiemVuDTO) o;
        if (this.idNhiemVu == null) {
            return false;
        }
        return Objects.equals(this.idNhiemVu, nhiemVuDTO.idNhiemVu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idNhiemVu);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhiemVuDTO{" +
            "idNhiemVu='" + getIdNhiemVu() + "'" +
            ", tenNhiemVu='" + getTenNhiemVu() + "'" +
            "}";
    }
}
