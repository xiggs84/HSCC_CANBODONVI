package vn.vnpt.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.ChiTietNganChanTaiSan} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChiTietNganChanTaiSanDTO implements Serializable {

    private Long idNganChan;

    private LocalDate ngayThaoTac;

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

    private TaiSanDTO taiSan;

    public Long getIdNganChan() {
        return idNganChan;
    }

    public void setIdNganChan(Long idNganChan) {
        this.idNganChan = idNganChan;
    }

    public LocalDate getNgayThaoTac() {
        return ngayThaoTac;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
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

    public TaiSanDTO getTaiSan() {
        return taiSan;
    }

    public void setTaiSan(TaiSanDTO taiSan) {
        this.taiSan = taiSan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiTietNganChanTaiSanDTO)) {
            return false;
        }

        ChiTietNganChanTaiSanDTO chiTietNganChanTaiSanDTO = (ChiTietNganChanTaiSanDTO) o;
        if (this.idNganChan == null) {
            return false;
        }
        return Objects.equals(this.idNganChan, chiTietNganChanTaiSanDTO.idNganChan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idNganChan);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiTietNganChanTaiSanDTO{" +
            "idNganChan=" + getIdNganChan() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
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
            ", taiSan=" + getTaiSan() +
            "}";
    }
}
