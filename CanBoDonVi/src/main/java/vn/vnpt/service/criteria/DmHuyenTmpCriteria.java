package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DmHuyenTmp} entity. This class is used
 * in {@link vn.vnpt.web.rest.DmHuyenTmpResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dm-huyen-tmps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmHuyenTmpCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter maHuyen;

    private StringFilter tenHuyen;

    private Boolean distinct;

    public DmHuyenTmpCriteria() {}

    public DmHuyenTmpCriteria(DmHuyenTmpCriteria other) {
        this.maHuyen = other.optionalMaHuyen().map(LongFilter::copy).orElse(null);
        this.tenHuyen = other.optionalTenHuyen().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DmHuyenTmpCriteria copy() {
        return new DmHuyenTmpCriteria(this);
    }

    public LongFilter getMaHuyen() {
        return maHuyen;
    }

    public Optional<LongFilter> optionalMaHuyen() {
        return Optional.ofNullable(maHuyen);
    }

    public LongFilter maHuyen() {
        if (maHuyen == null) {
            setMaHuyen(new LongFilter());
        }
        return maHuyen;
    }

    public void setMaHuyen(LongFilter maHuyen) {
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
        final DmHuyenTmpCriteria that = (DmHuyenTmpCriteria) o;
        return Objects.equals(maHuyen, that.maHuyen) && Objects.equals(tenHuyen, that.tenHuyen) && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHuyen, tenHuyen, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmHuyenTmpCriteria{" +
            optionalMaHuyen().map(f -> "maHuyen=" + f + ", ").orElse("") +
            optionalTenHuyen().map(f -> "tenHuyen=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
