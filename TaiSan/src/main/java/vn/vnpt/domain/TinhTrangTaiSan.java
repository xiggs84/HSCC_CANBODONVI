package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TinhTrangTaiSan.
 */
@Entity
@Table(name = "tinh_trang_tai_san")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TinhTrangTaiSan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_tinh_trang")
    private Long idTinhTrang;

    @Column(name = "dien_giai")
    private String dienGiai;

    @Column(name = "trang_thai")
    private Long trangThai;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tinhTrangTaiSan")
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
    private Set<TaiSan> tinhTrangs = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tinhTrangTaiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "taiSan", "danhMucLoaiTaiSan", "tinhTrangTaiSan" }, allowSetters = true)
    private Set<TaiSanDgc> taiSanDgcs = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tinhTrangTaiSan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "danhMucLoaiTaiSan", "tinhTrangTaiSan" }, allowSetters = true)
    private Set<TaiSanDatNha> taiSanDatNhas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdTinhTrang() {
        return this.idTinhTrang;
    }

    public TinhTrangTaiSan idTinhTrang(Long idTinhTrang) {
        this.setIdTinhTrang(idTinhTrang);
        return this;
    }

    public void setIdTinhTrang(Long idTinhTrang) {
        this.idTinhTrang = idTinhTrang;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public TinhTrangTaiSan dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public TinhTrangTaiSan trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public Set<TaiSan> getTinhTrangs() {
        return this.tinhTrangs;
    }

    public void setTinhTrangs(Set<TaiSan> taiSans) {
        if (this.tinhTrangs != null) {
            this.tinhTrangs.forEach(i -> i.setTinhTrangTaiSan(null));
        }
        if (taiSans != null) {
            taiSans.forEach(i -> i.setTinhTrangTaiSan(this));
        }
        this.tinhTrangs = taiSans;
    }

    public TinhTrangTaiSan tinhTrangs(Set<TaiSan> taiSans) {
        this.setTinhTrangs(taiSans);
        return this;
    }

    public TinhTrangTaiSan addTinhTrang(TaiSan taiSan) {
        this.tinhTrangs.add(taiSan);
        taiSan.setTinhTrangTaiSan(this);
        return this;
    }

    public TinhTrangTaiSan removeTinhTrang(TaiSan taiSan) {
        this.tinhTrangs.remove(taiSan);
        taiSan.setTinhTrangTaiSan(null);
        return this;
    }

    public Set<TaiSanDgc> getTaiSanDgcs() {
        return this.taiSanDgcs;
    }

    public void setTaiSanDgcs(Set<TaiSanDgc> taiSanDgcs) {
        if (this.taiSanDgcs != null) {
            this.taiSanDgcs.forEach(i -> i.setTinhTrangTaiSan(null));
        }
        if (taiSanDgcs != null) {
            taiSanDgcs.forEach(i -> i.setTinhTrangTaiSan(this));
        }
        this.taiSanDgcs = taiSanDgcs;
    }

    public TinhTrangTaiSan taiSanDgcs(Set<TaiSanDgc> taiSanDgcs) {
        this.setTaiSanDgcs(taiSanDgcs);
        return this;
    }

    public TinhTrangTaiSan addTaiSanDgc(TaiSanDgc taiSanDgc) {
        this.taiSanDgcs.add(taiSanDgc);
        taiSanDgc.setTinhTrangTaiSan(this);
        return this;
    }

    public TinhTrangTaiSan removeTaiSanDgc(TaiSanDgc taiSanDgc) {
        this.taiSanDgcs.remove(taiSanDgc);
        taiSanDgc.setTinhTrangTaiSan(null);
        return this;
    }

    public Set<TaiSanDatNha> getTaiSanDatNhas() {
        return this.taiSanDatNhas;
    }

    public void setTaiSanDatNhas(Set<TaiSanDatNha> taiSanDatNhas) {
        if (this.taiSanDatNhas != null) {
            this.taiSanDatNhas.forEach(i -> i.setTinhTrangTaiSan(null));
        }
        if (taiSanDatNhas != null) {
            taiSanDatNhas.forEach(i -> i.setTinhTrangTaiSan(this));
        }
        this.taiSanDatNhas = taiSanDatNhas;
    }

    public TinhTrangTaiSan taiSanDatNhas(Set<TaiSanDatNha> taiSanDatNhas) {
        this.setTaiSanDatNhas(taiSanDatNhas);
        return this;
    }

    public TinhTrangTaiSan addTaiSanDatNha(TaiSanDatNha taiSanDatNha) {
        this.taiSanDatNhas.add(taiSanDatNha);
        taiSanDatNha.setTinhTrangTaiSan(this);
        return this;
    }

    public TinhTrangTaiSan removeTaiSanDatNha(TaiSanDatNha taiSanDatNha) {
        this.taiSanDatNhas.remove(taiSanDatNha);
        taiSanDatNha.setTinhTrangTaiSan(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TinhTrangTaiSan)) {
            return false;
        }
        return getIdTinhTrang() != null && getIdTinhTrang().equals(((TinhTrangTaiSan) o).getIdTinhTrang());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TinhTrangTaiSan{" +
            "idTinhTrang=" + getIdTinhTrang() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
