package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DmHuyenTmp.
 */
@Entity
@Table(name = "dm_huyen_tmp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmHuyenTmp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "ma_huyen")
    private Long maHuyen;

    @Column(name = "ten_huyen")
    private String tenHuyen;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getMaHuyen() {
        return this.maHuyen;
    }

    public DmHuyenTmp maHuyen(Long maHuyen) {
        this.setMaHuyen(maHuyen);
        return this;
    }

    public void setMaHuyen(Long maHuyen) {
        this.maHuyen = maHuyen;
    }

    public String getTenHuyen() {
        return this.tenHuyen;
    }

    public DmHuyenTmp tenHuyen(String tenHuyen) {
        this.setTenHuyen(tenHuyen);
        return this;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmHuyenTmp)) {
            return false;
        }
        return getMaHuyen() != null && getMaHuyen().equals(((DmHuyenTmp) o).getMaHuyen());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmHuyenTmp{" +
            "maHuyen=" + getMaHuyen() +
            ", tenHuyen='" + getTenHuyen() + "'" +
            "}";
    }
}
