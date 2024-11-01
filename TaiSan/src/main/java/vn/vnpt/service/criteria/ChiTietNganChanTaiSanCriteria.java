package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.ChiTietNganChanTaiSan} entity. This class is used
 * in {@link vn.vnpt.web.rest.ChiTietNganChanTaiSanResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /chi-tiet-ngan-chan-tai-sans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChiTietNganChanTaiSanCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idNganChan;

    private LocalDateFilter ngayThaoTac;

    private StringFilter soHsCv;

    private StringFilter soCc;

    private StringFilter soVaoSo;

    private StringFilter moTa;

    private LocalDateFilter ngayNganChan;

    private LocalDateFilter ngayBdNganChan;

    private LocalDateFilter ngayKtNganChan;

    private LongFilter trangThai;

    private LongFilter nguoiThaoTac;

    private LongFilter loaiNganChan;

    private LocalDateFilter ngayCongVan;

    private LongFilter taiSanId;

    private Boolean distinct;

    public ChiTietNganChanTaiSanCriteria() {}

    public ChiTietNganChanTaiSanCriteria(ChiTietNganChanTaiSanCriteria other) {
        this.idNganChan = other.optionalIdNganChan().map(LongFilter::copy).orElse(null);
        this.ngayThaoTac = other.optionalNgayThaoTac().map(LocalDateFilter::copy).orElse(null);
        this.soHsCv = other.optionalSoHsCv().map(StringFilter::copy).orElse(null);
        this.soCc = other.optionalSoCc().map(StringFilter::copy).orElse(null);
        this.soVaoSo = other.optionalSoVaoSo().map(StringFilter::copy).orElse(null);
        this.moTa = other.optionalMoTa().map(StringFilter::copy).orElse(null);
        this.ngayNganChan = other.optionalNgayNganChan().map(LocalDateFilter::copy).orElse(null);
        this.ngayBdNganChan = other.optionalNgayBdNganChan().map(LocalDateFilter::copy).orElse(null);
        this.ngayKtNganChan = other.optionalNgayKtNganChan().map(LocalDateFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(LongFilter::copy).orElse(null);
        this.nguoiThaoTac = other.optionalNguoiThaoTac().map(LongFilter::copy).orElse(null);
        this.loaiNganChan = other.optionalLoaiNganChan().map(LongFilter::copy).orElse(null);
        this.ngayCongVan = other.optionalNgayCongVan().map(LocalDateFilter::copy).orElse(null);
        this.taiSanId = other.optionalTaiSanId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public ChiTietNganChanTaiSanCriteria copy() {
        return new ChiTietNganChanTaiSanCriteria(this);
    }

    public LongFilter getIdNganChan() {
        return idNganChan;
    }

    public Optional<LongFilter> optionalIdNganChan() {
        return Optional.ofNullable(idNganChan);
    }

    public LongFilter idNganChan() {
        if (idNganChan == null) {
            setIdNganChan(new LongFilter());
        }
        return idNganChan;
    }

    public void setIdNganChan(LongFilter idNganChan) {
        this.idNganChan = idNganChan;
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

    public StringFilter getSoHsCv() {
        return soHsCv;
    }

    public Optional<StringFilter> optionalSoHsCv() {
        return Optional.ofNullable(soHsCv);
    }

    public StringFilter soHsCv() {
        if (soHsCv == null) {
            setSoHsCv(new StringFilter());
        }
        return soHsCv;
    }

    public void setSoHsCv(StringFilter soHsCv) {
        this.soHsCv = soHsCv;
    }

    public StringFilter getSoCc() {
        return soCc;
    }

    public Optional<StringFilter> optionalSoCc() {
        return Optional.ofNullable(soCc);
    }

    public StringFilter soCc() {
        if (soCc == null) {
            setSoCc(new StringFilter());
        }
        return soCc;
    }

    public void setSoCc(StringFilter soCc) {
        this.soCc = soCc;
    }

    public StringFilter getSoVaoSo() {
        return soVaoSo;
    }

    public Optional<StringFilter> optionalSoVaoSo() {
        return Optional.ofNullable(soVaoSo);
    }

    public StringFilter soVaoSo() {
        if (soVaoSo == null) {
            setSoVaoSo(new StringFilter());
        }
        return soVaoSo;
    }

    public void setSoVaoSo(StringFilter soVaoSo) {
        this.soVaoSo = soVaoSo;
    }

    public StringFilter getMoTa() {
        return moTa;
    }

    public Optional<StringFilter> optionalMoTa() {
        return Optional.ofNullable(moTa);
    }

    public StringFilter moTa() {
        if (moTa == null) {
            setMoTa(new StringFilter());
        }
        return moTa;
    }

    public void setMoTa(StringFilter moTa) {
        this.moTa = moTa;
    }

    public LocalDateFilter getNgayNganChan() {
        return ngayNganChan;
    }

    public Optional<LocalDateFilter> optionalNgayNganChan() {
        return Optional.ofNullable(ngayNganChan);
    }

    public LocalDateFilter ngayNganChan() {
        if (ngayNganChan == null) {
            setNgayNganChan(new LocalDateFilter());
        }
        return ngayNganChan;
    }

    public void setNgayNganChan(LocalDateFilter ngayNganChan) {
        this.ngayNganChan = ngayNganChan;
    }

    public LocalDateFilter getNgayBdNganChan() {
        return ngayBdNganChan;
    }

    public Optional<LocalDateFilter> optionalNgayBdNganChan() {
        return Optional.ofNullable(ngayBdNganChan);
    }

    public LocalDateFilter ngayBdNganChan() {
        if (ngayBdNganChan == null) {
            setNgayBdNganChan(new LocalDateFilter());
        }
        return ngayBdNganChan;
    }

    public void setNgayBdNganChan(LocalDateFilter ngayBdNganChan) {
        this.ngayBdNganChan = ngayBdNganChan;
    }

    public LocalDateFilter getNgayKtNganChan() {
        return ngayKtNganChan;
    }

    public Optional<LocalDateFilter> optionalNgayKtNganChan() {
        return Optional.ofNullable(ngayKtNganChan);
    }

    public LocalDateFilter ngayKtNganChan() {
        if (ngayKtNganChan == null) {
            setNgayKtNganChan(new LocalDateFilter());
        }
        return ngayKtNganChan;
    }

    public void setNgayKtNganChan(LocalDateFilter ngayKtNganChan) {
        this.ngayKtNganChan = ngayKtNganChan;
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

    public LongFilter getLoaiNganChan() {
        return loaiNganChan;
    }

    public Optional<LongFilter> optionalLoaiNganChan() {
        return Optional.ofNullable(loaiNganChan);
    }

    public LongFilter loaiNganChan() {
        if (loaiNganChan == null) {
            setLoaiNganChan(new LongFilter());
        }
        return loaiNganChan;
    }

    public void setLoaiNganChan(LongFilter loaiNganChan) {
        this.loaiNganChan = loaiNganChan;
    }

    public LocalDateFilter getNgayCongVan() {
        return ngayCongVan;
    }

    public Optional<LocalDateFilter> optionalNgayCongVan() {
        return Optional.ofNullable(ngayCongVan);
    }

    public LocalDateFilter ngayCongVan() {
        if (ngayCongVan == null) {
            setNgayCongVan(new LocalDateFilter());
        }
        return ngayCongVan;
    }

    public void setNgayCongVan(LocalDateFilter ngayCongVan) {
        this.ngayCongVan = ngayCongVan;
    }

    public LongFilter getTaiSanId() {
        return taiSanId;
    }

    public Optional<LongFilter> optionalTaiSanId() {
        return Optional.ofNullable(taiSanId);
    }

    public LongFilter taiSanId() {
        if (taiSanId == null) {
            setTaiSanId(new LongFilter());
        }
        return taiSanId;
    }

    public void setTaiSanId(LongFilter taiSanId) {
        this.taiSanId = taiSanId;
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
        final ChiTietNganChanTaiSanCriteria that = (ChiTietNganChanTaiSanCriteria) o;
        return (
            Objects.equals(idNganChan, that.idNganChan) &&
            Objects.equals(ngayThaoTac, that.ngayThaoTac) &&
            Objects.equals(soHsCv, that.soHsCv) &&
            Objects.equals(soCc, that.soCc) &&
            Objects.equals(soVaoSo, that.soVaoSo) &&
            Objects.equals(moTa, that.moTa) &&
            Objects.equals(ngayNganChan, that.ngayNganChan) &&
            Objects.equals(ngayBdNganChan, that.ngayBdNganChan) &&
            Objects.equals(ngayKtNganChan, that.ngayKtNganChan) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(nguoiThaoTac, that.nguoiThaoTac) &&
            Objects.equals(loaiNganChan, that.loaiNganChan) &&
            Objects.equals(ngayCongVan, that.ngayCongVan) &&
            Objects.equals(taiSanId, that.taiSanId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            idNganChan,
            ngayThaoTac,
            soHsCv,
            soCc,
            soVaoSo,
            moTa,
            ngayNganChan,
            ngayBdNganChan,
            ngayKtNganChan,
            trangThai,
            nguoiThaoTac,
            loaiNganChan,
            ngayCongVan,
            taiSanId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiTietNganChanTaiSanCriteria{" +
            optionalIdNganChan().map(f -> "idNganChan=" + f + ", ").orElse("") +
            optionalNgayThaoTac().map(f -> "ngayThaoTac=" + f + ", ").orElse("") +
            optionalSoHsCv().map(f -> "soHsCv=" + f + ", ").orElse("") +
            optionalSoCc().map(f -> "soCc=" + f + ", ").orElse("") +
            optionalSoVaoSo().map(f -> "soVaoSo=" + f + ", ").orElse("") +
            optionalMoTa().map(f -> "moTa=" + f + ", ").orElse("") +
            optionalNgayNganChan().map(f -> "ngayNganChan=" + f + ", ").orElse("") +
            optionalNgayBdNganChan().map(f -> "ngayBdNganChan=" + f + ", ").orElse("") +
            optionalNgayKtNganChan().map(f -> "ngayKtNganChan=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalNguoiThaoTac().map(f -> "nguoiThaoTac=" + f + ", ").orElse("") +
            optionalLoaiNganChan().map(f -> "loaiNganChan=" + f + ", ").orElse("") +
            optionalNgayCongVan().map(f -> "ngayCongVan=" + f + ", ").orElse("") +
            optionalTaiSanId().map(f -> "taiSanId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
