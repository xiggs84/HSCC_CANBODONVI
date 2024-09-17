package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.LoaiDonVi} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiDonViDTO implements Serializable {

    private String idLoaiDv;

    private String tenLoaiDv;

    public String getIdLoaiDv() {
        return idLoaiDv;
    }

    public void setIdLoaiDv(String idLoaiDv) {
        this.idLoaiDv = idLoaiDv;
    }

    public String getTenLoaiDv() {
        return tenLoaiDv;
    }

    public void setTenLoaiDv(String tenLoaiDv) {
        this.tenLoaiDv = tenLoaiDv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoaiDonViDTO)) {
            return false;
        }

        LoaiDonViDTO loaiDonViDTO = (LoaiDonViDTO) o;
        if (this.idLoaiDv == null) {
            return false;
        }
        return Objects.equals(this.idLoaiDv, loaiDonViDTO.idLoaiDv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idLoaiDv);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiDonViDTO{" +
            "idLoaiDv='" + getIdLoaiDv() + "'" +
            ", tenLoaiDv='" + getTenLoaiDv() + "'" +
            "}";
    }
}
