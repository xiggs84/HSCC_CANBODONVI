package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucLoaiTaiSan} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiTaiSanDTO implements Serializable {

    private Long idLoaiTs;

    private String dienGiai;

    private Long trangThai;

    public Long getIdLoaiTs() {
        return idLoaiTs;
    }

    public void setIdLoaiTs(Long idLoaiTs) {
        this.idLoaiTs = idLoaiTs;
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
        if (!(o instanceof DanhMucLoaiTaiSanDTO)) {
            return false;
        }

        DanhMucLoaiTaiSanDTO danhMucLoaiTaiSanDTO = (DanhMucLoaiTaiSanDTO) o;
        if (this.idLoaiTs == null) {
            return false;
        }
        return Objects.equals(this.idLoaiTs, danhMucLoaiTaiSanDTO.idLoaiTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idLoaiTs);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiTaiSanDTO{" +
            "idLoaiTs=" + getIdLoaiTs() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
