package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhMucDauSoCmnd.
 */
@Entity
@Table(name = "danh_muc_dau_so_cmnd")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucDauSoCmnd implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_dau_so")
    private Long idDauSo;

    @Column(name = "dau_so")
    private String dauSo;

    @Column(name = "tinh_thanh")
    private String tinhThanh;

    @Column(name = "id_loai")
    private Long idLoai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdDauSo() {
        return this.idDauSo;
    }

    public DanhMucDauSoCmnd idDauSo(Long idDauSo) {
        this.setIdDauSo(idDauSo);
        return this;
    }

    public void setIdDauSo(Long idDauSo) {
        this.idDauSo = idDauSo;
    }

    public String getDauSo() {
        return this.dauSo;
    }

    public DanhMucDauSoCmnd dauSo(String dauSo) {
        this.setDauSo(dauSo);
        return this;
    }

    public void setDauSo(String dauSo) {
        this.dauSo = dauSo;
    }

    public String getTinhThanh() {
        return this.tinhThanh;
    }

    public DanhMucDauSoCmnd tinhThanh(String tinhThanh) {
        this.setTinhThanh(tinhThanh);
        return this;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public Long getIdLoai() {
        return this.idLoai;
    }

    public DanhMucDauSoCmnd idLoai(Long idLoai) {
        this.setIdLoai(idLoai);
        return this;
    }

    public void setIdLoai(Long idLoai) {
        this.idLoai = idLoai;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucDauSoCmnd)) {
            return false;
        }
        return getIdDauSo() != null && getIdDauSo().equals(((DanhMucDauSoCmnd) o).getIdDauSo());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucDauSoCmnd{" +
            "idDauSo=" + getIdDauSo() +
            ", dauSo='" + getDauSo() + "'" +
            ", tinhThanh='" + getTinhThanh() + "'" +
            ", idLoai=" + getIdLoai() +
            "}";
    }
}
