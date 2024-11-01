package vn.vnpt.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DanhMucLoaiHopDong.
 */
@Document(collection = "danh_muc_loai_hop_dong")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiHopDong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("dien_giai")
    private String dienGiai;

    @Field("id_vai_tro_1")
    private String idVaiTro1;

    @Field("id_vai_tro_2")
    private String idVaiTro2;

    @Field("file_hop_dong")
    private String fileHopDong;

    @Field("src_hop_dong")
    private byte[] srcHopDong;

    @Field("src_hop_dong_content_type")
    private String srcHopDongContentType;

    @Field("dieu_khoan")
    private String dieuKhoan;

    @Field("id_don_vi")
    private Long idDonVi;

    @Field("trang_thai")
    private Long trangThai;

    @Field("ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Field("nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Field("src_loi_chung")
    private byte[] srcLoiChung;

    @Field("src_loi_chung_content_type")
    private String srcLoiChungContentType;

    @Field("id_nhom_hop_dong")
    private String idNhomHopDong;

    @Field("file_loi_chung")
    private String fileLoiChung;

    @Field("chuyen_tai_san")
    private Long chuyenTaiSan;

    @Field("loai_sua_doi")
    private Long loaiSuaDoi;

    @Field("loai_huy_bo")
    private Long loaiHuyBo;

    @Field("trang_thai_duyet")
    private Long trangThaiDuyet;

    @Field("id_phan_loai_hop_dong")
    private String idPhanLoaiHopDong;

    @Field("src_cv")
    private String srcCv;

    @Field("src_tb")
    private String srcTb;

    @Field("src_ttpc")
    private String srcTtpc;

    @Field("dg_ten")
    private String dgTen;

    @Field("nhom_ten")
    private String nhomTen;

    @Field("id_vai_tro_3")
    private String idVaiTro3;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DanhMucLoaiHopDong id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DanhMucLoaiHopDong dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getIdVaiTro1() {
        return this.idVaiTro1;
    }

    public DanhMucLoaiHopDong idVaiTro1(String idVaiTro1) {
        this.setIdVaiTro1(idVaiTro1);
        return this;
    }

    public void setIdVaiTro1(String idVaiTro1) {
        this.idVaiTro1 = idVaiTro1;
    }

    public String getIdVaiTro2() {
        return this.idVaiTro2;
    }

    public DanhMucLoaiHopDong idVaiTro2(String idVaiTro2) {
        this.setIdVaiTro2(idVaiTro2);
        return this;
    }

    public void setIdVaiTro2(String idVaiTro2) {
        this.idVaiTro2 = idVaiTro2;
    }

    public String getFileHopDong() {
        return this.fileHopDong;
    }

    public DanhMucLoaiHopDong fileHopDong(String fileHopDong) {
        this.setFileHopDong(fileHopDong);
        return this;
    }

    public void setFileHopDong(String fileHopDong) {
        this.fileHopDong = fileHopDong;
    }

    public byte[] getSrcHopDong() {
        return this.srcHopDong;
    }

    public DanhMucLoaiHopDong srcHopDong(byte[] srcHopDong) {
        this.setSrcHopDong(srcHopDong);
        return this;
    }

    public void setSrcHopDong(byte[] srcHopDong) {
        this.srcHopDong = srcHopDong;
    }

    public String getSrcHopDongContentType() {
        return this.srcHopDongContentType;
    }

    public DanhMucLoaiHopDong srcHopDongContentType(String srcHopDongContentType) {
        this.srcHopDongContentType = srcHopDongContentType;
        return this;
    }

    public void setSrcHopDongContentType(String srcHopDongContentType) {
        this.srcHopDongContentType = srcHopDongContentType;
    }

    public String getDieuKhoan() {
        return this.dieuKhoan;
    }

    public DanhMucLoaiHopDong dieuKhoan(String dieuKhoan) {
        this.setDieuKhoan(dieuKhoan);
        return this;
    }

    public void setDieuKhoan(String dieuKhoan) {
        this.dieuKhoan = dieuKhoan;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public DanhMucLoaiHopDong idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DanhMucLoaiHopDong trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public DanhMucLoaiHopDong ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public DanhMucLoaiHopDong nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public byte[] getSrcLoiChung() {
        return this.srcLoiChung;
    }

    public DanhMucLoaiHopDong srcLoiChung(byte[] srcLoiChung) {
        this.setSrcLoiChung(srcLoiChung);
        return this;
    }

    public void setSrcLoiChung(byte[] srcLoiChung) {
        this.srcLoiChung = srcLoiChung;
    }

    public String getSrcLoiChungContentType() {
        return this.srcLoiChungContentType;
    }

    public DanhMucLoaiHopDong srcLoiChungContentType(String srcLoiChungContentType) {
        this.srcLoiChungContentType = srcLoiChungContentType;
        return this;
    }

    public void setSrcLoiChungContentType(String srcLoiChungContentType) {
        this.srcLoiChungContentType = srcLoiChungContentType;
    }

    public String getIdNhomHopDong() {
        return this.idNhomHopDong;
    }

    public DanhMucLoaiHopDong idNhomHopDong(String idNhomHopDong) {
        this.setIdNhomHopDong(idNhomHopDong);
        return this;
    }

    public void setIdNhomHopDong(String idNhomHopDong) {
        this.idNhomHopDong = idNhomHopDong;
    }

    public String getFileLoiChung() {
        return this.fileLoiChung;
    }

    public DanhMucLoaiHopDong fileLoiChung(String fileLoiChung) {
        this.setFileLoiChung(fileLoiChung);
        return this;
    }

    public void setFileLoiChung(String fileLoiChung) {
        this.fileLoiChung = fileLoiChung;
    }

    public Long getChuyenTaiSan() {
        return this.chuyenTaiSan;
    }

    public DanhMucLoaiHopDong chuyenTaiSan(Long chuyenTaiSan) {
        this.setChuyenTaiSan(chuyenTaiSan);
        return this;
    }

    public void setChuyenTaiSan(Long chuyenTaiSan) {
        this.chuyenTaiSan = chuyenTaiSan;
    }

    public Long getLoaiSuaDoi() {
        return this.loaiSuaDoi;
    }

    public DanhMucLoaiHopDong loaiSuaDoi(Long loaiSuaDoi) {
        this.setLoaiSuaDoi(loaiSuaDoi);
        return this;
    }

    public void setLoaiSuaDoi(Long loaiSuaDoi) {
        this.loaiSuaDoi = loaiSuaDoi;
    }

    public Long getLoaiHuyBo() {
        return this.loaiHuyBo;
    }

    public DanhMucLoaiHopDong loaiHuyBo(Long loaiHuyBo) {
        this.setLoaiHuyBo(loaiHuyBo);
        return this;
    }

    public void setLoaiHuyBo(Long loaiHuyBo) {
        this.loaiHuyBo = loaiHuyBo;
    }

    public Long getTrangThaiDuyet() {
        return this.trangThaiDuyet;
    }

    public DanhMucLoaiHopDong trangThaiDuyet(Long trangThaiDuyet) {
        this.setTrangThaiDuyet(trangThaiDuyet);
        return this;
    }

    public void setTrangThaiDuyet(Long trangThaiDuyet) {
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public String getIdPhanLoaiHopDong() {
        return this.idPhanLoaiHopDong;
    }

    public DanhMucLoaiHopDong idPhanLoaiHopDong(String idPhanLoaiHopDong) {
        this.setIdPhanLoaiHopDong(idPhanLoaiHopDong);
        return this;
    }

    public void setIdPhanLoaiHopDong(String idPhanLoaiHopDong) {
        this.idPhanLoaiHopDong = idPhanLoaiHopDong;
    }

    public String getSrcCv() {
        return this.srcCv;
    }

    public DanhMucLoaiHopDong srcCv(String srcCv) {
        this.setSrcCv(srcCv);
        return this;
    }

    public void setSrcCv(String srcCv) {
        this.srcCv = srcCv;
    }

    public String getSrcTb() {
        return this.srcTb;
    }

    public DanhMucLoaiHopDong srcTb(String srcTb) {
        this.setSrcTb(srcTb);
        return this;
    }

    public void setSrcTb(String srcTb) {
        this.srcTb = srcTb;
    }

    public String getSrcTtpc() {
        return this.srcTtpc;
    }

    public DanhMucLoaiHopDong srcTtpc(String srcTtpc) {
        this.setSrcTtpc(srcTtpc);
        return this;
    }

    public void setSrcTtpc(String srcTtpc) {
        this.srcTtpc = srcTtpc;
    }

    public String getDgTen() {
        return this.dgTen;
    }

    public DanhMucLoaiHopDong dgTen(String dgTen) {
        this.setDgTen(dgTen);
        return this;
    }

    public void setDgTen(String dgTen) {
        this.dgTen = dgTen;
    }

    public String getNhomTen() {
        return this.nhomTen;
    }

    public DanhMucLoaiHopDong nhomTen(String nhomTen) {
        this.setNhomTen(nhomTen);
        return this;
    }

    public void setNhomTen(String nhomTen) {
        this.nhomTen = nhomTen;
    }

    public String getIdVaiTro3() {
        return this.idVaiTro3;
    }

    public DanhMucLoaiHopDong idVaiTro3(String idVaiTro3) {
        this.setIdVaiTro3(idVaiTro3);
        return this;
    }

    public void setIdVaiTro3(String idVaiTro3) {
        this.idVaiTro3 = idVaiTro3;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucLoaiHopDong)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhMucLoaiHopDong) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiHopDong{" +
            "id=" + getId() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idVaiTro1='" + getIdVaiTro1() + "'" +
            ", idVaiTro2='" + getIdVaiTro2() + "'" +
            ", fileHopDong='" + getFileHopDong() + "'" +
            ", srcHopDong='" + getSrcHopDong() + "'" +
            ", srcHopDongContentType='" + getSrcHopDongContentType() + "'" +
            ", dieuKhoan='" + getDieuKhoan() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", trangThai=" + getTrangThai() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", srcLoiChung='" + getSrcLoiChung() + "'" +
            ", srcLoiChungContentType='" + getSrcLoiChungContentType() + "'" +
            ", idNhomHopDong='" + getIdNhomHopDong() + "'" +
            ", fileLoiChung='" + getFileLoiChung() + "'" +
            ", chuyenTaiSan=" + getChuyenTaiSan() +
            ", loaiSuaDoi=" + getLoaiSuaDoi() +
            ", loaiHuyBo=" + getLoaiHuyBo() +
            ", trangThaiDuyet=" + getTrangThaiDuyet() +
            ", idPhanLoaiHopDong='" + getIdPhanLoaiHopDong() + "'" +
            ", srcCv='" + getSrcCv() + "'" +
            ", srcTb='" + getSrcTb() + "'" +
            ", srcTtpc='" + getSrcTtpc() + "'" +
            ", dgTen='" + getDgTen() + "'" +
            ", nhomTen='" + getNhomTen() + "'" +
            ", idVaiTro3='" + getIdVaiTro3() + "'" +
            "}";
    }
}
