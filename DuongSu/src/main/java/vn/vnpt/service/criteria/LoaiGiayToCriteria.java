package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.LoaiGiayTo} entity. This class is used
 * in {@link vn.vnpt.web.rest.LoaiGiayToResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /loai-giay-tos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiGiayToCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter idLoaiGiayTo;

    private StringFilter tenLoaiGiayTo;

    private LongFilter idLoaiGiayToId;

    private LongFilter thongTinCapNhatDuongSuId;

    private Boolean distinct;

    public LoaiGiayToCriteria() {}

    public LoaiGiayToCriteria(LoaiGiayToCriteria other) {
        this.idLoaiGiayTo = other.optionalIdLoaiGiayTo().map(StringFilter::copy).orElse(null);
        this.tenLoaiGiayTo = other.optionalTenLoaiGiayTo().map(StringFilter::copy).orElse(null);
        this.idLoaiGiayToId = other.optionalIdLoaiGiayToId().map(LongFilter::copy).orElse(null);
        this.thongTinCapNhatDuongSuId = other.optionalThongTinCapNhatDuongSuId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public LoaiGiayToCriteria copy() {
        return new LoaiGiayToCriteria(this);
    }

    public StringFilter getIdLoaiGiayTo() {
        return idLoaiGiayTo;
    }

    public Optional<StringFilter> optionalIdLoaiGiayTo() {
        return Optional.ofNullable(idLoaiGiayTo);
    }

    public StringFilter idLoaiGiayTo() {
        if (idLoaiGiayTo == null) {
            setIdLoaiGiayTo(new StringFilter());
        }
        return idLoaiGiayTo;
    }

    public void setIdLoaiGiayTo(StringFilter idLoaiGiayTo) {
        this.idLoaiGiayTo = idLoaiGiayTo;
    }

    public StringFilter getTenLoaiGiayTo() {
        return tenLoaiGiayTo;
    }

    public Optional<StringFilter> optionalTenLoaiGiayTo() {
        return Optional.ofNullable(tenLoaiGiayTo);
    }

    public StringFilter tenLoaiGiayTo() {
        if (tenLoaiGiayTo == null) {
            setTenLoaiGiayTo(new StringFilter());
        }
        return tenLoaiGiayTo;
    }

    public void setTenLoaiGiayTo(StringFilter tenLoaiGiayTo) {
        this.tenLoaiGiayTo = tenLoaiGiayTo;
    }

    public LongFilter getIdLoaiGiayToId() {
        return idLoaiGiayToId;
    }

    public Optional<LongFilter> optionalIdLoaiGiayToId() {
        return Optional.ofNullable(idLoaiGiayToId);
    }

    public LongFilter idLoaiGiayToId() {
        if (idLoaiGiayToId == null) {
            setIdLoaiGiayToId(new LongFilter());
        }
        return idLoaiGiayToId;
    }

    public void setIdLoaiGiayToId(LongFilter idLoaiGiayToId) {
        this.idLoaiGiayToId = idLoaiGiayToId;
    }

    public LongFilter getThongTinCapNhatDuongSuId() {
        return thongTinCapNhatDuongSuId;
    }

    public Optional<LongFilter> optionalThongTinCapNhatDuongSuId() {
        return Optional.ofNullable(thongTinCapNhatDuongSuId);
    }

    public LongFilter thongTinCapNhatDuongSuId() {
        if (thongTinCapNhatDuongSuId == null) {
            setThongTinCapNhatDuongSuId(new LongFilter());
        }
        return thongTinCapNhatDuongSuId;
    }

    public void setThongTinCapNhatDuongSuId(LongFilter thongTinCapNhatDuongSuId) {
        this.thongTinCapNhatDuongSuId = thongTinCapNhatDuongSuId;
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
        final LoaiGiayToCriteria that = (LoaiGiayToCriteria) o;
        return (
            Objects.equals(idLoaiGiayTo, that.idLoaiGiayTo) &&
            Objects.equals(tenLoaiGiayTo, that.tenLoaiGiayTo) &&
            Objects.equals(idLoaiGiayToId, that.idLoaiGiayToId) &&
            Objects.equals(thongTinCapNhatDuongSuId, that.thongTinCapNhatDuongSuId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLoaiGiayTo, tenLoaiGiayTo, idLoaiGiayToId, thongTinCapNhatDuongSuId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiGiayToCriteria{" +
            optionalIdLoaiGiayTo().map(f -> "idLoaiGiayTo=" + f + ", ").orElse("") +
            optionalTenLoaiGiayTo().map(f -> "tenLoaiGiayTo=" + f + ", ").orElse("") +
            optionalIdLoaiGiayToId().map(f -> "idLoaiGiayToId=" + f + ", ").orElse("") +
            optionalThongTinCapNhatDuongSuId().map(f -> "thongTinCapNhatDuongSuId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
