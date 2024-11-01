package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhMucLoaiTaiSan.
 */
@Entity
@Table(name = "danh_muc_loai_tai_san")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiTaiSan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_loai_ts")
    private Long idLoaiTs;

    @Column(name = "dien_giai")
    private String dienGiai;

    @Column(name = "trang_thai")
    private Long trangThai;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "danhMucLoaiTaiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    private Set<TaiSan> loaiTaiSans = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "danhMucLoaiTaiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "danhMucLoaiTaiSan" }, allowSetters = true)
    private Set<DanhSachTaiSan> danhSachTaiSans = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "danhMucLoaiTaiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taiSan", "danhMucLoaiTaiSan", "tinhTrangTaiSan" }, allowSetters = true)
    private Set<TaiSanDgc> taiSanDgcs = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "danhMucLoaiTaiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "danhMucLoaiTaiSan", "tinhTrangTaiSan" }, allowSetters = true)
    private Set<TaiSanDatNha> taiSanDatNhas = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "danhMucLoaiTaiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taiSan", "danhMucLoaiTaiSan" }, allowSetters = true)
    private Set<ThongTinCapNhatTaiSan> thongTinCapNhatTaiSans = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdLoaiTs() {
        return this.idLoaiTs;
    }

    public DanhMucLoaiTaiSan idLoaiTs(Long idLoaiTs) {
        this.setIdLoaiTs(idLoaiTs);
        return this;
    }

    public void setIdLoaiTs(Long idLoaiTs) {
        this.idLoaiTs = idLoaiTs;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DanhMucLoaiTaiSan dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DanhMucLoaiTaiSan trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Set<TaiSan> getLoaiTaiSans() {
        return this.loaiTaiSans;
    }

    public void setLoaiTaiSans(Set<TaiSan> taiSans) {
        if (this.loaiTaiSans != null) {
            this.loaiTaiSans.forEach(i -> i.setDanhMucLoaiTaiSan(null));
        }
        if (taiSans != null) {
            taiSans.forEach(i -> i.setDanhMucLoaiTaiSan(this));
        }
        this.loaiTaiSans = taiSans;
    }

    public DanhMucLoaiTaiSan loaiTaiSans(Set<TaiSan> taiSans) {
        this.setLoaiTaiSans(taiSans);
        return this;
    }

    public DanhMucLoaiTaiSan addLoaiTaiSan(TaiSan taiSan) {
        this.loaiTaiSans.add(taiSan);
        taiSan.setDanhMucLoaiTaiSan(this);
        return this;
    }

    public DanhMucLoaiTaiSan removeLoaiTaiSan(TaiSan taiSan) {
        this.loaiTaiSans.remove(taiSan);
        taiSan.setDanhMucLoaiTaiSan(null);
        return this;
    }

    public Set<DanhSachTaiSan> getDanhSachTaiSans() {
        return this.danhSachTaiSans;
    }

    public void setDanhSachTaiSans(Set<DanhSachTaiSan> danhSachTaiSans) {
        if (this.danhSachTaiSans != null) {
            this.danhSachTaiSans.forEach(i -> i.setDanhMucLoaiTaiSan(null));
        }
        if (danhSachTaiSans != null) {
            danhSachTaiSans.forEach(i -> i.setDanhMucLoaiTaiSan(this));
        }
        this.danhSachTaiSans = danhSachTaiSans;
    }

    public DanhMucLoaiTaiSan danhSachTaiSans(Set<DanhSachTaiSan> danhSachTaiSans) {
        this.setDanhSachTaiSans(danhSachTaiSans);
        return this;
    }

    public DanhMucLoaiTaiSan addDanhSachTaiSan(DanhSachTaiSan danhSachTaiSan) {
        this.danhSachTaiSans.add(danhSachTaiSan);
        danhSachTaiSan.setDanhMucLoaiTaiSan(this);
        return this;
    }

    public DanhMucLoaiTaiSan removeDanhSachTaiSan(DanhSachTaiSan danhSachTaiSan) {
        this.danhSachTaiSans.remove(danhSachTaiSan);
        danhSachTaiSan.setDanhMucLoaiTaiSan(null);
        return this;
    }

    public Set<TaiSanDgc> getTaiSanDgcs() {
        return this.taiSanDgcs;
    }

    public void setTaiSanDgcs(Set<TaiSanDgc> taiSanDgcs) {
        if (this.taiSanDgcs != null) {
            this.taiSanDgcs.forEach(i -> i.setDanhMucLoaiTaiSan(null));
        }
        if (taiSanDgcs != null) {
            taiSanDgcs.forEach(i -> i.setDanhMucLoaiTaiSan(this));
        }
        this.taiSanDgcs = taiSanDgcs;
    }

    public DanhMucLoaiTaiSan taiSanDgcs(Set<TaiSanDgc> taiSanDgcs) {
        this.setTaiSanDgcs(taiSanDgcs);
        return this;
    }

    public DanhMucLoaiTaiSan addTaiSanDgc(TaiSanDgc taiSanDgc) {
        this.taiSanDgcs.add(taiSanDgc);
        taiSanDgc.setDanhMucLoaiTaiSan(this);
        return this;
    }

    public DanhMucLoaiTaiSan removeTaiSanDgc(TaiSanDgc taiSanDgc) {
        this.taiSanDgcs.remove(taiSanDgc);
        taiSanDgc.setDanhMucLoaiTaiSan(null);
        return this;
    }

    public Set<TaiSanDatNha> getTaiSanDatNhas() {
        return this.taiSanDatNhas;
    }

    public void setTaiSanDatNhas(Set<TaiSanDatNha> taiSanDatNhas) {
        if (this.taiSanDatNhas != null) {
            this.taiSanDatNhas.forEach(i -> i.setDanhMucLoaiTaiSan(null));
        }
        if (taiSanDatNhas != null) {
            taiSanDatNhas.forEach(i -> i.setDanhMucLoaiTaiSan(this));
        }
        this.taiSanDatNhas = taiSanDatNhas;
    }

    public DanhMucLoaiTaiSan taiSanDatNhas(Set<TaiSanDatNha> taiSanDatNhas) {
        this.setTaiSanDatNhas(taiSanDatNhas);
        return this;
    }

    public DanhMucLoaiTaiSan addTaiSanDatNha(TaiSanDatNha taiSanDatNha) {
        this.taiSanDatNhas.add(taiSanDatNha);
        taiSanDatNha.setDanhMucLoaiTaiSan(this);
        return this;
    }

    public DanhMucLoaiTaiSan removeTaiSanDatNha(TaiSanDatNha taiSanDatNha) {
        this.taiSanDatNhas.remove(taiSanDatNha);
        taiSanDatNha.setDanhMucLoaiTaiSan(null);
        return this;
    }

    public Set<ThongTinCapNhatTaiSan> getThongTinCapNhatTaiSans() {
        return this.thongTinCapNhatTaiSans;
    }

    public void setThongTinCapNhatTaiSans(Set<ThongTinCapNhatTaiSan> thongTinCapNhatTaiSans) {
        if (this.thongTinCapNhatTaiSans != null) {
            this.thongTinCapNhatTaiSans.forEach(i -> i.setDanhMucLoaiTaiSan(null));
        }
        if (thongTinCapNhatTaiSans != null) {
            thongTinCapNhatTaiSans.forEach(i -> i.setDanhMucLoaiTaiSan(this));
        }
        this.thongTinCapNhatTaiSans = thongTinCapNhatTaiSans;
    }

    public DanhMucLoaiTaiSan thongTinCapNhatTaiSans(Set<ThongTinCapNhatTaiSan> thongTinCapNhatTaiSans) {
        this.setThongTinCapNhatTaiSans(thongTinCapNhatTaiSans);
        return this;
    }

    public DanhMucLoaiTaiSan addThongTinCapNhatTaiSan(ThongTinCapNhatTaiSan thongTinCapNhatTaiSan) {
        this.thongTinCapNhatTaiSans.add(thongTinCapNhatTaiSan);
        thongTinCapNhatTaiSan.setDanhMucLoaiTaiSan(this);
        return this;
    }

    public DanhMucLoaiTaiSan removeThongTinCapNhatTaiSan(ThongTinCapNhatTaiSan thongTinCapNhatTaiSan) {
        this.thongTinCapNhatTaiSans.remove(thongTinCapNhatTaiSan);
        thongTinCapNhatTaiSan.setDanhMucLoaiTaiSan(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucLoaiTaiSan)) {
            return false;
        }
        return getIdLoaiTs() != null && getIdLoaiTs().equals(((DanhMucLoaiTaiSan) o).getIdLoaiTs());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiTaiSan{" +
            "idLoaiTs=" + getIdLoaiTs() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
