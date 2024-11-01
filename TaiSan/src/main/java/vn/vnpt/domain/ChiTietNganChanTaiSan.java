package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ChiTietNganChanTaiSan.
 */
@Entity
@Table(name = "chi_tiet_ngan_chan_tai_san")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChiTietNganChanTaiSan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_ngan_chan")
    private Long idNganChan;

    @Column(name = "ngay_thao_tac")
    private LocalDate ngayThaoTac;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "thuaTaches",
            "taiSanDuongSus",
            "taiSanDgcs",
            "thongTinCapNhatTaiSans",
            "chiTietNganChanTaiSans",
            "danhMucLoaiTaiSan",
            "tinhTrangTaiSan",
        },
        allowSetters = true
    )
    private TaiSan taiSan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdNganChan() {
        return this.idNganChan;
    }

    public ChiTietNganChanTaiSan idNganChan(Long idNganChan) {
        this.setIdNganChan(idNganChan);
        return this;
    }

    public void setIdNganChan(Long idNganChan) {
        this.idNganChan = idNganChan;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public ChiTietNganChanTaiSan ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public String getSoHsCv() {
        return this.soHsCv;
    }

    public ChiTietNganChanTaiSan soHsCv(String soHsCv) {
        this.setSoHsCv(soHsCv);
        return this;
    }

    public void setSoHsCv(String soHsCv) {
        this.soHsCv = soHsCv;
    }

    public String getSoCc() {
        return this.soCc;
    }

    public ChiTietNganChanTaiSan soCc(String soCc) {
        this.setSoCc(soCc);
        return this;
    }

    public void setSoCc(String soCc) {
        this.soCc = soCc;
    }

    public String getSoVaoSo() {
        return this.soVaoSo;
    }

    public ChiTietNganChanTaiSan soVaoSo(String soVaoSo) {
        this.setSoVaoSo(soVaoSo);
        return this;
    }

    public void setSoVaoSo(String soVaoSo) {
        this.soVaoSo = soVaoSo;
    }

    public String getMoTa() {
        return this.moTa;
    }

    public ChiTietNganChanTaiSan moTa(String moTa) {
        this.setMoTa(moTa);
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayNganChan() {
        return this.ngayNganChan;
    }

    public ChiTietNganChanTaiSan ngayNganChan(LocalDate ngayNganChan) {
        this.setNgayNganChan(ngayNganChan);
        return this;
    }

    public void setNgayNganChan(LocalDate ngayNganChan) {
        this.ngayNganChan = ngayNganChan;
    }

    public LocalDate getNgayBdNganChan() {
        return this.ngayBdNganChan;
    }

    public ChiTietNganChanTaiSan ngayBdNganChan(LocalDate ngayBdNganChan) {
        this.setNgayBdNganChan(ngayBdNganChan);
        return this;
    }

    public void setNgayBdNganChan(LocalDate ngayBdNganChan) {
        this.ngayBdNganChan = ngayBdNganChan;
    }

    public LocalDate getNgayKtNganChan() {
        return this.ngayKtNganChan;
    }

    public ChiTietNganChanTaiSan ngayKtNganChan(LocalDate ngayKtNganChan) {
        this.setNgayKtNganChan(ngayKtNganChan);
        return this;
    }

    public void setNgayKtNganChan(LocalDate ngayKtNganChan) {
        this.ngayKtNganChan = ngayKtNganChan;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public ChiTietNganChanTaiSan trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public ChiTietNganChanTaiSan nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getLoaiNganChan() {
        return this.loaiNganChan;
    }

    public ChiTietNganChanTaiSan loaiNganChan(Long loaiNganChan) {
        this.setLoaiNganChan(loaiNganChan);
        return this;
    }

    public void setLoaiNganChan(Long loaiNganChan) {
        this.loaiNganChan = loaiNganChan;
    }

    public LocalDate getNgayCongVan() {
        return this.ngayCongVan;
    }

    public ChiTietNganChanTaiSan ngayCongVan(LocalDate ngayCongVan) {
        this.setNgayCongVan(ngayCongVan);
        return this;
    }

    public void setNgayCongVan(LocalDate ngayCongVan) {
        this.ngayCongVan = ngayCongVan;
    }

    public TaiSan getTaiSan() {
        return this.taiSan;
    }

    public void setTaiSan(TaiSan taiSan) {
        this.taiSan = taiSan;
    }

    public ChiTietNganChanTaiSan taiSan(TaiSan taiSan) {
        this.setTaiSan(taiSan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiTietNganChanTaiSan)) {
            return false;
        }
        return getIdNganChan() != null && getIdNganChan().equals(((ChiTietNganChanTaiSan) o).getIdNganChan());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiTietNganChanTaiSan{" +
            "idNganChan=" + getIdNganChan() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
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
