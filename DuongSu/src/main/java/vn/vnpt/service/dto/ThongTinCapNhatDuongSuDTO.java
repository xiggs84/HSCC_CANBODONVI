package vn.vnpt.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.ThongTinCapNhatDuongSu} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ThongTinCapNhatDuongSuDTO implements Serializable {

    private Long idCapNhat;

    private String tenDuongSu;

    private String soGiayTo;

    @Lob
    private String thongTinDuongSu;

    private LocalDate ngayCapNhat;

    private LoaiDuongSuDTO loaiDuongSu;

    private LoaiGiayToDTO loaiGiayTo;

    private DuongSuDTO duongSu;

    public Long getIdCapNhat() {
        return idCapNhat;
    }

    public void setIdCapNhat(Long idCapNhat) {
        this.idCapNhat = idCapNhat;
    }

    public String getTenDuongSu() {
        return tenDuongSu;
    }

    public void setTenDuongSu(String tenDuongSu) {
        this.tenDuongSu = tenDuongSu;
    }

    public String getSoGiayTo() {
        return soGiayTo;
    }

    public void setSoGiayTo(String soGiayTo) {
        this.soGiayTo = soGiayTo;
    }

    public String getThongTinDuongSu() {
        return thongTinDuongSu;
    }

    public void setThongTinDuongSu(String thongTinDuongSu) {
        this.thongTinDuongSu = thongTinDuongSu;
    }

    public LocalDate getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(LocalDate ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public LoaiDuongSuDTO getLoaiDuongSu() {
        return loaiDuongSu;
    }

    public void setLoaiDuongSu(LoaiDuongSuDTO loaiDuongSu) {
        this.loaiDuongSu = loaiDuongSu;
    }

    public LoaiGiayToDTO getLoaiGiayTo() {
        return loaiGiayTo;
    }

    public void setLoaiGiayTo(LoaiGiayToDTO loaiGiayTo) {
        this.loaiGiayTo = loaiGiayTo;
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
        if (!(o instanceof ThongTinCapNhatDuongSuDTO)) {
            return false;
        }

        ThongTinCapNhatDuongSuDTO thongTinCapNhatDuongSuDTO = (ThongTinCapNhatDuongSuDTO) o;
        if (this.idCapNhat == null) {
            return false;
        }
        return Objects.equals(this.idCapNhat, thongTinCapNhatDuongSuDTO.idCapNhat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idCapNhat);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ThongTinCapNhatDuongSuDTO{" +
            "idCapNhat=" + getIdCapNhat() +
            ", tenDuongSu='" + getTenDuongSu() + "'" +
            ", soGiayTo='" + getSoGiayTo() + "'" +
            ", thongTinDuongSu='" + getThongTinDuongSu() + "'" +
            ", ngayCapNhat='" + getNgayCapNhat() + "'" +
            ", loaiDuongSu=" + getLoaiDuongSu() +
            ", loaiGiayTo=" + getLoaiGiayTo() +
            ", duongSu=" + getDuongSu() +
            "}";
    }
}
