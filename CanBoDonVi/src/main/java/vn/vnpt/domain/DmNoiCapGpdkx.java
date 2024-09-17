package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DmNoiCapGpdkx.
 */
@Entity
@Table(name = "dm_noi_cap_gpdkx")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmNoiCapGpdkx implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_noi_cap")
    private Long idNoiCap;

    @Column(name = "dien_giai")
    private String dienGiai;

    @Column(name = "trang_thai")
    private Long trangThai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdNoiCap() {
        return this.idNoiCap;
    }

    public DmNoiCapGpdkx idNoiCap(Long idNoiCap) {
        this.setIdNoiCap(idNoiCap);
        return this;
    }

    public void setIdNoiCap(Long idNoiCap) {
        this.idNoiCap = idNoiCap;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DmNoiCapGpdkx dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DmNoiCapGpdkx trangThai(Long trangThai) {
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
        if (!(o instanceof DmNoiCapGpdkx)) {
            return false;
        }
        return getIdNoiCap() != null && getIdNoiCap().equals(((DmNoiCapGpdkx) o).getIdNoiCap());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmNoiCapGpdkx{" +
            "idNoiCap=" + getIdNoiCap() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
