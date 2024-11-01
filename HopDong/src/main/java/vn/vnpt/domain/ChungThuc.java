package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A ChungThuc.
 */
@Document(collection = "chung_thuc")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChungThuc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("id_don_vi")
    private Long idDonVi;

    @Field("nguoi_yeu_cau")
    private String nguoiYeuCau;

    @Field("nguoi_chung_thuc")
    private Long nguoiChungThuc;

    @Field("nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Field("ngay_chung_thuc")
    private LocalDate ngayChungThuc;

    @Field("ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Field("so_tien_thu")
    private Long soTienThu;

    @Field("so_ban_sao")
    private Long soBanSao;

    @Field("van_ban")
    private String vanBan;

    @Field("trang_thai")
    private Long trangThai;

    @Field("quyen_so")
    private Long quyenSo;

    @Field("duong_su")
    private String duongSu;

    @Field("tai_san")
    private String taiSan;

    @Field("str_search")
    private String strSearch;

    @Field("src_chung_thuc")
    private String srcChungThuc;

    @Field("thong_tin_chung_thuc")
    private String thongTinChungThuc;

    @Field("chu_ky_ngoai_tru_so")
    private Long chuKyNgoaiTruSo;

    @Field("id_ct_goc")
    private Long idCtGoc;

    @Field("ngay_text")
    private String ngayText;

    @Field("chuc_danh_can_bo")
    private String chucDanhCanBo;

    @Field("ld_phe_duyet")
    private Long ldPheDuyet;

    @Field("chuc_danh_ld")
    private String chucDanhLd;

    @DBRef
    @Field("danhMucLoaiGiayToChungThuc")
    @JsonIgnoreProperties(value = { "danhSachChungThucs", "chungThucs" }, allowSetters = true)
    private DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ChungThuc id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public ChungThuc idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getNguoiYeuCau() {
        return this.nguoiYeuCau;
    }

    public ChungThuc nguoiYeuCau(String nguoiYeuCau) {
        this.setNguoiYeuCau(nguoiYeuCau);
        return this;
    }

    public void setNguoiYeuCau(String nguoiYeuCau) {
        this.nguoiYeuCau = nguoiYeuCau;
    }

    public Long getNguoiChungThuc() {
        return this.nguoiChungThuc;
    }

    public ChungThuc nguoiChungThuc(Long nguoiChungThuc) {
        this.setNguoiChungThuc(nguoiChungThuc);
        return this;
    }

    public void setNguoiChungThuc(Long nguoiChungThuc) {
        this.nguoiChungThuc = nguoiChungThuc;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public ChungThuc nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public LocalDate getNgayChungThuc() {
        return this.ngayChungThuc;
    }

    public ChungThuc ngayChungThuc(LocalDate ngayChungThuc) {
        this.setNgayChungThuc(ngayChungThuc);
        return this;
    }

    public void setNgayChungThuc(LocalDate ngayChungThuc) {
        this.ngayChungThuc = ngayChungThuc;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public ChungThuc ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getSoTienThu() {
        return this.soTienThu;
    }

    public ChungThuc soTienThu(Long soTienThu) {
        this.setSoTienThu(soTienThu);
        return this;
    }

    public void setSoTienThu(Long soTienThu) {
        this.soTienThu = soTienThu;
    }

    public Long getSoBanSao() {
        return this.soBanSao;
    }

    public ChungThuc soBanSao(Long soBanSao) {
        this.setSoBanSao(soBanSao);
        return this;
    }

    public void setSoBanSao(Long soBanSao) {
        this.soBanSao = soBanSao;
    }

    public String getVanBan() {
        return this.vanBan;
    }

    public ChungThuc vanBan(String vanBan) {
        this.setVanBan(vanBan);
        return this;
    }

    public void setVanBan(String vanBan) {
        this.vanBan = vanBan;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public ChungThuc trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Long getQuyenSo() {
        return this.quyenSo;
    }

    public ChungThuc quyenSo(Long quyenSo) {
        this.setQuyenSo(quyenSo);
        return this;
    }

    public void setQuyenSo(Long quyenSo) {
        this.quyenSo = quyenSo;
    }

    public String getDuongSu() {
        return this.duongSu;
    }

    public ChungThuc duongSu(String duongSu) {
        this.setDuongSu(duongSu);
        return this;
    }

    public void setDuongSu(String duongSu) {
        this.duongSu = duongSu;
    }

    public String getTaiSan() {
        return this.taiSan;
    }

    public ChungThuc taiSan(String taiSan) {
        this.setTaiSan(taiSan);
        return this;
    }

    public void setTaiSan(String taiSan) {
        this.taiSan = taiSan;
    }

    public String getStrSearch() {
        return this.strSearch;
    }

    public ChungThuc strSearch(String strSearch) {
        this.setStrSearch(strSearch);
        return this;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public String getSrcChungThuc() {
        return this.srcChungThuc;
    }

    public ChungThuc srcChungThuc(String srcChungThuc) {
        this.setSrcChungThuc(srcChungThuc);
        return this;
    }

    public void setSrcChungThuc(String srcChungThuc) {
        this.srcChungThuc = srcChungThuc;
    }

    public String getThongTinChungThuc() {
        return this.thongTinChungThuc;
    }

    public ChungThuc thongTinChungThuc(String thongTinChungThuc) {
        this.setThongTinChungThuc(thongTinChungThuc);
        return this;
    }

    public void setThongTinChungThuc(String thongTinChungThuc) {
        this.thongTinChungThuc = thongTinChungThuc;
    }

    public Long getChuKyNgoaiTruSo() {
        return this.chuKyNgoaiTruSo;
    }

    public ChungThuc chuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.setChuKyNgoaiTruSo(chuKyNgoaiTruSo);
        return this;
    }

    public void setChuKyNgoaiTruSo(Long chuKyNgoaiTruSo) {
        this.chuKyNgoaiTruSo = chuKyNgoaiTruSo;
    }

    public Long getIdCtGoc() {
        return this.idCtGoc;
    }

    public ChungThuc idCtGoc(Long idCtGoc) {
        this.setIdCtGoc(idCtGoc);
        return this;
    }

    public void setIdCtGoc(Long idCtGoc) {
        this.idCtGoc = idCtGoc;
    }

    public String getNgayText() {
        return this.ngayText;
    }

    public ChungThuc ngayText(String ngayText) {
        this.setNgayText(ngayText);
        return this;
    }

    public void setNgayText(String ngayText) {
        this.ngayText = ngayText;
    }

    public String getChucDanhCanBo() {
        return this.chucDanhCanBo;
    }

    public ChungThuc chucDanhCanBo(String chucDanhCanBo) {
        this.setChucDanhCanBo(chucDanhCanBo);
        return this;
    }

    public void setChucDanhCanBo(String chucDanhCanBo) {
        this.chucDanhCanBo = chucDanhCanBo;
    }

    public Long getLdPheDuyet() {
        return this.ldPheDuyet;
    }

    public ChungThuc ldPheDuyet(Long ldPheDuyet) {
        this.setLdPheDuyet(ldPheDuyet);
        return this;
    }

    public void setLdPheDuyet(Long ldPheDuyet) {
        this.ldPheDuyet = ldPheDuyet;
    }

    public String getChucDanhLd() {
        return this.chucDanhLd;
    }

    public ChungThuc chucDanhLd(String chucDanhLd) {
        this.setChucDanhLd(chucDanhLd);
        return this;
    }

    public void setChucDanhLd(String chucDanhLd) {
        this.chucDanhLd = chucDanhLd;
    }

    public DanhMucLoaiGiayToChungThuc getDanhMucLoaiGiayToChungThuc() {
        return this.danhMucLoaiGiayToChungThuc;
    }

    public void setDanhMucLoaiGiayToChungThuc(DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc) {
        this.danhMucLoaiGiayToChungThuc = danhMucLoaiGiayToChungThuc;
    }

    public ChungThuc danhMucLoaiGiayToChungThuc(DanhMucLoaiGiayToChungThuc danhMucLoaiGiayToChungThuc) {
        this.setDanhMucLoaiGiayToChungThuc(danhMucLoaiGiayToChungThuc);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChungThuc)) {
            return false;
        }
        return getId() != null && getId().equals(((ChungThuc) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChungThuc{" +
            "id=" + getId() +
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
            "}";
    }
}
