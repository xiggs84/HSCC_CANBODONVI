package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DonViScanQr} entity. This class is used
 * in {@link vn.vnpt.web.rest.DonViScanQrResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /don-vi-scan-qrs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DonViScanQrCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idLuotQuet;

    private LongFilter idCongDan;

    private LocalDateFilter ngayThaoTac;

    private Boolean distinct;

    public DonViScanQrCriteria() {}

    public DonViScanQrCriteria(DonViScanQrCriteria other) {
        this.idLuotQuet = other.optionalIdLuotQuet().map(LongFilter::copy).orElse(null);
        this.idCongDan = other.optionalIdCongDan().map(LongFilter::copy).orElse(null);
        this.ngayThaoTac = other.optionalNgayThaoTac().map(LocalDateFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DonViScanQrCriteria copy() {
        return new DonViScanQrCriteria(this);
    }

    public LongFilter getIdLuotQuet() {
        return idLuotQuet;
    }

    public Optional<LongFilter> optionalIdLuotQuet() {
        return Optional.ofNullable(idLuotQuet);
    }

    public LongFilter idLuotQuet() {
        if (idLuotQuet == null) {
            setIdLuotQuet(new LongFilter());
        }
        return idLuotQuet;
    }

    public void setIdLuotQuet(LongFilter idLuotQuet) {
        this.idLuotQuet = idLuotQuet;
    }

    public LongFilter getIdCongDan() {
        return idCongDan;
    }

    public Optional<LongFilter> optionalIdCongDan() {
        return Optional.ofNullable(idCongDan);
    }

    public LongFilter idCongDan() {
        if (idCongDan == null) {
            setIdCongDan(new LongFilter());
        }
        return idCongDan;
    }

    public void setIdCongDan(LongFilter idCongDan) {
        this.idCongDan = idCongDan;
    }

    public LocalDateFilter getNgayThaoTac() {
        return ngayThaoTac;
    }

    public Optional<LocalDateFilter> optionalNgayThaoTac() {
        return Optional.ofNullable(ngayThaoTac);
    }

    public LocalDateFilter ngayThaoTac() {
        if (ngayThaoTac == null) {
            setNgayThaoTac(new LocalDateFilter());
        }
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDateFilter ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DonViScanQrCriteria that = (DonViScanQrCriteria) o;
        return (
            Objects.equals(idLuotQuet, that.idLuotQuet) &&
            Objects.equals(idCongDan, that.idCongDan) &&
            Objects.equals(ngayThaoTac, that.ngayThaoTac) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLuotQuet, idCongDan, ngayThaoTac, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DonViScanQrCriteria{" +
            optionalIdLuotQuet().map(f -> "idLuotQuet=" + f + ", ").orElse("") +
            optionalIdCongDan().map(f -> "idCongDan=" + f + ", ").orElse("") +
            optionalNgayThaoTac().map(f -> "ngayThaoTac=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
