package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DmXaTmp} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmXaTmpDTO implements Serializable {

    private Long maXa;

    private String tenXa;

    public Long getMaXa() {
        return maXa;
    }

    public void setMaXa(Long maXa) {
        this.maXa = maXa;
    }

    public String getTenXa() {
        return tenXa;
    }

    public void setTenXa(String tenXa) {
        this.tenXa = tenXa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmXaTmpDTO)) {
            return false;
        }

        DmXaTmpDTO dmXaTmpDTO = (DmXaTmpDTO) o;
        if (this.maXa == null) {
            return false;
        }
        return Objects.equals(this.maXa, dmXaTmpDTO.maXa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.maXa);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmXaTmpDTO{" +
            "maXa=" + getMaXa() +
            ", tenXa='" + getTenXa() + "'" +
            "}";
    }
}
