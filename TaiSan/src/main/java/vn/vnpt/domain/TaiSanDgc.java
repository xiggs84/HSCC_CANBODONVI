package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TaiSanDgc.
 */
@Entity
@Table(name = "tai_san_dgc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaiSanDgc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ten_tai_san")
    private String tenTaiSan;

    @Column(name = "trang_thai")
    private Long trangThai;

    @Column(name = "thong_tin_ts")
    private String thongTinTs;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Column(name = "nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Column(name = "id_duong_su")
    private Long idDuongSu;

    @Column(name = "id_ts_goc")
    private Long idTsGoc;

    @Column(name = "ma_tai_san")
    private String maTaiSan;

    @Column(name = "id_loai_ngan_chan")
    private Long idLoaiNganChan;

    @Column(name = "ngay_bd_ngan_chan")
    private LocalDate ngayBdNganChan;

    @Column(name = "ngay_kt_ngan_chan")
    private LocalDate ngayKtNganChan;

    @Column(name = "id_master")
    private Long idMaster;

    @Column(name = "str_search")
    private String strSearch;

    @Column(name = "id_don_vi")
    private Long idDonVi;

    @Column(name = "so_hs_cv")
    private Long soHsCv;

    @Column(name = "so_cc")
    private Long soCc;

    @Column(name = "so_vao_so")
    private Long soVaoSo;

    @Column(name = "mo_ta")
    private String moTa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "thuaTaches",
            "taiSanDuongSus",
            "taiSanDgcs",
            "thongTinCapNhatTaiSans",
            "danhMucLoaiTaiSan",
            "tinhTrangTaiSan",
            "chiTietNganChanTaiSans",
        },
        allowSetters = true
    )
    private TaiSan taiSan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "loaiTaiSans", "danhSachTaiSans", "taiSanDgcs", "taiSanDatNhas", "thongTinCapNhatTaiSans" },
        allowSetters = true
    )
    private DanhMucLoaiTaiSan danhMucLoaiTaiSan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "tinhTrangs", "taiSanDgcs", "taiSanDatNhas" }, allowSetters = true)
    private TinhTrangTaiSan tinhTrangTaiSan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TaiSanDgc id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenTaiSan() {
        return this.tenTaiSan;
    }

    public TaiSanDgc tenTaiSan(String tenTaiSan) {
        this.setTenTaiSan(tenTaiSan);
        return this;
    }

    public void setTenTaiSan(String tenTaiSan) {
        this.tenTaiSan = tenTaiSan;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public TaiSanDgc trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getThongTinTs() {
        return this.thongTinTs;
    }

    public TaiSanDgc thongTinTs(String thongTinTs) {
        this.setThongTinTs(thongTinTs);
        return this;
    }

    public void setThongTinTs(String thongTinTs) {
        this.thongTinTs = thongTinTs;
    }

    public String getGhiChu() {
        return this.ghiChu;
    }

    public TaiSanDgc ghiChu(String ghiChu) {
        this.setGhiChu(ghiChu);
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public TaiSanDgc ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public TaiSanDgc nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getIdDuongSu() {
        return this.idDuongSu;
    }

    public TaiSanDgc idDuongSu(Long idDuongSu) {
        this.setIdDuongSu(idDuongSu);
        return this;
    }

    public void setIdDuongSu(Long idDuongSu) {
        this.idDuongSu = idDuongSu;
    }

    public Long getIdTsGoc() {
        return this.idTsGoc;
    }

    public TaiSanDgc idTsGoc(Long idTsGoc) {
        this.setIdTsGoc(idTsGoc);
        return this;
    }

    public void setIdTsGoc(Long idTsGoc) {
        this.idTsGoc = idTsGoc;
    }

    public String getMaTaiSan() {
        return this.maTaiSan;
    }

    public TaiSanDgc maTaiSan(String maTaiSan) {
        this.setMaTaiSan(maTaiSan);
        return this;
    }

    public void setMaTaiSan(String maTaiSan) {
        this.maTaiSan = maTaiSan;
    }

    public Long getIdLoaiNganChan() {
        return this.idLoaiNganChan;
    }

    public TaiSanDgc idLoaiNganChan(Long idLoaiNganChan) {
        this.setIdLoaiNganChan(idLoaiNganChan);
        return this;
    }

    public void setIdLoaiNganChan(Long idLoaiNganChan) {
        this.idLoaiNganChan = idLoaiNganChan;
    }

    public LocalDate getNgayBdNganChan() {
        return this.ngayBdNganChan;
    }

    public TaiSanDgc ngayBdNganChan(LocalDate ngayBdNganChan) {
        this.setNgayBdNganChan(ngayBdNganChan);
        return this;
    }

    public void setNgayBdNganChan(LocalDate ngayBdNganChan) {
        this.ngayBdNganChan = ngayBdNganChan;
    }

    public LocalDate getNgayKtNganChan() {
        return this.ngayKtNganChan;
    }

    public TaiSanDgc ngayKtNganChan(LocalDate ngayKtNganChan) {
        this.setNgayKtNganChan(ngayKtNganChan);
        return this;
    }

    public void setNgayKtNganChan(LocalDate ngayKtNganChan) {
        this.ngayKtNganChan = ngayKtNganChan;
    }

    public Long getIdMaster() {
        return this.idMaster;
    }

    public TaiSanDgc idMaster(Long idMaster) {
        this.setIdMaster(idMaster);
        return this;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public String getStrSearch() {
        return this.strSearch;
    }

    public TaiSanDgc strSearch(String strSearch) {
        this.setStrSearch(strSearch);
        return this;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public TaiSanDgc idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getSoHsCv() {
        return this.soHsCv;
    }

    public TaiSanDgc soHsCv(Long soHsCv) {
        this.setSoHsCv(soHsCv);
        return this;
    }

    public void setSoHsCv(Long soHsCv) {
        this.soHsCv = soHsCv;
    }

    public Long getSoCc() {
        return this.soCc;
    }

    public TaiSanDgc soCc(Long soCc) {
        this.setSoCc(soCc);
        return this;
    }

    public void setSoCc(Long soCc) {
        this.soCc = soCc;
    }

    public Long getSoVaoSo() {
        return this.soVaoSo;
    }

    public TaiSanDgc soVaoSo(Long soVaoSo) {
        this.setSoVaoSo(soVaoSo);
        return this;
    }

    public void setSoVaoSo(Long soVaoSo) {
        this.soVaoSo = soVaoSo;
    }

    public String getMoTa() {
        return this.moTa;
    }

    public TaiSanDgc moTa(String moTa) {
        this.setMoTa(moTa);
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public TaiSan getTaiSan() {
        return this.taiSan;
    }

    public void setTaiSan(TaiSan taiSan) {
        this.taiSan = taiSan;
    }

    public TaiSanDgc taiSan(TaiSan taiSan) {
        this.setTaiSan(taiSan);
        return this;
    }

    public DanhMucLoaiTaiSan getDanhMucLoaiTaiSan() {
        return this.danhMucLoaiTaiSan;
    }

    public void setDanhMucLoaiTaiSan(DanhMucLoaiTaiSan danhMucLoaiTaiSan) {
        this.danhMucLoaiTaiSan = danhMucLoaiTaiSan;
    }

    public TaiSanDgc danhMucLoaiTaiSan(DanhMucLoaiTaiSan danhMucLoaiTaiSan) {
        this.setDanhMucLoaiTaiSan(danhMucLoaiTaiSan);
        return this;
    }

    public TinhTrangTaiSan getTinhTrangTaiSan() {
        return this.tinhTrangTaiSan;
    }

    public void setTinhTrangTaiSan(TinhTrangTaiSan tinhTrangTaiSan) {
        this.tinhTrangTaiSan = tinhTrangTaiSan;
    }

    public TaiSanDgc tinhTrangTaiSan(TinhTrangTaiSan tinhTrangTaiSan) {
        this.setTinhTrangTaiSan(tinhTrangTaiSan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaiSanDgc)) {
            return false;
        }
        return getId() != null && getId().equals(((TaiSanDgc) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaiSanDgc{" +
            "id=" + getId() +
            ", tenTaiSan='" + getTenTaiSan() + "'" +
            ", trangThai=" + getTrangThai() +
            ", thongTinTs='" + getThongTinTs() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", idDuongSu=" + getIdDuongSu() +
            ", idTsGoc=" + getIdTsGoc() +
            ", maTaiSan='" + getMaTaiSan() + "'" +
            ", idLoaiNganChan=" + getIdLoaiNganChan() +
            ", ngayBdNganChan='" + getNgayBdNganChan() + "'" +
            ", ngayKtNganChan='" + getNgayKtNganChan() + "'" +
            ", idMaster=" + getIdMaster() +
            ", strSearch='" + getStrSearch() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", soHsCv=" + getSoHsCv() +
            ", soCc=" + getSoCc() +
            ", soVaoSo=" + getSoVaoSo() +
            ", moTa='" + getMoTa() + "'" +
            "}";
    }
}
