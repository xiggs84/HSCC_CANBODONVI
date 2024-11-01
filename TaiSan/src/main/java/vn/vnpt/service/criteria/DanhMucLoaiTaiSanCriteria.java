package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DanhMucLoaiTaiSan} entity. This class is used
 * in {@link vn.vnpt.web.rest.DanhMucLoaiTaiSanResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danh-muc-loai-tai-sans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiTaiSanCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idLoaiTs;

    private StringFilter dienGiai;

    private LongFilter trangThai;

    private LongFilter loaiTaiSanId;

    private LongFilter danhSachTaiSanId;

    private LongFilter taiSanDgcId;

    private LongFilter taiSanDatNhaId;

    private LongFilter thongTinCapNhatTaiSanId;

    private Boolean distinct;

    public DanhMucLoaiTaiSanCriteria() {}

    public DanhMucLoaiTaiSanCriteria(DanhMucLoaiTaiSanCriteria other) {
        this.idLoaiTs = other.optionalIdLoaiTs().map(LongFilter::copy).orElse(null);
        this.dienGiai = other.optionalDienGiai().map(StringFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(LongFilter::copy).orElse(null);
        this.loaiTaiSanId = other.optionalLoaiTaiSanId().map(LongFilter::copy).orElse(null);
        this.danhSachTaiSanId = other.optionalDanhSachTaiSanId().map(LongFilter::copy).orElse(null);
        this.taiSanDgcId = other.optionalTaiSanDgcId().map(LongFilter::copy).orElse(null);
        this.taiSanDatNhaId = other.optionalTaiSanDatNhaId().map(LongFilter::copy).orElse(null);
        this.thongTinCapNhatTaiSanId = other.optionalThongTinCapNhatTaiSanId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DanhMucLoaiTaiSanCriteria copy() {
        return new DanhMucLoaiTaiSanCriteria(this);
    }

    public LongFilter getIdLoaiTs() {
        return idLoaiTs;
    }

    public Optional<LongFilter> optionalIdLoaiTs() {
        return Optional.ofNullable(idLoaiTs);
    }

    public LongFilter idLoaiTs() {
        if (idLoaiTs == null) {
            setIdLoaiTs(new LongFilter());
        }
        return idLoaiTs;
    }

    public void setIdLoaiTs(LongFilter idLoaiTs) {
        this.idLoaiTs = idLoaiTs;
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

    public LongFilter getLoaiTaiSanId() {
        return loaiTaiSanId;
    }

    public Optional<LongFilter> optionalLoaiTaiSanId() {
        return Optional.ofNullable(loaiTaiSanId);
    }

    public LongFilter loaiTaiSanId() {
        if (loaiTaiSanId == null) {
            setLoaiTaiSanId(new LongFilter());
        }
        return loaiTaiSanId;
    }

    public void setLoaiTaiSanId(LongFilter loaiTaiSanId) {
        this.loaiTaiSanId = loaiTaiSanId;
    }

    public LongFilter getDanhSachTaiSanId() {
        return danhSachTaiSanId;
    }

    public Optional<LongFilter> optionalDanhSachTaiSanId() {
        return Optional.ofNullable(danhSachTaiSanId);
    }

    public LongFilter danhSachTaiSanId() {
        if (danhSachTaiSanId == null) {
            setDanhSachTaiSanId(new LongFilter());
        }
        return danhSachTaiSanId;
    }

    public void setDanhSachTaiSanId(LongFilter danhSachTaiSanId) {
        this.danhSachTaiSanId = danhSachTaiSanId;
    }

    public LongFilter getTaiSanDgcId() {
        return taiSanDgcId;
    }

    public Optional<LongFilter> optionalTaiSanDgcId() {
        return Optional.ofNullable(taiSanDgcId);
    }

    public LongFilter taiSanDgcId() {
        if (taiSanDgcId == null) {
            setTaiSanDgcId(new LongFilter());
        }
        return taiSanDgcId;
    }

    public void setTaiSanDgcId(LongFilter taiSanDgcId) {
        this.taiSanDgcId = taiSanDgcId;
    }

    public LongFilter getTaiSanDatNhaId() {
        return taiSanDatNhaId;
    }

    public Optional<LongFilter> optionalTaiSanDatNhaId() {
        return Optional.ofNullable(taiSanDatNhaId);
    }

    public LongFilter taiSanDatNhaId() {
        if (taiSanDatNhaId == null) {
            setTaiSanDatNhaId(new LongFilter());
        }
        return taiSanDatNhaId;
    }

    public void setTaiSanDatNhaId(LongFilter taiSanDatNhaId) {
        this.taiSanDatNhaId = taiSanDatNhaId;
    }

    public LongFilter getThongTinCapNhatTaiSanId() {
        return thongTinCapNhatTaiSanId;
    }

    public Optional<LongFilter> optionalThongTinCapNhatTaiSanId() {
        return Optional.ofNullable(thongTinCapNhatTaiSanId);
    }

    public LongFilter thongTinCapNhatTaiSanId() {
        if (thongTinCapNhatTaiSanId == null) {
            setThongTinCapNhatTaiSanId(new LongFilter());
        }
        return thongTinCapNhatTaiSanId;
    }

    public void setThongTinCapNhatTaiSanId(LongFilter thongTinCapNhatTaiSanId) {
        this.thongTinCapNhatTaiSanId = thongTinCapNhatTaiSanId;
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
        final DanhMucLoaiTaiSanCriteria that = (DanhMucLoaiTaiSanCriteria) o;
        return (
            Objects.equals(idLoaiTs, that.idLoaiTs) &&
            Objects.equals(dienGiai, that.dienGiai) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(loaiTaiSanId, that.loaiTaiSanId) &&
            Objects.equals(danhSachTaiSanId, that.danhSachTaiSanId) &&
            Objects.equals(taiSanDgcId, that.taiSanDgcId) &&
            Objects.equals(taiSanDatNhaId, that.taiSanDatNhaId) &&
            Objects.equals(thongTinCapNhatTaiSanId, that.thongTinCapNhatTaiSanId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            idLoaiTs,
            dienGiai,
            trangThai,
            loaiTaiSanId,
            danhSachTaiSanId,
            taiSanDgcId,
            taiSanDatNhaId,
            thongTinCapNhatTaiSanId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiTaiSanCriteria{" +
            optionalIdLoaiTs().map(f -> "idLoaiTs=" + f + ", ").orElse("") +
            optionalDienGiai().map(f -> "dienGiai=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalLoaiTaiSanId().map(f -> "loaiTaiSanId=" + f + ", ").orElse("") +
            optionalDanhSachTaiSanId().map(f -> "danhSachTaiSanId=" + f + ", ").orElse("") +
            optionalTaiSanDgcId().map(f -> "taiSanDgcId=" + f + ", ").orElse("") +
            optionalTaiSanDatNhaId().map(f -> "taiSanDatNhaId=" + f + ", ").orElse("") +
            optionalThongTinCapNhatTaiSanId().map(f -> "thongTinCapNhatTaiSanId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
