package vn.vnpt.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DanhMucTuVietTat.
 */
@Document(collection = "danh_muc_tu_viet_tat")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucTuVietTat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tu_viet_tat")
    private String tuVietTat;

    @Field("dien_giai")
    private String dienGiai;

    @Field("id_don_vi")
    private Long idDonVi;

    @Field("nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Field("ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Field("trang_thai")
    private Long trangThai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DanhMucTuVietTat id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTuVietTat() {
        return this.tuVietTat;
    }

    public DanhMucTuVietTat tuVietTat(String tuVietTat) {
        this.setTuVietTat(tuVietTat);
        return this;
    }

    public void setTuVietTat(String tuVietTat) {
        this.tuVietTat = tuVietTat;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DanhMucTuVietTat dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public DanhMucTuVietTat idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public DanhMucTuVietTat nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public DanhMucTuVietTat ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DanhMucTuVietTat trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucTuVietTat)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhMucTuVietTat) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucTuVietTat{" +
            "id=" + getId() +
            ", tuVietTat='" + getTuVietTat() + "'" +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
