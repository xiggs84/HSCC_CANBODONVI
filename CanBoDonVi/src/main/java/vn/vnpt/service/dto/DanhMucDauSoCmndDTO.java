package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucDauSoCmnd} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucDauSoCmndDTO implements Serializable {

    private Long idDauSo;

    private String dauSo;

    private String tinhThanh;

    private Long idLoai;

    public Long getIdDauSo() {
        return idDauSo;
    }

    public void setIdDauSo(Long idDauSo) {
        this.idDauSo = idDauSo;
    }

    public String getDauSo() {
        return dauSo;
    }

    public void setDauSo(String dauSo) {
        this.dauSo = dauSo;
    }

    public String getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public Long getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(Long idLoai) {
        this.idLoai = idLoai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucDauSoCmndDTO)) {
            return false;
        }

        DanhMucDauSoCmndDTO danhMucDauSoCmndDTO = (DanhMucDauSoCmndDTO) o;
        if (this.idDauSo == null) {
            return false;
        }
        return Objects.equals(this.idDauSo, danhMucDauSoCmndDTO.idDauSo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idDauSo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucDauSoCmndDTO{" +
            "idDauSo=" + getIdDauSo() +
            ", dauSo='" + getDauSo() + "'" +
            ", tinhThanh='" + getTinhThanh() + "'" +
            ", idLoai=" + getIdLoai() +
            "}";
    }
}
