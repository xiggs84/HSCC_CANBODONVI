package vn.vnpt.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DanhMucLoaiVanBan.
 */
@Document(collection = "danh_muc_loai_van_ban")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiVanBan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("dien_giai")
    private String dienGiai;

    @Field("id_loai_hop_dong")
    private String idLoaiHopDong;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DanhMucLoaiVanBan id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DanhMucLoaiVanBan dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getIdLoaiHopDong() {
        return this.idLoaiHopDong;
    }

    public DanhMucLoaiVanBan idLoaiHopDong(String idLoaiHopDong) {
        this.setIdLoaiHopDong(idLoaiHopDong);
        return this;
    }

    public void setIdLoaiHopDong(String idLoaiHopDong) {
        this.idLoaiHopDong = idLoaiHopDong;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucLoaiVanBan)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhMucLoaiVanBan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiVanBan{" +
            "id=" + getId() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idLoaiHopDong='" + getIdLoaiHopDong() + "'" +
            "}";
    }
}
