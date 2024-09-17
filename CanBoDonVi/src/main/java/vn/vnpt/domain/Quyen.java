package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Quyen.
 */
@Entity
@Table(name = "quyen")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Quyen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_quyen")
    private Long idQuyen;

    @Column(name = "ten_quyen")
    private String tenQuyen;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdQuyen() {
        return this.idQuyen;
    }

    public Quyen idQuyen(Long idQuyen) {
        this.setIdQuyen(idQuyen);
        return this;
    }

    public void setIdQuyen(Long idQuyen) {
        this.idQuyen = idQuyen;
    }

    public String getTenQuyen() {
        return this.tenQuyen;
    }

    public Quyen tenQuyen(String tenQuyen) {
        this.setTenQuyen(tenQuyen);
        return this;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quyen)) {
            return false;
        }
        return getIdQuyen() != null && getIdQuyen().equals(((Quyen) o).getIdQuyen());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Quyen{" +
            "idQuyen=" + getIdQuyen() +
            ", tenQuyen='" + getTenQuyen() + "'" +
            "}";
    }
}
