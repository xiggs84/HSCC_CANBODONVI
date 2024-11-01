package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TaiSan.
 */
@Entity
@Table(name = "tai_san")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaiSan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_tai_san")
    private Long idTaiSan;

    @Column(name = "ten_tai_san")
    private String tenTaiSan;

    @Column(name = "trang_thai")
    private Long trangThai;

    @Lob
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

    @Column(name = "loai_ngan_chan")
    private Long loaiNganChan;

    @Column(name = "sync_status")
    private Long syncStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taiSan" }, allowSetters = true)
    private Set<ThuaTach> thuaTaches = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taiSan" }, allowSetters = true)
    private Set<TaiSanDuongSu> taiSanDuongSus = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taiSan", "danhMucLoaiTaiSan", "tinhTrangTaiSan" }, allowSetters = true)
    private Set<TaiSanDgc> taiSanDgcs = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taiSan", "danhMucLoaiTaiSan" }, allowSetters = true)
    private Set<ThongTinCapNhatTaiSan> thongTinCapNhatTaiSans = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "loaiTaiSans", "danhSachTaiSans", "taiSanDgcs", "taiSanDatNhas", "thongTinCapNhatTaiSans" },
        allowSetters = true
    )
    private DanhMucLoaiTaiSan danhMucLoaiTaiSan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "tinhTrangs", "taiSanDgcs", "taiSanDatNhas" }, allowSetters = true)
    private TinhTrangTaiSan tinhTrangTaiSan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taiSan" }, allowSetters = true)
    private Set<ChiTietNganChanTaiSan> chiTietNganChanTaiSans = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdTaiSan() {
        return this.idTaiSan;
    }

    public TaiSan idTaiSan(Long idTaiSan) {
        this.setIdTaiSan(idTaiSan);
        return this;
    }

    public void setIdTaiSan(Long idTaiSan) {
        this.idTaiSan = idTaiSan;
    }

    public String getTenTaiSan() {
        return this.tenTaiSan;
    }

    public TaiSan tenTaiSan(String tenTaiSan) {
        this.setTenTaiSan(tenTaiSan);
        return this;
    }

    public void setTenTaiSan(String tenTaiSan) {
        this.tenTaiSan = tenTaiSan;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public TaiSan trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getThongTinTs() {
        return this.thongTinTs;
    }

    public TaiSan thongTinTs(String thongTinTs) {
        this.setThongTinTs(thongTinTs);
        return this;
    }

    public void setThongTinTs(String thongTinTs) {
        this.thongTinTs = thongTinTs;
    }

    public String getGhiChu() {
        return this.ghiChu;
    }

    public TaiSan ghiChu(String ghiChu) {
        this.setGhiChu(ghiChu);
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public TaiSan ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public TaiSan nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getIdDuongSu() {
        return this.idDuongSu;
    }

    public TaiSan idDuongSu(Long idDuongSu) {
        this.setIdDuongSu(idDuongSu);
        return this;
    }

    public void setIdDuongSu(Long idDuongSu) {
        this.idDuongSu = idDuongSu;
    }

    public Long getIdTsGoc() {
        return this.idTsGoc;
    }

    public TaiSan idTsGoc(Long idTsGoc) {
        this.setIdTsGoc(idTsGoc);
        return this;
    }

    public void setIdTsGoc(Long idTsGoc) {
        this.idTsGoc = idTsGoc;
    }

    public String getMaTaiSan() {
        return this.maTaiSan;
    }

    public TaiSan maTaiSan(String maTaiSan) {
        this.setMaTaiSan(maTaiSan);
        return this;
    }

    public void setMaTaiSan(String maTaiSan) {
        this.maTaiSan = maTaiSan;
    }

    public Long getIdLoaiNganChan() {
        return this.idLoaiNganChan;
    }

    public TaiSan idLoaiNganChan(Long idLoaiNganChan) {
        this.setIdLoaiNganChan(idLoaiNganChan);
        return this;
    }

    public void setIdLoaiNganChan(Long idLoaiNganChan) {
        this.idLoaiNganChan = idLoaiNganChan;
    }

    public LocalDate getNgayBdNganChan() {
        return this.ngayBdNganChan;
    }

    public TaiSan ngayBdNganChan(LocalDate ngayBdNganChan) {
        this.setNgayBdNganChan(ngayBdNganChan);
        return this;
    }

    public void setNgayBdNganChan(LocalDate ngayBdNganChan) {
        this.ngayBdNganChan = ngayBdNganChan;
    }

    public LocalDate getNgayKtNganChan() {
        return this.ngayKtNganChan;
    }

    public TaiSan ngayKtNganChan(LocalDate ngayKtNganChan) {
        this.setNgayKtNganChan(ngayKtNganChan);
        return this;
    }

    public void setNgayKtNganChan(LocalDate ngayKtNganChan) {
        this.ngayKtNganChan = ngayKtNganChan;
    }

    public Long getIdMaster() {
        return this.idMaster;
    }

    public TaiSan idMaster(Long idMaster) {
        this.setIdMaster(idMaster);
        return this;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public String getStrSearch() {
        return this.strSearch;
    }

    public TaiSan strSearch(String strSearch) {
        this.setStrSearch(strSearch);
        return this;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public TaiSan idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getSoHsCv() {
        return this.soHsCv;
    }

    public TaiSan soHsCv(Long soHsCv) {
        this.setSoHsCv(soHsCv);
        return this;
    }

    public void setSoHsCv(Long soHsCv) {
        this.soHsCv = soHsCv;
    }

    public Long getSoCc() {
        return this.soCc;
    }

    public TaiSan soCc(Long soCc) {
        this.setSoCc(soCc);
        return this;
    }

    public void setSoCc(Long soCc) {
        this.soCc = soCc;
    }

    public Long getSoVaoSo() {
        return this.soVaoSo;
    }

    public TaiSan soVaoSo(Long soVaoSo) {
        this.setSoVaoSo(soVaoSo);
        return this;
    }

    public void setSoVaoSo(Long soVaoSo) {
        this.soVaoSo = soVaoSo;
    }

    public String getMoTa() {
        return this.moTa;
    }

    public TaiSan moTa(String moTa) {
        this.setMoTa(moTa);
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Long getLoaiNganChan() {
        return this.loaiNganChan;
    }

    public TaiSan loaiNganChan(Long loaiNganChan) {
        this.setLoaiNganChan(loaiNganChan);
        return this;
    }

    public void setLoaiNganChan(Long loaiNganChan) {
        this.loaiNganChan = loaiNganChan;
    }

    public Long getSyncStatus() {
        return this.syncStatus;
    }

    public TaiSan syncStatus(Long syncStatus) {
        this.setSyncStatus(syncStatus);
        return this;
    }

    public void setSyncStatus(Long syncStatus) {
        this.syncStatus = syncStatus;
    }

    public Set<ThuaTach> getThuaTaches() {
        return this.thuaTaches;
    }

    public void setThuaTaches(Set<ThuaTach> thuaTaches) {
        if (this.thuaTaches != null) {
            this.thuaTaches.forEach(i -> i.setTaiSan(null));
        }
        if (thuaTaches != null) {
            thuaTaches.forEach(i -> i.setTaiSan(this));
        }
        this.thuaTaches = thuaTaches;
    }

    public TaiSan thuaTaches(Set<ThuaTach> thuaTaches) {
        this.setThuaTaches(thuaTaches);
        return this;
    }

    public TaiSan addThuaTach(ThuaTach thuaTach) {
        this.thuaTaches.add(thuaTach);
        thuaTach.setTaiSan(this);
        return this;
    }

    public TaiSan removeThuaTach(ThuaTach thuaTach) {
        this.thuaTaches.remove(thuaTach);
        thuaTach.setTaiSan(null);
        return this;
    }

    public Set<TaiSanDuongSu> getTaiSanDuongSus() {
        return this.taiSanDuongSus;
    }

    public void setTaiSanDuongSus(Set<TaiSanDuongSu> taiSanDuongSus) {
        if (this.taiSanDuongSus != null) {
            this.taiSanDuongSus.forEach(i -> i.setTaiSan(null));
        }
        if (taiSanDuongSus != null) {
            taiSanDuongSus.forEach(i -> i.setTaiSan(this));
        }
        this.taiSanDuongSus = taiSanDuongSus;
    }

    public TaiSan taiSanDuongSus(Set<TaiSanDuongSu> taiSanDuongSus) {
        this.setTaiSanDuongSus(taiSanDuongSus);
        return this;
    }

    public TaiSan addTaiSanDuongSu(TaiSanDuongSu taiSanDuongSu) {
        this.taiSanDuongSus.add(taiSanDuongSu);
        taiSanDuongSu.setTaiSan(this);
        return this;
    }

    public TaiSan removeTaiSanDuongSu(TaiSanDuongSu taiSanDuongSu) {
        this.taiSanDuongSus.remove(taiSanDuongSu);
        taiSanDuongSu.setTaiSan(null);
        return this;
    }

    public Set<TaiSanDgc> getTaiSanDgcs() {
        return this.taiSanDgcs;
    }

    public void setTaiSanDgcs(Set<TaiSanDgc> taiSanDgcs) {
        if (this.taiSanDgcs != null) {
            this.taiSanDgcs.forEach(i -> i.setTaiSan(null));
        }
        if (taiSanDgcs != null) {
            taiSanDgcs.forEach(i -> i.setTaiSan(this));
        }
        this.taiSanDgcs = taiSanDgcs;
    }

    public TaiSan taiSanDgcs(Set<TaiSanDgc> taiSanDgcs) {
        this.setTaiSanDgcs(taiSanDgcs);
        return this;
    }

    public TaiSan addTaiSanDgc(TaiSanDgc taiSanDgc) {
        this.taiSanDgcs.add(taiSanDgc);
        taiSanDgc.setTaiSan(this);
        return this;
    }

    public TaiSan removeTaiSanDgc(TaiSanDgc taiSanDgc) {
        this.taiSanDgcs.remove(taiSanDgc);
        taiSanDgc.setTaiSan(null);
        return this;
    }

    public Set<ThongTinCapNhatTaiSan> getThongTinCapNhatTaiSans() {
        return this.thongTinCapNhatTaiSans;
    }

    public void setThongTinCapNhatTaiSans(Set<ThongTinCapNhatTaiSan> thongTinCapNhatTaiSans) {
        if (this.thongTinCapNhatTaiSans != null) {
            this.thongTinCapNhatTaiSans.forEach(i -> i.setTaiSan(null));
        }
        if (thongTinCapNhatTaiSans != null) {
            thongTinCapNhatTaiSans.forEach(i -> i.setTaiSan(this));
        }
        this.thongTinCapNhatTaiSans = thongTinCapNhatTaiSans;
    }

    public TaiSan thongTinCapNhatTaiSans(Set<ThongTinCapNhatTaiSan> thongTinCapNhatTaiSans) {
        this.setThongTinCapNhatTaiSans(thongTinCapNhatTaiSans);
        return this;
    }

    public TaiSan addThongTinCapNhatTaiSan(ThongTinCapNhatTaiSan thongTinCapNhatTaiSan) {
        this.thongTinCapNhatTaiSans.add(thongTinCapNhatTaiSan);
        thongTinCapNhatTaiSan.setTaiSan(this);
        return this;
    }

    public TaiSan removeThongTinCapNhatTaiSan(ThongTinCapNhatTaiSan thongTinCapNhatTaiSan) {
        this.thongTinCapNhatTaiSans.remove(thongTinCapNhatTaiSan);
        thongTinCapNhatTaiSan.setTaiSan(null);
        return this;
    }

    public DanhMucLoaiTaiSan getDanhMucLoaiTaiSan() {
        return this.danhMucLoaiTaiSan;
    }

    public void setDanhMucLoaiTaiSan(DanhMucLoaiTaiSan danhMucLoaiTaiSan) {
        this.danhMucLoaiTaiSan = danhMucLoaiTaiSan;
    }

    public TaiSan danhMucLoaiTaiSan(DanhMucLoaiTaiSan danhMucLoaiTaiSan) {
        this.setDanhMucLoaiTaiSan(danhMucLoaiTaiSan);
        return this;
    }

    public TinhTrangTaiSan getTinhTrangTaiSan() {
        return this.tinhTrangTaiSan;
    }

    public void setTinhTrangTaiSan(TinhTrangTaiSan tinhTrangTaiSan) {
        this.tinhTrangTaiSan = tinhTrangTaiSan;
    }

    public TaiSan tinhTrangTaiSan(TinhTrangTaiSan tinhTrangTaiSan) {
        this.setTinhTrangTaiSan(tinhTrangTaiSan);
        return this;
    }

    public Set<ChiTietNganChanTaiSan> getChiTietNganChanTaiSans() {
        return this.chiTietNganChanTaiSans;
    }

    public void setChiTietNganChanTaiSans(Set<ChiTietNganChanTaiSan> chiTietNganChanTaiSans) {
        if (this.chiTietNganChanTaiSans != null) {
            this.chiTietNganChanTaiSans.forEach(i -> i.setTaiSan(null));
        }
        if (chiTietNganChanTaiSans != null) {
            chiTietNganChanTaiSans.forEach(i -> i.setTaiSan(this));
        }
        this.chiTietNganChanTaiSans = chiTietNganChanTaiSans;
    }

    public TaiSan chiTietNganChanTaiSans(Set<ChiTietNganChanTaiSan> chiTietNganChanTaiSans) {
        this.setChiTietNganChanTaiSans(chiTietNganChanTaiSans);
        return this;
    }

    public TaiSan addChiTietNganChanTaiSan(ChiTietNganChanTaiSan chiTietNganChanTaiSan) {
        this.chiTietNganChanTaiSans.add(chiTietNganChanTaiSan);
        chiTietNganChanTaiSan.setTaiSan(this);
        return this;
    }

    public TaiSan removeChiTietNganChanTaiSan(ChiTietNganChanTaiSan chiTietNganChanTaiSan) {
        this.chiTietNganChanTaiSans.remove(chiTietNganChanTaiSan);
        chiTietNganChanTaiSan.setTaiSan(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaiSan)) {
            return false;
        }
        return getIdTaiSan() != null && getIdTaiSan().equals(((TaiSan) o).getIdTaiSan());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaiSan{" +
            "idTaiSan=" + getIdTaiSan() +
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
            ", loaiNganChan=" + getLoaiNganChan() +
            ", syncStatus=" + getSyncStatus() +
            "}";
    }
}
