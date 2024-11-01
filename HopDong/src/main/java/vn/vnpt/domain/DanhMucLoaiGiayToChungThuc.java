package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DanhMucLoaiGiayToChungThuc.
 */
@Document(collection = "danh_muc_loai_giay_to_chung_thuc")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucLoaiGiayToChungThuc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("dien_giai")
    private String dienGiai;

    @Field("id_loai_so")
    private String idLoaiSo;

    @DBRef
    @Field("chungThuc")
    @JsonIgnoreProperties(value = { "danhMucLoaiGiayToChungThuc" }, allowSetters = true)
    private Set<ChungThuc> chungThucs = new HashSet<>();

    @DBRef
    @Field("danhSachChungThuc")
    @JsonIgnoreProperties(value = { "danhMucLoaiGiayToChungThuc" }, allowSetters = true)
    private Set<DanhSachChungThuc> danhSachChungThucs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DanhMucLoaiGiayToChungThuc id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public DanhMucLoaiGiayToChungThuc dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public String getIdLoaiSo() {
        return this.idLoaiSo;
    }

    public DanhMucLoaiGiayToChungThuc idLoaiSo(String idLoaiSo) {
        this.setIdLoaiSo(idLoaiSo);
        return this;
    }

    public void setIdLoaiSo(String idLoaiSo) {
        this.idLoaiSo = idLoaiSo;
    }

    public Set<ChungThuc> getChungThucs() {
        return this.chungThucs;
    }

    public void setChungThucs(Set<ChungThuc> chungThucs) {
        if (this.chungThucs != null) {
            this.chungThucs.forEach(i -> i.setDanhMucLoaiGiayToChungThuc(null));
        }
        if (chungThucs != null) {
            chungThucs.forEach(i -> i.setDanhMucLoaiGiayToChungThuc(this));
        }
        this.chungThucs = chungThucs;
    }

    public DanhMucLoaiGiayToChungThuc chungThucs(Set<ChungThuc> chungThucs) {
        this.setChungThucs(chungThucs);
        return this;
    }

    public DanhMucLoaiGiayToChungThuc addChungThuc(ChungThuc chungThuc) {
        this.chungThucs.add(chungThuc);
        chungThuc.setDanhMucLoaiGiayToChungThuc(this);
        return this;
    }

    public DanhMucLoaiGiayToChungThuc removeChungThuc(ChungThuc chungThuc) {
        this.chungThucs.remove(chungThuc);
        chungThuc.setDanhMucLoaiGiayToChungThuc(null);
        return this;
    }

    public Set<DanhSachChungThuc> getDanhSachChungThucs() {
        return this.danhSachChungThucs;
    }

    public void setDanhSachChungThucs(Set<DanhSachChungThuc> danhSachChungThucs) {
        if (this.danhSachChungThucs != null) {
            this.danhSachChungThucs.forEach(i -> i.setDanhMucLoaiGiayToChungThuc(null));
        }
        if (danhSachChungThucs != null) {
            danhSachChungThucs.forEach(i -> i.setDanhMucLoaiGiayToChungThuc(this));
        }
        this.danhSachChungThucs = danhSachChungThucs;
    }

    public DanhMucLoaiGiayToChungThuc danhSachChungThucs(Set<DanhSachChungThuc> danhSachChungThucs) {
        this.setDanhSachChungThucs(danhSachChungThucs);
        return this;
    }

    public DanhMucLoaiGiayToChungThuc addDanhSachChungThuc(DanhSachChungThuc danhSachChungThuc) {
        this.danhSachChungThucs.add(danhSachChungThuc);
        danhSachChungThuc.setDanhMucLoaiGiayToChungThuc(this);
        return this;
    }

    public DanhMucLoaiGiayToChungThuc removeDanhSachChungThuc(DanhSachChungThuc danhSachChungThuc) {
        this.danhSachChungThucs.remove(danhSachChungThuc);
        danhSachChungThuc.setDanhMucLoaiGiayToChungThuc(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucLoaiGiayToChungThuc)) {
            return false;
        }
        return getId() != null && getId().equals(((DanhMucLoaiGiayToChungThuc) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucLoaiGiayToChungThuc{" +
            "id=" + getId() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", idLoaiSo='" + getIdLoaiSo() + "'" +
            "}";
    }
}
