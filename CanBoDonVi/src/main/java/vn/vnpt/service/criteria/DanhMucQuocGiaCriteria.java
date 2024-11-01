package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DanhMucQuocGia} entity. This class is used
 * in {@link vn.vnpt.web.rest.DanhMucQuocGiaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danh-muc-quoc-gias?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucQuocGiaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idQuocGia;

    private StringFilter tenQuocGia;

    private StringFilter tenTiengAnh;

    private Boolean distinct;

    public DanhMucQuocGiaCriteria() {}

    public DanhMucQuocGiaCriteria(DanhMucQuocGiaCriteria other) {
        this.idQuocGia = other.optionalIdQuocGia().map(LongFilter::copy).orElse(null);
        this.tenQuocGia = other.optionalTenQuocGia().map(StringFilter::copy).orElse(null);
        this.tenTiengAnh = other.optionalTenTiengAnh().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DanhMucQuocGiaCriteria copy() {
        return new DanhMucQuocGiaCriteria(this);
    }

    public LongFilter getIdQuocGia() {
        return idQuocGia;
    }

    public Optional<LongFilter> optionalIdQuocGia() {
        return Optional.ofNullable(idQuocGia);
    }

    public LongFilter idQuocGia() {
        if (idQuocGia == null) {
            setIdQuocGia(new LongFilter());
        }
        return idQuocGia;
    }

    public void setIdQuocGia(LongFilter idQuocGia) {
        this.idQuocGia = idQuocGia;
    }

    public StringFilter getTenQuocGia() {
        return tenQuocGia;
    }

    public Optional<StringFilter> optionalTenQuocGia() {
        return Optional.ofNullable(tenQuocGia);
    }

    public StringFilter tenQuocGia() {
        if (tenQuocGia == null) {
            setTenQuocGia(new StringFilter());
        }
        return tenQuocGia;
    }

    public void setTenQuocGia(StringFilter tenQuocGia) {
        this.tenQuocGia = tenQuocGia;
    }

    public StringFilter getTenTiengAnh() {
        return tenTiengAnh;
    }

    public Optional<StringFilter> optionalTenTiengAnh() {
        return Optional.ofNullable(tenTiengAnh);
    }

    public StringFilter tenTiengAnh() {
        if (tenTiengAnh == null) {
            setTenTiengAnh(new StringFilter());
        }
        return tenTiengAnh;
    }

    public void setTenTiengAnh(StringFilter tenTiengAnh) {
        this.tenTiengAnh = tenTiengAnh;
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
        final DanhMucQuocGiaCriteria that = (DanhMucQuocGiaCriteria) o;
        return (
            Objects.equals(idQuocGia, that.idQuocGia) &&
            Objects.equals(tenQuocGia, that.tenQuocGia) &&
            Objects.equals(tenTiengAnh, that.tenTiengAnh) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuocGia, tenQuocGia, tenTiengAnh, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucQuocGiaCriteria{" +
            optionalIdQuocGia().map(f -> "idQuocGia=" + f + ", ").orElse("") +
            optionalTenQuocGia().map(f -> "tenQuocGia=" + f + ", ").orElse("") +
            optionalTenTiengAnh().map(f -> "tenTiengAnh=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
