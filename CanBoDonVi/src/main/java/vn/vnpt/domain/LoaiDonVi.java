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
 * A LoaiDonVi.
 */
@Entity
@Table(name = "loai_don_vi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new", "id" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiDonVi implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_loai_dv")
    private String idLoaiDv;

    @Column(name = "ten_loai_dv")
    private String tenLoaiDv;

    @Transient
    private boolean isPersisted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiDonVi")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "canBoQuyens", "capQuanLy", "loaiDonVi", "nhiemVu" }, allowSetters = true)
    private Set<DanhMucDonVi> idLoaiDvs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getIdLoaiDv() {
        return this.idLoaiDv;
    }

    public LoaiDonVi idLoaiDv(String idLoaiDv) {
        this.setIdLoaiDv(idLoaiDv);
        return this;
    }

    public void setIdLoaiDv(String idLoaiDv) {
        this.idLoaiDv = idLoaiDv;
    }

    public String getTenLoaiDv() {
        return this.tenLoaiDv;
    }

    public LoaiDonVi tenLoaiDv(String tenLoaiDv) {
        this.setTenLoaiDv(tenLoaiDv);
        return this;
    }

    public void setTenLoaiDv(String tenLoaiDv) {
        this.tenLoaiDv = tenLoaiDv;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    @Override
    public String getId() {
        return this.idLoaiDv;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public LoaiDonVi setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Set<DanhMucDonVi> getIdLoaiDvs() {
        return this.idLoaiDvs;
    }

    public void setIdLoaiDvs(Set<DanhMucDonVi> danhMucDonVis) {
        if (this.idLoaiDvs != null) {
            this.idLoaiDvs.forEach(i -> i.setLoaiDonVi(null));
        }
        if (danhMucDonVis != null) {
            danhMucDonVis.forEach(i -> i.setLoaiDonVi(this));
        }
        this.idLoaiDvs = danhMucDonVis;
    }

    public LoaiDonVi idLoaiDvs(Set<DanhMucDonVi> danhMucDonVis) {
        this.setIdLoaiDvs(danhMucDonVis);
        return this;
    }

    public LoaiDonVi addIdLoaiDv(DanhMucDonVi danhMucDonVi) {
        this.idLoaiDvs.add(danhMucDonVi);
        danhMucDonVi.setLoaiDonVi(this);
        return this;
    }

    public LoaiDonVi removeIdLoaiDv(DanhMucDonVi danhMucDonVi) {
        this.idLoaiDvs.remove(danhMucDonVi);
        danhMucDonVi.setLoaiDonVi(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoaiDonVi)) {
            return false;
        }
        return getIdLoaiDv() != null && getIdLoaiDv().equals(((LoaiDonVi) o).getIdLoaiDv());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiDonVi{" +
            "idLoaiDv=" + getIdLoaiDv() +
            ", tenLoaiDv='" + getTenLoaiDv() + "'" +
            "}";
    }
}
