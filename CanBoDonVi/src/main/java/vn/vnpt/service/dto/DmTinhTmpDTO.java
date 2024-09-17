package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DmTinhTmp} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmTinhTmpDTO implements Serializable {

    private Long maTinh;

    private String tenTinh;

    public Long getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(Long maTinh) {
        this.maTinh = maTinh;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmTinhTmpDTO)) {
            return false;
        }

        DmTinhTmpDTO dmTinhTmpDTO = (DmTinhTmpDTO) o;
        if (this.maTinh == null) {
            return false;
        }
        return Objects.equals(this.maTinh, dmTinhTmpDTO.maTinh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.maTinh);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmTinhTmpDTO{" +
            "maTinh=" + getMaTinh() +
            ", tenTinh='" + getTenTinh() + "'" +
            "}";
    }
}
