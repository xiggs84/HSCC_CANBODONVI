package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A DanhMucXa.
 */
@Entity
@Table(name = "danh_muc_xa")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new", "id" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucXa implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ma_xa")
    private String maXa;

    @Column(name = "ten_xa")
    private String tenXa;

    @Column(name = "ma_huyen")
    private String maHuyen;

    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getMaXa() {
        return this.maXa;
    }

    public DanhMucXa maXa(String maXa) {
        this.setMaXa(maXa);
        return this;
    }

    public void setMaXa(String maXa) {
        this.maXa = maXa;
    }

    public String getTenXa() {
        return this.tenXa;
    }

    public DanhMucXa tenXa(String tenXa) {
        this.setTenXa(tenXa);
        return this;
    }

    public void setTenXa(String tenXa) {
        this.tenXa = tenXa;
    }

    public String getMaHuyen() {
        return this.maHuyen;
    }

    public DanhMucXa maHuyen(String maHuyen) {
        this.setMaHuyen(maHuyen);
        return this;
    }

    public void setMaHuyen(String maHuyen) {
        this.maHuyen = maHuyen;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    @Override
    public String getId() {
        return this.maXa;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public DanhMucXa setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucXa)) {
            return false;
        }
        return getMaXa() != null && getMaXa().equals(((DanhMucXa) o).getMaXa());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucXa{" +
            "maXa=" + getMaXa() +
            ", tenXa='" + getTenXa() + "'" +
            ", maHuyen='" + getMaHuyen() + "'" +
            "}";
    }
}
