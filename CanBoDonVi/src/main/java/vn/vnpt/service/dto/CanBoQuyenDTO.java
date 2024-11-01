package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.CanBoQuyen} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CanBoQuyenDTO implements Serializable {

    private Long id;

    private DanhMucDonViDTO danhMucDonVi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DanhMucDonViDTO getDanhMucDonVi() {
        return danhMucDonVi;
    }

    public void setDanhMucDonVi(DanhMucDonViDTO danhMucDonVi) {
        this.danhMucDonVi = danhMucDonVi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CanBoQuyenDTO)) {
            return false;
        }

        CanBoQuyenDTO canBoQuyenDTO = (CanBoQuyenDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, canBoQuyenDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CanBoQuyenDTO{" +
            "id=" + getId() +
            ", danhMucDonVi=" + getDanhMucDonVi() +
            "}";
    }
}
