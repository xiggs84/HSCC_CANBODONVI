package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DanhMucHuyen} entity. This class is used
 * in {@link vn.vnpt.web.rest.DanhMucHuyenResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danh-muc-huyens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucHuyenCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter maHuyen;

    private StringFilter tenHuyen;

    private StringFilter maTinh;

    private Boolean distinct;

    public DanhMucHuyenCriteria() {}

    public DanhMucHuyenCriteria(DanhMucHuyenCriteria other) {
        this.maHuyen = other.optionalMaHuyen().map(StringFilter::copy).orElse(null);
        this.tenHuyen = other.optionalTenHuyen().map(StringFilter::copy).orElse(null);
        this.maTinh = other.optionalMaTinh().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DanhMucHuyenCriteria copy() {
        return new DanhMucHuyenCriteria(this);
    }

    public StringFilter getMaHuyen() {
        return maHuyen;
    }

    public Optional<StringFilter> optionalMaHuyen() {
        return Optional.ofNullable(maHuyen);
    }

    public StringFilter maHuyen() {
        if (maHuyen == null) {
            setMaHuyen(new StringFilter());
        }
        return maHuyen;
    }

    public void setMaHuyen(StringFilter maHuyen) {
        this.maHuyen = maHuyen;
    }

    public StringFilter getTenHuyen() {
        return tenHuyen;
    }

    public Optional<StringFilter> optionalTenHuyen() {
        return Optional.ofNullable(tenHuyen);
    }

    public StringFilter tenHuyen() {
        if (tenHuyen == null) {
            setTenHuyen(new StringFilter());
        }
        return tenHuyen;
    }

    public void setTenHuyen(StringFilter tenHuyen) {
        this.tenHuyen = tenHuyen;
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
        final DanhMucHuyenCriteria that = (DanhMucHuyenCriteria) o;
        return (
            Objects.equals(maHuyen, that.maHuyen) &&
            Objects.equals(tenHuyen, that.tenHuyen) &&
            Objects.equals(maTinh, that.maTinh) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHuyen, tenHuyen, maTinh, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucHuyenCriteria{" +
            optionalMaHuyen().map(f -> "maHuyen=" + f + ", ").orElse("") +
            optionalTenHuyen().map(f -> "tenHuyen=" + f + ", ").orElse("") +
            optionalMaTinh().map(f -> "maTinh=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
