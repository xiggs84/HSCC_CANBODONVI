package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.QuanHeNhanThan} entity. This class is used
 * in {@link vn.vnpt.web.rest.QuanHeNhanThanResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /quan-he-nhan-thans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuanHeNhanThanCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idQuanHe;

    private StringFilter dienGiai;

    private LongFilter idQuanHeDoiUng;

    private Boolean distinct;

    public QuanHeNhanThanCriteria() {}

    public QuanHeNhanThanCriteria(QuanHeNhanThanCriteria other) {
        this.idQuanHe = other.optionalIdQuanHe().map(LongFilter::copy).orElse(null);
        this.dienGiai = other.optionalDienGiai().map(StringFilter::copy).orElse(null);
        this.idQuanHeDoiUng = other.optionalIdQuanHeDoiUng().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public QuanHeNhanThanCriteria copy() {
        return new QuanHeNhanThanCriteria(this);
    }

    public LongFilter getIdQuanHe() {
        return idQuanHe;
    }

    public Optional<LongFilter> optionalIdQuanHe() {
        return Optional.ofNullable(idQuanHe);
    }

    public LongFilter idQuanHe() {
        if (idQuanHe == null) {
            setIdQuanHe(new LongFilter());
        }
        return idQuanHe;
    }

    public void setIdQuanHe(LongFilter idQuanHe) {
        this.idQuanHe = idQuanHe;
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

    public LongFilter getIdQuanHeDoiUng() {
        return idQuanHeDoiUng;
    }

    public Optional<LongFilter> optionalIdQuanHeDoiUng() {
        return Optional.ofNullable(idQuanHeDoiUng);
    }

    public LongFilter idQuanHeDoiUng() {
        if (idQuanHeDoiUng == null) {
            setIdQuanHeDoiUng(new LongFilter());
        }
        return idQuanHeDoiUng;
    }

    public void setIdQuanHeDoiUng(LongFilter idQuanHeDoiUng) {
        this.idQuanHeDoiUng = idQuanHeDoiUng;
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
        final QuanHeNhanThanCriteria that = (QuanHeNhanThanCriteria) o;
        return (
            Objects.equals(idQuanHe, that.idQuanHe) &&
            Objects.equals(dienGiai, that.dienGiai) &&
            Objects.equals(idQuanHeDoiUng, that.idQuanHeDoiUng) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuanHe, dienGiai, idQuanHeDoiUng, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuanHeNhanThanCriteria{" +
            optionalIdQuanHe().map(f -> "idQuanHe=" + f + ", ").orElse("") +
            optionalDienGiai().map(f -> "dienGiai=" + f + ", ").orElse("") +
            optionalIdQuanHeDoiUng().map(f -> "idQuanHeDoiUng=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
