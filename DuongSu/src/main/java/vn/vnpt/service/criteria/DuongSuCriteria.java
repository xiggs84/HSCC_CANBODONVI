package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DuongSu} entity. This class is used
 * in {@link vn.vnpt.web.rest.DuongSuResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /duong-sus?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DuongSuCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idDuongSu;

    private StringFilter tenDuongSu;

    private StringFilter diaChi;

    private StringFilter soDienThoai;

    private StringFilter email;

    private StringFilter fax;

    private StringFilter website;

    private IntegerFilter trangThai;

    private LocalDateFilter ngayThaoTac;

    private LongFilter nguoiThaoTac;

    private LongFilter idDsGoc;

    private StringFilter idMaster;

    private LongFilter idDonVi;

    private StringFilter strSearch;

    private StringFilter soGiayTo;

    private StringFilter ghiChu;

    private LongFilter idLoaiNganChan;

    private IntegerFilter syncStatus;

    private LongFilter thongTinCapNhatId;

    private LongFilter taiSanDuongSuId;

    private LongFilter quanHeDuongSuId;

    private LongFilter danhSachDuongSuId;

    private LongFilter duongSuTrungCmndId;

    private LongFilter duongSuTrungCmndBakId;

    private StringFilter loaiDuongSuId;

    private StringFilter loaiGiayToId;

    private Boolean distinct;

    public DuongSuCriteria() {}

    public DuongSuCriteria(DuongSuCriteria other) {
        this.idDuongSu = other.optionalIdDuongSu().map(LongFilter::copy).orElse(null);
        this.tenDuongSu = other.optionalTenDuongSu().map(StringFilter::copy).orElse(null);
        this.diaChi = other.optionalDiaChi().map(StringFilter::copy).orElse(null);
        this.soDienThoai = other.optionalSoDienThoai().map(StringFilter::copy).orElse(null);
        this.email = other.optionalEmail().map(StringFilter::copy).orElse(null);
        this.fax = other.optionalFax().map(StringFilter::copy).orElse(null);
        this.website = other.optionalWebsite().map(StringFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(IntegerFilter::copy).orElse(null);
        this.ngayThaoTac = other.optionalNgayThaoTac().map(LocalDateFilter::copy).orElse(null);
        this.nguoiThaoTac = other.optionalNguoiThaoTac().map(LongFilter::copy).orElse(null);
        this.idDsGoc = other.optionalIdDsGoc().map(LongFilter::copy).orElse(null);
        this.idMaster = other.optionalIdMaster().map(StringFilter::copy).orElse(null);
        this.idDonVi = other.optionalIdDonVi().map(LongFilter::copy).orElse(null);
        this.strSearch = other.optionalStrSearch().map(StringFilter::copy).orElse(null);
        this.soGiayTo = other.optionalSoGiayTo().map(StringFilter::copy).orElse(null);
        this.ghiChu = other.optionalGhiChu().map(StringFilter::copy).orElse(null);
        this.idLoaiNganChan = other.optionalIdLoaiNganChan().map(LongFilter::copy).orElse(null);
        this.syncStatus = other.optionalSyncStatus().map(IntegerFilter::copy).orElse(null);
        this.thongTinCapNhatId = other.optionalThongTinCapNhatId().map(LongFilter::copy).orElse(null);
        this.taiSanDuongSuId = other.optionalTaiSanDuongSuId().map(LongFilter::copy).orElse(null);
        this.quanHeDuongSuId = other.optionalQuanHeDuongSuId().map(LongFilter::copy).orElse(null);
        this.danhSachDuongSuId = other.optionalDanhSachDuongSuId().map(LongFilter::copy).orElse(null);
        this.duongSuTrungCmndId = other.optionalDuongSuTrungCmndId().map(LongFilter::copy).orElse(null);
        this.duongSuTrungCmndBakId = other.optionalDuongSuTrungCmndBakId().map(LongFilter::copy).orElse(null);
        this.loaiDuongSuId = other.optionalLoaiDuongSuId().map(StringFilter::copy).orElse(null);
        this.loaiGiayToId = other.optionalLoaiGiayToId().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DuongSuCriteria copy() {
        return new DuongSuCriteria(this);
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

    public StringFilter getSoDienThoai() {
        return soDienThoai;
    }

    public Optional<StringFilter> optionalSoDienThoai() {
        return Optional.ofNullable(soDienThoai);
    }

    public StringFilter soDienThoai() {
        if (soDienThoai == null) {
            setSoDienThoai(new StringFilter());
        }
        return soDienThoai;
    }

    public void setSoDienThoai(StringFilter soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public StringFilter getEmail() {
        return email;
    }

    public Optional<StringFilter> optionalEmail() {
        return Optional.ofNullable(email);
    }

    public StringFilter email() {
        if (email == null) {
            setEmail(new StringFilter());
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getFax() {
        return fax;
    }

    public Optional<StringFilter> optionalFax() {
        return Optional.ofNullable(fax);
    }

    public StringFilter fax() {
        if (fax == null) {
            setFax(new StringFilter());
        }
        return fax;
    }

    public void setFax(StringFilter fax) {
        this.fax = fax;
    }

    public StringFilter getWebsite() {
        return website;
    }

    public Optional<StringFilter> optionalWebsite() {
        return Optional.ofNullable(website);
    }

    public StringFilter website() {
        if (website == null) {
            setWebsite(new StringFilter());
        }
        return website;
    }

    public void setWebsite(StringFilter website) {
        this.website = website;
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

    public IntegerFilter getSyncStatus() {
        return syncStatus;
    }

    public Optional<IntegerFilter> optionalSyncStatus() {
        return Optional.ofNullable(syncStatus);
    }

    public IntegerFilter syncStatus() {
        if (syncStatus == null) {
            setSyncStatus(new IntegerFilter());
        }
        return syncStatus;
    }

    public void setSyncStatus(IntegerFilter syncStatus) {
        this.syncStatus = syncStatus;
    }

    public LongFilter getThongTinCapNhatId() {
        return thongTinCapNhatId;
    }

    public Optional<LongFilter> optionalThongTinCapNhatId() {
        return Optional.ofNullable(thongTinCapNhatId);
    }

    public LongFilter thongTinCapNhatId() {
        if (thongTinCapNhatId == null) {
            setThongTinCapNhatId(new LongFilter());
        }
        return thongTinCapNhatId;
    }

    public void setThongTinCapNhatId(LongFilter thongTinCapNhatId) {
        this.thongTinCapNhatId = thongTinCapNhatId;
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

    public LongFilter getQuanHeDuongSuId() {
        return quanHeDuongSuId;
    }

    public Optional<LongFilter> optionalQuanHeDuongSuId() {
        return Optional.ofNullable(quanHeDuongSuId);
    }

    public LongFilter quanHeDuongSuId() {
        if (quanHeDuongSuId == null) {
            setQuanHeDuongSuId(new LongFilter());
        }
        return quanHeDuongSuId;
    }

    public void setQuanHeDuongSuId(LongFilter quanHeDuongSuId) {
        this.quanHeDuongSuId = quanHeDuongSuId;
    }

    public LongFilter getDanhSachDuongSuId() {
        return danhSachDuongSuId;
    }

    public Optional<LongFilter> optionalDanhSachDuongSuId() {
        return Optional.ofNullable(danhSachDuongSuId);
    }

    public LongFilter danhSachDuongSuId() {
        if (danhSachDuongSuId == null) {
            setDanhSachDuongSuId(new LongFilter());
        }
        return danhSachDuongSuId;
    }

    public void setDanhSachDuongSuId(LongFilter danhSachDuongSuId) {
        this.danhSachDuongSuId = danhSachDuongSuId;
    }

    public LongFilter getDuongSuTrungCmndId() {
        return duongSuTrungCmndId;
    }

    public Optional<LongFilter> optionalDuongSuTrungCmndId() {
        return Optional.ofNullable(duongSuTrungCmndId);
    }

    public LongFilter duongSuTrungCmndId() {
        if (duongSuTrungCmndId == null) {
            setDuongSuTrungCmndId(new LongFilter());
        }
        return duongSuTrungCmndId;
    }

    public void setDuongSuTrungCmndId(LongFilter duongSuTrungCmndId) {
        this.duongSuTrungCmndId = duongSuTrungCmndId;
    }

    public LongFilter getDuongSuTrungCmndBakId() {
        return duongSuTrungCmndBakId;
    }

    public Optional<LongFilter> optionalDuongSuTrungCmndBakId() {
        return Optional.ofNullable(duongSuTrungCmndBakId);
    }

    public LongFilter duongSuTrungCmndBakId() {
        if (duongSuTrungCmndBakId == null) {
            setDuongSuTrungCmndBakId(new LongFilter());
        }
        return duongSuTrungCmndBakId;
    }

    public void setDuongSuTrungCmndBakId(LongFilter duongSuTrungCmndBakId) {
        this.duongSuTrungCmndBakId = duongSuTrungCmndBakId;
    }

    public StringFilter getLoaiDuongSuId() {
        return loaiDuongSuId;
    }

    public Optional<StringFilter> optionalLoaiDuongSuId() {
        return Optional.ofNullable(loaiDuongSuId);
    }

    public StringFilter loaiDuongSuId() {
        if (loaiDuongSuId == null) {
            setLoaiDuongSuId(new StringFilter());
        }
        return loaiDuongSuId;
    }

    public void setLoaiDuongSuId(StringFilter loaiDuongSuId) {
        this.loaiDuongSuId = loaiDuongSuId;
    }

    public StringFilter getLoaiGiayToId() {
        return loaiGiayToId;
    }

    public Optional<StringFilter> optionalLoaiGiayToId() {
        return Optional.ofNullable(loaiGiayToId);
    }

    public StringFilter loaiGiayToId() {
        if (loaiGiayToId == null) {
            setLoaiGiayToId(new StringFilter());
        }
        return loaiGiayToId;
    }

    public void setLoaiGiayToId(StringFilter loaiGiayToId) {
        this.loaiGiayToId = loaiGiayToId;
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
        final DuongSuCriteria that = (DuongSuCriteria) o;
        return (
            Objects.equals(idDuongSu, that.idDuongSu) &&
            Objects.equals(tenDuongSu, that.tenDuongSu) &&
            Objects.equals(diaChi, that.diaChi) &&
            Objects.equals(soDienThoai, that.soDienThoai) &&
            Objects.equals(email, that.email) &&
            Objects.equals(fax, that.fax) &&
            Objects.equals(website, that.website) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(ngayThaoTac, that.ngayThaoTac) &&
            Objects.equals(nguoiThaoTac, that.nguoiThaoTac) &&
            Objects.equals(idDsGoc, that.idDsGoc) &&
            Objects.equals(idMaster, that.idMaster) &&
            Objects.equals(idDonVi, that.idDonVi) &&
            Objects.equals(strSearch, that.strSearch) &&
            Objects.equals(soGiayTo, that.soGiayTo) &&
            Objects.equals(ghiChu, that.ghiChu) &&
            Objects.equals(idLoaiNganChan, that.idLoaiNganChan) &&
            Objects.equals(syncStatus, that.syncStatus) &&
            Objects.equals(thongTinCapNhatId, that.thongTinCapNhatId) &&
            Objects.equals(taiSanDuongSuId, that.taiSanDuongSuId) &&
            Objects.equals(quanHeDuongSuId, that.quanHeDuongSuId) &&
            Objects.equals(danhSachDuongSuId, that.danhSachDuongSuId) &&
            Objects.equals(duongSuTrungCmndId, that.duongSuTrungCmndId) &&
            Objects.equals(duongSuTrungCmndBakId, that.duongSuTrungCmndBakId) &&
            Objects.equals(loaiDuongSuId, that.loaiDuongSuId) &&
            Objects.equals(loaiGiayToId, that.loaiGiayToId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            idDuongSu,
            tenDuongSu,
            diaChi,
            soDienThoai,
            email,
            fax,
            website,
            trangThai,
            ngayThaoTac,
            nguoiThaoTac,
            idDsGoc,
            idMaster,
            idDonVi,
            strSearch,
            soGiayTo,
            ghiChu,
            idLoaiNganChan,
            syncStatus,
            thongTinCapNhatId,
            taiSanDuongSuId,
            quanHeDuongSuId,
            danhSachDuongSuId,
            duongSuTrungCmndId,
            duongSuTrungCmndBakId,
            loaiDuongSuId,
            loaiGiayToId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DuongSuCriteria{" +
            optionalIdDuongSu().map(f -> "idDuongSu=" + f + ", ").orElse("") +
            optionalTenDuongSu().map(f -> "tenDuongSu=" + f + ", ").orElse("") +
            optionalDiaChi().map(f -> "diaChi=" + f + ", ").orElse("") +
            optionalSoDienThoai().map(f -> "soDienThoai=" + f + ", ").orElse("") +
            optionalEmail().map(f -> "email=" + f + ", ").orElse("") +
            optionalFax().map(f -> "fax=" + f + ", ").orElse("") +
            optionalWebsite().map(f -> "website=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalNgayThaoTac().map(f -> "ngayThaoTac=" + f + ", ").orElse("") +
            optionalNguoiThaoTac().map(f -> "nguoiThaoTac=" + f + ", ").orElse("") +
            optionalIdDsGoc().map(f -> "idDsGoc=" + f + ", ").orElse("") +
            optionalIdMaster().map(f -> "idMaster=" + f + ", ").orElse("") +
            optionalIdDonVi().map(f -> "idDonVi=" + f + ", ").orElse("") +
            optionalStrSearch().map(f -> "strSearch=" + f + ", ").orElse("") +
            optionalSoGiayTo().map(f -> "soGiayTo=" + f + ", ").orElse("") +
            optionalGhiChu().map(f -> "ghiChu=" + f + ", ").orElse("") +
            optionalIdLoaiNganChan().map(f -> "idLoaiNganChan=" + f + ", ").orElse("") +
            optionalSyncStatus().map(f -> "syncStatus=" + f + ", ").orElse("") +
            optionalThongTinCapNhatId().map(f -> "thongTinCapNhatId=" + f + ", ").orElse("") +
            optionalTaiSanDuongSuId().map(f -> "taiSanDuongSuId=" + f + ", ").orElse("") +
            optionalQuanHeDuongSuId().map(f -> "quanHeDuongSuId=" + f + ", ").orElse("") +
            optionalDanhSachDuongSuId().map(f -> "danhSachDuongSuId=" + f + ", ").orElse("") +
            optionalDuongSuTrungCmndId().map(f -> "duongSuTrungCmndId=" + f + ", ").orElse("") +
            optionalDuongSuTrungCmndBakId().map(f -> "duongSuTrungCmndBakId=" + f + ", ").orElse("") +
            optionalLoaiDuongSuId().map(f -> "loaiDuongSuId=" + f + ", ").orElse("") +
            optionalLoaiGiayToId().map(f -> "loaiGiayToId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
