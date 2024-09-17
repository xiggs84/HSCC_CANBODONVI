package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucHuyen} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucHuyenDTO implements Serializable {

    private String maHuyen;

    private String tenHuyen;

    private String maTinh;

    public String getMaHuyen() {
        return maHuyen;
    }

    public void setMaHuyen(String maHuyen) {
        this.maHuyen = maHuyen;
    }

    public String getTenHuyen() {
        return tenHuyen;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucHuyenDTO)) {
            return false;
        }

        DanhMucHuyenDTO danhMucHuyenDTO = (DanhMucHuyenDTO) o;
        if (this.maHuyen == null) {
            return false;
        }
        return Objects.equals(this.maHuyen, danhMucHuyenDTO.maHuyen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.maHuyen);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucHuyenDTO{" +
            "maHuyen='" + getMaHuyen() + "'" +
            ", tenHuyen='" + getTenHuyen() + "'" +
            ", maTinh='" + getMaTinh() + "'" +
            "}";
    }
}
