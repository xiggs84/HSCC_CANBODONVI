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
 * A CapQuanLy.
 */
@Entity
@Table(name = "cap_quan_ly")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new", "id" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CapQuanLy implements Serializable, Persistable<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_cap_ql")
    private String idCapQl;

    @Column(name = "ten_cap_ql")
    private String tenCapQl;

    @Transient
    private boolean isPersisted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "capQuanLy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "canBoQuyens", "capQuanLy", "loaiDonVi", "nhiemVu" }, allowSetters = true)
    private Set<DanhMucDonVi> idCapQls = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getIdCapQl() {
        return this.idCapQl;
    }

    public CapQuanLy idCapQl(String idCapQl) {
        this.setIdCapQl(idCapQl);
        return this;
    }

    public void setIdCapQl(String idCapQl) {
        this.idCapQl = idCapQl;
    }

    public String getTenCapQl() {
        return this.tenCapQl;
    }

    public CapQuanLy tenCapQl(String tenCapQl) {
        this.setTenCapQl(tenCapQl);
        return this;
    }

    public void setTenCapQl(String tenCapQl) {
        this.tenCapQl = tenCapQl;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    @Override
    public String getId() {
        return this.idCapQl;
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public CapQuanLy setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Set<DanhMucDonVi> getIdCapQls() {
        return this.idCapQls;
    }

    public void setIdCapQls(Set<DanhMucDonVi> danhMucDonVis) {
        if (this.idCapQls != null) {
            this.idCapQls.forEach(i -> i.setCapQuanLy(null));
        }
        if (danhMucDonVis != null) {
            danhMucDonVis.forEach(i -> i.setCapQuanLy(this));
        }
        this.idCapQls = danhMucDonVis;
    }

    public CapQuanLy idCapQls(Set<DanhMucDonVi> danhMucDonVis) {
        this.setIdCapQls(danhMucDonVis);
        return this;
    }

    public CapQuanLy addIdCapQl(DanhMucDonVi danhMucDonVi) {
        this.idCapQls.add(danhMucDonVi);
        danhMucDonVi.setCapQuanLy(this);
        return this;
    }

    public CapQuanLy removeIdCapQl(DanhMucDonVi danhMucDonVi) {
        this.idCapQls.remove(danhMucDonVi);
        danhMucDonVi.setCapQuanLy(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CapQuanLy)) {
            return false;
        }
        return getIdCapQl() != null && getIdCapQl().equals(((CapQuanLy) o).getIdCapQl());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CapQuanLy{" +
            "idCapQl=" + getIdCapQl() +
            ", tenCapQl='" + getTenCapQl() + "'" +
            "}";
    }
}
