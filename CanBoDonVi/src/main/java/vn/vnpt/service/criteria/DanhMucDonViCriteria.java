package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.DanhMucDonVi} entity. This class is used
 * in {@link vn.vnpt.web.rest.DanhMucDonViResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /danh-muc-don-vis?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucDonViCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter idDonVi;

    private StringFilter tenDonVi;

    private StringFilter diaChi;

    private StringFilter nguoiDaiDien;

    private StringFilter soDienThoai;

    private LongFilter idDonViQl;

    private LocalDateFilter ngayKhaiBao;

    private LongFilter trangThai;

    private StringFilter soNha;

    private StringFilter maSoThue;

    private IntegerFilter hoaDonDt;

    private StringFilter maDonViIgate;

    private StringFilter maCoQuanIgate;

    private LongFilter kySo;

    private LongFilter qrScan;

    private LongFilter verifyIdCard;

    private LongFilter isVerifyFace;

    private LongFilter isElastic;

    private StringFilter apikeyCccd;

    private StringFilter apikeyFace;

    private StringFilter verifyCodeCccd;

    private StringFilter usernameElastic;

    private StringFilter passwordElastic;

    private StringFilter idNhiemVu;

    private StringFilter idLoaiDv;

    private StringFilter idCapQl;

    private LongFilter canBoChungThucId;

    private LongFilter canBoQuyenId;

    private StringFilter capQuanLyId;

    private StringFilter loaiDonViId;

    private StringFilter nhiemVuId;

    private LongFilter lanhDaoId;

    private Boolean distinct;

    public DanhMucDonViCriteria() {}

    public DanhMucDonViCriteria(DanhMucDonViCriteria other) {
        this.idDonVi = other.optionalIdDonVi().map(LongFilter::copy).orElse(null);
        this.tenDonVi = other.optionalTenDonVi().map(StringFilter::copy).orElse(null);
        this.diaChi = other.optionalDiaChi().map(StringFilter::copy).orElse(null);
        this.nguoiDaiDien = other.optionalNguoiDaiDien().map(StringFilter::copy).orElse(null);
        this.soDienThoai = other.optionalSoDienThoai().map(StringFilter::copy).orElse(null);
        this.idDonViQl = other.optionalIdDonViQl().map(LongFilter::copy).orElse(null);
        this.ngayKhaiBao = other.optionalNgayKhaiBao().map(LocalDateFilter::copy).orElse(null);
        this.trangThai = other.optionalTrangThai().map(LongFilter::copy).orElse(null);
        this.soNha = other.optionalSoNha().map(StringFilter::copy).orElse(null);
        this.maSoThue = other.optionalMaSoThue().map(StringFilter::copy).orElse(null);
        this.hoaDonDt = other.optionalHoaDonDt().map(IntegerFilter::copy).orElse(null);
        this.maDonViIgate = other.optionalMaDonViIgate().map(StringFilter::copy).orElse(null);
        this.maCoQuanIgate = other.optionalMaCoQuanIgate().map(StringFilter::copy).orElse(null);
        this.kySo = other.optionalKySo().map(LongFilter::copy).orElse(null);
        this.qrScan = other.optionalQrScan().map(LongFilter::copy).orElse(null);
        this.verifyIdCard = other.optionalVerifyIdCard().map(LongFilter::copy).orElse(null);
        this.isVerifyFace = other.optionalIsVerifyFace().map(LongFilter::copy).orElse(null);
        this.isElastic = other.optionalIsElastic().map(LongFilter::copy).orElse(null);
        this.apikeyCccd = other.optionalApikeyCccd().map(StringFilter::copy).orElse(null);
        this.apikeyFace = other.optionalApikeyFace().map(StringFilter::copy).orElse(null);
        this.verifyCodeCccd = other.optionalVerifyCodeCccd().map(StringFilter::copy).orElse(null);
        this.usernameElastic = other.optionalUsernameElastic().map(StringFilter::copy).orElse(null);
        this.passwordElastic = other.optionalPasswordElastic().map(StringFilter::copy).orElse(null);
        this.idNhiemVu = other.optionalIdNhiemVu().map(StringFilter::copy).orElse(null);
        this.idLoaiDv = other.optionalIdLoaiDv().map(StringFilter::copy).orElse(null);
        this.idCapQl = other.optionalIdCapQl().map(StringFilter::copy).orElse(null);
        this.canBoChungThucId = other.optionalCanBoChungThucId().map(LongFilter::copy).orElse(null);
        this.canBoQuyenId = other.optionalCanBoQuyenId().map(LongFilter::copy).orElse(null);
        this.capQuanLyId = other.optionalCapQuanLyId().map(StringFilter::copy).orElse(null);
        this.loaiDonViId = other.optionalLoaiDonViId().map(StringFilter::copy).orElse(null);
        this.nhiemVuId = other.optionalNhiemVuId().map(StringFilter::copy).orElse(null);
        this.lanhDaoId = other.optionalLanhDaoId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public DanhMucDonViCriteria copy() {
        return new DanhMucDonViCriteria(this);
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

    public StringFilter getTenDonVi() {
        return tenDonVi;
    }

    public Optional<StringFilter> optionalTenDonVi() {
        return Optional.ofNullable(tenDonVi);
    }

    public StringFilter tenDonVi() {
        if (tenDonVi == null) {
            setTenDonVi(new StringFilter());
        }
        return tenDonVi;
    }

    public void setTenDonVi(StringFilter tenDonVi) {
        this.tenDonVi = tenDonVi;
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

    public StringFilter getNguoiDaiDien() {
        return nguoiDaiDien;
    }

    public Optional<StringFilter> optionalNguoiDaiDien() {
        return Optional.ofNullable(nguoiDaiDien);
    }

    public StringFilter nguoiDaiDien() {
        if (nguoiDaiDien == null) {
            setNguoiDaiDien(new StringFilter());
        }
        return nguoiDaiDien;
    }

    public void setNguoiDaiDien(StringFilter nguoiDaiDien) {
        this.nguoiDaiDien = nguoiDaiDien;
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

    public LongFilter getIdDonViQl() {
        return idDonViQl;
    }

    public Optional<LongFilter> optionalIdDonViQl() {
        return Optional.ofNullable(idDonViQl);
    }

    public LongFilter idDonViQl() {
        if (idDonViQl == null) {
            setIdDonViQl(new LongFilter());
        }
        return idDonViQl;
    }

    public void setIdDonViQl(LongFilter idDonViQl) {
        this.idDonViQl = idDonViQl;
    }

    public LocalDateFilter getNgayKhaiBao() {
        return ngayKhaiBao;
    }

    public Optional<LocalDateFilter> optionalNgayKhaiBao() {
        return Optional.ofNullable(ngayKhaiBao);
    }

    public LocalDateFilter ngayKhaiBao() {
        if (ngayKhaiBao == null) {
            setNgayKhaiBao(new LocalDateFilter());
        }
        return ngayKhaiBao;
    }

    public void setNgayKhaiBao(LocalDateFilter ngayKhaiBao) {
        this.ngayKhaiBao = ngayKhaiBao;
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

    public StringFilter getSoNha() {
        return soNha;
    }

    public Optional<StringFilter> optionalSoNha() {
        return Optional.ofNullable(soNha);
    }

    public StringFilter soNha() {
        if (soNha == null) {
            setSoNha(new StringFilter());
        }
        return soNha;
    }

    public void setSoNha(StringFilter soNha) {
        this.soNha = soNha;
    }

    public StringFilter getMaSoThue() {
        return maSoThue;
    }

    public Optional<StringFilter> optionalMaSoThue() {
        return Optional.ofNullable(maSoThue);
    }

    public StringFilter maSoThue() {
        if (maSoThue == null) {
            setMaSoThue(new StringFilter());
        }
        return maSoThue;
    }

    public void setMaSoThue(StringFilter maSoThue) {
        this.maSoThue = maSoThue;
    }

    public IntegerFilter getHoaDonDt() {
        return hoaDonDt;
    }

    public Optional<IntegerFilter> optionalHoaDonDt() {
        return Optional.ofNullable(hoaDonDt);
    }

    public IntegerFilter hoaDonDt() {
        if (hoaDonDt == null) {
            setHoaDonDt(new IntegerFilter());
        }
        return hoaDonDt;
    }

    public void setHoaDonDt(IntegerFilter hoaDonDt) {
        this.hoaDonDt = hoaDonDt;
    }

    public StringFilter getMaDonViIgate() {
        return maDonViIgate;
    }

    public Optional<StringFilter> optionalMaDonViIgate() {
        return Optional.ofNullable(maDonViIgate);
    }

    public StringFilter maDonViIgate() {
        if (maDonViIgate == null) {
            setMaDonViIgate(new StringFilter());
        }
        return maDonViIgate;
    }

    public void setMaDonViIgate(StringFilter maDonViIgate) {
        this.maDonViIgate = maDonViIgate;
    }

    public StringFilter getMaCoQuanIgate() {
        return maCoQuanIgate;
    }

    public Optional<StringFilter> optionalMaCoQuanIgate() {
        return Optional.ofNullable(maCoQuanIgate);
    }

    public StringFilter maCoQuanIgate() {
        if (maCoQuanIgate == null) {
            setMaCoQuanIgate(new StringFilter());
        }
        return maCoQuanIgate;
    }

    public void setMaCoQuanIgate(StringFilter maCoQuanIgate) {
        this.maCoQuanIgate = maCoQuanIgate;
    }

    public LongFilter getKySo() {
        return kySo;
    }

    public Optional<LongFilter> optionalKySo() {
        return Optional.ofNullable(kySo);
    }

    public LongFilter kySo() {
        if (kySo == null) {
            setKySo(new LongFilter());
        }
        return kySo;
    }

    public void setKySo(LongFilter kySo) {
        this.kySo = kySo;
    }

    public LongFilter getQrScan() {
        return qrScan;
    }

    public Optional<LongFilter> optionalQrScan() {
        return Optional.ofNullable(qrScan);
    }

    public LongFilter qrScan() {
        if (qrScan == null) {
            setQrScan(new LongFilter());
        }
        return qrScan;
    }

    public void setQrScan(LongFilter qrScan) {
        this.qrScan = qrScan;
    }

    public LongFilter getVerifyIdCard() {
        return verifyIdCard;
    }

    public Optional<LongFilter> optionalVerifyIdCard() {
        return Optional.ofNullable(verifyIdCard);
    }

    public LongFilter verifyIdCard() {
        if (verifyIdCard == null) {
            setVerifyIdCard(new LongFilter());
        }
        return verifyIdCard;
    }

    public void setVerifyIdCard(LongFilter verifyIdCard) {
        this.verifyIdCard = verifyIdCard;
    }

    public LongFilter getIsVerifyFace() {
        return isVerifyFace;
    }

    public Optional<LongFilter> optionalIsVerifyFace() {
        return Optional.ofNullable(isVerifyFace);
    }

    public LongFilter isVerifyFace() {
        if (isVerifyFace == null) {
            setIsVerifyFace(new LongFilter());
        }
        return isVerifyFace;
    }

    public void setIsVerifyFace(LongFilter isVerifyFace) {
        this.isVerifyFace = isVerifyFace;
    }

    public LongFilter getIsElastic() {
        return isElastic;
    }

    public Optional<LongFilter> optionalIsElastic() {
        return Optional.ofNullable(isElastic);
    }

    public LongFilter isElastic() {
        if (isElastic == null) {
            setIsElastic(new LongFilter());
        }
        return isElastic;
    }

    public void setIsElastic(LongFilter isElastic) {
        this.isElastic = isElastic;
    }

    public StringFilter getApikeyCccd() {
        return apikeyCccd;
    }

    public Optional<StringFilter> optionalApikeyCccd() {
        return Optional.ofNullable(apikeyCccd);
    }

    public StringFilter apikeyCccd() {
        if (apikeyCccd == null) {
            setApikeyCccd(new StringFilter());
        }
        return apikeyCccd;
    }

    public void setApikeyCccd(StringFilter apikeyCccd) {
        this.apikeyCccd = apikeyCccd;
    }

    public StringFilter getApikeyFace() {
        return apikeyFace;
    }

    public Optional<StringFilter> optionalApikeyFace() {
        return Optional.ofNullable(apikeyFace);
    }

    public StringFilter apikeyFace() {
        if (apikeyFace == null) {
            setApikeyFace(new StringFilter());
        }
        return apikeyFace;
    }

    public void setApikeyFace(StringFilter apikeyFace) {
        this.apikeyFace = apikeyFace;
    }

    public StringFilter getVerifyCodeCccd() {
        return verifyCodeCccd;
    }

    public Optional<StringFilter> optionalVerifyCodeCccd() {
        return Optional.ofNullable(verifyCodeCccd);
    }

    public StringFilter verifyCodeCccd() {
        if (verifyCodeCccd == null) {
            setVerifyCodeCccd(new StringFilter());
        }
        return verifyCodeCccd;
    }

    public void setVerifyCodeCccd(StringFilter verifyCodeCccd) {
        this.verifyCodeCccd = verifyCodeCccd;
    }

    public StringFilter getUsernameElastic() {
        return usernameElastic;
    }

    public Optional<StringFilter> optionalUsernameElastic() {
        return Optional.ofNullable(usernameElastic);
    }

    public StringFilter usernameElastic() {
        if (usernameElastic == null) {
            setUsernameElastic(new StringFilter());
        }
        return usernameElastic;
    }

    public void setUsernameElastic(StringFilter usernameElastic) {
        this.usernameElastic = usernameElastic;
    }

    public StringFilter getPasswordElastic() {
        return passwordElastic;
    }

    public Optional<StringFilter> optionalPasswordElastic() {
        return Optional.ofNullable(passwordElastic);
    }

    public StringFilter passwordElastic() {
        if (passwordElastic == null) {
            setPasswordElastic(new StringFilter());
        }
        return passwordElastic;
    }

    public void setPasswordElastic(StringFilter passwordElastic) {
        this.passwordElastic = passwordElastic;
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

    public StringFilter getIdLoaiDv() {
        return idLoaiDv;
    }

    public Optional<StringFilter> optionalIdLoaiDv() {
        return Optional.ofNullable(idLoaiDv);
    }

    public StringFilter idLoaiDv() {
        if (idLoaiDv == null) {
            setIdLoaiDv(new StringFilter());
        }
        return idLoaiDv;
    }

    public void setIdLoaiDv(StringFilter idLoaiDv) {
        this.idLoaiDv = idLoaiDv;
    }

    public StringFilter getIdCapQl() {
        return idCapQl;
    }

    public Optional<StringFilter> optionalIdCapQl() {
        return Optional.ofNullable(idCapQl);
    }

    public StringFilter idCapQl() {
        if (idCapQl == null) {
            setIdCapQl(new StringFilter());
        }
        return idCapQl;
    }

    public void setIdCapQl(StringFilter idCapQl) {
        this.idCapQl = idCapQl;
    }

    public LongFilter getCanBoChungThucId() {
        return canBoChungThucId;
    }

    public Optional<LongFilter> optionalCanBoChungThucId() {
        return Optional.ofNullable(canBoChungThucId);
    }

    public LongFilter canBoChungThucId() {
        if (canBoChungThucId == null) {
            setCanBoChungThucId(new LongFilter());
        }
        return canBoChungThucId;
    }

    public void setCanBoChungThucId(LongFilter canBoChungThucId) {
        this.canBoChungThucId = canBoChungThucId;
    }

    public LongFilter getCanBoQuyenId() {
        return canBoQuyenId;
    }

    public Optional<LongFilter> optionalCanBoQuyenId() {
        return Optional.ofNullable(canBoQuyenId);
    }

    public LongFilter canBoQuyenId() {
        if (canBoQuyenId == null) {
            setCanBoQuyenId(new LongFilter());
        }
        return canBoQuyenId;
    }

    public void setCanBoQuyenId(LongFilter canBoQuyenId) {
        this.canBoQuyenId = canBoQuyenId;
    }

    public StringFilter getCapQuanLyId() {
        return capQuanLyId;
    }

    public Optional<StringFilter> optionalCapQuanLyId() {
        return Optional.ofNullable(capQuanLyId);
    }

    public StringFilter capQuanLyId() {
        if (capQuanLyId == null) {
            setCapQuanLyId(new StringFilter());
        }
        return capQuanLyId;
    }

    public void setCapQuanLyId(StringFilter capQuanLyId) {
        this.capQuanLyId = capQuanLyId;
    }

    public StringFilter getLoaiDonViId() {
        return loaiDonViId;
    }

    public Optional<StringFilter> optionalLoaiDonViId() {
        return Optional.ofNullable(loaiDonViId);
    }

    public StringFilter loaiDonViId() {
        if (loaiDonViId == null) {
            setLoaiDonViId(new StringFilter());
        }
        return loaiDonViId;
    }

    public void setLoaiDonViId(StringFilter loaiDonViId) {
        this.loaiDonViId = loaiDonViId;
    }

    public StringFilter getNhiemVuId() {
        return nhiemVuId;
    }

    public Optional<StringFilter> optionalNhiemVuId() {
        return Optional.ofNullable(nhiemVuId);
    }

    public StringFilter nhiemVuId() {
        if (nhiemVuId == null) {
            setNhiemVuId(new StringFilter());
        }
        return nhiemVuId;
    }

    public void setNhiemVuId(StringFilter nhiemVuId) {
        this.nhiemVuId = nhiemVuId;
    }

    public LongFilter getLanhDaoId() {
        return lanhDaoId;
    }

    public Optional<LongFilter> optionalLanhDaoId() {
        return Optional.ofNullable(lanhDaoId);
    }

    public LongFilter lanhDaoId() {
        if (lanhDaoId == null) {
            setLanhDaoId(new LongFilter());
        }
        return lanhDaoId;
    }

    public void setLanhDaoId(LongFilter lanhDaoId) {
        this.lanhDaoId = lanhDaoId;
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
        final DanhMucDonViCriteria that = (DanhMucDonViCriteria) o;
        return (
            Objects.equals(idDonVi, that.idDonVi) &&
            Objects.equals(tenDonVi, that.tenDonVi) &&
            Objects.equals(diaChi, that.diaChi) &&
            Objects.equals(nguoiDaiDien, that.nguoiDaiDien) &&
            Objects.equals(soDienThoai, that.soDienThoai) &&
            Objects.equals(idDonViQl, that.idDonViQl) &&
            Objects.equals(ngayKhaiBao, that.ngayKhaiBao) &&
            Objects.equals(trangThai, that.trangThai) &&
            Objects.equals(soNha, that.soNha) &&
            Objects.equals(maSoThue, that.maSoThue) &&
            Objects.equals(hoaDonDt, that.hoaDonDt) &&
            Objects.equals(maDonViIgate, that.maDonViIgate) &&
            Objects.equals(maCoQuanIgate, that.maCoQuanIgate) &&
            Objects.equals(kySo, that.kySo) &&
            Objects.equals(qrScan, that.qrScan) &&
            Objects.equals(verifyIdCard, that.verifyIdCard) &&
            Objects.equals(isVerifyFace, that.isVerifyFace) &&
            Objects.equals(isElastic, that.isElastic) &&
            Objects.equals(apikeyCccd, that.apikeyCccd) &&
            Objects.equals(apikeyFace, that.apikeyFace) &&
            Objects.equals(verifyCodeCccd, that.verifyCodeCccd) &&
            Objects.equals(usernameElastic, that.usernameElastic) &&
            Objects.equals(passwordElastic, that.passwordElastic) &&
            Objects.equals(idNhiemVu, that.idNhiemVu) &&
            Objects.equals(idLoaiDv, that.idLoaiDv) &&
            Objects.equals(idCapQl, that.idCapQl) &&
            Objects.equals(canBoChungThucId, that.canBoChungThucId) &&
            Objects.equals(canBoQuyenId, that.canBoQuyenId) &&
            Objects.equals(capQuanLyId, that.capQuanLyId) &&
            Objects.equals(loaiDonViId, that.loaiDonViId) &&
            Objects.equals(nhiemVuId, that.nhiemVuId) &&
            Objects.equals(lanhDaoId, that.lanhDaoId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            idDonVi,
            tenDonVi,
            diaChi,
            nguoiDaiDien,
            soDienThoai,
            idDonViQl,
            ngayKhaiBao,
            trangThai,
            soNha,
            maSoThue,
            hoaDonDt,
            maDonViIgate,
            maCoQuanIgate,
            kySo,
            qrScan,
            verifyIdCard,
            isVerifyFace,
            isElastic,
            apikeyCccd,
            apikeyFace,
            verifyCodeCccd,
            usernameElastic,
            passwordElastic,
            idNhiemVu,
            idLoaiDv,
            idCapQl,
            canBoChungThucId,
            canBoQuyenId,
            capQuanLyId,
            loaiDonViId,
            nhiemVuId,
            lanhDaoId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucDonViCriteria{" +
            optionalIdDonVi().map(f -> "idDonVi=" + f + ", ").orElse("") +
            optionalTenDonVi().map(f -> "tenDonVi=" + f + ", ").orElse("") +
            optionalDiaChi().map(f -> "diaChi=" + f + ", ").orElse("") +
            optionalNguoiDaiDien().map(f -> "nguoiDaiDien=" + f + ", ").orElse("") +
            optionalSoDienThoai().map(f -> "soDienThoai=" + f + ", ").orElse("") +
            optionalIdDonViQl().map(f -> "idDonViQl=" + f + ", ").orElse("") +
            optionalNgayKhaiBao().map(f -> "ngayKhaiBao=" + f + ", ").orElse("") +
            optionalTrangThai().map(f -> "trangThai=" + f + ", ").orElse("") +
            optionalSoNha().map(f -> "soNha=" + f + ", ").orElse("") +
            optionalMaSoThue().map(f -> "maSoThue=" + f + ", ").orElse("") +
            optionalHoaDonDt().map(f -> "hoaDonDt=" + f + ", ").orElse("") +
            optionalMaDonViIgate().map(f -> "maDonViIgate=" + f + ", ").orElse("") +
            optionalMaCoQuanIgate().map(f -> "maCoQuanIgate=" + f + ", ").orElse("") +
            optionalKySo().map(f -> "kySo=" + f + ", ").orElse("") +
            optionalQrScan().map(f -> "qrScan=" + f + ", ").orElse("") +
            optionalVerifyIdCard().map(f -> "verifyIdCard=" + f + ", ").orElse("") +
            optionalIsVerifyFace().map(f -> "isVerifyFace=" + f + ", ").orElse("") +
            optionalIsElastic().map(f -> "isElastic=" + f + ", ").orElse("") +
            optionalApikeyCccd().map(f -> "apikeyCccd=" + f + ", ").orElse("") +
            optionalApikeyFace().map(f -> "apikeyFace=" + f + ", ").orElse("") +
            optionalVerifyCodeCccd().map(f -> "verifyCodeCccd=" + f + ", ").orElse("") +
            optionalUsernameElastic().map(f -> "usernameElastic=" + f + ", ").orElse("") +
            optionalPasswordElastic().map(f -> "passwordElastic=" + f + ", ").orElse("") +
            optionalIdNhiemVu().map(f -> "idNhiemVu=" + f + ", ").orElse("") +
            optionalIdLoaiDv().map(f -> "idLoaiDv=" + f + ", ").orElse("") +
            optionalIdCapQl().map(f -> "idCapQl=" + f + ", ").orElse("") +
            optionalCanBoChungThucId().map(f -> "canBoChungThucId=" + f + ", ").orElse("") +
            optionalCanBoQuyenId().map(f -> "canBoQuyenId=" + f + ", ").orElse("") +
            optionalCapQuanLyId().map(f -> "capQuanLyId=" + f + ", ").orElse("") +
            optionalLoaiDonViId().map(f -> "loaiDonViId=" + f + ", ").orElse("") +
            optionalNhiemVuId().map(f -> "nhiemVuId=" + f + ", ").orElse("") +
            optionalLanhDaoId().map(f -> "lanhDaoId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
