package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.ThuaTach} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ThuaTachDTO implements Serializable {

    private Long idThuaTach;

    private String thongTinThuaTach;

    private Long trangThai;

    private TaiSanDTO taiSan;

    public Long getIdThuaTach() {
        return idThuaTach;
    }

    public void setIdThuaTach(Long idThuaTach) {
        this.idThuaTach = idThuaTach;
    }

    public String getThongTinThuaTach() {
        return thongTinThuaTach;
    }

    public void setThongTinThuaTach(String thongTinThuaTach) {
        this.thongTinThuaTach = thongTinThuaTach;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public TaiSanDTO getTaiSan() {
        return taiSan;
    }

    public void setTaiSan(TaiSanDTO taiSan) {
        this.taiSan = taiSan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThuaTachDTO)) {
            return false;
        }

        ThuaTachDTO thuaTachDTO = (ThuaTachDTO) o;
        if (this.idThuaTach == null) {
            return false;
        }
        return Objects.equals(this.idThuaTach, thuaTachDTO.idThuaTach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idThuaTach);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ThuaTachDTO{" +
            "idThuaTach=" + getIdThuaTach() +
            ", thongTinThuaTach='" + getThongTinThuaTach() + "'" +
            ", trangThai=" + getTrangThai() +
            ", taiSan=" + getTaiSan() +
            "}";
    }
}
