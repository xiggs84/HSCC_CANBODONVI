package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.TinhTrangTaiSan} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TinhTrangTaiSanDTO implements Serializable {

    private Long idTinhTrang;

    private String dienGiai;

    private Long trangThai;

    public Long getIdTinhTrang() {
        return idTinhTrang;
    }

    public void setIdTinhTrang(Long idTinhTrang) {
        this.idTinhTrang = idTinhTrang;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TinhTrangTaiSanDTO)) {
            return false;
        }

        TinhTrangTaiSanDTO tinhTrangTaiSanDTO = (TinhTrangTaiSanDTO) o;
        if (this.idTinhTrang == null) {
            return false;
        }
        return Objects.equals(this.idTinhTrang, tinhTrangTaiSanDTO.idTinhTrang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idTinhTrang);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TinhTrangTaiSanDTO{" +
            "idTinhTrang=" + getIdTinhTrang() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
