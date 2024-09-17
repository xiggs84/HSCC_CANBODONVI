package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A DanhMucHuyen.
 */
@Entity
@Table(name = "danh_muc_huyen")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new", "id" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucHuyen implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_huyen")
    private String maHuyen;

    @Column(name = "ten_huyen")
    private String tenHuyen;

    @Column(name = "ma_tinh")
    private String maTinh;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getMaHuyen() {
        return this.maHuyen;
    }

    public DanhMucHuyen maHuyen(String maHuyen) {
        this.setMaHuyen(maHuyen);
        return this;
    }

    public void setMaHuyen(String maHuyen) {
        this.maHuyen = maHuyen;
    }

    public String getTenHuyen() {
        return this.tenHuyen;
    }

    public DanhMucHuyen tenHuyen(String tenHuyen) {
        this.setTenHuyen(tenHuyen);
        return this;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }

    public String getMaTinh() {
        return this.maTinh;
    }

    public DanhMucHuyen maTinh(String maTinh) {
        this.setMaTinh(maTinh);
        return this;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    @Override
    public String getId() {
        return this.maHuyen;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public DanhMucHuyen setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucHuyen)) {
            return false;
        }
        return getMaHuyen() != null && getMaHuyen().equals(((DanhMucHuyen) o).getMaHuyen());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucHuyen{" +
            "maHuyen=" + getMaHuyen() +
            ", tenHuyen='" + getTenHuyen() + "'" +
            ", maTinh='" + getMaTinh() + "'" +
            "}";
    }
}
