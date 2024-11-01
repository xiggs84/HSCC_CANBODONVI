package vn.vnpt.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A SoChungThuc.
 */
@Document(collection = "so_chung_thuc")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoChungThuc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("id_don_vi")
    private Long idDonVi;

    @Field("ten_so")
    private String tenSo;

    @Field("gia_tri")
    private Long giaTri;

    @Field("ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Field("nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Field("trang_thai")
    private Long trangThai;

    @Field("id_loai_so")
    private String idLoaiSo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public SoChungThuc id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public SoChungThuc idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getTenSo() {
        return this.tenSo;
    }

    public SoChungThuc tenSo(String tenSo) {
        this.setTenSo(tenSo);
        return this;
    }

    public void setTenSo(String tenSo) {
        this.tenSo = tenSo;
    }

    public Long getGiaTri() {
        return this.giaTri;
    }

    public SoChungThuc giaTri(Long giaTri) {
        this.setGiaTri(giaTri);
        return this;
    }

    public void setGiaTri(Long giaTri) {
        this.giaTri = giaTri;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public SoChungThuc ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public SoChungThuc nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public SoChungThuc trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdLoaiSo() {
        return this.idLoaiSo;
    }

    public SoChungThuc idLoaiSo(String idLoaiSo) {
        this.setIdLoaiSo(idLoaiSo);
        return this;
    }

    public void setIdLoaiSo(String idLoaiSo) {
        this.idLoaiSo = idLoaiSo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoChungThuc)) {
            return false;
        }
        return getId() != null && getId().equals(((SoChungThuc) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoChungThuc{" +
            "id=" + getId() +
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
