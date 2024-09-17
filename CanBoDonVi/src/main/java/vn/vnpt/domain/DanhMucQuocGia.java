package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhMucQuocGia.
 */
@Entity
@Table(name = "danh_muc_quoc_gia")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucQuocGia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_quoc_gia")
    private Long idQuocGia;

    @Column(name = "ten_quoc_gia")
    private String tenQuocGia;

    @Column(name = "ten_tieng_anh")
    private String tenTiengAnh;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdQuocGia() {
        return this.idQuocGia;
    }

    public DanhMucQuocGia idQuocGia(Long idQuocGia) {
        this.setIdQuocGia(idQuocGia);
        return this;
    }

    public void setIdQuocGia(Long idQuocGia) {
        this.idQuocGia = idQuocGia;
    }

    public String getTenQuocGia() {
        return this.tenQuocGia;
    }

    public DanhMucQuocGia tenQuocGia(String tenQuocGia) {
        this.setTenQuocGia(tenQuocGia);
        return this;
    }

    public void setTenQuocGia(String tenQuocGia) {
        this.tenQuocGia = tenQuocGia;
    }

    public String getTenTiengAnh() {
        return this.tenTiengAnh;
    }

    public DanhMucQuocGia tenTiengAnh(String tenTiengAnh) {
        this.setTenTiengAnh(tenTiengAnh);
        return this;
    }

    public void setTenTiengAnh(String tenTiengAnh) {
        this.tenTiengAnh = tenTiengAnh;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucQuocGia)) {
            return false;
        }
        return getIdQuocGia() != null && getIdQuocGia().equals(((DanhMucQuocGia) o).getIdQuocGia());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucQuocGia{" +
            "idQuocGia=" + getIdQuocGia() +
            ", tenQuocGia='" + getTenQuocGia() + "'" +
            ", tenTiengAnh='" + getTenTiengAnh() + "'" +
            "}";
    }
}
