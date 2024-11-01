package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DanhMucXa} entity. This class is used
 * in {@link vn.vnpt.web.rest.DanhMucXaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danh-muc-xas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucXaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter maXa;

    private StringFilter tenXa;

    private StringFilter maHuyen;

    private Boolean distinct;

    public DanhMucXaCriteria() {}

    public DanhMucXaCriteria(DanhMucXaCriteria other) {
        this.maXa = other.optionalMaXa().map(StringFilter::copy).orElse(null);
        this.tenXa = other.optionalTenXa().map(StringFilter::copy).orElse(null);
        this.maHuyen = other.optionalMaHuyen().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DanhMucXaCriteria copy() {
        return new DanhMucXaCriteria(this);
    }

    public StringFilter getMaXa() {
        return maXa;
    }

    public Optional<StringFilter> optionalMaXa() {
        return Optional.ofNullable(maXa);
    }

    public StringFilter maXa() {
        if (maXa == null) {
            setMaXa(new StringFilter());
        }
        return maXa;
    }

    public void setMaXa(StringFilter maXa) {
        this.maXa = maXa;
    }

    public StringFilter getTenXa() {
        return tenXa;
    }

    public Optional<StringFilter> optionalTenXa() {
        return Optional.ofNullable(tenXa);
    }

    public StringFilter tenXa() {
        if (tenXa == null) {
            setTenXa(new StringFilter());
        }
        return tenXa;
    }

    public void setTenXa(StringFilter tenXa) {
        this.tenXa = tenXa;
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
        final DanhMucXaCriteria that = (DanhMucXaCriteria) o;
        return (
            Objects.equals(maXa, that.maXa) &&
            Objects.equals(tenXa, that.tenXa) &&
            Objects.equals(maHuyen, that.maHuyen) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(maXa, tenXa, maHuyen, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucXaCriteria{" +
            optionalMaXa().map(f -> "maXa=" + f + ", ").orElse("") +
            optionalTenXa().map(f -> "tenXa=" + f + ", ").orElse("") +
            optionalMaHuyen().map(f -> "maHuyen=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
