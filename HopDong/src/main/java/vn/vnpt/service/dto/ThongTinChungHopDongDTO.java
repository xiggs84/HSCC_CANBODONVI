package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.ThongTinChungHopDong} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ThongTinChungHopDongDTO implements Serializable {

    private String id;

    private LocalDate ngayLapHd;

    private Long nguoiLapHd;

    private String thongTinVanBan;

    private Long trangThai;

    private String idLoaiHd;

    private String dieuKhoanHd;

    private Long idDonVi;

    private LocalDate ngayThaoTac;

    private Long nguoiThaoTac;

    private String idHdGoc;

    private String maHopDong;

    private String srcHopDong;

    private LocalDate ngayHen;

    private String idSoCongChung;

    private Long soCongChung;

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

    private String thongTinChung;

    private String thongTinChungClob;

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

    public Long getSoCongChung() {
        return soCongChung;
    }

    public void setSoCongChung(Long soCongChung) {
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

    public String getThongTinChung() {
        return thongTinChung;
    }

    public void setThongTinChung(String thongTinChung) {
        this.thongTinChung = thongTinChung;
    }

    public String getThongTinChungClob() {
        return thongTinChungClob;
    }

    public void setThongTinChungClob(String thongTinChungClob) {
        this.thongTinChungClob = thongTinChungClob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThongTinChungHopDongDTO)) {
            return false;
        }

        ThongTinChungHopDongDTO thongTinChungHopDongDTO = (ThongTinChungHopDongDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, thongTinChungHopDongDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ThongTinChungHopDongDTO{" +
            "id='" + getId() + "'" +
            ", ngayLapHd='" + getNgayLapHd() + "'" +
            ", nguoiLapHd=" + getNguoiLapHd() +
            ", thongTinVanBan='" + getThongTinVanBan() + "'" +
            ", trangThai=" + getTrangThai() +
            ", idLoaiHd='" + getIdLoaiHd() + "'" +
            ", dieuKhoanHd='" + getDieuKhoanHd() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", idHdGoc='" + getIdHdGoc() + "'" +
            ", maHopDong='" + getMaHopDong() + "'" +
            ", srcHopDong='" + getSrcHopDong() + "'" +
            ", ngayHen='" + getNgayHen() + "'" +
            ", idSoCongChung='" + getIdSoCongChung() + "'" +
            ", soCongChung=" + getSoCongChung() +
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
            ", thongTinChung='" + getThongTinChung() + "'" +
            ", thongTinChungClob='" + getThongTinChungClob() + "'" +
            "}";
    }
}
