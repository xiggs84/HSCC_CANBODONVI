package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ThongTinCapNhatTaiSan.
 */
@Entity
@Table(name = "thong_tin_cap_nhat_tai_san")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ThongTinCapNhatTaiSan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_cap_nhat")
    private Long idCapNhat;

    @Column(name = "ten_tai_san")
    private String tenTaiSan;

    @Lob
    @Column(name = "thong_tin_tai_san")
    private String thongTinTaiSan;

    @Column(name = "ngay_cap_nhat")
    private LocalDate ngayCapNhat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "thuaTaches",
            "taiSanDuongSus",
            "taiSanDgcs",
            "thongTinCapNhatTaiSans",
            "danhMucLoaiTaiSan",
            "tinhTrangTaiSan",
            "chiTietNganChanTaiSans",
        },
        allowSetters = true
    )
    private TaiSan taiSan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "loaiTaiSans", "danhSachTaiSans", "taiSanDgcs", "taiSanDatNhas", "thongTinCapNhatTaiSans" },
        allowSetters = true
    )
    private DanhMucLoaiTaiSan danhMucLoaiTaiSan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdCapNhat() {
        return this.idCapNhat;
    }

    public ThongTinCapNhatTaiSan idCapNhat(Long idCapNhat) {
        this.setIdCapNhat(idCapNhat);
        return this;
    }

    public void setIdCapNhat(Long idCapNhat) {
        this.idCapNhat = idCapNhat;
    }

    public String getTenTaiSan() {
        return this.tenTaiSan;
    }

    public ThongTinCapNhatTaiSan tenTaiSan(String tenTaiSan) {
        this.setTenTaiSan(tenTaiSan);
        return this;
    }

    public void setTenTaiSan(String tenTaiSan) {
        this.tenTaiSan = tenTaiSan;
    }

    public String getThongTinTaiSan() {
        return this.thongTinTaiSan;
    }

    public ThongTinCapNhatTaiSan thongTinTaiSan(String thongTinTaiSan) {
        this.setThongTinTaiSan(thongTinTaiSan);
        return this;
    }

    public void setThongTinTaiSan(String thongTinTaiSan) {
        this.thongTinTaiSan = thongTinTaiSan;
    }

    public LocalDate getNgayCapNhat() {
        return this.ngayCapNhat;
    }

    public ThongTinCapNhatTaiSan ngayCapNhat(LocalDate ngayCapNhat) {
        this.setNgayCapNhat(ngayCapNhat);
        return this;
    }

    public void setNgayCapNhat(LocalDate ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public TaiSan getTaiSan() {
        return this.taiSan;
    }

    public void setTaiSan(TaiSan taiSan) {
        this.taiSan = taiSan;
    }

    public ThongTinCapNhatTaiSan taiSan(TaiSan taiSan) {
        this.setTaiSan(taiSan);
        return this;
    }

    public DanhMucLoaiTaiSan getDanhMucLoaiTaiSan() {
        return this.danhMucLoaiTaiSan;
    }

    public void setDanhMucLoaiTaiSan(DanhMucLoaiTaiSan danhMucLoaiTaiSan) {
        this.danhMucLoaiTaiSan = danhMucLoaiTaiSan;
    }

    public ThongTinCapNhatTaiSan danhMucLoaiTaiSan(DanhMucLoaiTaiSan danhMucLoaiTaiSan) {
        this.setDanhMucLoaiTaiSan(danhMucLoaiTaiSan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThongTinCapNhatTaiSan)) {
            return false;
        }
        return getIdCapNhat() != null && getIdCapNhat().equals(((ThongTinCapNhatTaiSan) o).getIdCapNhat());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ThongTinCapNhatTaiSan{" +
            "idCapNhat=" + getIdCapNhat() +
            ", tenTaiSan='" + getTenTaiSan() + "'" +
            ", thongTinTaiSan='" + getThongTinTaiSan() + "'" +
            ", ngayCapNhat='" + getNgayCapNhat() + "'" +
            "}";
    }
}
