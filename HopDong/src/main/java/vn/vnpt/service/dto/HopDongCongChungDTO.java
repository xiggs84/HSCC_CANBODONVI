package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.HopDongCongChung} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HopDongCongChungDTO implements Serializable {

    private String id;

    private LocalDate ngayLapHd;

    private Long nguoiLapHd;

    private String thongTinDuongSu;

    private String thongTinTaiSan;

    private String thongTinVanBan;

    private Long trangThai;

    private String idLoaiHd;

    private String dieuKhoanHd;

    private Long idDonVi;

    private LocalDate ngayThaoTac;

    private Long nguoiThaoTac;

    private String idHdGoc;

    private String thongTinChuyenNhuong;

    private String maHopDong;

    private String srcHopDong;

    private LocalDate ngayHen;

    private String idSoCongChung;

    private String soCongChung;

    private Long congChungVien;

    private LocalDate ngayKyHd;

    private Long nguoiRutTrich;

    private Long soTienRutTrich;

    private LocalDate ngayRutTrich;

    private Long hdThuCong;

    private Long trangThaiRutTrich;

    private Long chuKyNgoaiTruSo;

    private String strSearch;

    private Long idMaster;

    private Long idHdSdHb;

    private String srcDmMaster;

    private Long repRefUnique;

    private String ngayText;

    private Long ngayNum;

    private LocalDate ngayThaoTacRutTrich;

    private Long thuLaoCongChung;

    private String quyenLaiSt;

    private String soLaiSt;

    private String quyenLaiTl;

    private String soLaiTl;

    private String srcKySoPdf;

    private String srcKySoPdfSigned;

    private Long syncStatus;

    private String ngayRutTrichText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getNgayLapHd() {
        return ngayLapHd;
    }

    public void setNgayLapHd(LocalDate ngayLapHd) {
        this.ngayLapHd = ngayLapHd;
    }

    public Long getNguoiLapHd() {
        return nguoiLapHd;
    }

    public void setNguoiLapHd(Long nguoiLapHd) {
        this.nguoiLapHd = nguoiLapHd;
    }

    public String getThongTinDuongSu() {
        return thongTinDuongSu;
    }

    public void setThongTinDuongSu(String thongTinDuongSu) {
        this.thongTinDuongSu = thongTinDuongSu;
    }

    public String getThongTinTaiSan() {
        return thongTinTaiSan;
    }

    public void setThongTinTaiSan(String thongTinTaiSan) {
        this.thongTinTaiSan = thongTinTaiSan;
    }

    public String getThongTinVanBan() {
        return thongTinVanBan;
    }

    public void setThongTinVanBan(String thongTinVanBan) {
        this.thongTinVanBan = thongTinVanBan;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdLoaiHd() {
        return idLoaiHd;
    }

    public void setIdLoaiHd(String idLoaiHd) {
        this.idLoaiHd = idLoaiHd;
    }

    public String getDieuKhoanHd() {
        return dieuKhoanHd;
    }

    public void setDieuKhoanHd(String dieuKhoanHd) {
        this.dieuKhoanHd = dieuKhoanHd;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return nguoiThaoTac;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public String getIdHdGoc() {
        return idHdGoc;
    }

    public void setIdHdGoc(String idHdGoc) {
        this.idHdGoc = idHdGoc;
    }

    public String getThongTinChuyenNhuong() {
        return thongTinChuyenNhuong;
    }

    public void setThongTinChuyenNhuong(String thongTinChuyenNhuong) {
        this.thongTinChuyenNhuong = thongTinChuyenNhuong;
    }

    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public String getSrcHopDong() {
        return srcHopDong;
    }

    public void setSrcHopDong(String srcHopDong) {
        this.srcHopDong = srcHopDong;
    }

    public LocalDate getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(LocalDate ngayHen) {
        this.ngayHen = ngayHen;
    }

    public String getIdSoCongChung() {
        return idSoCongChung;
    }

    public void setIdSoCongChung(String idSoCongChung) {
        this.idSoCongChung = idSoCongChung;
    }

    public String getSoCongChung() {
        return soCongChung;
    }

    public void setSoCongChung(String soCongChung) {
        this.soCongChung = soCongChung;
    }

    public Long getCongChungVien() {
        return congChungVien;
    }

    public void setCongChungVien(Long congChungVien) {
        this.congChungVien = congChungVien;
    }

    public LocalDate getNgayKyHd() {
        return ngayKyHd;
    }

    public void setNgayKyHd(LocalDate ngayKyHd) {
        this.ngayKyHd = ngayKyHd;
    }

    public Long getNguoiRutTrich() {
        return nguoiRutTrich;
    }

    public void setNguoiRutTrich(Long nguoiRutTrich) {
        this.nguoiRutTrich = nguoiRutTrich;
    }

    public Long getSoTienRutTrich() {
        return soTienRutTrich;
    }

    public void setSoTienRutTrich(Long soTienRutTrich) {
        this.soTienRutTrich = soTienRutTrich;
    }

    public LocalDate getNgayRutTrich() {
        return ngayRutTrich;
    }

    public void setNgayRutTrich(LocalDate ngayRutTrich) {
        this.ngayRutTrich = ngayRutTrich;
    }

    public Long getHdThuCong() {
        return hdThuCong;
    }

    public void setHdThuCong(Long hdThuCong) {
        this.hdThuCong = hdThuCong;
    }

    public Long getTrangThaiRutTrich() {
        return trangThaiRutTrich;
    }

    public void setTrangThaiRutTrich(Long trangThaiRutTrich) {
        this.trangThaiRutTrich = trangThaiRutTrich;
    }

    public Long getChuKyNgoaiTruSo() {
        return chuKyNgoaiTruSo;
    }

    public void setChuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.chuKyNgoaiTruSo = chuKyNgoaiTruSo;
    }

    public String getStrSearch() {
        return strSearch;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public Long getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public Long getIdHdSdHb() {
        return idHdSdHb;
    }

    public void setIdHdSdHb(Long idHdSdHb) {
        this.idHdSdHb = idHdSdHb;
    }

    public String getSrcDmMaster() {
        return srcDmMaster;
    }

    public void setSrcDmMaster(String srcDmMaster) {
        this.srcDmMaster = srcDmMaster;
    }

    public Long getRepRefUnique() {
        return repRefUnique;
    }

    public void setRepRefUnique(Long repRefUnique) {
        this.repRefUnique = repRefUnique;
    }

    public String getNgayText() {
        return ngayText;
    }

    public void setNgayText(String ngayText) {
        this.ngayText = ngayText;
    }

    public Long getNgayNum() {
        return ngayNum;
    }

    public void setNgayNum(Long ngayNum) {
        this.ngayNum = ngayNum;
    }

    public LocalDate getNgayThaoTacRutTrich() {
        return ngayThaoTacRutTrich;
    }

    public void setNgayThaoTacRutTrich(LocalDate ngayThaoTacRutTrich) {
        this.ngayThaoTacRutTrich = ngayThaoTacRutTrich;
    }

    public Long getThuLaoCongChung() {
        return thuLaoCongChung;
    }

    public void setThuLaoCongChung(Long thuLaoCongChung) {
        this.thuLaoCongChung = thuLaoCongChung;
    }

    public String getQuyenLaiSt() {
        return quyenLaiSt;
    }

    public void setQuyenLaiSt(String quyenLaiSt) {
        this.quyenLaiSt = quyenLaiSt;
    }

    public String getSoLaiSt() {
        return soLaiSt;
    }

    public void setSoLaiSt(String soLaiSt) {
        this.soLaiSt = soLaiSt;
    }

    public String getQuyenLaiTl() {
        return quyenLaiTl;
    }

    public void setQuyenLaiTl(String quyenLaiTl) {
        this.quyenLaiTl = quyenLaiTl;
    }

    public String getSoLaiTl() {
        return soLaiTl;
    }

    public void setSoLaiTl(String soLaiTl) {
        this.soLaiTl = soLaiTl;
    }

    public String getSrcKySoPdf() {
        return srcKySoPdf;
    }

    public void setSrcKySoPdf(String srcKySoPdf) {
        this.srcKySoPdf = srcKySoPdf;
    }

    public String getSrcKySoPdfSigned() {
        return srcKySoPdfSigned;
    }

    public void setSrcKySoPdfSigned(String srcKySoPdfSigned) {
        this.srcKySoPdfSigned = srcKySoPdfSigned;
    }

    public Long getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Long syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getNgayRutTrichText() {
        return ngayRutTrichText;
    }

    public void setNgayRutTrichText(String ngayRutTrichText) {
        this.ngayRutTrichText = ngayRutTrichText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HopDongCongChungDTO)) {
            return false;
        }

        HopDongCongChungDTO hopDongCongChungDTO = (HopDongCongChungDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, hopDongCongChungDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HopDongCongChungDTO{" +
            "id='" + getId() + "'" +
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
