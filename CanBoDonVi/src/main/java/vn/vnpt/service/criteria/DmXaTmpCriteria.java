package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DmXaTmp} entity. This class is used
 * in {@link vn.vnpt.web.rest.DmXaTmpResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dm-xa-tmps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmXaTmpCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter maXa;

    private StringFilter tenXa;

    private Boolean distinct;

    public DmXaTmpCriteria() {}

    public DmXaTmpCriteria(DmXaTmpCriteria other) {
        this.maXa = other.optionalMaXa().map(LongFilter::copy).orElse(null);
        this.tenXa = other.optionalTenXa().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DmXaTmpCriteria copy() {
        return new DmXaTmpCriteria(this);
    }

    public LongFilter getMaXa() {
        return maXa;
    }

    public Optional<LongFilter> optionalMaXa() {
        return Optional.ofNullable(maXa);
    }

    public LongFilter maXa() {
        if (maXa == null) {
            setMaXa(new LongFilter());
        }
        return maXa;
    }

    public void setMaXa(LongFilter maXa) {
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
        final DmXaTmpCriteria that = (DmXaTmpCriteria) o;
        return Objects.equals(maXa, that.maXa) && Objects.equals(tenXa, that.tenXa) && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maXa, tenXa, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmXaTmpCriteria{" +
            optionalMaXa().map(f -> "maXa=" + f + ", ").orElse("") +
            optionalTenXa().map(f -> "tenXa=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
