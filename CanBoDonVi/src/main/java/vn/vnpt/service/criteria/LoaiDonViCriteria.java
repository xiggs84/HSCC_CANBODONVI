package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.LoaiDonVi} entity. This class is used
 * in {@link vn.vnpt.web.rest.LoaiDonViResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /loai-don-vis?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiDonViCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter idLoaiDv;

    private StringFilter tenLoaiDv;

    private LongFilter idLoaiDvId;

    private Boolean distinct;

    public LoaiDonViCriteria() {}

    public LoaiDonViCriteria(LoaiDonViCriteria other) {
        this.idLoaiDv = other.optionalIdLoaiDv().map(StringFilter::copy).orElse(null);
        this.tenLoaiDv = other.optionalTenLoaiDv().map(StringFilter::copy).orElse(null);
        this.idLoaiDvId = other.optionalIdLoaiDvId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public LoaiDonViCriteria copy() {
        return new LoaiDonViCriteria(this);
    }

    public StringFilter getIdLoaiDv() {
        return idLoaiDv;
    }

    public Optional<StringFilter> optionalIdLoaiDv() {
        return Optional.ofNullable(idLoaiDv);
    }

    public StringFilter idLoaiDv() {
        if (idLoaiDv == null) {
            setIdLoaiDv(new StringFilter());
        }
        return idLoaiDv;
    }

    public void setIdLoaiDv(StringFilter idLoaiDv) {
        this.idLoaiDv = idLoaiDv;
    }

    public StringFilter getTenLoaiDv() {
        return tenLoaiDv;
    }

    public Optional<StringFilter> optionalTenLoaiDv() {
        return Optional.ofNullable(tenLoaiDv);
    }

    public StringFilter tenLoaiDv() {
        if (tenLoaiDv == null) {
            setTenLoaiDv(new StringFilter());
        }
        return tenLoaiDv;
    }

    public void setTenLoaiDv(StringFilter tenLoaiDv) {
        this.tenLoaiDv = tenLoaiDv;
    }

    public LongFilter getIdLoaiDvId() {
        return idLoaiDvId;
    }

    public Optional<LongFilter> optionalIdLoaiDvId() {
        return Optional.ofNullable(idLoaiDvId);
    }

    public LongFilter idLoaiDvId() {
        if (idLoaiDvId == null) {
            setIdLoaiDvId(new LongFilter());
        }
        return idLoaiDvId;
    }

    public void setIdLoaiDvId(LongFilter idLoaiDvId) {
        this.idLoaiDvId = idLoaiDvId;
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
        final LoaiDonViCriteria that = (LoaiDonViCriteria) o;
        return (
            Objects.equals(idLoaiDv, that.idLoaiDv) &&
            Objects.equals(tenLoaiDv, that.tenLoaiDv) &&
            Objects.equals(idLoaiDvId, that.idLoaiDvId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLoaiDv, tenLoaiDv, idLoaiDvId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiDonViCriteria{" +
            optionalIdLoaiDv().map(f -> "idLoaiDv=" + f + ", ").orElse("") +
            optionalTenLoaiDv().map(f -> "tenLoaiDv=" + f + ", ").orElse("") +
            optionalIdLoaiDvId().map(f -> "idLoaiDvId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
