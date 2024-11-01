package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.PhanLoaiHopDong} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PhanLoaiHopDongDTO implements Serializable {

    private String id;

    private String dienGiai;

    private String idNhomHopDong;

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

    public String getIdNhomHopDong() {
        return idNhomHopDong;
    }

    public void setIdNhomHopDong(String idNhomHopDong) {
        this.idNhomHopDong = idNhomHopDong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhanLoaiHopDongDTO)) {
            return false;
        }

        PhanLoaiHopDongDTO phanLoaiHopDongDTO = (PhanLoaiHopDongDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, phanLoaiHopDongDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PhanLoaiHopDongDTO{" +
            "id='" + getId() + "'" +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idNhomHopDong='" + getIdNhomHopDong() + "'" +
            "}";
    }
}
