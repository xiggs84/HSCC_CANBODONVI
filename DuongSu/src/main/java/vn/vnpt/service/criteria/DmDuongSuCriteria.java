package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DmDuongSu} entity. This class is used
 * in {@link vn.vnpt.web.rest.DmDuongSuResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dm-duong-sus?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DmDuongSuCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idDuongSu;

    private StringFilter tenDuongSu;

    private StringFilter diaChi;

    private IntegerFilter trangThai;

    private StringFilter thongTinDs;

    private LocalDateFilter ngayThaoTac;

    private LongFilter nguoiThaoTac;

    private LongFilter idDsGoc;

    private StringFilter idMaster;

    private LongFilter idDonVi;

    private StringFilter strSearch;

    private StringFilter soGiayTo;

    private LongFilter idLoaiNganChan;

    private Boolean distinct;

    public DmDuongSuCriteria() {}

    public DmDuongSuCriteria(DmDuongSuCriteria other) {
        this.idDuongSu = other.optionalIdDuongSu().map(LongFilter::copy).orElse(null);
        this.tenDuongSu = other.optionalTenDuongSu().map(StringFilter::copy).orElse(null);
        this.diaChi = other.optionalDiaChi().map(StringFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(IntegerFilter::copy).orElse(null);
        this.thongTinDs = other.optionalThongTinDs().map(StringFilter::copy).orElse(null);
        this.ngayThaoTac = other.optionalNgayThaoTac().map(LocalDateFilter::copy).orElse(null);
        this.nguoiThaoTac = other.optionalNguoiThaoTac().map(LongFilter::copy).orElse(null);
        this.idDsGoc = other.optionalIdDsGoc().map(LongFilter::copy).orElse(null);
        this.idMaster = other.optionalIdMaster().map(StringFilter::copy).orElse(null);
        this.idDonVi = other.optionalIdDonVi().map(LongFilter::copy).orElse(null);
        this.strSearch = other.optionalStrSearch().map(StringFilter::copy).orElse(null);
        this.soGiayTo = other.optionalSoGiayTo().map(StringFilter::copy).orElse(null);
        this.idLoaiNganChan = other.optionalIdLoaiNganChan().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DmDuongSuCriteria copy() {
        return new DmDuongSuCriteria(this);
    }

    public LongFilter getIdDuongSu() {
        return idDuongSu;
    }

    public Optional<LongFilter> optionalIdDuongSu() {
        return Optional.ofNullable(idDuongSu);
    }

    public LongFilter idDuongSu() {
        if (idDuongSu == null) {
            setIdDuongSu(new LongFilter());
        }
        return idDuongSu;
    }

    public void setIdDuongSu(LongFilter idDuongSu) {
        this.idDuongSu = idDuongSu;
    }

    public StringFilter getTenDuongSu() {
        return tenDuongSu;
    }

    public Optional<StringFilter> optionalTenDuongSu() {
        return Optional.ofNullable(tenDuongSu);
    }

    public StringFilter tenDuongSu() {
        if (tenDuongSu == null) {
            setTenDuongSu(new StringFilter());
        }
        return tenDuongSu;
    }

    public void setTenDuongSu(StringFilter tenDuongSu) {
        this.tenDuongSu = tenDuongSu;
    }

    public StringFilter getDiaChi() {
        return diaChi;
    }

    public Optional<StringFilter> optionalDiaChi() {
        return Optional.ofNullable(diaChi);
    }

    public StringFilter diaChi() {
        if (diaChi == null) {
            setDiaChi(new StringFilter());
        }
        return diaChi;
    }

    public void setDiaChi(StringFilter diaChi) {
        this.diaChi = diaChi;
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

    public StringFilter getThongTinDs() {
        return thongTinDs;
    }

    public Optional<StringFilter> optionalThongTinDs() {
        return Optional.ofNullable(thongTinDs);
    }

    public StringFilter thongTinDs() {
        if (thongTinDs == null) {
            setThongTinDs(new StringFilter());
        }
        return thongTinDs;
    }

    public void setThongTinDs(StringFilter thongTinDs) {
        this.thongTinDs = thongTinDs;
    }

    public LocalDateFilter getNgayThaoTac() {
        return ngayThaoTac;
    }

    public Optional<LocalDateFilter> optionalNgayThaoTac() {
        return Optional.ofNullable(ngayThaoTac);
    }

    public LocalDateFilter ngayThaoTac() {
        if (ngayThaoTac == null) {
            setNgayThaoTac(new LocalDateFilter());
        }
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDateFilter ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public LongFilter getNguoiThaoTac() {
        return nguoiThaoTac;
    }

    public Optional<LongFilter> optionalNguoiThaoTac() {
        return Optional.ofNullable(nguoiThaoTac);
    }

    public LongFilter nguoiThaoTac() {
        if (nguoiThaoTac == null) {
            setNguoiThaoTac(new LongFilter());
        }
        return nguoiThaoTac;
    }

    public void setNguoiThaoTac(LongFilter nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public LongFilter getIdDsGoc() {
        return idDsGoc;
    }

    public Optional<LongFilter> optionalIdDsGoc() {
        return Optional.ofNullable(idDsGoc);
    }

    public LongFilter idDsGoc() {
        if (idDsGoc == null) {
            setIdDsGoc(new LongFilter());
        }
        return idDsGoc;
    }

    public void setIdDsGoc(LongFilter idDsGoc) {
        this.idDsGoc = idDsGoc;
    }

    public StringFilter getIdMaster() {
        return idMaster;
    }

    public Optional<StringFilter> optionalIdMaster() {
        return Optional.ofNullable(idMaster);
    }

    public StringFilter idMaster() {
        if (idMaster == null) {
            setIdMaster(new StringFilter());
        }
        return idMaster;
    }

    public void setIdMaster(StringFilter idMaster) {
        this.idMaster = idMaster;
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

    public StringFilter getStrSearch() {
        return strSearch;
    }

    public Optional<StringFilter> optionalStrSearch() {
        return Optional.ofNullable(strSearch);
    }

    public StringFilter strSearch() {
        if (strSearch == null) {
            setStrSearch(new StringFilter());
        }
        return strSearch;
    }

    public void setStrSearch(StringFilter strSearch) {
        this.strSearch = strSearch;
    }

    public StringFilter getSoGiayTo() {
        return soGiayTo;
    }

    public Optional<StringFilter> optionalSoGiayTo() {
        return Optional.ofNullable(soGiayTo);
    }

    public StringFilter soGiayTo() {
        if (soGiayTo == null) {
            setSoGiayTo(new StringFilter());
        }
        return soGiayTo;
    }

    public void setSoGiayTo(StringFilter soGiayTo) {
        this.soGiayTo = soGiayTo;
    }

    public LongFilter getIdLoaiNganChan() {
        return idLoaiNganChan;
    }

    public Optional<LongFilter> optionalIdLoaiNganChan() {
        return Optional.ofNullable(idLoaiNganChan);
    }

    public LongFilter idLoaiNganChan() {
        if (idLoaiNganChan == null) {
            setIdLoaiNganChan(new LongFilter());
        }
        return idLoaiNganChan;
    }

    public void setIdLoaiNganChan(LongFilter idLoaiNganChan) {
        this.idLoaiNganChan = idLoaiNganChan;
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
        final DmDuongSuCriteria that = (DmDuongSuCriteria) o;
        return (
            Objects.equals(idDuongSu, that.idDuongSu) &&
            Objects.equals(tenDuongSu, that.tenDuongSu) &&
            Objects.equals(diaChi, that.diaChi) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(thongTinDs, that.thongTinDs) &&
            Objects.equals(ngayThaoTac, that.ngayThaoTac) &&
            Objects.equals(nguoiThaoTac, that.nguoiThaoTac) &&
            Objects.equals(idDsGoc, that.idDsGoc) &&
            Objects.equals(idMaster, that.idMaster) &&
            Objects.equals(idDonVi, that.idDonVi) &&
            Objects.equals(strSearch, that.strSearch) &&
            Objects.equals(soGiayTo, that.soGiayTo) &&
            Objects.equals(idLoaiNganChan, that.idLoaiNganChan) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            idDuongSu,
            tenDuongSu,
            diaChi,
            trangThai,
            thongTinDs,
            ngayThaoTac,
            nguoiThaoTac,
            idDsGoc,
            idMaster,
            idDonVi,
            strSearch,
            soGiayTo,
            idLoaiNganChan,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmDuongSuCriteria{" +
            optionalIdDuongSu().map(f -> "idDuongSu=" + f + ", ").orElse("") +
            optionalTenDuongSu().map(f -> "tenDuongSu=" + f + ", ").orElse("") +
            optionalDiaChi().map(f -> "diaChi=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalThongTinDs().map(f -> "thongTinDs=" + f + ", ").orElse("") +
            optionalNgayThaoTac().map(f -> "ngayThaoTac=" + f + ", ").orElse("") +
            optionalNguoiThaoTac().map(f -> "nguoiThaoTac=" + f + ", ").orElse("") +
            optionalIdDsGoc().map(f -> "idDsGoc=" + f + ", ").orElse("") +
            optionalIdMaster().map(f -> "idMaster=" + f + ", ").orElse("") +
            optionalIdDonVi().map(f -> "idDonVi=" + f + ", ").orElse("") +
            optionalStrSearch().map(f -> "strSearch=" + f + ", ").orElse("") +
            optionalSoGiayTo().map(f -> "soGiayTo=" + f + ", ").orElse("") +
            optionalIdLoaiNganChan().map(f -> "idLoaiNganChan=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
