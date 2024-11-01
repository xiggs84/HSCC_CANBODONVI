package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucLoaiVanBan} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiVanBanDTO implements Serializable {

    private String id;

    private String dienGiai;

    private String idLoaiHopDong;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getIdLoaiHopDong() {
        return idLoaiHopDong;
    }

    public void setIdLoaiHopDong(String idLoaiHopDong) {
        this.idLoaiHopDong = idLoaiHopDong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucLoaiVanBanDTO)) {
            return false;
        }

        DanhMucLoaiVanBanDTO danhMucLoaiVanBanDTO = (DanhMucLoaiVanBanDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, danhMucLoaiVanBanDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiVanBanDTO{" +
            "id='" + getId() + "'" +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idLoaiHopDong='" + getIdLoaiHopDong() + "'" +
            "}";
    }
}
