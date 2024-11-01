package vn.vnpt.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DuongSuTrungCmnd} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DuongSuTrungCmndDTO implements Serializable {

    private Long id;

    private String tenDuongSu;

    private String diaChi;

    @Min(value = 0)
    @Max(value = 1)
    private Integer trangThai;

    private String thongTinDs;

    private LocalDate ngayThaoTac;

    private Long nguoiThaoTac;

    private Long idDsGoc;

    private String idMaster;

    private Long idDonVi;

    private String strSearch;

    private String soGiayTo;

    private Long idDuongSuMin;

    private Long idMasterMin;

    private Long idDuongSuMax;

    private Long idMasterMax;

    private DuongSuDTO duongSu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenDuongSu() {
        return tenDuongSu;
    }

    public void setTenDuongSu(String tenDuongSu) {
        this.tenDuongSu = tenDuongSu;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public String getSoGiayTo() {
        return soGiayTo;
    }

    public void setSoGiayTo(String soGiayTo) {
        this.soGiayTo = soGiayTo;
    }

    public Long getIdDuongSuMin() {
        return idDuongSuMin;
    }

    public void setIdDuongSuMin(Long idDuongSuMin) {
        this.idDuongSuMin = idDuongSuMin;
    }

    public Long getIdMasterMin() {
        return idMasterMin;
    }

    public void setIdMasterMin(Long idMasterMin) {
        this.idMasterMin = idMasterMin;
    }

    public Long getIdDuongSuMax() {
        return idDuongSuMax;
    }

    public void setIdDuongSuMax(Long idDuongSuMax) {
        this.idDuongSuMax = idDuongSuMax;
    }

    public Long getIdMasterMax() {
        return idMasterMax;
    }

    public void setIdMasterMax(Long idMasterMax) {
        this.idMasterMax = idMasterMax;
    }

    public DuongSuDTO getDuongSu() {
        return duongSu;
    }

    public void setDuongSu(DuongSuDTO duongSu) {
        this.duongSu = duongSu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DuongSuTrungCmndDTO)) {
            return false;
        }

        DuongSuTrungCmndDTO duongSuTrungCmndDTO = (DuongSuTrungCmndDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, duongSuTrungCmndDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DuongSuTrungCmndDTO{" +
            "id=" + getId() +
            ", tenDuongSu='" + getTenDuongSu() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", trangThai=" + getTrangThai() +
            ", thongTinDs='" + getThongTinDs() + "'" +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", idDsGoc=" + getIdDsGoc() +
            ", idMaster='" + getIdMaster() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", strSearch='" + getStrSearch() + "'" +
            ", soGiayTo='" + getSoGiayTo() + "'" +
            ", idDuongSuMin=" + getIdDuongSuMin() +
            ", idMasterMin=" + getIdMasterMin() +
            ", idDuongSuMax=" + getIdDuongSuMax() +
            ", idMasterMax=" + getIdMasterMax() +
            ", duongSu=" + getDuongSu() +
            "}";
    }
}
