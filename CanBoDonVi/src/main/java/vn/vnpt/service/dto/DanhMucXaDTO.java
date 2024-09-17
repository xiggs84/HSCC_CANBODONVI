package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucXa} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucXaDTO implements Serializable {

    private String maXa;

    private String tenXa;

    private String maHuyen;

    public String getMaXa() {
        return maXa;
    }

    public void setMaXa(String maXa) {
        this.maXa = maXa;
    }

    public String getTenXa() {
        return tenXa;
    }

    public void setTenXa(String tenXa) {
        this.tenXa = tenXa;
    }

    public String getMaHuyen() {
        return maHuyen;
    }

    public void setMaHuyen(String maHuyen) {
        this.maHuyen = maHuyen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucXaDTO)) {
            return false;
        }

        DanhMucXaDTO danhMucXaDTO = (DanhMucXaDTO) o;
        if (this.maXa == null) {
            return false;
        }
        return Objects.equals(this.maXa, danhMucXaDTO.maXa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.maXa);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucXaDTO{" +
            "maXa='" + getMaXa() + "'" +
            ", tenXa='" + getTenXa() + "'" +
            ", maHuyen='" + getMaHuyen() + "'" +
            "}";
    }
}
