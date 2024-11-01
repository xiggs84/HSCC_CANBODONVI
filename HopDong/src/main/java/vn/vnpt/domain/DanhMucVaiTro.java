package vn.vnpt.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DanhMucVaiTro.
 */
@Document(collection = "danh_muc_vai_tro")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucVaiTro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("dien_giai")
    private String dienGiai;

    @Field("id_loai_hop_dong")
    private String idLoaiHopDong;

    @Field("id_loai_vai_tro")
    private String idLoaiVaiTro;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DanhMucVaiTro id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DanhMucVaiTro dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getIdLoaiHopDong() {
        return this.idLoaiHopDong;
    }

    public DanhMucVaiTro idLoaiHopDong(String idLoaiHopDong) {
        this.setIdLoaiHopDong(idLoaiHopDong);
        return this;
    }

    public void setIdLoaiHopDong(String idLoaiHopDong) {
        this.idLoaiHopDong = idLoaiHopDong;
    }

    public String getIdLoaiVaiTro() {
        return this.idLoaiVaiTro;
    }

    public DanhMucVaiTro idLoaiVaiTro(String idLoaiVaiTro) {
        this.setIdLoaiVaiTro(idLoaiVaiTro);
        return this;
    }

    public void setIdLoaiVaiTro(String idLoaiVaiTro) {
        this.idLoaiVaiTro = idLoaiVaiTro;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucVaiTro)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhMucVaiTro) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucVaiTro{" +
            "id=" + getId() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idLoaiHopDong='" + getIdLoaiHopDong() + "'" +
            ", idLoaiVaiTro='" + getIdLoaiVaiTro() + "'" +
            "}";
    }
}
