package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.Quyen} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuyenDTO implements Serializable {

    private Long idQuyen;

    private String tenQuyen;

    public Long getIdQuyen() {
        return idQuyen;
    }

    public void setIdQuyen(Long idQuyen) {
        this.idQuyen = idQuyen;
    }

    public String getTenQuyen() {
        return tenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.tenQuyen = tenQuyen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuyenDTO)) {
            return false;
        }

        QuyenDTO quyenDTO = (QuyenDTO) o;
        if (this.idQuyen == null) {
            return false;
        }
        return Objects.equals(this.idQuyen, quyenDTO.idQuyen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idQuyen);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuyenDTO{" +
            "idQuyen=" + getIdQuyen() +
            ", tenQuyen='" + getTenQuyen() + "'" +
            "}";
    }
}
