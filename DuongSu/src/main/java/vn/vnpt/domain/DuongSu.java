package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import vn.vnpt.domain.enumeration.LoaiDuongSu;
import vn.vnpt.domain.enumeration.LoaiGiayTo;

/**
 * A DuongSu.
 */
@Entity
@Table(name = "duong_su")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DuongSu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_duong_su")
    private Long idDuongSu;

    @Column(name = "ten_duong_su")
    private String tenDuongSu;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_duong_su")
    private LoaiDuongSu loaiDuongSu;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "fax")
    private String fax;

    @Column(name = "website")
    private String website;

    @Min(value = 0)
    @Max(value = 1)
    @Column(name = "trang_thai")
    private Integer trangThai;

    @Lob
    @Column(name = "thong_tin_ds")
    private String thongTinDs;

    @Column(name = "ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Column(name = "nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Column(name = "id_ds_goc")
    private Long idDsGoc;

    @Column(name = "id_master")
    private String idMaster;

    @Column(name = "id_don_vi")
    private Long idDonVi;

    @Column(name = "str_search")
    private String strSearch;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_giay_to")
    private LoaiGiayTo loaiGiayTo;

    @Column(name = "so_giay_to")
    private String soGiayTo;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "id_loai_ngan_chan")
    private Long idLoaiNganChan;

    @Min(value = 0)
    @Max(value = 1)
    @Column(name = "sync_status")
    private Integer syncStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idDuongSu")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idDuongSu" }, allowSetters = true)
    private Set<TaiSanDuongSu> taiSanDuongSus = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idDuongSu")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idDuongSu" }, allowSetters = true)
    private Set<QuanHeDuongSu> quanHeDuongSus = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idDuongSu")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idDuongSu" }, allowSetters = true)
    private Set<DanhSachDuongSu> danhSachDuongSus = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idDuongSu")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idDuongSu" }, allowSetters = true)
    private Set<DuongSuTrungCmnd> duongSuTrungCmnds = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idDuongSu")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idDuongSu" }, allowSetters = true)
    private Set<DuongSuTrungCmndBak> duongSuTrungCmndBaks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdDuongSu() {
        return this.idDuongSu;
    }

    public DuongSu idDuongSu(Long idDuongSu) {
        this.setIdDuongSu(idDuongSu);
        return this;
    }

    public void setIdDuongSu(Long idDuongSu) {
        this.idDuongSu = idDuongSu;
    }

    public String getTenDuongSu() {
        return this.tenDuongSu;
    }

    public DuongSu tenDuongSu(String tenDuongSu) {
        this.setTenDuongSu(tenDuongSu);
        return this;
    }

    public void setTenDuongSu(String tenDuongSu) {
        this.tenDuongSu = tenDuongSu;
    }

    public LoaiDuongSu getLoaiDuongSu() {
        return this.loaiDuongSu;
    }

    public DuongSu loaiDuongSu(LoaiDuongSu loaiDuongSu) {
        this.setLoaiDuongSu(loaiDuongSu);
        return this;
    }

    public void setLoaiDuongSu(LoaiDuongSu loaiDuongSu) {
        this.loaiDuongSu = loaiDuongSu;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public DuongSu diaChi(String diaChi) {
        this.setDiaChi(diaChi);
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public DuongSu soDienThoai(String soDienThoai) {
        this.setSoDienThoai(soDienThoai);
        return this;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return this.email;
    }

    public DuongSu email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return this.fax;
    }

    public DuongSu fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return this.website;
    }

    public DuongSu website(String website) {
        this.setWebsite(website);
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getTrangThai() {
        return this.trangThai;
    }

    public DuongSu trangThai(Integer trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getThongTinDs() {
        return this.thongTinDs;
    }

    public DuongSu thongTinDs(String thongTinDs) {
        this.setThongTinDs(thongTinDs);
        return this;
    }

    public void setThongTinDs(String thongTinDs) {
        this.thongTinDs = thongTinDs;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public DuongSu ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public DuongSu nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getIdDsGoc() {
        return this.idDsGoc;
    }

    public DuongSu idDsGoc(Long idDsGoc) {
        this.setIdDsGoc(idDsGoc);
        return this;
    }

    public void setIdDsGoc(Long idDsGoc) {
        this.idDsGoc = idDsGoc;
    }

    public String getIdMaster() {
        return this.idMaster;
    }

    public DuongSu idMaster(String idMaster) {
        this.setIdMaster(idMaster);
        return this;
    }

    public void setIdMaster(String idMaster) {
        this.idMaster = idMaster;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public DuongSu idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getStrSearch() {
        return this.strSearch;
    }

    public DuongSu strSearch(String strSearch) {
        this.setStrSearch(strSearch);
        return this;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public LoaiGiayTo getLoaiGiayTo() {
        return this.loaiGiayTo;
    }

    public DuongSu loaiGiayTo(LoaiGiayTo loaiGiayTo) {
        this.setLoaiGiayTo(loaiGiayTo);
        return this;
    }

    public void setLoaiGiayTo(LoaiGiayTo loaiGiayTo) {
        this.loaiGiayTo = loaiGiayTo;
    }

    public String getSoGiayTo() {
        return this.soGiayTo;
    }

    public DuongSu soGiayTo(String soGiayTo) {
        this.setSoGiayTo(soGiayTo);
        return this;
    }

    public void setSoGiayTo(String soGiayTo) {
        this.soGiayTo = soGiayTo;
    }

    public String getGhiChu() {
        return this.ghiChu;
    }

    public DuongSu ghiChu(String ghiChu) {
        this.setGhiChu(ghiChu);
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Long getIdLoaiNganChan() {
        return this.idLoaiNganChan;
    }

    public DuongSu idLoaiNganChan(Long idLoaiNganChan) {
        this.setIdLoaiNganChan(idLoaiNganChan);
        return this;
    }

    public void setIdLoaiNganChan(Long idLoaiNganChan) {
        this.idLoaiNganChan = idLoaiNganChan;
    }

    public Integer getSyncStatus() {
        return this.syncStatus;
    }

    public DuongSu syncStatus(Integer syncStatus) {
        this.setSyncStatus(syncStatus);
        return this;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    public Set<TaiSanDuongSu> getTaiSanDuongSus() {
        return this.taiSanDuongSus;
    }

    public void setTaiSanDuongSus(Set<TaiSanDuongSu> taiSanDuongSus) {
        if (this.taiSanDuongSus != null) {
            this.taiSanDuongSus.forEach(i -> i.setIdDuongSu(null));
        }
        if (taiSanDuongSus != null) {
            taiSanDuongSus.forEach(i -> i.setIdDuongSu(this));
        }
        this.taiSanDuongSus = taiSanDuongSus;
    }

    public DuongSu taiSanDuongSus(Set<TaiSanDuongSu> taiSanDuongSus) {
        this.setTaiSanDuongSus(taiSanDuongSus);
        return this;
    }

    public DuongSu addTaiSanDuongSu(TaiSanDuongSu taiSanDuongSu) {
        this.taiSanDuongSus.add(taiSanDuongSu);
        taiSanDuongSu.setIdDuongSu(this);
        return this;
    }

    public DuongSu removeTaiSanDuongSu(TaiSanDuongSu taiSanDuongSu) {
        this.taiSanDuongSus.remove(taiSanDuongSu);
        taiSanDuongSu.setIdDuongSu(null);
        return this;
    }

    public Set<QuanHeDuongSu> getQuanHeDuongSus() {
        return this.quanHeDuongSus;
    }

    public void setQuanHeDuongSus(Set<QuanHeDuongSu> quanHeDuongSus) {
        if (this.quanHeDuongSus != null) {
            this.quanHeDuongSus.forEach(i -> i.setIdDuongSu(null));
        }
        if (quanHeDuongSus != null) {
            quanHeDuongSus.forEach(i -> i.setIdDuongSu(this));
        }
        this.quanHeDuongSus = quanHeDuongSus;
    }

    public DuongSu quanHeDuongSus(Set<QuanHeDuongSu> quanHeDuongSus) {
        this.setQuanHeDuongSus(quanHeDuongSus);
        return this;
    }

    public DuongSu addQuanHeDuongSu(QuanHeDuongSu quanHeDuongSu) {
        this.quanHeDuongSus.add(quanHeDuongSu);
        quanHeDuongSu.setIdDuongSu(this);
        return this;
    }

    public DuongSu removeQuanHeDuongSu(QuanHeDuongSu quanHeDuongSu) {
        this.quanHeDuongSus.remove(quanHeDuongSu);
        quanHeDuongSu.setIdDuongSu(null);
        return this;
    }

    public Set<DanhSachDuongSu> getDanhSachDuongSus() {
        return this.danhSachDuongSus;
    }

    public void setDanhSachDuongSus(Set<DanhSachDuongSu> danhSachDuongSus) {
        if (this.danhSachDuongSus != null) {
            this.danhSachDuongSus.forEach(i -> i.setIdDuongSu(null));
        }
        if (danhSachDuongSus != null) {
            danhSachDuongSus.forEach(i -> i.setIdDuongSu(this));
        }
        this.danhSachDuongSus = danhSachDuongSus;
    }

    public DuongSu danhSachDuongSus(Set<DanhSachDuongSu> danhSachDuongSus) {
        this.setDanhSachDuongSus(danhSachDuongSus);
        return this;
    }

    public DuongSu addDanhSachDuongSu(DanhSachDuongSu danhSachDuongSu) {
        this.danhSachDuongSus.add(danhSachDuongSu);
        danhSachDuongSu.setIdDuongSu(this);
        return this;
    }

    public DuongSu removeDanhSachDuongSu(DanhSachDuongSu danhSachDuongSu) {
        this.danhSachDuongSus.remove(danhSachDuongSu);
        danhSachDuongSu.setIdDuongSu(null);
        return this;
    }

    public Set<DuongSuTrungCmnd> getDuongSuTrungCmnds() {
        return this.duongSuTrungCmnds;
    }

    public void setDuongSuTrungCmnds(Set<DuongSuTrungCmnd> duongSuTrungCmnds) {
        if (this.duongSuTrungCmnds != null) {
            this.duongSuTrungCmnds.forEach(i -> i.setIdDuongSu(null));
        }
        if (duongSuTrungCmnds != null) {
            duongSuTrungCmnds.forEach(i -> i.setIdDuongSu(this));
        }
        this.duongSuTrungCmnds = duongSuTrungCmnds;
    }

    public DuongSu duongSuTrungCmnds(Set<DuongSuTrungCmnd> duongSuTrungCmnds) {
        this.setDuongSuTrungCmnds(duongSuTrungCmnds);
        return this;
    }

    public DuongSu addDuongSuTrungCmnd(DuongSuTrungCmnd duongSuTrungCmnd) {
        this.duongSuTrungCmnds.add(duongSuTrungCmnd);
        duongSuTrungCmnd.setIdDuongSu(this);
        return this;
    }

    public DuongSu removeDuongSuTrungCmnd(DuongSuTrungCmnd duongSuTrungCmnd) {
        this.duongSuTrungCmnds.remove(duongSuTrungCmnd);
        duongSuTrungCmnd.setIdDuongSu(null);
        return this;
    }

    public Set<DuongSuTrungCmndBak> getDuongSuTrungCmndBaks() {
        return this.duongSuTrungCmndBaks;
    }

    public void setDuongSuTrungCmndBaks(Set<DuongSuTrungCmndBak> duongSuTrungCmndBaks) {
        if (this.duongSuTrungCmndBaks != null) {
            this.duongSuTrungCmndBaks.forEach(i -> i.setIdDuongSu(null));
        }
        if (duongSuTrungCmndBaks != null) {
            duongSuTrungCmndBaks.forEach(i -> i.setIdDuongSu(this));
        }
        this.duongSuTrungCmndBaks = duongSuTrungCmndBaks;
    }

    public DuongSu duongSuTrungCmndBaks(Set<DuongSuTrungCmndBak> duongSuTrungCmndBaks) {
        this.setDuongSuTrungCmndBaks(duongSuTrungCmndBaks);
        return this;
    }

    public DuongSu addDuongSuTrungCmndBak(DuongSuTrungCmndBak duongSuTrungCmndBak) {
        this.duongSuTrungCmndBaks.add(duongSuTrungCmndBak);
        duongSuTrungCmndBak.setIdDuongSu(this);
        return this;
    }

    public DuongSu removeDuongSuTrungCmndBak(DuongSuTrungCmndBak duongSuTrungCmndBak) {
        this.duongSuTrungCmndBaks.remove(duongSuTrungCmndBak);
        duongSuTrungCmndBak.setIdDuongSu(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DuongSu)) {
            return false;
        }
        return getIdDuongSu() != null && getIdDuongSu().equals(((DuongSu) o).getIdDuongSu());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DuongSu{" +
            "idDuongSu=" + getIdDuongSu() +
            ", tenDuongSu='" + getTenDuongSu() + "'" +
            ", loaiDuongSu='" + getLoaiDuongSu() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", email='" + getEmail() + "'" +
            ", fax='" + getFax() + "'" +
            ", website='" + getWebsite() + "'" +
            ", trangThai=" + getTrangThai() +
            ", thongTinDs='" + getThongTinDs() + "'" +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", idDsGoc=" + getIdDsGoc() +
            ", idMaster='" + getIdMaster() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", strSearch='" + getStrSearch() + "'" +
            ", loaiGiayTo='" + getLoaiGiayTo() + "'" +
            ", soGiayTo='" + getSoGiayTo() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", idLoaiNganChan=" + getIdLoaiNganChan() +
            ", syncStatus=" + getSyncStatus() +
            "}";
    }
}
