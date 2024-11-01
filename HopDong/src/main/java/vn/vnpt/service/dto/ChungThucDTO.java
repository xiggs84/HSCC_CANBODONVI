package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.ChungThuc} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChungThucDTO implements Serializable {

    private String id;

    private Long idDonVi;

    private String nguoiYeuCau;

    private Long nguoiChungThuc;

    private Long nguoiThaoTac;

    private LocalDate ngayChungThuc;

    private LocalDate ngayThaoTac;

    private Long soTienThu;

    private Long soBanSao;

    private String vanBan;

    private Long trangThai;

    private Long quyenSo;

    private String duongSu;

    private String taiSan;

    private String strSearch;

    private String srcChungThuc;

    private String thongTinChungThuc;

    private Long chuKyNgoaiTruSo;

    private Long idCtGoc;

    private String ngayText;

    private String chucDanhCanBo;

    private Long ldPheDuyet;

    private String chucDanhLd;

    private DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThuc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getNguoiYeuCau() {
        return nguoiYeuCau;
    }

    public void setNguoiYeuCau(String nguoiYeuCau) {
        this.nguoiYeuCau = nguoiYeuCau;
    }

    public Long getNguoiChungThuc() {
        return nguoiChungThuc;
    }

    public void setNguoiChungThuc(Long nguoiChungThuc) {
        this.nguoiChungThuc = nguoiChungThuc;
    }

    public Long getNguoiThaoTac() {
        return nguoiThaoTac;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public LocalDate getNgayChungThuc() {
        return ngayChungThuc;
    }

    public void setNgayChungThuc(LocalDate ngayChungThuc) {
        this.ngayChungThuc = ngayChungThuc;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getSoTienThu() {
        return soTienThu;
    }

    public void setSoTienThu(Long soTienThu) {
        this.soTienThu = soTienThu;
    }

    public Long getSoBanSao() {
        return soBanSao;
    }

    public void setSoBanSao(Long soBanSao) {
        this.soBanSao = soBanSao;
    }

    public String getVanBan() {
        return vanBan;
    }

    public void setVanBan(String vanBan) {
        this.vanBan = vanBan;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Long getQuyenSo() {
        return quyenSo;
    }

    public void setQuyenSo(Long quyenSo) {
        this.quyenSo = quyenSo;
    }

    public String getDuongSu() {
        return duongSu;
    }

    public void setDuongSu(String duongSu) {
        this.duongSu = duongSu;
    }

    public String getTaiSan() {
        return taiSan;
    }

    public void setTaiSan(String taiSan) {
        this.taiSan = taiSan;
    }

    public String getStrSearch() {
        return strSearch;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public String getSrcChungThuc() {
        return srcChungThuc;
    }

    public void setSrcChungThuc(String srcChungThuc) {
        this.srcChungThuc = srcChungThuc;
    }

    public String getThongTinChungThuc() {
        return thongTinChungThuc;
    }

    public void setThongTinChungThuc(String thongTinChungThuc) {
        this.thongTinChungThuc = thongTinChungThuc;
    }

    public Long getChuKyNgoaiTruSo() {
        return chuKyNgoaiTruSo;
    }

    public void setChuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.chuKyNgoaiTruSo = chuKyNgoaiTruSo;
    }

    public Long getIdCtGoc() {
        return idCtGoc;
    }

    public void setIdCtGoc(Long idCtGoc) {
        this.idCtGoc = idCtGoc;
    }

    public String getNgayText() {
        return ngayText;
    }

    public void setNgayText(String ngayText) {
        this.ngayText = ngayText;
    }

    public String getChucDanhCanBo() {
        return chucDanhCanBo;
    }

    public void setChucDanhCanBo(String chucDanhCanBo) {
        this.chucDanhCanBo = chucDanhCanBo;
    }

    public Long getLdPheDuyet() {
        return ldPheDuyet;
    }

    public void setLdPheDuyet(Long ldPheDuyet) {
        this.ldPheDuyet = ldPheDuyet;
    }

    public String getChucDanhLd() {
        return chucDanhLd;
    }

    public void setChucDanhLd(String chucDanhLd) {
        this.chucDanhLd = chucDanhLd;
    }

    public DanhMucLoaiGiayToChungThucDTO getDanhMucLoaiGiayToChungThuc() {
        return danhMucLoaiGiayToChungThuc;
    }

    public void setDanhMucLoaiGiayToChungThuc(DanhMucLoaiGiayToChungThucDTO danhMucLoaiGiayToChungThuc) {
        this.danhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThuc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChungThucDTO)) {
            return false;
        }

        ChungThucDTO chungThucDTO = (ChungThucDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, chungThucDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChungThucDTO{" +
            "id='" + getId() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", nguoiYeuCau='" + getNguoiYeuCau() + "'" +
            ", nguoiChungThuc=" + getNguoiChungThuc() +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", ngayChungThuc='" + getNgayChungThuc() + "'" +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", soTienThu=" + getSoTienThu() +
            ", soBanSao=" + getSoBanSao() +
            ", vanBan='" + getVanBan() + "'" +
            ", trangThai=" + getTrangThai() +
            ", quyenSo=" + getQuyenSo() +
            ", duongSu='" + getDuongSu() + "'" +
            ", taiSan='" + getTaiSan() + "'" +
            ", strSearch='" + getStrSearch() + "'" +
            ", srcChungThuc='" + getSrcChungThuc() + "'" +
            ", thongTinChungThuc='" + getThongTinChungThuc() + "'" +
            ", chuKyNgoaiTruSo=" + getChuKyNgoaiTruSo() +
            ", idCtGoc=" + getIdCtGoc() +
            ", ngayText='" + getNgayText() + "'" +
            ", chucDanhCanBo='" + getChucDanhCanBo() + "'" +
            ", ldPheDuyet=" + getLdPheDuyet() +
            ", chucDanhLd='" + getChucDanhLd() + "'" +
            ", danhMucLoaiGiayToChungThuc=" + getDanhMucLoaiGiayToChungThuc() +
            "}";
    }
}
