package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.CauHinhThongTinDuongSu} entity. This class is used
 * in {@link vn.vnpt.web.rest.CauHinhThongTinDuongSuResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cau-hinh-thong-tin-duong-sus?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CauHinhThongTinDuongSuCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idCauHinh;

    private StringFilter noiDung;

    private StringFilter javascript;

    private StringFilter css;

    private LongFilter idLoaiDs;

    private LongFilter idDonVi;

    private IntegerFilter trangThai;

    private Boolean distinct;

    public CauHinhThongTinDuongSuCriteria() {}

    public CauHinhThongTinDuongSuCriteria(CauHinhThongTinDuongSuCriteria other) {
        this.idCauHinh = other.optionalIdCauHinh().map(LongFilter::copy).orElse(null);
        this.noiDung = other.optionalNoiDung().map(StringFilter::copy).orElse(null);
        this.javascript = other.optionalJavascript().map(StringFilter::copy).orElse(null);
        this.css = other.optionalCss().map(StringFilter::copy).orElse(null);
        this.idLoaiDs = other.optionalIdLoaiDs().map(LongFilter::copy).orElse(null);
        this.idDonVi = other.optionalIdDonVi().map(LongFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(IntegerFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public CauHinhThongTinDuongSuCriteria copy() {
        return new CauHinhThongTinDuongSuCriteria(this);
    }

    public LongFilter getIdCauHinh() {
        return idCauHinh;
    }

    public Optional<LongFilter> optionalIdCauHinh() {
        return Optional.ofNullable(idCauHinh);
    }

    public LongFilter idCauHinh() {
        if (idCauHinh == null) {
            setIdCauHinh(new LongFilter());
        }
        return idCauHinh;
    }

    public void setIdCauHinh(LongFilter idCauHinh) {
        this.idCauHinh = idCauHinh;
    }

    public StringFilter getNoiDung() {
        return noiDung;
    }

    public Optional<StringFilter> optionalNoiDung() {
        return Optional.ofNullable(noiDung);
    }

    public StringFilter noiDung() {
        if (noiDung == null) {
            setNoiDung(new StringFilter());
        }
        return noiDung;
    }

    public void setNoiDung(StringFilter noiDung) {
        this.noiDung = noiDung;
    }

    public StringFilter getJavascript() {
        return javascript;
    }

    public Optional<StringFilter> optionalJavascript() {
        return Optional.ofNullable(javascript);
    }

    public StringFilter javascript() {
        if (javascript == null) {
            setJavascript(new StringFilter());
        }
        return javascript;
    }

    public void setJavascript(StringFilter javascript) {
        this.javascript = javascript;
    }

    public StringFilter getCss() {
        return css;
    }

    public Optional<StringFilter> optionalCss() {
        return Optional.ofNullable(css);
    }

    public StringFilter css() {
        if (css == null) {
            setCss(new StringFilter());
        }
        return css;
    }

    public void setCss(StringFilter css) {
        this.css = css;
    }

    public LongFilter getIdLoaiDs() {
        return idLoaiDs;
    }

    public Optional<LongFilter> optionalIdLoaiDs() {
        return Optional.ofNullable(idLoaiDs);
    }

    public LongFilter idLoaiDs() {
        if (idLoaiDs == null) {
            setIdLoaiDs(new LongFilter());
        }
        return idLoaiDs;
    }

    public void setIdLoaiDs(LongFilter idLoaiDs) {
        this.idLoaiDs = idLoaiDs;
    }

    public LongFilter getIdDonVi() {
        return idDonVi;
    }

    public Optional<LongFilter> optionalIdDonVi() {
        return Optional.ofNullable(idDonVi);
    }

    public LongFilter idDonVi() {
        if (idDonVi == null) {
            setIdDonVi(new LongFilter());
        }
        return idDonVi;
    }

    public void setIdDonVi(LongFilter idDonVi) {
        this.idDonVi = idDonVi;
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
        final CauHinhThongTinDuongSuCriteria that = (CauHinhThongTinDuongSuCriteria) o;
        return (
            Objects.equals(idCauHinh, that.idCauHinh) &&
            Objects.equals(noiDung, that.noiDung) &&
            Objects.equals(javascript, that.javascript) &&
            Objects.equals(css, that.css) &&
            Objects.equals(idLoaiDs, that.idLoaiDs) &&
            Objects.equals(idDonVi, that.idDonVi) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCauHinh, noiDung, javascript, css, idLoaiDs, idDonVi, trangThai, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CauHinhThongTinDuongSuCriteria{" +
            optionalIdCauHinh().map(f -> "idCauHinh=" + f + ", ").orElse("") +
            optionalNoiDung().map(f -> "noiDung=" + f + ", ").orElse("") +
            optionalJavascript().map(f -> "javascript=" + f + ", ").orElse("") +
            optionalCss().map(f -> "css=" + f + ", ").orElse("") +
            optionalIdLoaiDs().map(f -> "idLoaiDs=" + f + ", ").orElse("") +
            optionalIdDonVi().map(f -> "idDonVi=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
