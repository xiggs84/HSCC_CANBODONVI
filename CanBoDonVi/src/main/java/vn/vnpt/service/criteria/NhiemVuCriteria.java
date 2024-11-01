package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.NhiemVu} entity. This class is used
 * in {@link vn.vnpt.web.rest.NhiemVuResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /nhiem-vus?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NhiemVuCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter idNhiemVu;

    private StringFilter tenNhiemVu;

    private LongFilter idNhiemVuId;

    private Boolean distinct;

    public NhiemVuCriteria() {}

    public NhiemVuCriteria(NhiemVuCriteria other) {
        this.idNhiemVu = other.optionalIdNhiemVu().map(StringFilter::copy).orElse(null);
        this.tenNhiemVu = other.optionalTenNhiemVu().map(StringFilter::copy).orElse(null);
        this.idNhiemVuId = other.optionalIdNhiemVuId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public NhiemVuCriteria copy() {
        return new NhiemVuCriteria(this);
    }

    public StringFilter getIdNhiemVu() {
        return idNhiemVu;
    }

    public Optional<StringFilter> optionalIdNhiemVu() {
        return Optional.ofNullable(idNhiemVu);
    }

    public StringFilter idNhiemVu() {
        if (idNhiemVu == null) {
            setIdNhiemVu(new StringFilter());
        }
        return idNhiemVu;
    }

    public void setIdNhiemVu(StringFilter idNhiemVu) {
        this.idNhiemVu = idNhiemVu;
    }

    public StringFilter getTenNhiemVu() {
        return tenNhiemVu;
    }

    public Optional<StringFilter> optionalTenNhiemVu() {
        return Optional.ofNullable(tenNhiemVu);
    }

    public StringFilter tenNhiemVu() {
        if (tenNhiemVu == null) {
            setTenNhiemVu(new StringFilter());
        }
        return tenNhiemVu;
    }

    public void setTenNhiemVu(StringFilter tenNhiemVu) {
        this.tenNhiemVu = tenNhiemVu;
    }

    public LongFilter getIdNhiemVuId() {
        return idNhiemVuId;
    }

    public Optional<LongFilter> optionalIdNhiemVuId() {
        return Optional.ofNullable(idNhiemVuId);
    }

    public LongFilter idNhiemVuId() {
        if (idNhiemVuId == null) {
            setIdNhiemVuId(new LongFilter());
        }
        return idNhiemVuId;
    }

    public void setIdNhiemVuId(LongFilter idNhiemVuId) {
        this.idNhiemVuId = idNhiemVuId;
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
        final NhiemVuCriteria that = (NhiemVuCriteria) o;
        return (
            Objects.equals(idNhiemVu, that.idNhiemVu) &&
            Objects.equals(tenNhiemVu, that.tenNhiemVu) &&
            Objects.equals(idNhiemVuId, that.idNhiemVuId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNhiemVu, tenNhiemVu, idNhiemVuId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NhiemVuCriteria{" +
            optionalIdNhiemVu().map(f -> "idNhiemVu=" + f + ", ").orElse("") +
            optionalTenNhiemVu().map(f -> "tenNhiemVu=" + f + ", ").orElse("") +
            optionalIdNhiemVuId().map(f -> "idNhiemVuId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
