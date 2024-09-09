package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ChiTietNganChan.
 */
@Entity
@Table(name = "chi_tiet_ngan_chan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChiTietNganChan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_doi_tuong")
    private Long idDoiTuong;

    @Column(name = "ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Column(name = "loai_doi_tuong")
    private Long loaiDoiTuong;

    @Column(name = "so_hs_cv")
    private String soHsCv;

    @Column(name = "so_cc")
    private String soCc;

    @Column(name = "so_vao_so")
    private String soVaoSo;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ngay_ngan_chan")
    private LocalDate ngayNganChan;

    @Column(name = "ngay_bd_ngan_chan")
    private LocalDate ngayBdNganChan;

    @Column(name = "ngay_kt_ngan_chan")
    private LocalDate ngayKtNganChan;

    @Column(name = "trang_thai")
    private Long trangThai;

    @Column(name = "nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Column(name = "loai_ngan_chan")
    private Long loaiNganChan;

    @Column(name = "ngay_cong_van")
    private LocalDate ngayCongVan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ChiTietNganChan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDoiTuong() {
        return this.idDoiTuong;
    }

    public ChiTietNganChan idDoiTuong(Long idDoiTuong) {
        this.setIdDoiTuong(idDoiTuong);
        return this;
    }

    public void setIdDoiTuong(Long idDoiTuong) {
        this.idDoiTuong = idDoiTuong;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public ChiTietNganChan ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getLoaiDoiTuong() {
        return this.loaiDoiTuong;
    }

    public ChiTietNganChan loaiDoiTuong(Long loaiDoiTuong) {
        this.setLoaiDoiTuong(loaiDoiTuong);
        return this;
    }

    public void setLoaiDoiTuong(Long loaiDoiTuong) {
        this.loaiDoiTuong = loaiDoiTuong;
    }

    public String getSoHsCv() {
        return this.soHsCv;
    }

    public ChiTietNganChan soHsCv(String soHsCv) {
        this.setSoHsCv(soHsCv);
        return this;
    }

    public void setSoHsCv(String soHsCv) {
        this.soHsCv = soHsCv;
    }

    public String getSoCc() {
        return this.soCc;
    }

    public ChiTietNganChan soCc(String soCc) {
        this.setSoCc(soCc);
        return this;
    }

    public void setSoCc(String soCc) {
        this.soCc = soCc;
    }

    public String getSoVaoSo() {
        return this.soVaoSo;
    }

    public ChiTietNganChan soVaoSo(String soVaoSo) {
        this.setSoVaoSo(soVaoSo);
        return this;
    }

    public void setSoVaoSo(String soVaoSo) {
        this.soVaoSo = soVaoSo;
    }

    public String getMoTa() {
        return this.moTa;
    }

    public ChiTietNganChan moTa(String moTa) {
        this.setMoTa(moTa);
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayNganChan() {
        return this.ngayNganChan;
    }

    public ChiTietNganChan ngayNganChan(LocalDate ngayNganChan) {
        this.setNgayNganChan(ngayNganChan);
        return this;
    }

    public void setNgayNganChan(LocalDate ngayNganChan) {
        this.ngayNganChan = ngayNganChan;
    }

    public LocalDate getNgayBdNganChan() {
        return this.ngayBdNganChan;
    }

    public ChiTietNganChan ngayBdNganChan(LocalDate ngayBdNganChan) {
        this.setNgayBdNganChan(ngayBdNganChan);
        return this;
    }

    public void setNgayBdNganChan(LocalDate ngayBdNganChan) {
        this.ngayBdNganChan = ngayBdNganChan;
    }

    public LocalDate getNgayKtNganChan() {
        return this.ngayKtNganChan;
    }

    public ChiTietNganChan ngayKtNganChan(LocalDate ngayKtNganChan) {
        this.setNgayKtNganChan(ngayKtNganChan);
        return this;
    }

    public void setNgayKtNganChan(LocalDate ngayKtNganChan) {
        this.ngayKtNganChan = ngayKtNganChan;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public ChiTietNganChan trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public ChiTietNganChan nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getLoaiNganChan() {
        return this.loaiNganChan;
    }

    public ChiTietNganChan loaiNganChan(Long loaiNganChan) {
        this.setLoaiNganChan(loaiNganChan);
        return this;
    }

    public void setLoaiNganChan(Long loaiNganChan) {
        this.loaiNganChan = loaiNganChan;
    }

    public LocalDate getNgayCongVan() {
        return this.ngayCongVan;
    }

    public ChiTietNganChan ngayCongVan(LocalDate ngayCongVan) {
        this.setNgayCongVan(ngayCongVan);
        return this;
    }

    public void setNgayCongVan(LocalDate ngayCongVan) {
        this.ngayCongVan = ngayCongVan;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiTietNganChan)) {
            return false;
        }
        return getId() != null && getId().equals(((ChiTietNganChan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiTietNganChan{" +
            "id=" + getId() +
            ", idDoiTuong=" + getIdDoiTuong() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", loaiDoiTuong=" + getLoaiDoiTuong() +
            ", soHsCv='" + getSoHsCv() + "'" +
            ", soCc='" + getSoCc() + "'" +
            ", soVaoSo='" + getSoVaoSo() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", ngayNganChan='" + getNgayNganChan() + "'" +
            ", ngayBdNganChan='" + getNgayBdNganChan() + "'" +
            ", ngayKtNganChan='" + getNgayKtNganChan() + "'" +
            ", trangThai=" + getTrangThai() +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", loaiNganChan=" + getLoaiNganChan() +
            ", ngayCongVan='" + getNgayCongVan() + "'" +
            "}";
    }
}
