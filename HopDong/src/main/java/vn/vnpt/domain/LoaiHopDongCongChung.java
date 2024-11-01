package vn.vnpt.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A LoaiHopDongCongChung.
 */
@Document(collection = "loai_hop_dong_cong_chung")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiHopDongCongChung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("dien_giai")
    private String dienGiai;

    @Field("gia_tri")
    private Long giaTri;

    @Field("trang_thai")
    private Long trangThai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public LoaiHopDongCongChung id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public LoaiHopDongCongChung dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getGiaTri() {
        return this.giaTri;
    }

    public LoaiHopDongCongChung giaTri(Long giaTri) {
        this.setGiaTri(giaTri);
        return this;
    }

    public void setGiaTri(Long giaTri) {
        this.giaTri = giaTri;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public LoaiHopDongCongChung trangThai(Long trangThai) {
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
        if (!(o instanceof LoaiHopDongCongChung)) {
            return false;
        }
        return getId() != null && getId().equals(((LoaiHopDongCongChung) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiHopDongCongChung{" +
            "id=" + getId() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", giaTri=" + getGiaTri() +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
