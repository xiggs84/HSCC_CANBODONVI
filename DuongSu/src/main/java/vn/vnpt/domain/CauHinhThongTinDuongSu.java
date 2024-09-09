package vn.vnpt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CauHinhThongTinDuongSu.
 */
@Entity
@Table(name = "cau_hinh_thong_tin_duong_su")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CauHinhThongTinDuongSu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_cau_hinh")
    private Long idCauHinh;

    @Column(name = "noi_dung")
    private String noiDung;

    @Column(name = "javascript")
    private String javascript;

    @Column(name = "css")
    private String css;

    @Column(name = "id_loai_ds")
    private Long idLoaiDs;

    @Column(name = "id_don_vi")
    private Long idDonVi;

    @Min(value = 0)
    @Max(value = 1)
    @Column(name = "trang_thai")
    private Integer trangThai;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdCauHinh() {
        return this.idCauHinh;
    }

    public CauHinhThongTinDuongSu idCauHinh(Long idCauHinh) {
        this.setIdCauHinh(idCauHinh);
        return this;
    }

    public void setIdCauHinh(Long idCauHinh) {
        this.idCauHinh = idCauHinh;
    }

    public String getNoiDung() {
        return this.noiDung;
    }

    public CauHinhThongTinDuongSu noiDung(String noiDung) {
        this.setNoiDung(noiDung);
        return this;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getJavascript() {
        return this.javascript;
    }

    public CauHinhThongTinDuongSu javascript(String javascript) {
        this.setJavascript(javascript);
        return this;
    }

    public void setJavascript(String javascript) {
        this.javascript = javascript;
    }

    public String getCss() {
        return this.css;
    }

    public CauHinhThongTinDuongSu css(String css) {
        this.setCss(css);
        return this;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public Long getIdLoaiDs() {
        return this.idLoaiDs;
    }

    public CauHinhThongTinDuongSu idLoaiDs(Long idLoaiDs) {
        this.setIdLoaiDs(idLoaiDs);
        return this;
    }

    public void setIdLoaiDs(Long idLoaiDs) {
        this.idLoaiDs = idLoaiDs;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public CauHinhThongTinDuongSu idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Integer getTrangThai() {
        return this.trangThai;
    }

    public CauHinhThongTinDuongSu trangThai(Integer trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CauHinhThongTinDuongSu)) {
            return false;
        }
        return getIdCauHinh() != null && getIdCauHinh().equals(((CauHinhThongTinDuongSu) o).getIdCauHinh());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CauHinhThongTinDuongSu{" +
            "idCauHinh=" + getIdCauHinh() +
            ", noiDung='" + getNoiDung() + "'" +
            ", javascript='" + getJavascript() + "'" +
            ", css='" + getCss() + "'" +
            ", idLoaiDs=" + getIdLoaiDs() +
            ", idDonVi=" + getIdDonVi() +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
