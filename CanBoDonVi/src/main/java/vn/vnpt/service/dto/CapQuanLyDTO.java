package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.CapQuanLy} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CapQuanLyDTO implements Serializable {

    private String idCapQl;

    private String tenCapQl;

    public String getIdCapQl() {
        return idCapQl;
    }

    public void setIdCapQl(String idCapQl) {
        this.idCapQl = idCapQl;
    }

    public String getTenCapQl() {
        return tenCapQl;
    }

    public void setTenCapQl(String tenCapQl) {
        this.tenCapQl = tenCapQl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CapQuanLyDTO)) {
            return false;
        }

        CapQuanLyDTO capQuanLyDTO = (CapQuanLyDTO) o;
        if (this.idCapQl == null) {
            return false;
        }
        return Objects.equals(this.idCapQl, capQuanLyDTO.idCapQl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idCapQl);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CapQuanLyDTO{" +
            "idCapQl='" + getIdCapQl() + "'" +
            ", tenCapQl='" + getTenCapQl() + "'" +
            "}";
    }
}
