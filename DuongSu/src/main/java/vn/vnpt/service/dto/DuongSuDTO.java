package vn.vnpt.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import vn.vnpt.domain.enumeration.LoaiDuongSu;
import vn.vnpt.domain.enumeration.LoaiGiayTo;

/**
 * A DTO for the {@link vn.vnpt.domain.DuongSu} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DuongSuDTO implements Serializable {

    private Long idDuongSu;

    private String tenDuongSu;

    private LoaiDuongSu loaiDuongSu;

    private String diaChi;

    private String soDienThoai;

    private String email;

    private String fax;

    private String website;

    @Min(value = 0)
    @Max(value = 1)
    private Integer trangThai;

    @Lob
    private String thongTinDs;

    private LocalDate ngayThaoTac;

    private Long nguoiThaoTac;

    private Long idDsGoc;

    private String idMaster;

    private Long idDonVi;

    private String strSearch;

    private LoaiGiayTo loaiGiayTo;

    private String soGiayTo;

    private String ghiChu;

    private Long idLoaiNganChan;

    @Min(value = 0)
    @Max(value = 1)
    private Integer syncStatus;

    public Long getIdDuongSu() {
        return idDuongSu;
    }

    public void setIdDuongSu(Long idDuongSu) {
        this.idDuongSu = idDuongSu;
    }

    public String getTenDuongSu() {
        return tenDuongSu;
    }

    public void setTenDuongSu(String tenDuongSu) {
        this.tenDuongSu = tenDuongSu;
    }

    public LoaiDuongSu getLoaiDuongSu() {
        return loaiDuongSu;
    }

    public void setLoaiDuongSu(String loaiDuongSu) {
        this.loaiDuongSu = LoaiDuongSu.fromString(loaiDuongSu);
    }    

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getThongTinDs() {
        return thongTinDs;
    }

    public void setThongTinDs(String thongTinDs) {
        this.thongTinDs = thongTinDs;
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

    public Long getIdDsGoc() {
        return idDsGoc;
    }

    public void setIdDsGoc(Long idDsGoc) {
        this.idDsGoc = idDsGoc;
    }

    public String getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(String idMaster) {
        this.idMaster = idMaster;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getStrSearch() {
        return strSearch;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public LoaiGiayTo getLoaiGiayTo() {
        return loaiGiayTo;
    }

    public void setLoaiGiayTo(LoaiGiayTo loaiGiayTo) {
        this.loaiGiayTo = loaiGiayTo;
    }

    public String getSoGiayTo() {
        return soGiayTo;
    }

    public void setSoGiayTo(String soGiayTo) {
        this.soGiayTo = soGiayTo;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Long getIdLoaiNganChan() {
        return idLoaiNganChan;
    }

    public void setIdLoaiNganChan(Long idLoaiNganChan) {
        this.idLoaiNganChan = idLoaiNganChan;
    }

    public Integer getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DuongSuDTO)) {
            return false;
        }

        DuongSuDTO duongSuDTO = (DuongSuDTO) o;
        if (this.idDuongSu == null) {
            return false;
        }
        return Objects.equals(this.idDuongSu, duongSuDTO.idDuongSu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idDuongSu);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DuongSuDTO{" +
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
