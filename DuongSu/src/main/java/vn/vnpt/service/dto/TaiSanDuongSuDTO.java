package vn.vnpt.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.TaiSanDuongSu} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaiSanDuongSuDTO implements Serializable {

    private Long id;

    private Long idTaiSan;

    @Min(value = 0)
    @Max(value = 1)
    private Integer trangThai;

    private LocalDate ngayThaoTac;

    private Long idHopDong;

    private Long idLoaiHopDong;

    private Long idChungThuc;

    private DuongSuDTO duongSu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTaiSan() {
        return idTaiSan;
    }

    public void setIdTaiSan(Long idTaiSan) {
        this.idTaiSan = idTaiSan;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getIdHopDong() {
        return idHopDong;
    }

    public void setIdHopDong(Long idHopDong) {
        this.idHopDong = idHopDong;
    }

    public Long getIdLoaiHopDong() {
        return idLoaiHopDong;
    }

    public void setIdLoaiHopDong(Long idLoaiHopDong) {
        this.idLoaiHopDong = idLoaiHopDong;
    }

    public Long getIdChungThuc() {
        return idChungThuc;
    }

    public void setIdChungThuc(Long idChungThuc) {
        this.idChungThuc = idChungThuc;
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
        if (!(o instanceof TaiSanDuongSuDTO)) {
            return false;
        }

        TaiSanDuongSuDTO taiSanDuongSuDTO = (TaiSanDuongSuDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, taiSanDuongSuDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaiSanDuongSuDTO{" +
            "id=" + getId() +
            ", idTaiSan=" + getIdTaiSan() +
            ", trangThai=" + getTrangThai() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", idHopDong=" + getIdHopDong() +
            ", idLoaiHopDong=" + getIdLoaiHopDong() +
            ", idChungThuc=" + getIdChungThuc() +
            ", duongSu=" + getDuongSu() +
            "}";
    }
}
