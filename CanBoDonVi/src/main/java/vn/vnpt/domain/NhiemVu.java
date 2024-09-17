package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A NhiemVu.
 */
@Entity
@Table(name = "nhiem_vu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new", "id" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NhiemVu implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_nhiem_vu")
    private String idNhiemVu;

    @Column(name = "ten_nhiem_vu")
    private String tenNhiemVu;

    @Transient
    private boolean isPersisted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nhiemVu")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "capQuanLy", "loaiDonVi", "nhiemVu" }, allowSetters = true)
    private Set<DanhMucDonVi> idNhiemVus = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getIdNhiemVu() {
        return this.idNhiemVu;
    }

    public NhiemVu idNhiemVu(String idNhiemVu) {
        this.setIdNhiemVu(idNhiemVu);
        return this;
    }

    public void setIdNhiemVu(String idNhiemVu) {
        this.idNhiemVu = idNhiemVu;
    }

    public String getTenNhiemVu() {
        return this.tenNhiemVu;
    }

    public NhiemVu tenNhiemVu(String tenNhiemVu) {
        this.setTenNhiemVu(tenNhiemVu);
        return this;
    }

    public void setTenNhiemVu(String tenNhiemVu) {
        this.tenNhiemVu = tenNhiemVu;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    @Override
    public String getId() {
        return this.idNhiemVu;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public NhiemVu setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Set<DanhMucDonVi> getIdNhiemVus() {
        return this.idNhiemVus;
    }

    public void setIdNhiemVus(Set<DanhMucDonVi> danhMucDonVis) {
        if (this.idNhiemVus != null) {
            this.idNhiemVus.forEach(i -> i.setNhiemVu(null));
        }
        if (danhMucDonVis != null) {
            danhMucDonVis.forEach(i -> i.setNhiemVu(this));
        }
        this.idNhiemVus = danhMucDonVis;
    }

    public NhiemVu idNhiemVus(Set<DanhMucDonVi> danhMucDonVis) {
        this.setIdNhiemVus(danhMucDonVis);
        return this;
    }

    public NhiemVu addIdNhiemVu(DanhMucDonVi danhMucDonVi) {
        this.idNhiemVus.add(danhMucDonVi);
        danhMucDonVi.setNhiemVu(this);
        return this;
    }

    public NhiemVu removeIdNhiemVu(DanhMucDonVi danhMucDonVi) {
        this.idNhiemVus.remove(danhMucDonVi);
        danhMucDonVi.setNhiemVu(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NhiemVu)) {
            return false;
        }
        return getIdNhiemVu() != null && getIdNhiemVu().equals(((NhiemVu) o).getIdNhiemVu());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhiemVu{" +
            "idNhiemVu=" + getIdNhiemVu() +
            ", tenNhiemVu='" + getTenNhiemVu() + "'" +
            "}";
    }
}
