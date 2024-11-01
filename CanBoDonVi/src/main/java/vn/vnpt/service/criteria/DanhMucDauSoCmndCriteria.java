package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DanhMucDauSoCmnd} entity. This class is used
 * in {@link vn.vnpt.web.rest.DanhMucDauSoCmndResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danh-muc-dau-so-cmnds?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucDauSoCmndCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idDauSo;

    private StringFilter dauSo;

    private StringFilter tinhThanh;

    private LongFilter idLoai;

    private Boolean distinct;

    public DanhMucDauSoCmndCriteria() {}

    public DanhMucDauSoCmndCriteria(DanhMucDauSoCmndCriteria other) {
        this.idDauSo = other.optionalIdDauSo().map(LongFilter::copy).orElse(null);
        this.dauSo = other.optionalDauSo().map(StringFilter::copy).orElse(null);
        this.tinhThanh = other.optionalTinhThanh().map(StringFilter::copy).orElse(null);
        this.idLoai = other.optionalIdLoai().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DanhMucDauSoCmndCriteria copy() {
        return new DanhMucDauSoCmndCriteria(this);
    }

    public LongFilter getIdDauSo() {
        return idDauSo;
    }

    public Optional<LongFilter> optionalIdDauSo() {
        return Optional.ofNullable(idDauSo);
    }

    public LongFilter idDauSo() {
        if (idDauSo == null) {
            setIdDauSo(new LongFilter());
        }
        return idDauSo;
    }

    public void setIdDauSo(LongFilter idDauSo) {
        this.idDauSo = idDauSo;
    }

    public StringFilter getDauSo() {
        return dauSo;
    }

    public Optional<StringFilter> optionalDauSo() {
        return Optional.ofNullable(dauSo);
    }

    public StringFilter dauSo() {
        if (dauSo == null) {
            setDauSo(new StringFilter());
        }
        return dauSo;
    }

    public void setDauSo(StringFilter dauSo) {
        this.dauSo = dauSo;
    }

    public StringFilter getTinhThanh() {
        return tinhThanh;
    }

    public Optional<StringFilter> optionalTinhThanh() {
        return Optional.ofNullable(tinhThanh);
    }

    public StringFilter tinhThanh() {
        if (tinhThanh == null) {
            setTinhThanh(new StringFilter());
        }
        return tinhThanh;
    }

    public void setTinhThanh(StringFilter tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public LongFilter getIdLoai() {
        return idLoai;
    }

    public Optional<LongFilter> optionalIdLoai() {
        return Optional.ofNullable(idLoai);
    }

    public LongFilter idLoai() {
        if (idLoai == null) {
            setIdLoai(new LongFilter());
        }
        return idLoai;
    }

    public void setIdLoai(LongFilter idLoai) {
        this.idLoai = idLoai;
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
        final DanhMucDauSoCmndCriteria that = (DanhMucDauSoCmndCriteria) o;
        return (
            Objects.equals(idDauSo, that.idDauSo) &&
            Objects.equals(dauSo, that.dauSo) &&
            Objects.equals(tinhThanh, that.tinhThanh) &&
            Objects.equals(idLoai, that.idLoai) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDauSo, dauSo, tinhThanh, idLoai, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucDauSoCmndCriteria{" +
            optionalIdDauSo().map(f -> "idDauSo=" + f + ", ").orElse("") +
            optionalDauSo().map(f -> "dauSo=" + f + ", ").orElse("") +
            optionalTinhThanh().map(f -> "tinhThanh=" + f + ", ").orElse("") +
            optionalIdLoai().map(f -> "idLoai=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
