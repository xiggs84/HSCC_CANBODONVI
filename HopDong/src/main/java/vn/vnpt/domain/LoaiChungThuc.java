package vn.vnpt.domain;

import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A LoaiChungThuc.
 */
@Document(collection = "loai_chung_thuc")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LoaiChungThuc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("dien_giai")
    private String dienGiai;

    @Field("khung_gia")
    private Long khungGia;

    @Field("has_ben_b")
    private Long hasBenB;

    @Field("has_tai_san")
    private Long hasTaiSan;

    @Field("trang_thai")
    private Long trangThai;

    @Field("file_chung_thuc")
    private String fileChungThuc;

    @Field("src_chung_thuc")
    private byte[] srcChungThuc;

    @Field("src_chung_thuc_content_type")
    private String srcChungThucContentType;

    @Field("ngay_thao_tac")
    private LocalDate ngayThaoTac;

    @Field("nguoi_thao_tac")
    private Long nguoiThaoTac;

    @Field("id_don_vi")
    private Long idDonVi;

    @Field("id_loai_so")
    private String idLoaiSo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public LoaiChungThuc id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDienGiai() {
        return this.dienGiai;
    }

    public LoaiChungThuc dienGiai(String dienGiai) {
        this.setDienGiai(dienGiai);
        return this;
    }

    public void setDienGiai(String dienGiai) {
        this.dienGiai = dienGiai;
    }

    public Long getKhungGia() {
        return this.khungGia;
    }

    public LoaiChungThuc khungGia(Long khungGia) {
        this.setKhungGia(khungGia);
        return this;
    }

    public void setKhungGia(Long khungGia) {
        this.khungGia = khungGia;
    }

    public Long getHasBenB() {
        return this.hasBenB;
    }

    public LoaiChungThuc hasBenB(Long hasBenB) {
        this.setHasBenB(hasBenB);
        return this;
    }

    public void setHasBenB(Long hasBenB) {
        this.hasBenB = hasBenB;
    }

    public Long getHasTaiSan() {
        return this.hasTaiSan;
    }

    public LoaiChungThuc hasTaiSan(Long hasTaiSan) {
        this.setHasTaiSan(hasTaiSan);
        return this;
    }

    public void setHasTaiSan(Long hasTaiSan) {
        this.hasTaiSan = hasTaiSan;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public LoaiChungThuc trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getFileChungThuc() {
        return this.fileChungThuc;
    }

    public LoaiChungThuc fileChungThuc(String fileChungThuc) {
        this.setFileChungThuc(fileChungThuc);
        return this;
    }

    public void setFileChungThuc(String fileChungThuc) {
        this.fileChungThuc = fileChungThuc;
    }

    public byte[] getSrcChungThuc() {
        return this.srcChungThuc;
    }

    public LoaiChungThuc srcChungThuc(byte[] srcChungThuc) {
        this.setSrcChungThuc(srcChungThuc);
        return this;
    }

    public void setSrcChungThuc(byte[] srcChungThuc) {
        this.srcChungThuc = srcChungThuc;
    }

    public String getSrcChungThucContentType() {
        return this.srcChungThucContentType;
    }

    public LoaiChungThuc srcChungThucContentType(String srcChungThucContentType) {
        this.srcChungThucContentType = srcChungThucContentType;
        return this;
    }

    public void setSrcChungThucContentType(String srcChungThucContentType) {
        this.srcChungThucContentType = srcChungThucContentType;
    }

    public LocalDate getNgayThaoTac() {
        return this.ngayThaoTac;
    }

    public LoaiChungThuc ngayThaoTac(LocalDate ngayThaoTac) {
        this.setNgayThaoTac(ngayThaoTac);
        return this;
    }

    public void setNgayThaoTac(LocalDate ngayThaoTac) {
        this.ngayThaoTac = ngayThaoTac;
    }

    public Long getNguoiThaoTac() {
        return this.nguoiThaoTac;
    }

    public LoaiChungThuc nguoiThaoTac(Long nguoiThaoTac) {
        this.setNguoiThaoTac(nguoiThaoTac);
        return this;
    }

    public void setNguoiThaoTac(Long nguoiThaoTac) {
        this.nguoiThaoTac = nguoiThaoTac;
    }

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public LoaiChungThuc idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getIdLoaiSo() {
        return this.idLoaiSo;
    }

    public LoaiChungThuc idLoaiSo(String idLoaiSo) {
        this.setIdLoaiSo(idLoaiSo);
        return this;
    }

    public void setIdLoaiSo(String idLoaiSo) {
        this.idLoaiSo = idLoaiSo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LoaiChungThuc)) {
            return false;
        }
        return getId() != null && getId().equals(((LoaiChungThuc) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoaiChungThuc{" +
            "id=" + getId() +
            ", dienGiai='" + getDienGiai() + "'" +
            ", khungGia=" + getKhungGia() +
            ", hasBenB=" + getHasBenB() +
            ", hasTaiSan=" + getHasTaiSan() +
            ", trangThai=" + getTrangThai() +
            ", fileChungThuc='" + getFileChungThuc() + "'" +
            ", srcChungThuc='" + getSrcChungThuc() + "'" +
            ", srcChungThucContentType='" + getSrcChungThucContentType() + "'" +
            ", ngayThaoTac='" + getNgayThaoTac() + "'" +
            ", nguoiThaoTac=" + getNguoiThaoTac() +
            ", idDonVi=" + getIdDonVi() +
            ", idLoaiSo='" + getIdLoaiSo() + "'" +
            "}";
    }
}
