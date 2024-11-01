package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DanhMucCapQuanLy} entity. This class is used
 * in {@link vn.vnpt.web.rest.DanhMucCapQuanLyResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danh-muc-cap-quan-lies?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucCapQuanLyCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idCapQl;

    private StringFilter dienGiai;

    private Boolean distinct;

    public DanhMucCapQuanLyCriteria() {}

    public DanhMucCapQuanLyCriteria(DanhMucCapQuanLyCriteria other) {
        this.idCapQl = other.optionalIdCapQl().map(LongFilter::copy).orElse(null);
        this.dienGiai = other.optionalDienGiai().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DanhMucCapQuanLyCriteria copy() {
        return new DanhMucCapQuanLyCriteria(this);
    }

    public LongFilter getIdCapQl() {
        return idCapQl;
    }

    public Optional<LongFilter> optionalIdCapQl() {
        return Optional.ofNullable(idCapQl);
    }

    public LongFilter idCapQl() {
        if (idCapQl == null) {
            setIdCapQl(new LongFilter());
        }
        return idCapQl;
    }

    public void setIdCapQl(LongFilter idCapQl) {
        this.idCapQl = idCapQl;
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
        final DanhMucCapQuanLyCriteria that = (DanhMucCapQuanLyCriteria) o;
        return Objects.equals(idCapQl, that.idCapQl) && Objects.equals(dienGiai, that.dienGiai) && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCapQl, dienGiai, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucCapQuanLyCriteria{" +
            optionalIdCapQl().map(f -> "idCapQl=" + f + ", ").orElse("") +
            optionalDienGiai().map(f -> "dienGiai=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
