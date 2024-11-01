package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucLoaiSoCongChung} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiSoCongChungDTO implements Serializable {

    private String id;

    private String tenLoai;

    private Long trangThai;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
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
        if (!(o instanceof DanhMucLoaiSoCongChungDTO)) {
            return false;
        }

        DanhMucLoaiSoCongChungDTO danhMucLoaiSoCongChungDTO = (DanhMucLoaiSoCongChungDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, danhMucLoaiSoCongChungDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiSoCongChungDTO{" +
            "id='" + getId() + "'" +
            ", tenLoai='" + getTenLoai() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
