package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.ChiTietNganChan} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChiTietNganChanDTO implements Serializable {

    private Long id;

    private Long idDoiTuong;

    private LocalDate ngayThaoTac;

    private Long loaiDoiTuong;

    private String soHsCv;

    private String soCc;

    private String soVaoSo;

    private String moTa;

    private LocalDate ngayNganChan;

    private LocalDate ngayBdNganChan;

    private LocalDate ngayKtNganChan;

    private Long trangThai;

    private Long nguoiThaoTac;

    private Long loaiNganChan;

    private LocalDate ngayCongVan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDoiTuong() {
        return idDoiTuong;
    }

    public void setIdDoiTuong(Long idDoiTuong) {
        this.idDoiTuong = idDoiTuong;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getLoaiDoiTuong() {
        return loaiDoiTuong;
    }

    public void setLoaiDoiTuong(Long loaiDoiTuong) {
        this.loaiDoiTuong = loaiDoiTuong;
    }

    public String getSoHsCv() {
        return soHsCv;
    }

    public void setSoHsCv(String soHsCv) {
        this.soHsCv = soHsCv;
    }

    public String getSoCc() {
        return soCc;
    }

    public void setSoCc(String soCc) {
        this.soCc = soCc;
    }

    public String getSoVaoSo() {
        return soVaoSo;
    }

    public void setSoVaoSo(String soVaoSo) {
        this.soVaoSo = soVaoSo;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayNganChan() {
        return ngayNganChan;
    }

    public void setNgayNganChan(LocalDate ngayNganChan) {
        this.ngayNganChan = ngayNganChan;
    }

    public LocalDate getNgayBdNganChan() {
        return ngayBdNganChan;
    }

    public void setNgayBdNganChan(LocalDate ngayBdNganChan) {
        this.ngayBdNganChan = ngayBdNganChan;
    }

    public LocalDate getNgayKtNganChan() {
        return ngayKtNganChan;
    }

    public void setNgayKtNganChan(LocalDate ngayKtNganChan) {
        this.ngayKtNganChan = ngayKtNganChan;
    }

    public Long getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Long getNguoiThaoTac() {
        return nguoiThaoTac;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getLoaiNganChan() {
        return loaiNganChan;
    }

    public void setLoaiNganChan(Long loaiNganChan) {
        this.loaiNganChan = loaiNganChan;
    }

    public LocalDate getNgayCongVan() {
        return ngayCongVan;
    }

    public void setNgayCongVan(LocalDate ngayCongVan) {
        this.ngayCongVan = ngayCongVan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiTietNganChanDTO)) {
            return false;
        }

        ChiTietNganChanDTO chiTietNganChanDTO = (ChiTietNganChanDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, chiTietNganChanDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiTietNganChanDTO{" +
            "id=" + getId() +
            ", idDoiTuong=" + getIdDoiTuong() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", loaiDoiTuong=" + getLoaiDoiTuong() +
            ", soHsCv='" + getSoHsCv() + "'" +
            ", soCc='" + getSoCc() + "'" +
            ", soVaoSo='" + getSoVaoSo() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", ngayNganChan='" + getNgayNganChan() + "'" +
            ", ngayBdNganChan='" + getNgayBdNganChan() + "'" +
            ", ngayKtNganChan='" + getNgayKtNganChan() + "'" +
            ", trangThai=" + getTrangThai() +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", loaiNganChan=" + getLoaiNganChan() +
            ", ngayCongVan='" + getNgayCongVan() + "'" +
            "}";
    }
}
