package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucTuVietTat} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucTuVietTatDTO implements Serializable {

    private String id;

    private String tuVietTat;

    private String dienGiai;

    private Long idDonVi;

    private Long nguoiThaoTac;

    private LocalDate ngayThaoTac;

    private Long trangThai;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTuVietTat() {
        return tuVietTat;
    }

    public void setTuVietTat(String tuVietTat) {
        this.tuVietTat = tuVietTat;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getNguoiThaoTac() {
        return nguoiThaoTac;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucTuVietTatDTO)) {
            return false;
        }

        DanhMucTuVietTatDTO danhMucTuVietTatDTO = (DanhMucTuVietTatDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, danhMucTuVietTatDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucTuVietTatDTO{" +
            "id='" + getId() + "'" +
            ", tuVietTat='" + getTuVietTat() + "'" +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
