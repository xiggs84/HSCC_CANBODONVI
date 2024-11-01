package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.DanhMucLoaiHopDong} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiHopDongDTO implements Serializable {

    private String id;

    private String dienGiai;

    private String idVaiTro1;

    private String idVaiTro2;

    private String fileHopDong;

    private byte[] srcHopDong;

    private String srcHopDongContentType;

    private String dieuKhoan;

    private Long idDonVi;

    private Long trangThai;

    private LocalDate ngayThaoTac;

    private Long nguoiThaoTac;

    private byte[] srcLoiChung;

    private String srcLoiChungContentType;

    private String idNhomHopDong;

    private String fileLoiChung;

    private Long chuyenTaiSan;

    private Long loaiSuaDoi;

    private Long loaiHuyBo;

    private Long trangThaiDuyet;

    private String idPhanLoaiHopDong;

    private String srcCv;

    private String srcTb;

    private String srcTtpc;

    private String dgTen;

    private String nhomTen;

    private String idVaiTro3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDienGiai() {
        return dienGiai;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getIdVaiTro1() {
        return idVaiTro1;
    }

    public void setIdVaiTro1(String idVaiTro1) {
        this.idVaiTro1 = idVaiTro1;
    }

    public String getIdVaiTro2() {
        return idVaiTro2;
    }

    public void setIdVaiTro2(String idVaiTro2) {
        this.idVaiTro2 = idVaiTro2;
    }

    public String getFileHopDong() {
        return fileHopDong;
    }

    public void setFileHopDong(String fileHopDong) {
        this.fileHopDong = fileHopDong;
    }

    public byte[] getSrcHopDong() {
        return srcHopDong;
    }

    public void setSrcHopDong(byte[] srcHopDong) {
        this.srcHopDong = srcHopDong;
    }

    public String getSrcHopDongContentType() {
        return srcHopDongContentType;
    }

    public void setSrcHopDongContentType(String srcHopDongContentType) {
        this.srcHopDongContentType = srcHopDongContentType;
    }

    public String getDieuKhoan() {
        return dieuKhoan;
    }

    public void setDieuKhoan(String dieuKhoan) {
        this.dieuKhoan = dieuKhoan;
    }

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return nguoiThaoTac;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public byte[] getSrcLoiChung() {
        return srcLoiChung;
    }

    public void setSrcLoiChung(byte[] srcLoiChung) {
        this.srcLoiChung = srcLoiChung;
    }

    public String getSrcLoiChungContentType() {
        return srcLoiChungContentType;
    }

    public void setSrcLoiChungContentType(String srcLoiChungContentType) {
        this.srcLoiChungContentType = srcLoiChungContentType;
    }

    public String getIdNhomHopDong() {
        return idNhomHopDong;
    }

    public void setIdNhomHopDong(String idNhomHopDong) {
        this.idNhomHopDong = idNhomHopDong;
    }

    public String getFileLoiChung() {
        return fileLoiChung;
    }

    public void setFileLoiChung(String fileLoiChung) {
        this.fileLoiChung = fileLoiChung;
    }

    public Long getChuyenTaiSan() {
        return chuyenTaiSan;
    }

    public void setChuyenTaiSan(Long chuyenTaiSan) {
        this.chuyenTaiSan = chuyenTaiSan;
    }

    public Long getLoaiSuaDoi() {
        return loaiSuaDoi;
    }

    public void setLoaiSuaDoi(Long loaiSuaDoi) {
        this.loaiSuaDoi = loaiSuaDoi;
    }

    public Long getLoaiHuyBo() {
        return loaiHuyBo;
    }

    public void setLoaiHuyBo(Long loaiHuyBo) {
        this.loaiHuyBo = loaiHuyBo;
    }

    public Long getTrangThaiDuyet() {
        return trangThaiDuyet;
    }

    public void setTrangThaiDuyet(Long trangThaiDuyet) {
        this.trangThaiDuyet = trangThaiDuyet;
    }

    public String getIdPhanLoaiHopDong() {
        return idPhanLoaiHopDong;
    }

    public void setIdPhanLoaiHopDong(String idPhanLoaiHopDong) {
        this.idPhanLoaiHopDong = idPhanLoaiHopDong;
    }

    public String getSrcCv() {
        return srcCv;
    }

    public void setSrcCv(String srcCv) {
        this.srcCv = srcCv;
    }

    public String getSrcTb() {
        return srcTb;
    }

    public void setSrcTb(String srcTb) {
        this.srcTb = srcTb;
    }

    public String getSrcTtpc() {
        return srcTtpc;
    }

    public void setSrcTtpc(String srcTtpc) {
        this.srcTtpc = srcTtpc;
    }

    public String getDgTen() {
        return dgTen;
    }

    public void setDgTen(String dgTen) {
        this.dgTen = dgTen;
    }

    public String getNhomTen() {
        return nhomTen;
    }

    public void setNhomTen(String nhomTen) {
        this.nhomTen = nhomTen;
    }

    public String getIdVaiTro3() {
        return idVaiTro3;
    }

    public void setIdVaiTro3(String idVaiTro3) {
        this.idVaiTro3 = idVaiTro3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucLoaiHopDongDTO)) {
            return false;
        }

        DanhMucLoaiHopDongDTO danhMucLoaiHopDongDTO = (DanhMucLoaiHopDongDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, danhMucLoaiHopDongDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiHopDongDTO{" +
            "id='" + getId() + "'" +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idVaiTro1='" + getIdVaiTro1() + "'" +
            ", idVaiTro2='" + getIdVaiTro2() + "'" +
            ", fileHopDong='" + getFileHopDong() + "'" +
            ", srcHopDong='" + getSrcHopDong() + "'" +
            ", dieuKhoan='" + getDieuKhoan() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", trangThai=" + getTrangThai() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", srcLoiChung='" + getSrcLoiChung() + "'" +
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
