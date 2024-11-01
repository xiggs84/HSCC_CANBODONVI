package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.QuanHeDuongSu} entity. This class is used
 * in {@link vn.vnpt.web.rest.QuanHeDuongSuResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /quan-he-duong-sus?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QuanHeDuongSuCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idQuanHe;

    private LongFilter idDuongSuQh;

    private IntegerFilter trangThai;

    private LongFilter duongSuId;

    private Boolean distinct;

    public QuanHeDuongSuCriteria() {}

    public QuanHeDuongSuCriteria(QuanHeDuongSuCriteria other) {
        this.idQuanHe = other.optionalIdQuanHe().map(LongFilter::copy).orElse(null);
        this.idDuongSuQh = other.optionalIdDuongSuQh().map(LongFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(IntegerFilter::copy).orElse(null);
        this.duongSuId = other.optionalDuongSuId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public QuanHeDuongSuCriteria copy() {
        return new QuanHeDuongSuCriteria(this);
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

    public LongFilter getIdDuongSuQh() {
        return idDuongSuQh;
    }

    public Optional<LongFilter> optionalIdDuongSuQh() {
        return Optional.ofNullable(idDuongSuQh);
    }

    public LongFilter idDuongSuQh() {
        if (idDuongSuQh == null) {
            setIdDuongSuQh(new LongFilter());
        }
        return idDuongSuQh;
    }

    public void setIdDuongSuQh(LongFilter idDuongSuQh) {
        this.idDuongSuQh = idDuongSuQh;
    }

    public IntegerFilter getTrangThai() {
        return trangThai;
    }

    public Optional<IntegerFilter> optionalTrangThai() {
        return Optional.ofNullable(trangThai);
    }

    public IntegerFilter trangThai() {
        if (trangThai == null) {
            setTrangThai(new IntegerFilter());
        }
        return trangThai;
    }

    public void setTrangThai(IntegerFilter trangThai) {
        this.trangThai = trangThai;
    }

    public LongFilter getDuongSuId() {
        return duongSuId;
    }

    public Optional<LongFilter> optionalDuongSuId() {
        return Optional.ofNullable(duongSuId);
    }

    public LongFilter duongSuId() {
        if (duongSuId == null) {
            setDuongSuId(new LongFilter());
        }
        return duongSuId;
    }

    public void setDuongSuId(LongFilter duongSuId) {
        this.duongSuId = duongSuId;
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
        final QuanHeDuongSuCriteria that = (QuanHeDuongSuCriteria) o;
        return (
            Objects.equals(idQuanHe, that.idQuanHe) &&
            Objects.equals(idDuongSuQh, that.idDuongSuQh) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(duongSuId, that.duongSuId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuanHe, idDuongSuQh, trangThai, duongSuId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QuanHeDuongSuCriteria{" +
            optionalIdQuanHe().map(f -> "idQuanHe=" + f + ", ").orElse("") +
            optionalIdDuongSuQh().map(f -> "idDuongSuQh=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalDuongSuId().map(f -> "duongSuId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
