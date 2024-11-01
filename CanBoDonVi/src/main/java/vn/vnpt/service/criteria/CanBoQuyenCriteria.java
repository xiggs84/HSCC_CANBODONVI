package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.CanBoQuyen} entity. This class is used
 * in {@link vn.vnpt.web.rest.CanBoQuyenResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /can-bo-quyens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CanBoQuyenCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter danhMucDonViId;

    private Boolean distinct;

    public CanBoQuyenCriteria() {}

    public CanBoQuyenCriteria(CanBoQuyenCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.danhMucDonViId = other.optionalDanhMucDonViId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public CanBoQuyenCriteria copy() {
        return new CanBoQuyenCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getDanhMucDonViId() {
        return danhMucDonViId;
    }

    public Optional<LongFilter> optionalDanhMucDonViId() {
        return Optional.ofNullable(danhMucDonViId);
    }

    public LongFilter danhMucDonViId() {
        if (danhMucDonViId == null) {
            setDanhMucDonViId(new LongFilter());
        }
        return danhMucDonViId;
    }

    public void setDanhMucDonViId(LongFilter danhMucDonViId) {
        this.danhMucDonViId = danhMucDonViId;
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
        final CanBoQuyenCriteria that = (CanBoQuyenCriteria) o;
        return (
            Objects.equals(id, that.id) && Objects.equals(danhMucDonViId, that.danhMucDonViId) && Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, danhMucDonViId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CanBoQuyenCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalDanhMucDonViId().map(f -> "danhMucDonViId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
