package vn.vnpt.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A ThongTinChungHopDong.
 */
@Document(collection = "thong_tin_chung_hop_dong")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ThongTinChungHopDong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("ngay_lap_hd")
    private LocalDate ngayLapHd;

    @Field("nguoi_lap_hd")
    private Long nguoiLapHd;

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

    @Field("ma_hop_dong")
    private String maHopDong;

    @Field("src_hop_dong")
    private String srcHopDong;

    @Field("ngay_hen")
    private LocalDate ngayHen;

    @Field("id_so_cong_chung")
    private String idSoCongChung;

    @Field("so_cong_chung")
    private Long soCongChung;

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

    @Field("thong_tin_chung")
    private String thongTinChung;

    @Field("thong_tin_chung_clob")
    private String thongTinChungClob;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ThongTinChungHopDong id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getNgayLapHd() {
        return this.ngayLapHd;
    }

    public ThongTinChungHopDong ngayLapHd(LocalDate ngayLapHd) {
        this.setNgayLapHd(ngayLapHd);
        return this;
    }

    public void setNgayLapHd(LocalDate ngayLapHd) {
        this.ngayLapHd = ngayLapHd;
    }

    public Long getNguoiLapHd() {
        return this.nguoiLapHd;
    }

    public ThongTinChungHopDong nguoiLapHd(Long nguoiLapHd) {
        this.setNguoiLapHd(nguoiLapHd);
        return this;
    }

    public void setNguoiLapHd(Long nguoiLapHd) {
        this.nguoiLapHd = nguoiLapHd;
    }

    public String getThongTinVanBan() {
        return this.thongTinVanBan;
    }

    public ThongTinChungHopDong thongTinVanBan(String thongTinVanBan) {
        this.setThongTinVanBan(thongTinVanBan);
        return this;
    }

    public void setThongTinVanBan(String thongTinVanBan) {
        this.thongTinVanBan = thongTinVanBan;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public ThongTinChungHopDong trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getIdLoaiHd() {
        return this.idLoaiHd;
    }

    public ThongTinChungHopDong idLoaiHd(String idLoaiHd) {
        this.setIdLoaiHd(idLoaiHd);
        return this;
    }

    public void setIdLoaiHd(String idLoaiHd) {
        this.idLoaiHd = idLoaiHd;
    }

    public String getDieuKhoanHd() {
        return this.dieuKhoanHd;
    }

    public ThongTinChungHopDong dieuKhoanHd(String dieuKhoanHd) {
        this.setDieuKhoanHd(dieuKhoanHd);
        return this;
    }

    public void setDieuKhoanHd(String dieuKhoanHd) {
        this.dieuKhoanHd = dieuKhoanHd;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public ThongTinChungHopDong idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public ThongTinChungHopDong ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public ThongTinChungHopDong nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public String getIdHdGoc() {
        return this.idHdGoc;
    }

    public ThongTinChungHopDong idHdGoc(String idHdGoc) {
        this.setIdHdGoc(idHdGoc);
        return this;
    }

    public void setIdHdGoc(String idHdGoc) {
        this.idHdGoc = idHdGoc;
    }

    public String getMaHopDong() {
        return this.maHopDong;
    }

    public ThongTinChungHopDong maHopDong(String maHopDong) {
        this.setMaHopDong(maHopDong);
        return this;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public String getSrcHopDong() {
        return this.srcHopDong;
    }

    public ThongTinChungHopDong srcHopDong(String srcHopDong) {
        this.setSrcHopDong(srcHopDong);
        return this;
    }

    public void setSrcHopDong(String srcHopDong) {
        this.srcHopDong = srcHopDong;
    }

    public LocalDate getNgayHen() {
        return this.ngayHen;
    }

    public ThongTinChungHopDong ngayHen(LocalDate ngayHen) {
        this.setNgayHen(ngayHen);
        return this;
    }

    public void setNgayHen(LocalDate ngayHen) {
        this.ngayHen = ngayHen;
    }

    public String getIdSoCongChung() {
        return this.idSoCongChung;
    }

    public ThongTinChungHopDong idSoCongChung(String idSoCongChung) {
        this.setIdSoCongChung(idSoCongChung);
        return this;
    }

    public void setIdSoCongChung(String idSoCongChung) {
        this.idSoCongChung = idSoCongChung;
    }

    public Long getSoCongChung() {
        return this.soCongChung;
    }

    public ThongTinChungHopDong soCongChung(Long soCongChung) {
        this.setSoCongChung(soCongChung);
        return this;
    }

    public void setSoCongChung(Long soCongChung) {
        this.soCongChung = soCongChung;
    }

    public Long getCongChungVien() {
        return this.congChungVien;
    }

    public ThongTinChungHopDong congChungVien(Long congChungVien) {
        this.setCongChungVien(congChungVien);
        return this;
    }

    public void setCongChungVien(Long congChungVien) {
        this.congChungVien = congChungVien;
    }

    public LocalDate getNgayKyHd() {
        return this.ngayKyHd;
    }

    public ThongTinChungHopDong ngayKyHd(LocalDate ngayKyHd) {
        this.setNgayKyHd(ngayKyHd);
        return this;
    }

    public void setNgayKyHd(LocalDate ngayKyHd) {
        this.ngayKyHd = ngayKyHd;
    }

    public Long getNguoiRutTrich() {
        return this.nguoiRutTrich;
    }

    public ThongTinChungHopDong nguoiRutTrich(Long nguoiRutTrich) {
        this.setNguoiRutTrich(nguoiRutTrich);
        return this;
    }

    public void setNguoiRutTrich(Long nguoiRutTrich) {
        this.nguoiRutTrich = nguoiRutTrich;
    }

    public Long getSoTienRutTrich() {
        return this.soTienRutTrich;
    }

    public ThongTinChungHopDong soTienRutTrich(Long soTienRutTrich) {
        this.setSoTienRutTrich(soTienRutTrich);
        return this;
    }

    public void setSoTienRutTrich(Long soTienRutTrich) {
        this.soTienRutTrich = soTienRutTrich;
    }

    public LocalDate getNgayRutTrich() {
        return this.ngayRutTrich;
    }

    public ThongTinChungHopDong ngayRutTrich(LocalDate ngayRutTrich) {
        this.setNgayRutTrich(ngayRutTrich);
        return this;
    }

    public void setNgayRutTrich(LocalDate ngayRutTrich) {
        this.ngayRutTrich = ngayRutTrich;
    }

    public Long getHdThuCong() {
        return this.hdThuCong;
    }

    public ThongTinChungHopDong hdThuCong(Long hdThuCong) {
        this.setHdThuCong(hdThuCong);
        return this;
    }

    public void setHdThuCong(Long hdThuCong) {
        this.hdThuCong = hdThuCong;
    }

    public Long getTrangThaiRutTrich() {
        return this.trangThaiRutTrich;
    }

    public ThongTinChungHopDong trangThaiRutTrich(Long trangThaiRutTrich) {
        this.setTrangThaiRutTrich(trangThaiRutTrich);
        return this;
    }

    public void setTrangThaiRutTrich(Long trangThaiRutTrich) {
        this.trangThaiRutTrich = trangThaiRutTrich;
    }

    public Long getChuKyNgoaiTruSo() {
        return this.chuKyNgoaiTruSo;
    }

    public ThongTinChungHopDong chuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.setChuKyNgoaiTruSo(chuKyNgoaiTruSo);
        return this;
    }

    public void setChuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.chuKyNgoaiTruSo = chuKyNgoaiTruSo;
    }

    public String getStrSearch() {
        return this.strSearch;
    }

    public ThongTinChungHopDong strSearch(String strSearch) {
        this.setStrSearch(strSearch);
        return this;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public Long getIdMaster() {
        return this.idMaster;
    }

    public ThongTinChungHopDong idMaster(Long idMaster) {
        this.setIdMaster(idMaster);
        return this;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public Long getIdHdSdHb() {
        return this.idHdSdHb;
    }

    public ThongTinChungHopDong idHdSdHb(Long idHdSdHb) {
        this.setIdHdSdHb(idHdSdHb);
        return this;
    }

    public void setIdHdSdHb(Long idHdSdHb) {
        this.idHdSdHb = idHdSdHb;
    }

    public String getSrcDmMaster() {
        return this.srcDmMaster;
    }

    public ThongTinChungHopDong srcDmMaster(String srcDmMaster) {
        this.setSrcDmMaster(srcDmMaster);
        return this;
    }

    public void setSrcDmMaster(String srcDmMaster) {
        this.srcDmMaster = srcDmMaster;
    }

    public Long getRepRefUnique() {
        return this.repRefUnique;
    }

    public ThongTinChungHopDong repRefUnique(Long repRefUnique) {
        this.setRepRefUnique(repRefUnique);
        return this;
    }

    public void setRepRefUnique(Long repRefUnique) {
        this.repRefUnique = repRefUnique;
    }

    public String getNgayText() {
        return this.ngayText;
    }

    public ThongTinChungHopDong ngayText(String ngayText) {
        this.setNgayText(ngayText);
        return this;
    }

    public void setNgayText(String ngayText) {
        this.ngayText = ngayText;
    }

    public String getThongTinChung() {
        return this.thongTinChung;
    }

    public ThongTinChungHopDong thongTinChung(String thongTinChung) {
        this.setThongTinChung(thongTinChung);
        return this;
    }

    public void setThongTinChung(String thongTinChung) {
        this.thongTinChung = thongTinChung;
    }

    public String getThongTinChungClob() {
        return this.thongTinChungClob;
    }

    public ThongTinChungHopDong thongTinChungClob(String thongTinChungClob) {
        this.setThongTinChungClob(thongTinChungClob);
        return this;
    }

    public void setThongTinChungClob(String thongTinChungClob) {
        this.thongTinChungClob = thongTinChungClob;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThongTinChungHopDong)) {
            return false;
        }
        return getId() != null && getId().equals(((ThongTinChungHopDong) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ThongTinChungHopDong{" +
            "id=" + getId() +
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
