package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucCapQuanLy} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucCapQuanLyDTO implements Serializable {

    private Long idCapQl;

    private String dienGiai;

    public Long getIdCapQl() {
        return idCapQl;
    }

    public void setIdCapQl(Long idCapQl) {
        this.idCapQl = idCapQl;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucCapQuanLyDTO)) {
            return false;
        }

        DanhMucCapQuanLyDTO danhMucCapQuanLyDTO = (DanhMucCapQuanLyDTO) o;
        if (this.idCapQl == null) {
            return false;
        }
        return Objects.equals(this.idCapQl, danhMucCapQuanLyDTO.idCapQl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idCapQl);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucCapQuanLyDTO{" +
            "idCapQl=" + getIdCapQl() +
            ", dienGiai='" + getDienGiai() + "'" +
            "}";
    }
}
