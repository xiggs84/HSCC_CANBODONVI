package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucTinh} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucTinhDTO implements Serializable {

    private String maTinh;

    private String tenTinh;

    public String getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucTinhDTO)) {
            return false;
        }

        DanhMucTinhDTO danhMucTinhDTO = (DanhMucTinhDTO) o;
        if (this.maTinh == null) {
            return false;
        }
        return Objects.equals(this.maTinh, danhMucTinhDTO.maTinh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.maTinh);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucTinhDTO{" +
            "maTinh='" + getMaTinh() + "'" +
            ", tenTinh='" + getTenTinh() + "'" +
            "}";
    }
}
