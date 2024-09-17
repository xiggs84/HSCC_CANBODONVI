package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.NoiCapGttt} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NoiCapGtttDTO implements Serializable {

    private Long idNoiCap;

    private String dienGiai;

    private Long trangThai;

    public Long getIdNoiCap() {
        return idNoiCap;
    }

    public void setIdNoiCap(Long idNoiCap) {
        this.idNoiCap = idNoiCap;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
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
        if (!(o instanceof NoiCapGtttDTO)) {
            return false;
        }

        NoiCapGtttDTO noiCapGtttDTO = (NoiCapGtttDTO) o;
        if (this.idNoiCap == null) {
            return false;
        }
        return Objects.equals(this.idNoiCap, noiCapGtttDTO.idNoiCap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idNoiCap);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoiCapGtttDTO{" +
            "idNoiCap=" + getIdNoiCap() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}