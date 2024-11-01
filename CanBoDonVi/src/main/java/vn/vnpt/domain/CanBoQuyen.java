package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CanBoQuyen.
 */
@Entity
@Table(name = "can_bo_quyen")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CanBoQuyen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "canBoQuyens", "capQuanLy", "loaiDonVi", "nhiemVu" }, allowSetters = true)
    private DanhMucDonVi danhMucDonVi;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CanBoQuyen id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DanhMucDonVi getDanhMucDonVi() {
        return this.danhMucDonVi;
    }

    public void setDanhMucDonVi(DanhMucDonVi danhMucDonVi) {
        this.danhMucDonVi = danhMucDonVi;
    }

    public CanBoQuyen danhMucDonVi(DanhMucDonVi danhMucDonVi) {
        this.setDanhMucDonVi(danhMucDonVi);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CanBoQuyen)) {
            return false;
        }
        return getId() != null && getId().equals(((CanBoQuyen) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CanBoQuyen{" +
            "id=" + getId() +
            "}";
    }
}
