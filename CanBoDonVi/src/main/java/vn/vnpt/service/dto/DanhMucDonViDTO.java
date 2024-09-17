package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucDonVi} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucDonViDTO implements Serializable {

    private Long idDonVi;

    private String tenDonVi;

    private String diaChi;

    private String nguoiDaiDien;

    private String soDienThoai;

    private Long idDonViQl;

    private LocalDate ngayKhaiBao;

    private Long trangThai;

    private String soNha;

    private String maSoThue;

    private Integer hoaDonDt;

    private String maDonViIgate;

    private String maCoQuanIgate;

    private Long kySo;

    private Long qrScan;

    private Long verifyIdCard;

    private Long isVerifyFace;

    private Long isElastic;

    private String apikeyCccd;

    private String apikeyFace;

    private String verifyCodeCccd;

    private String usernameElastic;

    private String passwordElastic;

    private String idNhiemVu;

    private String idLoaiDv;

    private String idCapQl;

    private CapQuanLyDTO capQuanLy;

    private LoaiDonViDTO loaiDonVi;

    private NhiemVuDTO nhiemVu;

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNguoiDaiDien() {
        return nguoiDaiDien;
    }

    public void setNguoiDaiDien(String nguoiDaiDien) {
        this.nguoiDaiDien = nguoiDaiDien;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Long getIdDonViQl() {
        return idDonViQl;
    }

    public void setIdDonViQl(Long idDonViQl) {
        this.idDonViQl = idDonViQl;
    }

    public LocalDate getNgayKhaiBao() {
        return ngayKhaiBao;
    }

    public void setNgayKhaiBao(LocalDate ngayKhaiBao) {
        this.ngayKhaiBao = ngayKhaiBao;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public Integer getHoaDonDt() {
        return hoaDonDt;
    }

    public void setHoaDonDt(Integer hoaDonDt) {
        this.hoaDonDt = hoaDonDt;
    }

    public String getMaDonViIgate() {
        return maDonViIgate;
    }

    public void setMaDonViIgate(String maDonViIgate) {
        this.maDonViIgate = maDonViIgate;
    }

    public String getMaCoQuanIgate() {
        return maCoQuanIgate;
    }

    public void setMaCoQuanIgate(String maCoQuanIgate) {
        this.maCoQuanIgate = maCoQuanIgate;
    }

    public Long getKySo() {
        return kySo;
    }

    public void setKySo(Long kySo) {
        this.kySo = kySo;
    }

    public Long getQrScan() {
        return qrScan;
    }

    public void setQrScan(Long qrScan) {
        this.qrScan = qrScan;
    }

    public Long getVerifyIdCard() {
        return verifyIdCard;
    }

    public void setVerifyIdCard(Long verifyIdCard) {
        this.verifyIdCard = verifyIdCard;
    }

    public Long getIsVerifyFace() {
        return isVerifyFace;
    }

    public void setIsVerifyFace(Long isVerifyFace) {
        this.isVerifyFace = isVerifyFace;
    }

    public Long getIsElastic() {
        return isElastic;
    }

    public void setIsElastic(Long isElastic) {
        this.isElastic = isElastic;
    }

    public String getApikeyCccd() {
        return apikeyCccd;
    }

    public void setApikeyCccd(String apikeyCccd) {
        this.apikeyCccd = apikeyCccd;
    }

    public String getApikeyFace() {
        return apikeyFace;
    }

    public void setApikeyFace(String apikeyFace) {
        this.apikeyFace = apikeyFace;
    }

    public String getVerifyCodeCccd() {
        return verifyCodeCccd;
    }

    public void setVerifyCodeCccd(String verifyCodeCccd) {
        this.verifyCodeCccd = verifyCodeCccd;
    }

    public String getUsernameElastic() {
        return usernameElastic;
    }

    public void setUsernameElastic(String usernameElastic) {
        this.usernameElastic = usernameElastic;
    }

    public String getPasswordElastic() {
        return passwordElastic;
    }

    public void setPasswordElastic(String passwordElastic) {
        this.passwordElastic = passwordElastic;
    }

    public String getIdNhiemVu() {
        return idNhiemVu;
    }

    public void setIdNhiemVu(String idNhiemVu) {
        this.idNhiemVu = idNhiemVu;
    }

    public String getIdLoaiDv() {
        return idLoaiDv;
    }

    public void setIdLoaiDv(String idLoaiDv) {
        this.idLoaiDv = idLoaiDv;
    }

    public String getIdCapQl() {
        return idCapQl;
    }

    public void setIdCapQl(String idCapQl) {
        this.idCapQl = idCapQl;
    }

    public CapQuanLyDTO getCapQuanLy() {
        return capQuanLy;
    }

    public void setCapQuanLy(CapQuanLyDTO capQuanLy) {
        this.capQuanLy = capQuanLy;
    }

    public LoaiDonViDTO getLoaiDonVi() {
        return loaiDonVi;
    }

    public void setLoaiDonVi(LoaiDonViDTO loaiDonVi) {
        this.loaiDonVi = loaiDonVi;
    }

    public NhiemVuDTO getNhiemVu() {
        return nhiemVu;
    }

    public void setNhiemVu(NhiemVuDTO nhiemVu) {
        this.nhiemVu = nhiemVu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucDonViDTO)) {
            return false;
        }

        DanhMucDonViDTO danhMucDonViDTO = (DanhMucDonViDTO) o;
        if (this.idDonVi == null) {
            return false;
        }
        return Objects.equals(this.idDonVi, danhMucDonViDTO.idDonVi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idDonVi);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucDonViDTO{" +
            "idDonVi=" + getIdDonVi() +
            ", tenDonVi='" + getTenDonVi() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", nguoiDaiDien='" + getNguoiDaiDien() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", idDonViQl=" + getIdDonViQl() +
            ", ngayKhaiBao='" + getNgayKhaiBao() + "'" +
            ", trangThai=" + getTrangThai() +
            ", soNha='" + getSoNha() + "'" +
            ", maSoThue='" + getMaSoThue() + "'" +
            ", hoaDonDt=" + getHoaDonDt() +
            ", maDonViIgate='" + getMaDonViIgate() + "'" +
            ", maCoQuanIgate='" + getMaCoQuanIgate() + "'" +
            ", kySo=" + getKySo() +
            ", qrScan=" + getQrScan() +
            ", verifyIdCard=" + getVerifyIdCard() +
            ", isVerifyFace=" + getIsVerifyFace() +
            ", isElastic=" + getIsElastic() +
            ", apikeyCccd='" + getApikeyCccd() + "'" +
            ", apikeyFace='" + getApikeyFace() + "'" +
            ", verifyCodeCccd='" + getVerifyCodeCccd() + "'" +
            ", usernameElastic='" + getUsernameElastic() + "'" +
            ", passwordElastic='" + getPasswordElastic() + "'" +
            ", idNhiemVu='" + getIdNhiemVu() + "'" +
            ", idLoaiDv='" + getIdLoaiDv() + "'" +
            ", idCapQl='" + getIdCapQl() + "'" +
            ", capQuanLy=" + getCapQuanLy() +
            ", loaiDonVi=" + getLoaiDonVi() +
            ", nhiemVu=" + getNhiemVu() +
            "}";
    }
}
