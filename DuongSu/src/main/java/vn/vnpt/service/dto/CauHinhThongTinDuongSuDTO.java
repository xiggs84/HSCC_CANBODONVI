package vn.vnpt.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.CauHinhThongTinDuongSu} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CauHinhThongTinDuongSuDTO implements Serializable {

    private Long idCauHinh;

    private String noiDung;

    private String javascript;

    private String css;

    private Long idLoaiDs;

    private Long idDonVi;

    @Min(value = 0)
    @Max(value = 1)
    private Integer trangThai;

    public Long getIdCauHinh() {
        return idCauHinh;
    }

    public void setIdCauHinh(Long idCauHinh) {
        this.idCauHinh = idCauHinh;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getJavascript() {
        return javascript;
    }

    public void setJavascript(String javascript) {
        this.javascript = javascript;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public Long getIdLoaiDs() {
        return idLoaiDs;
    }

    public void setIdLoaiDs(Long idLoaiDs) {
        this.idLoaiDs = idLoaiDs;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CauHinhThongTinDuongSuDTO)) {
            return false;
        }

        CauHinhThongTinDuongSuDTO cauHinhThongTinDuongSuDTO = (CauHinhThongTinDuongSuDTO) o;
        if (this.idCauHinh == null) {
            return false;
        }
        return Objects.equals(this.idCauHinh, cauHinhThongTinDuongSuDTO.idCauHinh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idCauHinh);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CauHinhThongTinDuongSuDTO{" +
            "idCauHinh=" + getIdCauHinh() +
            ", noiDung='" + getNoiDung() + "'" +
            ", javascript='" + getJavascript() + "'" +
            ", css='" + getCss() + "'" +
            ", idLoaiDs=" + getIdLoaiDs() +
            ", idDonVi=" + getIdDonVi() +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
