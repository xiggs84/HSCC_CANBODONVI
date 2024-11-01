package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.SoChungThuc} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoChungThucDTO implements Serializable {

    private String id;

    private Long idDonVi;

    private String tenSo;

    private Long giaTri;

    private LocalDate ngayThaoTac;

    private Long nguoiThaoTac;

    private Long trangThai;

    private String idLoaiSo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getTenSo() {
        return tenSo;
    }

    public void setTenSo(String tenSo) {
        this.tenSo = tenSo;
    }

    public Long getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Long giaTri) {
        this.giaTri = giaTri;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return nguoiThaoTac;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdLoaiSo() {
        return idLoaiSo;
    }

    public void setIdLoaiSo(String idLoaiSo) {
        this.idLoaiSo = idLoaiSo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoChungThucDTO)) {
            return false;
        }

        SoChungThucDTO soChungThucDTO = (SoChungThucDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, soChungThucDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoChungThucDTO{" +
            "id='" + getId() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", tenSo='" + getTenSo() + "'" +
            ", giaTri=" + getGiaTri() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", trangThai=" + getTrangThai() +
            ", idLoaiSo='" + getIdLoaiSo() + "'" +
            "}";
    }
}
