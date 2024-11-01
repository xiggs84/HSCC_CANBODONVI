package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.LoaiChungThuc} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiChungThucDTO implements Serializable {

    private String id;

    private String dienGiai;

    private Long khungGia;

    private Long hasBenB;

    private Long hasTaiSan;

    private Long trangThai;

    private String fileChungThuc;

    private byte[] srcChungThuc;

    private String srcChungThucContentType;

    private LocalDate ngayThaoTac;

    private Long nguoiThaoTac;

    private Long idDonVi;

    private String idLoaiSo;

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

    public Long getKhungGia() {
        return khungGia;
    }

    public void setKhungGia(Long khungGia) {
        this.khungGia = khungGia;
    }

    public Long getHasBenB() {
        return hasBenB;
    }

    public void setHasBenB(Long hasBenB) {
        this.hasBenB = hasBenB;
    }

    public Long getHasTaiSan() {
        return hasTaiSan;
    }

    public void setHasTaiSan(Long hasTaiSan) {
        this.hasTaiSan = hasTaiSan;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getFileChungThuc() {
        return fileChungThuc;
    }

    public void setFileChungThuc(String fileChungThuc) {
        this.fileChungThuc = fileChungThuc;
    }

    public byte[] getSrcChungThuc() {
        return srcChungThuc;
    }

    public void setSrcChungThuc(byte[] srcChungThuc) {
        this.srcChungThuc = srcChungThuc;
    }

    public String getSrcChungThucContentType() {
        return srcChungThucContentType;
    }

    public void setSrcChungThucContentType(String srcChungThucContentType) {
        this.srcChungThucContentType = srcChungThucContentType;
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

    public Long getIdDonVi() {
        return idDonVi;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getIdLoaiSo() {
        return idLoaiSo;
    }

    public void setIdLoaiSo(String idLoaiSo) {
        this.idLoaiSo = idLoaiSo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoaiChungThucDTO)) {
            return false;
        }

        LoaiChungThucDTO loaiChungThucDTO = (LoaiChungThucDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, loaiChungThucDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiChungThucDTO{" +
            "id='" + getId() + "'" +
            ", dienGiai='" + getDienGiai() + "'" +
            ", khungGia=" + getKhungGia() +
            ", hasBenB=" + getHasBenB() +
            ", hasTaiSan=" + getHasTaiSan() +
            ", trangThai=" + getTrangThai() +
            ", fileChungThuc='" + getFileChungThuc() + "'" +
            ", srcChungThuc='" + getSrcChungThuc() + "'" +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", idDonVi=" + getIdDonVi() +
            ", idLoaiSo='" + getIdLoaiSo() + "'" +
            "}";
    }
}
