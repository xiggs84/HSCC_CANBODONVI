package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucLoaiGiayToChungThuc} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiGiayToChungThucDTO implements Serializable {

    private String id;

    private String dienGiai;

    private String idLoaiSo;

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

    public String getIdLoaiSo() {
        return idLoaiSo;
    }

    public void setIdLoaiSo(String idLoaiSo) {
        this.idLoaiSo = idLoaiSo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucLoaiGiayToChungThucDTO)) {
            return false;
        }

        DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThucDTO = (DanhMucLoaiGiayToChungThucDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, danhMucLoaiGiayToChungThucDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiGiayToChungThucDTO{" +
            "id='" + getId() + "'" +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idLoaiSo='" + getIdLoaiSo() + "'" +
            "}";
    }
}
