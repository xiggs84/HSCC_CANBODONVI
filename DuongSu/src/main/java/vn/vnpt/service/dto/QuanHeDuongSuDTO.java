package vn.vnpt.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.QuanHeDuongSu} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuanHeDuongSuDTO implements Serializable {

    private Long idQuanHe;

    private Long idDuongSuQh;

    @Lob
    private String thongTinQuanHe;

    @Min(value = 0)
    @Max(value = 1)
    private Integer trangThai;

    private DuongSuDTO duongSu;

    public Long getIdQuanHe() {
        return idQuanHe;
    }

    public void setIdQuanHe(Long idQuanHe) {
        this.idQuanHe = idQuanHe;
    }

    public Long getIdDuongSuQh() {
        return idDuongSuQh;
    }

    public void setIdDuongSuQh(Long idDuongSuQh) {
        this.idDuongSuQh = idDuongSuQh;
    }

    public String getThongTinQuanHe() {
        return thongTinQuanHe;
    }

    public void setThongTinQuanHe(String thongTinQuanHe) {
        this.thongTinQuanHe = thongTinQuanHe;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
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
        if (!(o instanceof QuanHeDuongSuDTO)) {
            return false;
        }

        QuanHeDuongSuDTO quanHeDuongSuDTO = (QuanHeDuongSuDTO) o;
        if (this.idQuanHe == null) {
            return false;
        }
        return Objects.equals(this.idQuanHe, quanHeDuongSuDTO.idQuanHe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idQuanHe);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuanHeDuongSuDTO{" +
            "idQuanHe=" + getIdQuanHe() +
            ", idDuongSuQh=" + getIdDuongSuQh() +
            ", thongTinQuanHe='" + getThongTinQuanHe() + "'" +
            ", trangThai=" + getTrangThai() +
            ", duongSu=" + getDuongSu() +
            "}";
    }
}
