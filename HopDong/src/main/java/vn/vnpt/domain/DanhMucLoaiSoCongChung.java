package vn.vnpt.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DanhMucLoaiSoCongChung.
 */
@Document(collection = "danh_muc_loai_so_cong_chung")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiSoCongChung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("ten_loai")
    private String tenLoai;

    @Field("trang_thai")
    private Long trangThai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DanhMucLoaiSoCongChung id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenLoai() {
        return this.tenLoai;
    }

    public DanhMucLoaiSoCongChung tenLoai(String tenLoai) {
        this.setTenLoai(tenLoai);
        return this;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DanhMucLoaiSoCongChung trangThai(Long trangThai) {
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
        if (!(o instanceof DanhMucLoaiSoCongChung)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhMucLoaiSoCongChung) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiSoCongChung{" +
            "id=" + getId() +
            ", tenLoai='" + getTenLoai() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
