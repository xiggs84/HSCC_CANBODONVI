package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A QuanHeDuongSu.
 */
@Entity
@Table(name = "quan_he_duong_su")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuanHeDuongSu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_quan_he")
    private Long idQuanHe;

    @Column(name = "id_duong_su_qh")
    private Long idDuongSuQh;

    @Lob
    @Column(name = "thong_tin_quan_he")
    private String thongTinQuanHe;

    @Min(value = 0)
    @Max(value = 1)
    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "thongTinCapNhats",
            "taiSanDuongSus",
            "quanHeDuongSus",
            "danhSachDuongSus",
            "duongSuTrungCmnds",
            "duongSuTrungCmndBaks",
            "loaiDuongSu",
            "loaiGiayTo",
        },
        allowSetters = true
    )
    private DuongSu duongSu;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdQuanHe() {
        return this.idQuanHe;
    }

    public QuanHeDuongSu idQuanHe(Long idQuanHe) {
        this.setIdQuanHe(idQuanHe);
        return this;
    }

    public void setIdQuanHe(Long idQuanHe) {
        this.idQuanHe = idQuanHe;
    }

    public Long getIdDuongSuQh() {
        return this.idDuongSuQh;
    }

    public QuanHeDuongSu idDuongSuQh(Long idDuongSuQh) {
        this.setIdDuongSuQh(idDuongSuQh);
        return this;
    }

    public void setIdDuongSuQh(Long idDuongSuQh) {
        this.idDuongSuQh = idDuongSuQh;
    }

    public String getThongTinQuanHe() {
        return this.thongTinQuanHe;
    }

    public QuanHeDuongSu thongTinQuanHe(String thongTinQuanHe) {
        this.setThongTinQuanHe(thongTinQuanHe);
        return this;
    }

    public void setThongTinQuanHe(String thongTinQuanHe) {
        this.thongTinQuanHe = thongTinQuanHe;
    }

    public Integer getTrangThai() {
        return this.trangThai;
    }

    public QuanHeDuongSu trangThai(Integer trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public DuongSu getDuongSu() {
        return this.duongSu;
    }

    public void setDuongSu(DuongSu duongSu) {
        this.duongSu = duongSu;
    }

    public QuanHeDuongSu duongSu(DuongSu duongSu) {
        this.setDuongSu(duongSu);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuanHeDuongSu)) {
            return false;
        }
        return getIdQuanHe() != null && getIdQuanHe().equals(((QuanHeDuongSu) o).getIdQuanHe());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuanHeDuongSu{" +
            "idQuanHe=" + getIdQuanHe() +
            ", idDuongSuQh=" + getIdDuongSuQh() +
            ", thongTinQuanHe='" + getThongTinQuanHe() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
