package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhMucCapQuanLy.
 */
@Entity
@Table(name = "danh_muc_cap_quan_ly")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucCapQuanLy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_cap_ql")
    private Long idCapQl;

    @Column(name = "dien_giai")
    private String dienGiai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdCapQl() {
        return this.idCapQl;
    }

    public DanhMucCapQuanLy idCapQl(Long idCapQl) {
        this.setIdCapQl(idCapQl);
        return this;
    }

    public void setIdCapQl(Long idCapQl) {
        this.idCapQl = idCapQl;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DanhMucCapQuanLy dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucCapQuanLy)) {
            return false;
        }
        return getIdCapQl() != null && getIdCapQl().equals(((DanhMucCapQuanLy) o).getIdCapQl());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucCapQuanLy{" +
            "idCapQl=" + getIdCapQl() +
            ", dienGiai='" + getDienGiai() + "'" +
            "}";
    }
}
