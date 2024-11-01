package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.NoiCapGttt} entity. This class is used
 * in {@link vn.vnpt.web.rest.NoiCapGtttResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /noi-cap-gttts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NoiCapGtttCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idNoiCap;

    private StringFilter dienGiai;

    private LongFilter trangThai;

    private Boolean distinct;

    public NoiCapGtttCriteria() {}

    public NoiCapGtttCriteria(NoiCapGtttCriteria other) {
        this.idNoiCap = other.optionalIdNoiCap().map(LongFilter::copy).orElse(null);
        this.dienGiai = other.optionalDienGiai().map(StringFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public NoiCapGtttCriteria copy() {
        return new NoiCapGtttCriteria(this);
    }

    public LongFilter getIdNoiCap() {
        return idNoiCap;
    }

    public Optional<LongFilter> optionalIdNoiCap() {
        return Optional.ofNullable(idNoiCap);
    }

    public LongFilter idNoiCap() {
        if (idNoiCap == null) {
            setIdNoiCap(new LongFilter());
        }
        return idNoiCap;
    }

    public void setIdNoiCap(LongFilter idNoiCap) {
        this.idNoiCap = idNoiCap;
    }

    public StringFilter getDienGiai() {
        return dienGiai;
    }

    public Optional<StringFilter> optionalDienGiai() {
        return Optional.ofNullable(dienGiai);
    }

    public StringFilter dienGiai() {
        if (dienGiai == null) {
            setDienGiai(new StringFilter());
        }
        return dienGiai;
    }

    public void setDienGiai(StringFilter dienGiai) {
        this.dienGiai = dienGiai;
    }

    public LongFilter getTrangThai() {
        return trangThai;
    }

    public Optional<LongFilter> optionalTrangThai() {
        return Optional.ofNullable(trangThai);
    }

    public LongFilter trangThai() {
        if (trangThai == null) {
            setTrangThai(new LongFilter());
        }
        return trangThai;
    }

    public void setTrangThai(LongFilter trangThai) {
        this.trangThai = trangThai;
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
        final NoiCapGtttCriteria that = (NoiCapGtttCriteria) o;
        return (
            Objects.equals(idNoiCap, that.idNoiCap) &&
            Objects.equals(dienGiai, that.dienGiai) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNoiCap, dienGiai, trangThai, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NoiCapGtttCriteria{" +
            optionalIdNoiCap().map(f -> "idNoiCap=" + f + ", ").orElse("") +
            optionalDienGiai().map(f -> "dienGiai=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
