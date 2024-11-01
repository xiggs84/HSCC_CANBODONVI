package vn.vnpt.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A HopDongCongChung.
 */
@Document(collection = "hop_dong_cong_chung")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HopDongCongChung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("ngay_lap_hd")
    private LocalDate ngayLapHd;

    @Field("nguoi_lap_hd")
    private Long nguoiLapHd;

    @Field("thong_tin_duong_su")
    private String thongTinDuongSu;

    @Field("thong_tin_tai_san")
    private String thongTinTaiSan;

    @Field("thong_tin_van_ban")
    private String thongTinVanBan;

    @Field("trang_thai")
    private Long trangThai;

    @Field("id_loai_hd")
    private String idLoaiHd;

    @Field("dieu_khoan_hd")
    private String dieuKhoanHd;

    @Field("id_don_vi")
    private Long idDonVi;

    @Field("ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Field("nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Field("id_hd_goc")
    private String idHdGoc;

    @Field("thong_tin_chuyen_nhuong")
    private String thongTinChuyenNhuong;

    @Field("ma_hop_dong")
    private String maHopDong;

    @Field("src_hop_dong")
    private String srcHopDong;

    @Field("ngay_hen")
    private LocalDate ngayHen;

    @Field("id_so_cong_chung")
    private String idSoCongChung;

    @Field("so_cong_chung")
    private String soCongChung;

    @Field("cong_chung_vien")
    private Long congChungVien;

    @Field("ngay_ky_hd")
    private LocalDate ngayKyHd;

    @Field("nguoi_rut_trich")
    private Long nguoiRutTrich;

    @Field("so_tien_rut_trich")
    private Long soTienRutTrich;

    @Field("ngay_rut_trich")
    private LocalDate ngayRutTrich;

    @Field("hd_thu_cong")
    private Long hdThuCong;

    @Field("trang_thai_rut_trich")
    private Long trangThaiRutTrich;

    @Field("chu_ky_ngoai_tru_so")
    private Long chuKyNgoaiTruSo;

    @Field("str_search")
    private String strSearch;

    @Field("id_master")
    private Long idMaster;

    @Field("id_hd_sd_hb")
    private Long idHdSdHb;

    @Field("src_dm_master")
    private String srcDmMaster;

    @Field("rep_ref_unique")
    private Long repRefUnique;

    @Field("ngay_text")
    private String ngayText;

    @Field("ngay_num")
    private Long ngayNum;

    @Field("ngay_thao_tac_rut_trich")
    private LocalDate ngayThaoTacRutTrich;

    @Field("thu_lao_cong_chung")
    private Long thuLaoCongChung;

    @Field("quyen_lai_st")
    private String quyenLaiSt;

    @Field("so_lai_st")
    private String soLaiSt;

    @Field("quyen_lai_tl")
    private String quyenLaiTl;

    @Field("so_lai_tl")
    private String soLaiTl;

    @Field("src_ky_so_pdf")
    private String srcKySoPdf;

    @Field("src_ky_so_pdf_signed")
    private String srcKySoPdfSigned;

    @Field("sync_status")
    private Long syncStatus;

    @Field("ngay_rut_trich_text")
    private String ngayRutTrichText;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public HopDongCongChung id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getNgayLapHd() {
        return this.ngayLapHd;
    }

    public HopDongCongChung ngayLapHd(LocalDate ngayLapHd) {
        this.setNgayLapHd(ngayLapHd);
        return this;
    }

    public void setNgayLapHd(LocalDate ngayLapHd) {
        this.ngayLapHd = ngayLapHd;
    }

    public Long getNguoiLapHd() {
        return this.nguoiLapHd;
    }

    public HopDongCongChung nguoiLapHd(Long nguoiLapHd) {
        this.setNguoiLapHd(nguoiLapHd);
        return this;
    }

    public void setNguoiLapHd(Long nguoiLapHd) {
        this.nguoiLapHd = nguoiLapHd;
    }

    public String getThongTinDuongSu() {
        return this.thongTinDuongSu;
    }

    public HopDongCongChung thongTinDuongSu(String thongTinDuongSu) {
        this.setThongTinDuongSu(thongTinDuongSu);
        return this;
    }

    public void setThongTinDuongSu(String thongTinDuongSu) {
        this.thongTinDuongSu = thongTinDuongSu;
    }

    public String getThongTinTaiSan() {
        return this.thongTinTaiSan;
    }

    public HopDongCongChung thongTinTaiSan(String thongTinTaiSan) {
        this.setThongTinTaiSan(thongTinTaiSan);
        return this;
    }

    public void setThongTinTaiSan(String thongTinTaiSan) {
        this.thongTinTaiSan = thongTinTaiSan;
    }

    public String getThongTinVanBan() {
        return this.thongTinVanBan;
    }

    public HopDongCongChung thongTinVanBan(String thongTinVanBan) {
        this.setThongTinVanBan(thongTinVanBan);
        return this;
    }

    public void setThongTinVanBan(String thongTinVanBan) {
        this.thongTinVanBan = thongTinVanBan;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public HopDongCongChung trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdLoaiHd() {
        return this.idLoaiHd;
    }

    public HopDongCongChung idLoaiHd(String idLoaiHd) {
        this.setIdLoaiHd(idLoaiHd);
        return this;
    }

    public void setIdLoaiHd(String idLoaiHd) {
        this.idLoaiHd = idLoaiHd;
    }

    public String getDieuKhoanHd() {
        return this.dieuKhoanHd;
    }

    public HopDongCongChung dieuKhoanHd(String dieuKhoanHd) {
        this.setDieuKhoanHd(dieuKhoanHd);
        return this;
    }

    public void setDieuKhoanHd(String dieuKhoanHd) {
        this.dieuKhoanHd = dieuKhoanHd;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public HopDongCongChung idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public HopDongCongChung ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public HopDongCongChung nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public String getIdHdGoc() {
        return this.idHdGoc;
    }

    public HopDongCongChung idHdGoc(String idHdGoc) {
        this.setIdHdGoc(idHdGoc);
        return this;
    }

    public void setIdHdGoc(String idHdGoc) {
        this.idHdGoc = idHdGoc;
    }

    public String getThongTinChuyenNhuong() {
        return this.thongTinChuyenNhuong;
    }

    public HopDongCongChung thongTinChuyenNhuong(String thongTinChuyenNhuong) {
        this.setThongTinChuyenNhuong(thongTinChuyenNhuong);
        return this;
    }

    public void setThongTinChuyenNhuong(String thongTinChuyenNhuong) {
        this.thongTinChuyenNhuong = thongTinChuyenNhuong;
    }

    public String getMaHopDong() {
        return this.maHopDong;
    }

    public HopDongCongChung maHopDong(String maHopDong) {
        this.setMaHopDong(maHopDong);
        return this;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public String getSrcHopDong() {
        return this.srcHopDong;
    }

    public HopDongCongChung srcHopDong(String srcHopDong) {
        this.setSrcHopDong(srcHopDong);
        return this;
    }

    public void setSrcHopDong(String srcHopDong) {
        this.srcHopDong = srcHopDong;
    }

    public LocalDate getNgayHen() {
        return this.ngayHen;
    }

    public HopDongCongChung ngayHen(LocalDate ngayHen) {
        this.setNgayHen(ngayHen);
        return this;
    }

    public void setNgayHen(LocalDate ngayHen) {
        this.ngayHen = ngayHen;
    }

    public String getIdSoCongChung() {
        return this.idSoCongChung;
    }

    public HopDongCongChung idSoCongChung(String idSoCongChung) {
        this.setIdSoCongChung(idSoCongChung);
        return this;
    }

    public void setIdSoCongChung(String idSoCongChung) {
        this.idSoCongChung = idSoCongChung;
    }

    public String getSoCongChung() {
        return this.soCongChung;
    }

    public HopDongCongChung soCongChung(String soCongChung) {
        this.setSoCongChung(soCongChung);
        return this;
    }

    public void setSoCongChung(String soCongChung) {
        this.soCongChung = soCongChung;
    }

    public Long getCongChungVien() {
        return this.congChungVien;
    }

    public HopDongCongChung congChungVien(Long congChungVien) {
        this.setCongChungVien(congChungVien);
        return this;
    }

    public void setCongChungVien(Long congChungVien) {
        this.congChungVien = congChungVien;
    }

    public LocalDate getNgayKyHd() {
        return this.ngayKyHd;
    }

    public HopDongCongChung ngayKyHd(LocalDate ngayKyHd) {
        this.setNgayKyHd(ngayKyHd);
        return this;
    }

    public void setNgayKyHd(LocalDate ngayKyHd) {
        this.ngayKyHd = ngayKyHd;
    }

    public Long getNguoiRutTrich() {
        return this.nguoiRutTrich;
    }

    public HopDongCongChung nguoiRutTrich(Long nguoiRutTrich) {
        this.setNguoiRutTrich(nguoiRutTrich);
        return this;
    }

    public void setNguoiRutTrich(Long nguoiRutTrich) {
        this.nguoiRutTrich = nguoiRutTrich;
    }

    public Long getSoTienRutTrich() {
        return this.soTienRutTrich;
    }

    public HopDongCongChung soTienRutTrich(Long soTienRutTrich) {
        this.setSoTienRutTrich(soTienRutTrich);
        return this;
    }

    public void setSoTienRutTrich(Long soTienRutTrich) {
        this.soTienRutTrich = soTienRutTrich;
    }

    public LocalDate getNgayRutTrich() {
        return this.ngayRutTrich;
    }

    public HopDongCongChung ngayRutTrich(LocalDate ngayRutTrich) {
        this.setNgayRutTrich(ngayRutTrich);
        return this;
    }

    public void setNgayRutTrich(LocalDate ngayRutTrich) {
        this.ngayRutTrich = ngayRutTrich;
    }

    public Long getHdThuCong() {
        return this.hdThuCong;
    }

    public HopDongCongChung hdThuCong(Long hdThuCong) {
        this.setHdThuCong(hdThuCong);
        return this;
    }

    public void setHdThuCong(Long hdThuCong) {
        this.hdThuCong = hdThuCong;
    }

    public Long getTrangThaiRutTrich() {
        return this.trangThaiRutTrich;
    }

    public HopDongCongChung trangThaiRutTrich(Long trangThaiRutTrich) {
        this.setTrangThaiRutTrich(trangThaiRutTrich);
        return this;
    }

    public void setTrangThaiRutTrich(Long trangThaiRutTrich) {
        this.trangThaiRutTrich = trangThaiRutTrich;
    }

    public Long getChuKyNgoaiTruSo() {
        return this.chuKyNgoaiTruSo;
    }

    public HopDongCongChung chuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.setChuKyNgoaiTruSo(chuKyNgoaiTruSo);
        return this;
    }

    public void setChuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.chuKyNgoaiTruSo = chuKyNgoaiTruSo;
    }

    public String getStrSearch() {
        return this.strSearch;
    }

    public HopDongCongChung strSearch(String strSearch) {
        this.setStrSearch(strSearch);
        return this;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public Long getIdMaster() {
        return this.idMaster;
    }

    public HopDongCongChung idMaster(Long idMaster) {
        this.setIdMaster(idMaster);
        return this;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public Long getIdHdSdHb() {
        return this.idHdSdHb;
    }

    public HopDongCongChung idHdSdHb(Long idHdSdHb) {
        this.setIdHdSdHb(idHdSdHb);
        return this;
    }

    public void setIdHdSdHb(Long idHdSdHb) {
        this.idHdSdHb = idHdSdHb;
    }

    public String getSrcDmMaster() {
        return this.srcDmMaster;
    }

    public HopDongCongChung srcDmMaster(String srcDmMaster) {
        this.setSrcDmMaster(srcDmMaster);
        return this;
    }

    public void setSrcDmMaster(String srcDmMaster) {
        this.srcDmMaster = srcDmMaster;
    }

    public Long getRepRefUnique() {
        return this.repRefUnique;
    }

    public HopDongCongChung repRefUnique(Long repRefUnique) {
        this.setRepRefUnique(repRefUnique);
        return this;
    }

    public void setRepRefUnique(Long repRefUnique) {
        this.repRefUnique = repRefUnique;
    }

    public String getNgayText() {
        return this.ngayText;
    }

    public HopDongCongChung ngayText(String ngayText) {
        this.setNgayText(ngayText);
        return this;
    }

    public void setNgayText(String ngayText) {
        this.ngayText = ngayText;
    }

    public Long getNgayNum() {
        return this.ngayNum;
    }

    public HopDongCongChung ngayNum(Long ngayNum) {
        this.setNgayNum(ngayNum);
        return this;
    }

    public void setNgayNum(Long ngayNum) {
        this.ngayNum = ngayNum;
    }

    public LocalDate getNgayThaoTacRutTrich() {
        return this.ngayThaoTacRutTrich;
    }

    public HopDongCongChung ngayThaoTacRutTrich(LocalDate ngayThaoTacRutTrich) {
        this.setNgayThaoTacRutTrich(ngayThaoTacRutTrich);
        return this;
    }

    public void setNgayThaoTacRutTrich(LocalDate ngayThaoTacRutTrich) {
        this.ngayThaoTacRutTrich = ngayThaoTacRutTrich;
    }

    public Long getThuLaoCongChung() {
        return this.thuLaoCongChung;
    }

    public HopDongCongChung thuLaoCongChung(Long thuLaoCongChung) {
        this.setThuLaoCongChung(thuLaoCongChung);
        return this;
    }

    public void setThuLaoCongChung(Long thuLaoCongChung) {
        this.thuLaoCongChung = thuLaoCongChung;
    }

    public String getQuyenLaiSt() {
        return this.quyenLaiSt;
    }

    public HopDongCongChung quyenLaiSt(String quyenLaiSt) {
        this.setQuyenLaiSt(quyenLaiSt);
        return this;
    }

    public void setQuyenLaiSt(String quyenLaiSt) {
        this.quyenLaiSt = quyenLaiSt;
    }

    public String getSoLaiSt() {
        return this.soLaiSt;
    }

    public HopDongCongChung soLaiSt(String soLaiSt) {
        this.setSoLaiSt(soLaiSt);
        return this;
    }

    public void setSoLaiSt(String soLaiSt) {
        this.soLaiSt = soLaiSt;
    }

    public String getQuyenLaiTl() {
        return this.quyenLaiTl;
    }

    public HopDongCongChung quyenLaiTl(String quyenLaiTl) {
        this.setQuyenLaiTl(quyenLaiTl);
        return this;
    }

    public void setQuyenLaiTl(String quyenLaiTl) {
        this.quyenLaiTl = quyenLaiTl;
    }

    public String getSoLaiTl() {
        return this.soLaiTl;
    }

    public HopDongCongChung soLaiTl(String soLaiTl) {
        this.setSoLaiTl(soLaiTl);
        return this;
    }

    public void setSoLaiTl(String soLaiTl) {
        this.soLaiTl = soLaiTl;
    }

    public String getSrcKySoPdf() {
        return this.srcKySoPdf;
    }

    public HopDongCongChung srcKySoPdf(String srcKySoPdf) {
        this.setSrcKySoPdf(srcKySoPdf);
        return this;
    }

    public void setSrcKySoPdf(String srcKySoPdf) {
        this.srcKySoPdf = srcKySoPdf;
    }

    public String getSrcKySoPdfSigned() {
        return this.srcKySoPdfSigned;
    }

    public HopDongCongChung srcKySoPdfSigned(String srcKySoPdfSigned) {
        this.setSrcKySoPdfSigned(srcKySoPdfSigned);
        return this;
    }

    public void setSrcKySoPdfSigned(String srcKySoPdfSigned) {
        this.srcKySoPdfSigned = srcKySoPdfSigned;
    }

    public Long getSyncStatus() {
        return this.syncStatus;
    }

    public HopDongCongChung syncStatus(Long syncStatus) {
        this.setSyncStatus(syncStatus);
        return this;
    }

    public void setSyncStatus(Long syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getNgayRutTrichText() {
        return this.ngayRutTrichText;
    }

    public HopDongCongChung ngayRutTrichText(String ngayRutTrichText) {
        this.setNgayRutTrichText(ngayRutTrichText);
        return this;
    }

    public void setNgayRutTrichText(String ngayRutTrichText) {
        this.ngayRutTrichText = ngayRutTrichText;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HopDongCongChung)) {
            return false;
        }
        return getId() != null && getId().equals(((HopDongCongChung) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HopDongCongChung{" +
            "id=" + getId() +
            ", ngayLapHd='" + getNgayLapHd() + "'" +
            ", nguoiLapHd=" + getNguoiLapHd() +
            ", thongTinDuongSu='" + getThongTinDuongSu() + "'" +
            ", thongTinTaiSan='" + getThongTinTaiSan() + "'" +
            ", thongTinVanBan='" + getThongTinVanBan() + "'" +
            ", trangThai=" + getTrangThai() +
            ", idLoaiHd='" + getIdLoaiHd() + "'" +
            ", dieuKhoanHd='" + getDieuKhoanHd() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", idHdGoc='" + getIdHdGoc() + "'" +
            ", thongTinChuyenNhuong='" + getThongTinChuyenNhuong() + "'" +
            ", maHopDong='" + getMaHopDong() + "'" +
            ", srcHopDong='" + getSrcHopDong() + "'" +
            ", ngayHen='" + getNgayHen() + "'" +
            ", idSoCongChung='" + getIdSoCongChung() + "'" +
            ", soCongChung='" + getSoCongChung() + "'" +
            ", congChungVien=" + getCongChungVien() +
            ", ngayKyHd='" + getNgayKyHd() + "'" +
            ", nguoiRutTrich=" + getNguoiRutTrich() +
            ", soTienRutTrich=" + getSoTienRutTrich() +
            ", ngayRutTrich='" + getNgayRutTrich() + "'" +
            ", hdThuCong=" + getHdThuCong() +
            ", trangThaiRutTrich=" + getTrangThaiRutTrich() +
            ", chuKyNgoaiTruSo=" + getChuKyNgoaiTruSo() +
            ", strSearch='" + getStrSearch() + "'" +
            ", idMaster=" + getIdMaster() +
            ", idHdSdHb=" + getIdHdSdHb() +
            ", srcDmMaster='" + getSrcDmMaster() + "'" +
            ", repRefUnique=" + getRepRefUnique() +
            ", ngayText='" + getNgayText() + "'" +
            ", ngayNum=" + getNgayNum() +
            ", ngayThaoTacRutTrich='" + getNgayThaoTacRutTrich() + "'" +
            ", thuLaoCongChung=" + getThuLaoCongChung() +
            ", quyenLaiSt='" + getQuyenLaiSt() + "'" +
            ", soLaiSt='" + getSoLaiSt() + "'" +
            ", quyenLaiTl='" + getQuyenLaiTl() + "'" +
            ", soLaiTl='" + getSoLaiTl() + "'" +
            ", srcKySoPdf='" + getSrcKySoPdf() + "'" +
            ", srcKySoPdfSigned='" + getSrcKySoPdfSigned() + "'" +
            ", syncStatus=" + getSyncStatus() +
            ", ngayRutTrichText='" + getNgayRutTrichText() + "'" +
            "}";
    }
}
