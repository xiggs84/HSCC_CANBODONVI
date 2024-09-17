package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DmXaTmp.
 */
@Entity
@Table(name = "dm_xa_tmp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmXaTmp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "ma_xa")
    private Long maXa;

    @Column(name = "ten_xa")
    private String tenXa;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getMaXa() {
        return this.maXa;
    }

    public DmXaTmp maXa(Long maXa) {
        this.setMaXa(maXa);
        return this;
    }

    public void setMaXa(Long maXa) {
        this.maXa = maXa;
    }

    public String getTenXa() {
        return this.tenXa;
    }

    public DmXaTmp tenXa(String tenXa) {
        this.setTenXa(tenXa);
        return this;
    }

    public void setTenXa(String tenXa) {
        this.tenXa = tenXa;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmXaTmp)) {
            return false;
        }
        return getMaXa() != null && getMaXa().equals(((DmXaTmp) o).getMaXa());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmXaTmp{" +
            "maXa=" + getMaXa() +
            ", tenXa='" + getTenXa() + "'" +
            "}";
    }
}
