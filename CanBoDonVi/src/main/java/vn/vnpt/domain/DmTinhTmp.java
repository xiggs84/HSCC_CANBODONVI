package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DmTinhTmp.
 */
@Entity
@Table(name = "dm_tinh_tmp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmTinhTmp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "ma_tinh")
    private Long maTinh;

    @Column(name = "ten_tinh")
    private String tenTinh;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getMaTinh() {
        return this.maTinh;
    }

    public DmTinhTmp maTinh(Long maTinh) {
        this.setMaTinh(maTinh);
        return this;
    }

    public void setMaTinh(Long maTinh) {
        this.maTinh = maTinh;
    }

    public String getTenTinh() {
        return this.tenTinh;
    }

    public DmTinhTmp tenTinh(String tenTinh) {
        this.setTenTinh(tenTinh);
        return this;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmTinhTmp)) {
            return false;
        }
        return getMaTinh() != null && getMaTinh().equals(((DmTinhTmp) o).getMaTinh());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmTinhTmp{" +
            "maTinh=" + getMaTinh() +
            ", tenTinh='" + getTenTinh() + "'" +
            "}";
    }
}
