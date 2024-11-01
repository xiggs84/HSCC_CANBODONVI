package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.CapQuanLy} entity. This class is used
 * in {@link vn.vnpt.web.rest.CapQuanLyResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cap-quan-lies?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CapQuanLyCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter idCapQl;

    private StringFilter tenCapQl;

    private LongFilter idCapQlId;

    private Boolean distinct;

    public CapQuanLyCriteria() {}

    public CapQuanLyCriteria(CapQuanLyCriteria other) {
        this.idCapQl = other.optionalIdCapQl().map(StringFilter::copy).orElse(null);
        this.tenCapQl = other.optionalTenCapQl().map(StringFilter::copy).orElse(null);
        this.idCapQlId = other.optionalIdCapQlId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public CapQuanLyCriteria copy() {
        return new CapQuanLyCriteria(this);
    }

    public StringFilter getIdCapQl() {
        return idCapQl;
    }

    public Optional<StringFilter> optionalIdCapQl() {
        return Optional.ofNullable(idCapQl);
    }

    public StringFilter idCapQl() {
        if (idCapQl == null) {
            setIdCapQl(new StringFilter());
        }
        return idCapQl;
    }

    public void setIdCapQl(StringFilter idCapQl) {
        this.idCapQl = idCapQl;
    }

    public StringFilter getTenCapQl() {
        return tenCapQl;
    }

    public Optional<StringFilter> optionalTenCapQl() {
        return Optional.ofNullable(tenCapQl);
    }

    public StringFilter tenCapQl() {
        if (tenCapQl == null) {
            setTenCapQl(new StringFilter());
        }
        return tenCapQl;
    }

    public void setTenCapQl(StringFilter tenCapQl) {
        this.tenCapQl = tenCapQl;
    }

    public LongFilter getIdCapQlId() {
        return idCapQlId;
    }

    public Optional<LongFilter> optionalIdCapQlId() {
        return Optional.ofNullable(idCapQlId);
    }

    public LongFilter idCapQlId() {
        if (idCapQlId == null) {
            setIdCapQlId(new LongFilter());
        }
        return idCapQlId;
    }

    public void setIdCapQlId(LongFilter idCapQlId) {
        this.idCapQlId = idCapQlId;
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
        final CapQuanLyCriteria that = (CapQuanLyCriteria) o;
        return (
            Objects.equals(idCapQl, that.idCapQl) &&
            Objects.equals(tenCapQl, that.tenCapQl) &&
            Objects.equals(idCapQlId, that.idCapQlId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCapQl, tenCapQl, idCapQlId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CapQuanLyCriteria{" +
            optionalIdCapQl().map(f -> "idCapQl=" + f + ", ").orElse("") +
            optionalTenCapQl().map(f -> "tenCapQl=" + f + ", ").orElse("") +
            optionalIdCapQlId().map(f -> "idCapQlId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
