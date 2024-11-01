package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.TaiSan} entity. This class is used
 * in {@link vn.vnpt.web.rest.TaiSanResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /tai-sans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TaiSanCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idTaiSan;

    private StringFilter tenTaiSan;

    private LongFilter trangThai;

    private StringFilter ghiChu;

    private LocalDateFilter ngayThaoTac;

    private LongFilter nguoiThaoTac;

    private LongFilter idDuongSu;

    private LongFilter idTsGoc;

    private StringFilter maTaiSan;

    private LongFilter idLoaiNganChan;

    private LocalDateFilter ngayBdNganChan;

    private LocalDateFilter ngayKtNganChan;

    private LongFilter idMaster;

    private StringFilter strSearch;

    private LongFilter idDonVi;

    private LongFilter soHsCv;

    private LongFilter soCc;

    private LongFilter soVaoSo;

    private StringFilter moTa;

    private LongFilter loaiNganChan;

    private LongFilter syncStatus;

    private LongFilter thuaTachId;

    private LongFilter taiSanDuongSuId;

    private LongFilter taiSanDgcId;

    private LongFilter thongTinCapNhatTaiSanId;

    private LongFilter danhMucLoaiTaiSanId;

    private LongFilter tinhTrangTaiSanId;

    private LongFilter chiTietNganChanTaiSanId;

    private Boolean distinct;

    public TaiSanCriteria() {}

    public TaiSanCriteria(TaiSanCriteria other) {
        this.idTaiSan = other.optionalIdTaiSan().map(LongFilter::copy).orElse(null);
        this.tenTaiSan = other.optionalTenTaiSan().map(StringFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(LongFilter::copy).orElse(null);
        this.ghiChu = other.optionalGhiChu().map(StringFilter::copy).orElse(null);
        this.ngayThaoTac = other.optionalNgayThaoTac().map(LocalDateFilter::copy).orElse(null);
        this.nguoiThaoTac = other.optionalNguoiThaoTac().map(LongFilter::copy).orElse(null);
        this.idDuongSu = other.optionalIdDuongSu().map(LongFilter::copy).orElse(null);
        this.idTsGoc = other.optionalIdTsGoc().map(LongFilter::copy).orElse(null);
        this.maTaiSan = other.optionalMaTaiSan().map(StringFilter::copy).orElse(null);
        this.idLoaiNganChan = other.optionalIdLoaiNganChan().map(LongFilter::copy).orElse(null);
        this.ngayBdNganChan = other.optionalNgayBdNganChan().map(LocalDateFilter::copy).orElse(null);
        this.ngayKtNganChan = other.optionalNgayKtNganChan().map(LocalDateFilter::copy).orElse(null);
        this.idMaster = other.optionalIdMaster().map(LongFilter::copy).orElse(null);
        this.strSearch = other.optionalStrSearch().map(StringFilter::copy).orElse(null);
        this.idDonVi = other.optionalIdDonVi().map(LongFilter::copy).orElse(null);
        this.soHsCv = other.optionalSoHsCv().map(LongFilter::copy).orElse(null);
        this.soCc = other.optionalSoCc().map(LongFilter::copy).orElse(null);
        this.soVaoSo = other.optionalSoVaoSo().map(LongFilter::copy).orElse(null);
        this.moTa = other.optionalMoTa().map(StringFilter::copy).orElse(null);
        this.loaiNganChan = other.optionalLoaiNganChan().map(LongFilter::copy).orElse(null);
        this.syncStatus = other.optionalSyncStatus().map(LongFilter::copy).orElse(null);
        this.thuaTachId = other.optionalThuaTachId().map(LongFilter::copy).orElse(null);
        this.taiSanDuongSuId = other.optionalTaiSanDuongSuId().map(LongFilter::copy).orElse(null);
        this.taiSanDgcId = other.optionalTaiSanDgcId().map(LongFilter::copy).orElse(null);
        this.thongTinCapNhatTaiSanId = other.optionalThongTinCapNhatTaiSanId().map(LongFilter::copy).orElse(null);
        this.danhMucLoaiTaiSanId = other.optionalDanhMucLoaiTaiSanId().map(LongFilter::copy).orElse(null);
        this.tinhTrangTaiSanId = other.optionalTinhTrangTaiSanId().map(LongFilter::copy).orElse(null);
        this.chiTietNganChanTaiSanId = other.optionalChiTietNganChanTaiSanId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public TaiSanCriteria copy() {
        return new TaiSanCriteria(this);
    }

    public LongFilter getIdTaiSan() {
        return idTaiSan;
    }

    public Optional<LongFilter> optionalIdTaiSan() {
        return Optional.ofNullable(idTaiSan);
    }

    public LongFilter idTaiSan() {
        if (idTaiSan == null) {
            setIdTaiSan(new LongFilter());
        }
        return idTaiSan;
    }

    public void setIdTaiSan(LongFilter idTaiSan) {
        this.idTaiSan = idTaiSan;
    }

    public StringFilter getTenTaiSan() {
        return tenTaiSan;
    }

    public Optional<StringFilter> optionalTenTaiSan() {
        return Optional.ofNullable(tenTaiSan);
    }

    public StringFilter tenTaiSan() {
        if (tenTaiSan == null) {
            setTenTaiSan(new StringFilter());
        }
        return tenTaiSan;
    }

    public void setTenTaiSan(StringFilter tenTaiSan) {
        this.tenTaiSan = tenTaiSan;
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

    public StringFilter getGhiChu() {
        return ghiChu;
    }

    public Optional<StringFilter> optionalGhiChu() {
        return Optional.ofNullable(ghiChu);
    }

    public StringFilter ghiChu() {
        if (ghiChu == null) {
            setGhiChu(new StringFilter());
        }
        return ghiChu;
    }

    public void setGhiChu(StringFilter ghiChu) {
        this.ghiChu = ghiChu;
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

    public LongFilter getIdTsGoc() {
        return idTsGoc;
    }

    public Optional<LongFilter> optionalIdTsGoc() {
        return Optional.ofNullable(idTsGoc);
    }

    public LongFilter idTsGoc() {
        if (idTsGoc == null) {
            setIdTsGoc(new LongFilter());
        }
        return idTsGoc;
    }

    public void setIdTsGoc(LongFilter idTsGoc) {
        this.idTsGoc = idTsGoc;
    }

    public StringFilter getMaTaiSan() {
        return maTaiSan;
    }

    public Optional<StringFilter> optionalMaTaiSan() {
        return Optional.ofNullable(maTaiSan);
    }

    public StringFilter maTaiSan() {
        if (maTaiSan == null) {
            setMaTaiSan(new StringFilter());
        }
        return maTaiSan;
    }

    public void setMaTaiSan(StringFilter maTaiSan) {
        this.maTaiSan = maTaiSan;
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

    public LongFilter getIdMaster() {
        return idMaster;
    }

    public Optional<LongFilter> optionalIdMaster() {
        return Optional.ofNullable(idMaster);
    }

    public LongFilter idMaster() {
        if (idMaster == null) {
            setIdMaster(new LongFilter());
        }
        return idMaster;
    }

    public void setIdMaster(LongFilter idMaster) {
        this.idMaster = idMaster;
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

    public LongFilter getSoHsCv() {
        return soHsCv;
    }

    public Optional<LongFilter> optionalSoHsCv() {
        return Optional.ofNullable(soHsCv);
    }

    public LongFilter soHsCv() {
        if (soHsCv == null) {
            setSoHsCv(new LongFilter());
        }
        return soHsCv;
    }

    public void setSoHsCv(LongFilter soHsCv) {
        this.soHsCv = soHsCv;
    }

    public LongFilter getSoCc() {
        return soCc;
    }

    public Optional<LongFilter> optionalSoCc() {
        return Optional.ofNullable(soCc);
    }

    public LongFilter soCc() {
        if (soCc == null) {
            setSoCc(new LongFilter());
        }
        return soCc;
    }

    public void setSoCc(LongFilter soCc) {
        this.soCc = soCc;
    }

    public LongFilter getSoVaoSo() {
        return soVaoSo;
    }

    public Optional<LongFilter> optionalSoVaoSo() {
        return Optional.ofNullable(soVaoSo);
    }

    public LongFilter soVaoSo() {
        if (soVaoSo == null) {
            setSoVaoSo(new LongFilter());
        }
        return soVaoSo;
    }

    public void setSoVaoSo(LongFilter soVaoSo) {
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

    public LongFilter getSyncStatus() {
        return syncStatus;
    }

    public Optional<LongFilter> optionalSyncStatus() {
        return Optional.ofNullable(syncStatus);
    }

    public LongFilter syncStatus() {
        if (syncStatus == null) {
            setSyncStatus(new LongFilter());
        }
        return syncStatus;
    }

    public void setSyncStatus(LongFilter syncStatus) {
        this.syncStatus = syncStatus;
    }

    public LongFilter getThuaTachId() {
        return thuaTachId;
    }

    public Optional<LongFilter> optionalThuaTachId() {
        return Optional.ofNullable(thuaTachId);
    }

    public LongFilter thuaTachId() {
        if (thuaTachId == null) {
            setThuaTachId(new LongFilter());
        }
        return thuaTachId;
    }

    public void setThuaTachId(LongFilter thuaTachId) {
        this.thuaTachId = thuaTachId;
    }

    public LongFilter getTaiSanDuongSuId() {
        return taiSanDuongSuId;
    }

    public Optional<LongFilter> optionalTaiSanDuongSuId() {
        return Optional.ofNullable(taiSanDuongSuId);
    }

    public LongFilter taiSanDuongSuId() {
        if (taiSanDuongSuId == null) {
            setTaiSanDuongSuId(new LongFilter());
        }
        return taiSanDuongSuId;
    }

    public void setTaiSanDuongSuId(LongFilter taiSanDuongSuId) {
        this.taiSanDuongSuId = taiSanDuongSuId;
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

    public LongFilter getDanhMucLoaiTaiSanId() {
        return danhMucLoaiTaiSanId;
    }

    public Optional<LongFilter> optionalDanhMucLoaiTaiSanId() {
        return Optional.ofNullable(danhMucLoaiTaiSanId);
    }

    public LongFilter danhMucLoaiTaiSanId() {
        if (danhMucLoaiTaiSanId == null) {
            setDanhMucLoaiTaiSanId(new LongFilter());
        }
        return danhMucLoaiTaiSanId;
    }

    public void setDanhMucLoaiTaiSanId(LongFilter danhMucLoaiTaiSanId) {
        this.danhMucLoaiTaiSanId = danhMucLoaiTaiSanId;
    }

    public LongFilter getTinhTrangTaiSanId() {
        return tinhTrangTaiSanId;
    }

    public Optional<LongFilter> optionalTinhTrangTaiSanId() {
        return Optional.ofNullable(tinhTrangTaiSanId);
    }

    public LongFilter tinhTrangTaiSanId() {
        if (tinhTrangTaiSanId == null) {
            setTinhTrangTaiSanId(new LongFilter());
        }
        return tinhTrangTaiSanId;
    }

    public void setTinhTrangTaiSanId(LongFilter tinhTrangTaiSanId) {
        this.tinhTrangTaiSanId = tinhTrangTaiSanId;
    }

    public LongFilter getChiTietNganChanTaiSanId() {
        return chiTietNganChanTaiSanId;
    }

    public Optional<LongFilter> optionalChiTietNganChanTaiSanId() {
        return Optional.ofNullable(chiTietNganChanTaiSanId);
    }

    public LongFilter chiTietNganChanTaiSanId() {
        if (chiTietNganChanTaiSanId == null) {
            setChiTietNganChanTaiSanId(new LongFilter());
        }
        return chiTietNganChanTaiSanId;
    }

    public void setChiTietNganChanTaiSanId(LongFilter chiTietNganChanTaiSanId) {
        this.chiTietNganChanTaiSanId = chiTietNganChanTaiSanId;
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
        final TaiSanCriteria that = (TaiSanCriteria) o;
        return (
            Objects.equals(idTaiSan, that.idTaiSan) &&
            Objects.equals(tenTaiSan, that.tenTaiSan) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(ghiChu, that.ghiChu) &&
            Objects.equals(ngayThaoTac, that.ngayThaoTac) &&
            Objects.equals(nguoiThaoTac, that.nguoiThaoTac) &&
            Objects.equals(idDuongSu, that.idDuongSu) &&
            Objects.equals(idTsGoc, that.idTsGoc) &&
            Objects.equals(maTaiSan, that.maTaiSan) &&
            Objects.equals(idLoaiNganChan, that.idLoaiNganChan) &&
            Objects.equals(ngayBdNganChan, that.ngayBdNganChan) &&
            Objects.equals(ngayKtNganChan, that.ngayKtNganChan) &&
            Objects.equals(idMaster, that.idMaster) &&
            Objects.equals(strSearch, that.strSearch) &&
            Objects.equals(idDonVi, that.idDonVi) &&
            Objects.equals(soHsCv, that.soHsCv) &&
            Objects.equals(soCc, that.soCc) &&
            Objects.equals(soVaoSo, that.soVaoSo) &&
            Objects.equals(moTa, that.moTa) &&
            Objects.equals(loaiNganChan, that.loaiNganChan) &&
            Objects.equals(syncStatus, that.syncStatus) &&
            Objects.equals(thuaTachId, that.thuaTachId) &&
            Objects.equals(taiSanDuongSuId, that.taiSanDuongSuId) &&
            Objects.equals(taiSanDgcId, that.taiSanDgcId) &&
            Objects.equals(thongTinCapNhatTaiSanId, that.thongTinCapNhatTaiSanId) &&
            Objects.equals(danhMucLoaiTaiSanId, that.danhMucLoaiTaiSanId) &&
            Objects.equals(tinhTrangTaiSanId, that.tinhTrangTaiSanId) &&
            Objects.equals(chiTietNganChanTaiSanId, that.chiTietNganChanTaiSanId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            idTaiSan,
            tenTaiSan,
            trangThai,
            ghiChu,
            ngayThaoTac,
            nguoiThaoTac,
            idDuongSu,
            idTsGoc,
            maTaiSan,
            idLoaiNganChan,
            ngayBdNganChan,
            ngayKtNganChan,
            idMaster,
            strSearch,
            idDonVi,
            soHsCv,
            soCc,
            soVaoSo,
            moTa,
            loaiNganChan,
            syncStatus,
            thuaTachId,
            taiSanDuongSuId,
            taiSanDgcId,
            thongTinCapNhatTaiSanId,
            danhMucLoaiTaiSanId,
            tinhTrangTaiSanId,
            chiTietNganChanTaiSanId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaiSanCriteria{" +
            optionalIdTaiSan().map(f -> "idTaiSan=" + f + ", ").orElse("") +
            optionalTenTaiSan().map(f -> "tenTaiSan=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalGhiChu().map(f -> "ghiChu=" + f + ", ").orElse("") +
            optionalNgayThaoTac().map(f -> "ngayThaoTac=" + f + ", ").orElse("") +
            optionalNguoiThaoTac().map(f -> "nguoiThaoTac=" + f + ", ").orElse("") +
            optionalIdDuongSu().map(f -> "idDuongSu=" + f + ", ").orElse("") +
            optionalIdTsGoc().map(f -> "idTsGoc=" + f + ", ").orElse("") +
            optionalMaTaiSan().map(f -> "maTaiSan=" + f + ", ").orElse("") +
            optionalIdLoaiNganChan().map(f -> "idLoaiNganChan=" + f + ", ").orElse("") +
            optionalNgayBdNganChan().map(f -> "ngayBdNganChan=" + f + ", ").orElse("") +
            optionalNgayKtNganChan().map(f -> "ngayKtNganChan=" + f + ", ").orElse("") +
            optionalIdMaster().map(f -> "idMaster=" + f + ", ").orElse("") +
            optionalStrSearch().map(f -> "strSearch=" + f + ", ").orElse("") +
            optionalIdDonVi().map(f -> "idDonVi=" + f + ", ").orElse("") +
            optionalSoHsCv().map(f -> "soHsCv=" + f + ", ").orElse("") +
            optionalSoCc().map(f -> "soCc=" + f + ", ").orElse("") +
            optionalSoVaoSo().map(f -> "soVaoSo=" + f + ", ").orElse("") +
            optionalMoTa().map(f -> "moTa=" + f + ", ").orElse("") +
            optionalLoaiNganChan().map(f -> "loaiNganChan=" + f + ", ").orElse("") +
            optionalSyncStatus().map(f -> "syncStatus=" + f + ", ").orElse("") +
            optionalThuaTachId().map(f -> "thuaTachId=" + f + ", ").orElse("") +
            optionalTaiSanDuongSuId().map(f -> "taiSanDuongSuId=" + f + ", ").orElse("") +
            optionalTaiSanDgcId().map(f -> "taiSanDgcId=" + f + ", ").orElse("") +
            optionalThongTinCapNhatTaiSanId().map(f -> "thongTinCapNhatTaiSanId=" + f + ", ").orElse("") +
            optionalDanhMucLoaiTaiSanId().map(f -> "danhMucLoaiTaiSanId=" + f + ", ").orElse("") +
            optionalTinhTrangTaiSanId().map(f -> "tinhTrangTaiSanId=" + f + ", ").orElse("") +
            optionalChiTietNganChanTaiSanId().map(f -> "chiTietNganChanTaiSanId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
