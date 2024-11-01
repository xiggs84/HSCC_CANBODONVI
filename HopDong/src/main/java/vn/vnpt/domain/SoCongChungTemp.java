package vn.vnpt.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A SoCongChungTemp.
 */
@Document(collection = "so_cong_chung_temp")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoCongChungTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("id_hop_dong")
    private String idHopDong;

    @Field("id_master")
    private Long idMaster;

    @Field("so_cc")
    private String soCc;

    @Field("ngay_thao_tac")
    private LocalDate ngayThaoTac;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public SoCongChungTemp id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHopDong() {
        return this.idHopDong;
    }

    public SoCongChungTemp idHopDong(String idHopDong) {
        this.setIdHopDong(idHopDong);
        return this;
    }

    public void setIdHopDong(String idHopDong) {
        this.idHopDong = idHopDong;
    }

    public Long getIdMaster() {
        return this.idMaster;
    }

    public SoCongChungTemp idMaster(Long idMaster) {
        this.setIdMaster(idMaster);
        return this;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public String getSoCc() {
        return this.soCc;
    }

    public SoCongChungTemp soCc(String soCc) {
        this.setSoCc(soCc);
        return this;
    }

    public void setSoCc(String soCc) {
        this.soCc = soCc;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public SoCongChungTemp ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoCongChungTemp)) {
            return false;
        }
        return getId() != null && getId().equals(((SoCongChungTemp) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoCongChungTemp{" +
            "id=" + getId() +
            ", idHopDong='" + getIdHopDong() + "'" +
            ", idMaster=" + getIdMaster() +
            ", soCc='" + getSoCc() + "'" +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            "}";
    }
}
