package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhSachDuongSu.
 */
@Entity
@Table(name = "danh_sach_duong_su")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhSachDuongSu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ten_duong_su")
    private String tenDuongSu;

    @Column(name = "dia_chi")
    private String diaChi;

    @Min(value = 0)
    @Max(value = 1)
    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Column(name = "nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Column(name = "id_ds_goc")
    private Long idDsGoc;

    @Column(name = "id_master")
    private String idMaster;

    @Column(name = "id_don_vi")
    private Long idDonVi;

    @Column(name = "str_search")
    private String strSearch;

    @Column(name = "so_giay_to")
    private String soGiayTo;

    @Column(name = "id_loai_ngan_chan")
    private Long idLoaiNganChan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "thongTinCapNhats",
            "taiSanDuongSus",
            "quanHeDuongSus",
            "danhSachDuongSus",
            "duongSuTrungCmnds",
            "duongSuTrungCmndBaks",
            "loaiDuongSu",
            "loaiGiayTo",
        },
        allowSetters = true
    )
    private DuongSu duongSu;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DanhSachDuongSu id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenDuongSu() {
        return this.tenDuongSu;
    }

    public DanhSachDuongSu tenDuongSu(String tenDuongSu) {
        this.setTenDuongSu(tenDuongSu);
        return this;
    }

    public void setTenDuongSu(String tenDuongSu) {
        this.tenDuongSu = tenDuongSu;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public DanhSachDuongSu diaChi(String diaChi) {
        this.setDiaChi(diaChi);
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getTrangThai() {
        return this.trangThai;
    }

    public DanhSachDuongSu trangThai(Integer trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public DanhSachDuongSu ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public DanhSachDuongSu nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getIdDsGoc() {
        return this.idDsGoc;
    }

    public DanhSachDuongSu idDsGoc(Long idDsGoc) {
        this.setIdDsGoc(idDsGoc);
        return this;
    }

    public void setIdDsGoc(Long idDsGoc) {
        this.idDsGoc = idDsGoc;
    }

    public String getIdMaster() {
        return this.idMaster;
    }

    public DanhSachDuongSu idMaster(String idMaster) {
        this.setIdMaster(idMaster);
        return this;
    }

    public void setIdMaster(String idMaster) {
        this.idMaster = idMaster;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public DanhSachDuongSu idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getStrSearch() {
        return this.strSearch;
    }

    public DanhSachDuongSu strSearch(String strSearch) {
        this.setStrSearch(strSearch);
        return this;
    }

    public void setStrSearch(String strSearch) {
        this.strSearch = strSearch;
    }

    public String getSoGiayTo() {
        return this.soGiayTo;
    }

    public DanhSachDuongSu soGiayTo(String soGiayTo) {
        this.setSoGiayTo(soGiayTo);
        return this;
    }

    public void setSoGiayTo(String soGiayTo) {
        this.soGiayTo = soGiayTo;
    }

    public Long getIdLoaiNganChan() {
        return this.idLoaiNganChan;
    }

    public DanhSachDuongSu idLoaiNganChan(Long idLoaiNganChan) {
        this.setIdLoaiNganChan(idLoaiNganChan);
        return this;
    }

    public void setIdLoaiNganChan(Long idLoaiNganChan) {
        this.idLoaiNganChan = idLoaiNganChan;
    }

    public DuongSu getDuongSu() {
        return this.duongSu;
    }

    public void setDuongSu(DuongSu duongSu) {
        this.duongSu = duongSu;
    }

    public DanhSachDuongSu duongSu(DuongSu duongSu) {
        this.setDuongSu(duongSu);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhSachDuongSu)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhSachDuongSu) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhSachDuongSu{" +
            "id=" + getId() +
            ", tenDuongSu='" + getTenDuongSu() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", trangThai=" + getTrangThai() +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", idDsGoc=" + getIdDsGoc() +
            ", idMaster='" + getIdMaster() + "'" +
            ", idDonVi=" + getIdDonVi() +
            ", strSearch='" + getStrSearch() + "'" +
            ", soGiayTo='" + getSoGiayTo() + "'" +
            ", idLoaiNganChan=" + getIdLoaiNganChan() +
            "}";
    }
}
