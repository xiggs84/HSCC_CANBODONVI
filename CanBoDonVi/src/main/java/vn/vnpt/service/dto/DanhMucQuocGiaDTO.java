package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucQuocGia} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucQuocGiaDTO implements Serializable {

    private Long idQuocGia;

    private String tenQuocGia;

    private String tenTiengAnh;

    public Long getIdQuocGia() {
        return idQuocGia;
    }

    public void setIdQuocGia(Long idQuocGia) {
        this.idQuocGia = idQuocGia;
    }

    public String getTenQuocGia() {
        return tenQuocGia;
    }

    public void setTenQuocGia(String tenQuocGia) {
        this.tenQuocGia = tenQuocGia;
    }

    public String getTenTiengAnh() {
        return tenTiengAnh;
    }

    public void setTenTiengAnh(String tenTiengAnh) {
        this.tenTiengAnh = tenTiengAnh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucQuocGiaDTO)) {
            return false;
        }

        DanhMucQuocGiaDTO danhMucQuocGiaDTO = (DanhMucQuocGiaDTO) o;
        if (this.idQuocGia == null) {
            return false;
        }
        return Objects.equals(this.idQuocGia, danhMucQuocGiaDTO.idQuocGia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idQuocGia);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucQuocGiaDTO{" +
            "idQuocGia=" + getIdQuocGia() +
            ", tenQuocGia='" + getTenQuocGia() + "'" +
            ", tenTiengAnh='" + getTenTiengAnh() + "'" +
            "}";
    }
}
