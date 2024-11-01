package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhSachHopDong} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhSachHopDongDTO implements Serializable {

    private String id;

    private LocalDate ngayLapHd;

    private Long nguoiLapHd;

    private Long trangThai;

    private String idLoaiHd;

    private Long idDonVi;

    private LocalDate ngayThaoTac;

    private Long nguoiThaoTac;

    private String srcHopDong;

    private String idSoCongChung;

    private String soCongChung;

    private Long congChungVien;

    private Long soTienRutTrich;

    private Long hdThuCong;

    private Long trangThaiRutTrich;

    private Long chuKyNgoaiTruSo;

    private String strSearch;

    private String ngayText;

    private String ngayRutTrichText;

    private LocalDate ngayThaoTacRutTrich;

    private Long thuLaoCongChung;

    private String quyenLaiSt;

    private String soLaiSt;

    private String quyenLaiTl;

    private String soLaiTl;

    private String srcKySoPdf;

    private String srcKySoPdfSigned;

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

    public String getSrcHopDong() {
        return srcHopDong;
    }

    public void setSrcHopDong(String srcHopDong) {
        this.srcHopDong = srcHopDong;
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

    public Long getSoTienRutTrich() {
        return soTienRutTrich;
    }

    public void setSoTienRutTrich(Long soTienRutTrich) {
        this.soTienRutTrich = soTienRutTrich;
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

    public String getNgayText() {
        return ngayText;
    }

    public void setNgayText(String ngayText) {
        this.ngayText = ngayText;
    }

    public String getNgayRutTrichText() {
        return ngayRutTrichText;
    }

    public void setNgayRutTrichText(String ngayRutTrichText) {
        this.ngayRutTrichText = ngayRutTrichText;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhSachHopDongDTO)) {
            return false;
        }

        DanhSachHopDongDTO danhSachHopDongDTO = (DanhSachHopDongDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, danhSachHopDongDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhSachHopDongDTO{" +
            "id='" + getId() + "'" +
            ", ngayLapHd='" + getNgayLapHd() + "'" +
            ", nguoiLapHd=" + getNguoiLapHd() +
            ", trangThai=" + getTrangThai() +
            ", idLoaiHd='" + getIdLoaiHd() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", srcHopDong='" + getSrcHopDong() + "'" +
            ", idSoCongChung='" + getIdSoCongChung() + "'" +
            ", soCongChung='" + getSoCongChung() + "'" +
            ", congChungVien=" + getCongChungVien() +
            ", soTienRutTrich=" + getSoTienRutTrich() +
            ", hdThuCong=" + getHdThuCong() +
            ", trangThaiRutTrich=" + getTrangThaiRutTrich() +
            ", chuKyNgoaiTruSo=" + getChuKyNgoaiTruSo() +
            ", strSearch='" + getStrSearch() + "'" +
            ", ngayText='" + getNgayText() + "'" +
            ", ngayRutTrichText='" + getNgayRutTrichText() + "'" +
            ", ngayThaoTacRutTrich='" + getNgayThaoTacRutTrich() + "'" +
            ", thuLaoCongChung=" + getThuLaoCongChung() +
            ", quyenLaiSt='" + getQuyenLaiSt() + "'" +
            ", soLaiSt='" + getSoLaiSt() + "'" +
            ", quyenLaiTl='" + getQuyenLaiTl() + "'" +
            ", soLaiTl='" + getSoLaiTl() + "'" +
            ", srcKySoPdf='" + getSrcKySoPdf() + "'" +
            ", srcKySoPdfSigned='" + getSrcKySoPdfSigned() + "'" +
            "}";
    }
}
