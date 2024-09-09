package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.QuanHeMaster} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuanHeMasterDTO implements Serializable {

    private Long id;

    private Long idDuongSu;

    private Long idDuongSuQh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDuongSu() {
        return idDuongSu;
    }

    public void setIdDuongSu(Long idDuongSu) {
        this.idDuongSu = idDuongSu;
    }

    public Long getIdDuongSuQh() {
        return idDuongSuQh;
    }

    public void setIdDuongSuQh(Long idDuongSuQh) {
        this.idDuongSuQh = idDuongSuQh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuanHeMasterDTO)) {
            return false;
        }

        QuanHeMasterDTO quanHeMasterDTO = (QuanHeMasterDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, quanHeMasterDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuanHeMasterDTO{" +
            "id=" + getId() +
            ", idDuongSu=" + getIdDuongSu() +
            ", idDuongSuQh=" + getIdDuongSuQh() +
            "}";
    }
}
