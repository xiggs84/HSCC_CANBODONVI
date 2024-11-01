package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ThongTinCapNhatDuongSu.
 */
@Entity
@Table(name = "thong_tin_cap_nhat_duong_su")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ThongTinCapNhatDuongSu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_cap_nhat")
    private Long idCapNhat;

    @Column(name = "ten_duong_su")
    private String tenDuongSu;

    @Column(name = "so_giay_to")
    private String soGiayTo;

    @Lob
    @Column(name = "thong_tin_duong_su")
    private String thongTinDuongSu;

    @Column(name = "ngay_cap_nhat")
    private LocalDate ngayCapNhat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "idLoaiDuongSus", "thongTinCapNhatDuongSus" }, allowSetters = true)
    private LoaiDuongSu loaiDuongSu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "idLoaiGiayTos", "thongTinCapNhatDuongSus" }, allowSetters = true)
    private LoaiGiayTo loaiGiayTo;

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

    public Long getIdCapNhat() {
        return this.idCapNhat;
    }

    public ThongTinCapNhatDuongSu idCapNhat(Long idCapNhat) {
        this.setIdCapNhat(idCapNhat);
        return this;
    }

    public void setIdCapNhat(Long idCapNhat) {
        this.idCapNhat = idCapNhat;
    }

    public String getTenDuongSu() {
        return this.tenDuongSu;
    }

    public ThongTinCapNhatDuongSu tenDuongSu(String tenDuongSu) {
        this.setTenDuongSu(tenDuongSu);
        return this;
    }

    public void setTenDuongSu(String tenDuongSu) {
        this.tenDuongSu = tenDuongSu;
    }

    public String getSoGiayTo() {
        return this.soGiayTo;
    }

    public ThongTinCapNhatDuongSu soGiayTo(String soGiayTo) {
        this.setSoGiayTo(soGiayTo);
        return this;
    }

    public void setSoGiayTo(String soGiayTo) {
        this.soGiayTo = soGiayTo;
    }

    public String getThongTinDuongSu() {
        return this.thongTinDuongSu;
    }

    public ThongTinCapNhatDuongSu thongTinDuongSu(String thongTinDuongSu) {
        this.setThongTinDuongSu(thongTinDuongSu);
        return this;
    }

    public void setThongTinDuongSu(String thongTinDuongSu) {
        this.thongTinDuongSu = thongTinDuongSu;
    }

    public LocalDate getNgayCapNhat() {
        return this.ngayCapNhat;
    }

    public ThongTinCapNhatDuongSu ngayCapNhat(LocalDate ngayCapNhat) {
        this.setNgayCapNhat(ngayCapNhat);
        return this;
    }

    public void setNgayCapNhat(LocalDate ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public LoaiDuongSu getLoaiDuongSu() {
        return this.loaiDuongSu;
    }

    public void setLoaiDuongSu(LoaiDuongSu loaiDuongSu) {
        this.loaiDuongSu = loaiDuongSu;
    }

    public ThongTinCapNhatDuongSu loaiDuongSu(LoaiDuongSu loaiDuongSu) {
        this.setLoaiDuongSu(loaiDuongSu);
        return this;
    }

    public LoaiGiayTo getLoaiGiayTo() {
        return this.loaiGiayTo;
    }

    public void setLoaiGiayTo(LoaiGiayTo loaiGiayTo) {
        this.loaiGiayTo = loaiGiayTo;
    }

    public ThongTinCapNhatDuongSu loaiGiayTo(LoaiGiayTo loaiGiayTo) {
        this.setLoaiGiayTo(loaiGiayTo);
        return this;
    }

    public DuongSu getDuongSu() {
        return this.duongSu;
    }

    public void setDuongSu(DuongSu duongSu) {
        this.duongSu = duongSu;
    }

    public ThongTinCapNhatDuongSu duongSu(DuongSu duongSu) {
        this.setDuongSu(duongSu);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ThongTinCapNhatDuongSu)) {
            return false;
        }
        return getIdCapNhat() != null && getIdCapNhat().equals(((ThongTinCapNhatDuongSu) o).getIdCapNhat());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ThongTinCapNhatDuongSu{" +
            "idCapNhat=" + getIdCapNhat() +
            ", tenDuongSu='" + getTenDuongSu() + "'" +
            ", soGiayTo='" + getSoGiayTo() + "'" +
            ", thongTinDuongSu='" + getThongTinDuongSu() + "'" +
            ", ngayCapNhat='" + getNgayCapNhat() + "'" +
            "}";
    }
}
