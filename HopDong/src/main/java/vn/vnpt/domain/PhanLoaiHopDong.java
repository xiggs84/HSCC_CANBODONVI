package vn.vnpt.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A PhanLoaiHopDong.
 */
@Document(collection = "phan_loai_hop_dong")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PhanLoaiHopDong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("dien_giai")
    private String dienGiai;

    @Field("id_nhom_hop_dong")
    private String idNhomHopDong;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public PhanLoaiHopDong id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public PhanLoaiHopDong dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getIdNhomHopDong() {
        return this.idNhomHopDong;
    }

    public PhanLoaiHopDong idNhomHopDong(String idNhomHopDong) {
        this.setIdNhomHopDong(idNhomHopDong);
        return this;
    }

    public void setIdNhomHopDong(String idNhomHopDong) {
        this.idNhomHopDong = idNhomHopDong;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhanLoaiHopDong)) {
            return false;
        }
        return getId() != null && getId().equals(((PhanLoaiHopDong) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PhanLoaiHopDong{" +
            "id=" + getId() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idNhomHopDong='" + getIdNhomHopDong() + "'" +
            "}";
    }
}
