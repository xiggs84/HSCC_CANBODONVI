package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.Quyen} entity. This class is used
 * in {@link vn.vnpt.web.rest.QuyenResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /quyens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuyenCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idQuyen;

    private StringFilter tenQuyen;

    private Boolean distinct;

    public QuyenCriteria() {}

    public QuyenCriteria(QuyenCriteria other) {
        this.idQuyen = other.optionalIdQuyen().map(LongFilter::copy).orElse(null);
        this.tenQuyen = other.optionalTenQuyen().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public QuyenCriteria copy() {
        return new QuyenCriteria(this);
    }

    public LongFilter getIdQuyen() {
        return idQuyen;
    }

    public Optional<LongFilter> optionalIdQuyen() {
        return Optional.ofNullable(idQuyen);
    }

    public LongFilter idQuyen() {
        if (idQuyen == null) {
            setIdQuyen(new LongFilter());
        }
        return idQuyen;
    }

    public void setIdQuyen(LongFilter idQuyen) {
        this.idQuyen = idQuyen;
    }

    public StringFilter getTenQuyen() {
        return tenQuyen;
    }

    public Optional<StringFilter> optionalTenQuyen() {
        return Optional.ofNullable(tenQuyen);
    }

    public StringFilter tenQuyen() {
        if (tenQuyen == null) {
            setTenQuyen(new StringFilter());
        }
        return tenQuyen;
    }

    public void setTenQuyen(StringFilter tenQuyen) {
        this.tenQuyen = tenQuyen;
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
        final QuyenCriteria that = (QuyenCriteria) o;
        return Objects.equals(idQuyen, that.idQuyen) && Objects.equals(tenQuyen, that.tenQuyen) && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuyen, tenQuyen, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuyenCriteria{" +
            optionalIdQuyen().map(f -> "idQuyen=" + f + ", ").orElse("") +
            optionalTenQuyen().map(f -> "tenQuyen=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
