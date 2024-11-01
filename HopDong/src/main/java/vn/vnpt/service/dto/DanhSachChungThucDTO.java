package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhSachChungThuc} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhSachChungThucDTO implements Serializable {

    private String id;

    private Long idDonVi;

    private Long nguoiChungThuc;

    private Long nguoiThaoTac;

    private LocalDate ngayChungThuc;

    private LocalDate ngayThaoTac;

    private Long trangThai;

    private Long quyenSo;

    private String srcChungThuc;

    private Long chuKyNgoaiTruSo;

    private String ngayText;

    private String strSearch;

    private Long soTienThu;

    private Long ldPheDuyet;

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

    public String getSrcChungThuc() {
        return srcChungThuc;
    }

    public void setSrcChungThuc(String srcChungThuc) {
        this.srcChungThuc = srcChungThuc;
    }

    public Long getChuKyNgoaiTruSo() {
        return chuKyNgoaiTruSo;
    }

    public void setChuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.chuKyNgoaiTruSo = chuKyNgoaiTruSo;
    }

    public String getNgayText() {
        return ngayText;
    }

    public void setNgayText(String ngayText) {
        this.ngayText = ngayText;
    }

    public String getStrSearch() {
        return strSearch;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public Long getSoTienThu() {
        return soTienThu;
    }

    public void setSoTienThu(Long soTienThu) {
        this.soTienThu = soTienThu;
    }

    public Long getLdPheDuyet() {
        return ldPheDuyet;
    }

    public void setLdPheDuyet(Long ldPheDuyet) {
        this.ldPheDuyet = ldPheDuyet;
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
        if (!(o instanceof DanhSachChungThucDTO)) {
            return false;
        }

        DanhSachChungThucDTO danhSachChungThucDTO = (DanhSachChungThucDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, danhSachChungThucDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhSachChungThucDTO{" +
            "id='" + getId() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", nguoiChungThuc=" + getNguoiChungThuc() +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", ngayChungThuc='" + getNgayChungThuc() + "'" +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", trangThai=" + getTrangThai() +
            ", quyenSo=" + getQuyenSo() +
            ", srcChungThuc='" + getSrcChungThuc() + "'" +
            ", chuKyNgoaiTruSo=" + getChuKyNgoaiTruSo() +
            ", ngayText='" + getNgayText() + "'" +
            ", strSearch='" + getStrSearch() + "'" +
            ", soTienThu=" + getSoTienThu() +
            ", ldPheDuyet=" + getLdPheDuyet() +
            ", danhMucLoaiGiayToChungThuc=" + getDanhMucLoaiGiayToChungThuc() +
            "}";
    }
}
