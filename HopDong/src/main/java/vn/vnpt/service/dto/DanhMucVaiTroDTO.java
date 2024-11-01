package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucVaiTro} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucVaiTroDTO implements Serializable {

    private String id;

    private String dienGiai;

    private String idLoaiHopDong;

    private String idLoaiVaiTro;

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

    public String getIdLoaiVaiTro() {
        return idLoaiVaiTro;
    }

    public void setIdLoaiVaiTro(String idLoaiVaiTro) {
        this.idLoaiVaiTro = idLoaiVaiTro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucVaiTroDTO)) {
            return false;
        }

        DanhMucVaiTroDTO danhMucVaiTroDTO = (DanhMucVaiTroDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, danhMucVaiTroDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucVaiTroDTO{" +
            "id='" + getId() + "'" +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idLoaiHopDong='" + getIdLoaiHopDong() + "'" +
            ", idLoaiVaiTro='" + getIdLoaiVaiTro() + "'" +
            "}";
    }
}
