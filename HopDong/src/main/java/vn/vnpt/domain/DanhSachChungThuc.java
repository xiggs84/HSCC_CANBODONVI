package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DanhSachChungThuc.
 */
@Document(collection = "danh_sach_chung_thuc")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhSachChungThuc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("id_don_vi")
    private Long idDonVi;

    @Field("nguoi_chung_thuc")
    private Long nguoiChungThuc;

    @Field("nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Field("ngay_chung_thuc")
    private LocalDate ngayChungThuc;

    @Field("ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Field("trang_thai")
    private Long trangThai;

    @Field("quyen_so")
    private Long quyenSo;

    @Field("src_chung_thuc")
    private String srcChungThuc;

    @Field("chu_ky_ngoai_tru_so")
    private Long chuKyNgoaiTruSo;

    @Field("ngay_text")
    private String ngayText;

    @Field("str_search")
    private String strSearch;

    @Field("so_tien_thu")
    private Long soTienThu;

    @Field("ld_phe_duyet")
    private Long ldPheDuyet;

    @DBRef
    @Field("danhMucLoaiGiayToChungThuc")
    @JsonIgnoreProperties(value = { "danhSachChungThucs", "chungThucs" }, allowSetters = true)
    private DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DanhSachChungThuc id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public DanhSachChungThuc idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getNguoiChungThuc() {
        return this.nguoiChungThuc;
    }

    public DanhSachChungThuc nguoiChungThuc(Long nguoiChungThuc) {
        this.setNguoiChungThuc(nguoiChungThuc);
        return this;
    }

    public void setNguoiChungThuc(Long nguoiChungThuc) {
        this.nguoiChungThuc = nguoiChungThuc;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public DanhSachChungThuc nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public LocalDate getNgayChungThuc() {
        return this.ngayChungThuc;
    }

    public DanhSachChungThuc ngayChungThuc(LocalDate ngayChungThuc) {
        this.setNgayChungThuc(ngayChungThuc);
        return this;
    }

    public void setNgayChungThuc(LocalDate ngayChungThuc) {
        this.ngayChungThuc = ngayChungThuc;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public DanhSachChungThuc ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DanhSachChungThuc trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Long getQuyenSo() {
        return this.quyenSo;
    }

    public DanhSachChungThuc quyenSo(Long quyenSo) {
        this.setQuyenSo(quyenSo);
        return this;
    }

    public void setQuyenSo(Long quyenSo) {
        this.quyenSo = quyenSo;
    }

    public String getSrcChungThuc() {
        return this.srcChungThuc;
    }

    public DanhSachChungThuc srcChungThuc(String srcChungThuc) {
        this.setSrcChungThuc(srcChungThuc);
        return this;
    }

    public void setSrcChungThuc(String srcChungThuc) {
        this.srcChungThuc = srcChungThuc;
    }

    public Long getChuKyNgoaiTruSo() {
        return this.chuKyNgoaiTruSo;
    }

    public DanhSachChungThuc chuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.setChuKyNgoaiTruSo(chuKyNgoaiTruSo);
        return this;
    }

    public void setChuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.chuKyNgoaiTruSo = chuKyNgoaiTruSo;
    }

    public String getNgayText() {
        return this.ngayText;
    }

    public DanhSachChungThuc ngayText(String ngayText) {
        this.setNgayText(ngayText);
        return this;
    }

    public void setNgayText(String ngayText) {
        this.ngayText = ngayText;
    }

    public String getStrSearch() {
        return this.strSearch;
    }

    public DanhSachChungThuc strSearch(String strSearch) {
        this.setStrSearch(strSearch);
        return this;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public Long getSoTienThu() {
        return this.soTienThu;
    }

    public DanhSachChungThuc soTienThu(Long soTienThu) {
        this.setSoTienThu(soTienThu);
        return this;
    }

    public void setSoTienThu(Long soTienThu) {
        this.soTienThu = soTienThu;
    }

    public Long getLdPheDuyet() {
        return this.ldPheDuyet;
    }

    public DanhSachChungThuc ldPheDuyet(Long ldPheDuyet) {
        this.setLdPheDuyet(ldPheDuyet);
        return this;
    }

    public void setLdPheDuyet(Long ldPheDuyet) {
        this.ldPheDuyet = ldPheDuyet;
    }

    public DanhMucLoaiGiayToChungThuc getDanhMucLoaiGiayToChungThuc() {
        return this.danhMucLoaiGiayToChungThuc;
    }

    public void setDanhMucLoaiGiayToChungThuc(DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc) {
        this.danhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThuc;
    }

    public DanhSachChungThuc danhMucLoaiGiayToChungThuc(DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc) {
        this.setDanhMucLoaiGiayToChungThuc(danhMucLoaiGiayToChungThuc);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhSachChungThuc)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhSachChungThuc) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhSachChungThuc{" +
            "id=" + getId() +
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
            "}";
    }
}
