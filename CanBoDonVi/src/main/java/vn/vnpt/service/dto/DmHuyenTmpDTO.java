package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DmHuyenTmp} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmHuyenTmpDTO implements Serializable {

    private Long maHuyen;

    private String tenHuyen;

    public Long getMaHuyen() {
        return maHuyen;
    }

    public void setMaHuyen(Long maHuyen) {
        this.maHuyen = maHuyen;
    }

    public String getTenHuyen() {
        return tenHuyen;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmHuyenTmpDTO)) {
            return false;
        }

        DmHuyenTmpDTO dmHuyenTmpDTO = (DmHuyenTmpDTO) o;
        if (this.maHuyen == null) {
            return false;
        }
        return Objects.equals(this.maHuyen, dmHuyenTmpDTO.maHuyen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.maHuyen);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmHuyenTmpDTO{" +
            "maHuyen=" + getMaHuyen() +
            ", tenHuyen='" + getTenHuyen() + "'" +
            "}";
    }
}
