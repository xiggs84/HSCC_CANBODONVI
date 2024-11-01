package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DmTinhTmp} entity. This class is used
 * in {@link vn.vnpt.web.rest.DmTinhTmpResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dm-tinh-tmps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmTinhTmpCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter maTinh;

    private StringFilter tenTinh;

    private Boolean distinct;

    public DmTinhTmpCriteria() {}

    public DmTinhTmpCriteria(DmTinhTmpCriteria other) {
        this.maTinh = other.optionalMaTinh().map(LongFilter::copy).orElse(null);
        this.tenTinh = other.optionalTenTinh().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DmTinhTmpCriteria copy() {
        return new DmTinhTmpCriteria(this);
    }

    public LongFilter getMaTinh() {
        return maTinh;
    }

    public Optional<LongFilter> optionalMaTinh() {
        return Optional.ofNullable(maTinh);
    }

    public LongFilter maTinh() {
        if (maTinh == null) {
            setMaTinh(new LongFilter());
        }
        return maTinh;
    }

    public void setMaTinh(LongFilter maTinh) {
        this.maTinh = maTinh;
    }

    public StringFilter getTenTinh() {
        return tenTinh;
    }

    public Optional<StringFilter> optionalTenTinh() {
        return Optional.ofNullable(tenTinh);
    }

    public StringFilter tenTinh() {
        if (tenTinh == null) {
            setTenTinh(new StringFilter());
        }
        return tenTinh;
    }

    public void setTenTinh(StringFilter tenTinh) {
        this.tenTinh = tenTinh;
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
        final DmTinhTmpCriteria that = (DmTinhTmpCriteria) o;
        return Objects.equals(maTinh, that.maTinh) && Objects.equals(tenTinh, that.tenTinh) && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maTinh, tenTinh, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmTinhTmpCriteria{" +
            optionalMaTinh().map(f -> "maTinh=" + f + ", ").orElse("") +
            optionalTenTinh().map(f -> "tenTinh=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
