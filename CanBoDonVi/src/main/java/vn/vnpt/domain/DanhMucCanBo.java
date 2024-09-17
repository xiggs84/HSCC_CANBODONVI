package vn.vnpt.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhMucCanBo.
 */
@Entity
@Table(name = "danh_muc_can_bo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucCanBo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_can_bo")
    private Long idCanBo;

    @Column(name = "ten_can_bo")
    private String tenCanBo;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "nam_sinh")
    private LocalDate namSinh;

    @Column(name = "email")
    private String email;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "so_cmnd")
    private String soCmnd;

    @Column(name = "ten_dang_nhap")
    private String tenDangNhap;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "trang_thai")
    private Long trangThai;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "username_kyso")
    private String usernameKyso;

    @Column(name = "password_kyso")
    private String passwordKyso;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdCanBo() {
        return this.idCanBo;
    }

    public DanhMucCanBo idCanBo(Long idCanBo) {
        this.setIdCanBo(idCanBo);
        return this;
    }

    public void setIdCanBo(Long idCanBo) {
        this.idCanBo = idCanBo;
    }

    public String getTenCanBo() {
        return this.tenCanBo;
    }

    public DanhMucCanBo tenCanBo(String tenCanBo) {
        this.setTenCanBo(tenCanBo);
        return this;
    }

    public void setTenCanBo(String tenCanBo) {
        this.tenCanBo = tenCanBo;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public DanhMucCanBo diaChi(String diaChi) {
        this.setDiaChi(diaChi);
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public LocalDate getNamSinh() {
        return this.namSinh;
    }

    public DanhMucCanBo namSinh(LocalDate namSinh) {
        this.setNamSinh(namSinh);
        return this;
    }

    public void setNamSinh(LocalDate namSinh) {
        this.namSinh = namSinh;
    }

    public String getEmail() {
        return this.email;
    }

    public DanhMucCanBo email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public DanhMucCanBo soDienThoai(String soDienThoai) {
        this.setSoDienThoai(soDienThoai);
        return this;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getSoCmnd() {
        return this.soCmnd;
    }

    public DanhMucCanBo soCmnd(String soCmnd) {
        this.setSoCmnd(soCmnd);
        return this;
    }

    public void setSoCmnd(String soCmnd) {
        this.soCmnd = soCmnd;
    }

    public String getTenDangNhap() {
        return this.tenDangNhap;
    }

    public DanhMucCanBo tenDangNhap(String tenDangNhap) {
        this.setTenDangNhap(tenDangNhap);
        return this;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return this.matKhau;
    }

    public DanhMucCanBo matKhau(String matKhau) {
        this.setMatKhau(matKhau);
        return this;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DanhMucCanBo trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getClientId() {
        return this.clientId;
    }

    public DanhMucCanBo clientId(String clientId) {
        this.setClientId(clientId);
        return this;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public DanhMucCanBo clientSecret(String clientSecret) {
        this.setClientSecret(clientSecret);
        return this;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getUsernameKyso() {
        return this.usernameKyso;
    }

    public DanhMucCanBo usernameKyso(String usernameKyso) {
        this.setUsernameKyso(usernameKyso);
        return this;
    }

    public void setUsernameKyso(String usernameKyso) {
        this.usernameKyso = usernameKyso;
    }

    public String getPasswordKyso() {
        return this.passwordKyso;
    }

    public DanhMucCanBo passwordKyso(String passwordKyso) {
        this.setPasswordKyso(passwordKyso);
        return this;
    }

    public void setPasswordKyso(String passwordKyso) {
        this.passwordKyso = passwordKyso;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucCanBo)) {
            return false;
        }
        return getIdCanBo() != null && getIdCanBo().equals(((DanhMucCanBo) o).getIdCanBo());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucCanBo{" +
            "idCanBo=" + getIdCanBo() +
            ", tenCanBo='" + getTenCanBo() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", namSinh='" + getNamSinh() + "'" +
            ", email='" + getEmail() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", soCmnd='" + getSoCmnd() + "'" +
            ", tenDangNhap='" + getTenDangNhap() + "'" +
            ", matKhau='" + getMatKhau() + "'" +
            ", trangThai=" + getTrangThai() +
            ", clientId='" + getClientId() + "'" +
            ", clientSecret='" + getClientSecret() + "'" +
            ", usernameKyso='" + getUsernameKyso() + "'" +
            ", passwordKyso='" + getPasswordKyso() + "'" +
            "}";
    }
}
