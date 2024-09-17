package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DonViScanQr.
 */
@Entity
@Table(name = "don_vi_scan_qr")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DonViScanQr implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_luot_quet")
    private Long idLuotQuet;

    @Column(name = "id_cong_dan")
    private Long idCongDan;

    @Column(name = "ngay_thao_tac")
    private LocalDate ngayThaoTac;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdLuotQuet() {
        return this.idLuotQuet;
    }

    public DonViScanQr idLuotQuet(Long idLuotQuet) {
        this.setIdLuotQuet(idLuotQuet);
        return this;
    }

    public void setIdLuotQuet(Long idLuotQuet) {
        this.idLuotQuet = idLuotQuet;
    }

    public Long getIdCongDan() {
        return this.idCongDan;
    }

    public DonViScanQr idCongDan(Long idCongDan) {
        this.setIdCongDan(idCongDan);
        return this;
    }

    public void setIdCongDan(Long idCongDan) {
        this.idCongDan = idCongDan;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public DonViScanQr ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DonViScanQr)) {
            return false;
        }
        return getIdLuotQuet() != null && getIdLuotQuet().equals(((DonViScanQr) o).getIdLuotQuet());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DonViScanQr{" +
            "idLuotQuet=" + getIdLuotQuet() +
            ", idCongDan=" + getIdCongDan() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            "}";
    }
}
