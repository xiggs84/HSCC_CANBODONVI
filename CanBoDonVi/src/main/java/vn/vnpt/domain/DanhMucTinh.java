package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A DanhMucTinh.
 */
@Entity
@Table(name = "danh_muc_tinh")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new", "id" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucTinh implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_tinh")
    private String maTinh;

    @Column(name = "ten_tinh")
    private String tenTinh;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getMaTinh() {
        return this.maTinh;
    }

    public DanhMucTinh maTinh(String maTinh) {
        this.setMaTinh(maTinh);
        return this;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getTenTinh() {
        return this.tenTinh;
    }

    public DanhMucTinh tenTinh(String tenTinh) {
        this.setTenTinh(tenTinh);
        return this;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    @Override
    public String getId() {
        return this.maTinh;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public DanhMucTinh setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucTinh)) {
            return false;
        }
        return getMaTinh() != null && getMaTinh().equals(((DanhMucTinh) o).getMaTinh());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucTinh{" +
            "maTinh=" + getMaTinh() +
            ", tenTinh='" + getTenTinh() + "'" +
            "}";
    }
}
