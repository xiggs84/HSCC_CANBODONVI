package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DanhMucTinh} entity. This class is used
 * in {@link vn.vnpt.web.rest.DanhMucTinhResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danh-muc-tinhs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucTinhCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter maTinh;

    private StringFilter tenTinh;

    private Boolean distinct;

    public DanhMucTinhCriteria() {}

    public DanhMucTinhCriteria(DanhMucTinhCriteria other) {
        this.maTinh = other.optionalMaTinh().map(StringFilter::copy).orElse(null);
        this.tenTinh = other.optionalTenTinh().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DanhMucTinhCriteria copy() {
        return new DanhMucTinhCriteria(this);
    }

    public StringFilter getMaTinh() {
        return maTinh;
    }

    public Optional<StringFilter> optionalMaTinh() {
        return Optional.ofNullable(maTinh);
    }

    public StringFilter maTinh() {
        if (maTinh == null) {
            setMaTinh(new StringFilter());
        }
        return maTinh;
    }

    public void setMaTinh(StringFilter maTinh) {
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
        final DanhMucTinhCriteria that = (DanhMucTinhCriteria) o;
        return Objects.equals(maTinh, that.maTinh) && Objects.equals(tenTinh, that.tenTinh) && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maTinh, tenTinh, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucTinhCriteria{" +
            optionalMaTinh().map(f -> "maTinh=" + f + ", ").orElse("") +
            optionalTenTinh().map(f -> "tenTinh=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
