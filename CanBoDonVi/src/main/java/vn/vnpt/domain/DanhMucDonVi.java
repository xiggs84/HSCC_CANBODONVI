package vn.vnpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DanhMucDonVi.
 */
@Entity
@Table(name = "danh_muc_don_vi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DanhMucDonVi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id_don_vi")
    private Long idDonVi;

    @Column(name = "ten_don_vi")
    private String tenDonVi;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "nguoi_dai_dien")
    private String nguoiDaiDien;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "id_don_vi_ql")
    private Long idDonViQl;

    @Column(name = "ngay_khai_bao")
    private LocalDate ngayKhaiBao;

    @Column(name = "trang_thai")
    private Long trangThai;

    @Column(name = "so_nha")
    private String soNha;

    @Column(name = "ma_so_thue")
    private String maSoThue;

    @Column(name = "hoa_don_dt")
    private Integer hoaDonDt;

    @Column(name = "ma_don_vi_igate")
    private String maDonViIgate;

    @Column(name = "ma_co_quan_igate")
    private String maCoQuanIgate;

    @Column(name = "ky_so")
    private Long kySo;

    @Column(name = "qr_scan")
    private Long qrScan;

    @Column(name = "verify_id_card")
    private Long verifyIdCard;

    @Column(name = "is_verify_face")
    private Long isVerifyFace;

    @Column(name = "is_elastic")
    private Long isElastic;

    @Column(name = "apikey_cccd")
    private String apikeyCccd;

    @Column(name = "apikey_face")
    private String apikeyFace;

    @Column(name = "verify_code_cccd")
    private String verifyCodeCccd;

    @Column(name = "username_elastic")
    private String usernameElastic;

    @Column(name = "password_elastic")
    private String passwordElastic;

    @Column(name = "id_nhiem_vu")
    private String idNhiemVu;

    @Column(name = "id_loai_dv")
    private String idLoaiDv;

    @Column(name = "id_cap_ql")
    private String idCapQl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "danhMucDonVi")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "danhMucDonVi" }, allowSetters = true)
    private Set<CanBoChungThuc> canBoChungThucs = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "danhMucDonVi")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "danhMucDonVi" }, allowSetters = true)
    private Set<CanBoQuyen> canBoQuyens = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "idCapQls" }, allowSetters = true)
    private CapQuanLy capQuanLy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "idLoaiDvs" }, allowSetters = true)
    private LoaiDonVi loaiDonVi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "idNhiemVus" }, allowSetters = true)
    private NhiemVu nhiemVu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "danhMucDonVi")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "danhMucDonVi" }, allowSetters = true)
    private Set<LanhDao> lanhDaos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getIdDonVi() {
        return this.idDonVi;
    }

    public DanhMucDonVi idDonVi(Long idDonVi) {
        this.setIdDonVi(idDonVi);
        return this;
    }

    public void setIdDonVi(Long idDonVi) {
        this.idDonVi = idDonVi;
    }

    public String getTenDonVi() {
        return this.tenDonVi;
    }

    public DanhMucDonVi tenDonVi(String tenDonVi) {
        this.setTenDonVi(tenDonVi);
        return this;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public DanhMucDonVi diaChi(String diaChi) {
        this.setDiaChi(diaChi);
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNguoiDaiDien() {
        return this.nguoiDaiDien;
    }

    public DanhMucDonVi nguoiDaiDien(String nguoiDaiDien) {
        this.setNguoiDaiDien(nguoiDaiDien);
        return this;
    }

    public void setNguoiDaiDien(String nguoiDaiDien) {
        this.nguoiDaiDien = nguoiDaiDien;
    }

    public String getSoDienThoai() {
        return this.soDienThoai;
    }

    public DanhMucDonVi soDienThoai(String soDienThoai) {
        this.setSoDienThoai(soDienThoai);
        return this;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Long getIdDonViQl() {
        return this.idDonViQl;
    }

    public DanhMucDonVi idDonViQl(Long idDonViQl) {
        this.setIdDonViQl(idDonViQl);
        return this;
    }

    public void setIdDonViQl(Long idDonViQl) {
        this.idDonViQl = idDonViQl;
    }

    public LocalDate getNgayKhaiBao() {
        return this.ngayKhaiBao;
    }

    public DanhMucDonVi ngayKhaiBao(LocalDate ngayKhaiBao) {
        this.setNgayKhaiBao(ngayKhaiBao);
        return this;
    }

    public void setNgayKhaiBao(LocalDate ngayKhaiBao) {
        this.ngayKhaiBao = ngayKhaiBao;
    }

    public Long getTrangThai() {
        return this.trangThai;
    }

    public DanhMucDonVi trangThai(Long trangThai) {
        this.setTrangThai(trangThai);
        return this;
    }

    public void setTrangThai(Long trangThai) {
        this.trangThai = trangThai;
    }

    public String getSoNha() {
        return this.soNha;
    }

    public DanhMucDonVi soNha(String soNha) {
        this.setSoNha(soNha);
        return this;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getMaSoThue() {
        return this.maSoThue;
    }

    public DanhMucDonVi maSoThue(String maSoThue) {
        this.setMaSoThue(maSoThue);
        return this;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public Integer getHoaDonDt() {
        return this.hoaDonDt;
    }

    public DanhMucDonVi hoaDonDt(Integer hoaDonDt) {
        this.setHoaDonDt(hoaDonDt);
        return this;
    }

    public void setHoaDonDt(Integer hoaDonDt) {
        this.hoaDonDt = hoaDonDt;
    }

    public String getMaDonViIgate() {
        return this.maDonViIgate;
    }

    public DanhMucDonVi maDonViIgate(String maDonViIgate) {
        this.setMaDonViIgate(maDonViIgate);
        return this;
    }

    public void setMaDonViIgate(String maDonViIgate) {
        this.maDonViIgate = maDonViIgate;
    }

    public String getMaCoQuanIgate() {
        return this.maCoQuanIgate;
    }

    public DanhMucDonVi maCoQuanIgate(String maCoQuanIgate) {
        this.setMaCoQuanIgate(maCoQuanIgate);
        return this;
    }

    public void setMaCoQuanIgate(String maCoQuanIgate) {
        this.maCoQuanIgate = maCoQuanIgate;
    }

    public Long getKySo() {
        return this.kySo;
    }

    public DanhMucDonVi kySo(Long kySo) {
        this.setKySo(kySo);
        return this;
    }

    public void setKySo(Long kySo) {
        this.kySo = kySo;
    }

    public Long getQrScan() {
        return this.qrScan;
    }

    public DanhMucDonVi qrScan(Long qrScan) {
        this.setQrScan(qrScan);
        return this;
    }

    public void setQrScan(Long qrScan) {
        this.qrScan = qrScan;
    }

    public Long getVerifyIdCard() {
        return this.verifyIdCard;
    }

    public DanhMucDonVi verifyIdCard(Long verifyIdCard) {
        this.setVerifyIdCard(verifyIdCard);
        return this;
    }

    public void setVerifyIdCard(Long verifyIdCard) {
        this.verifyIdCard = verifyIdCard;
    }

    public Long getIsVerifyFace() {
        return this.isVerifyFace;
    }

    public DanhMucDonVi isVerifyFace(Long isVerifyFace) {
        this.setIsVerifyFace(isVerifyFace);
        return this;
    }

    public void setIsVerifyFace(Long isVerifyFace) {
        this.isVerifyFace = isVerifyFace;
    }

    public Long getIsElastic() {
        return this.isElastic;
    }

    public DanhMucDonVi isElastic(Long isElastic) {
        this.setIsElastic(isElastic);
        return this;
    }

    public void setIsElastic(Long isElastic) {
        this.isElastic = isElastic;
    }

    public String getApikeyCccd() {
        return this.apikeyCccd;
    }

    public DanhMucDonVi apikeyCccd(String apikeyCccd) {
        this.setApikeyCccd(apikeyCccd);
        return this;
    }

    public void setApikeyCccd(String apikeyCccd) {
        this.apikeyCccd = apikeyCccd;
    }

    public String getApikeyFace() {
        return this.apikeyFace;
    }

    public DanhMucDonVi apikeyFace(String apikeyFace) {
        this.setApikeyFace(apikeyFace);
        return this;
    }

    public void setApikeyFace(String apikeyFace) {
        this.apikeyFace = apikeyFace;
    }

    public String getVerifyCodeCccd() {
        return this.verifyCodeCccd;
    }

    public DanhMucDonVi verifyCodeCccd(String verifyCodeCccd) {
        this.setVerifyCodeCccd(verifyCodeCccd);
        return this;
    }

    public void setVerifyCodeCccd(String verifyCodeCccd) {
        this.verifyCodeCccd = verifyCodeCccd;
    }

    public String getUsernameElastic() {
        return this.usernameElastic;
    }

    public DanhMucDonVi usernameElastic(String usernameElastic) {
        this.setUsernameElastic(usernameElastic);
        return this;
    }

    public void setUsernameElastic(String usernameElastic) {
        this.usernameElastic = usernameElastic;
    }

    public String getPasswordElastic() {
        return this.passwordElastic;
    }

    public DanhMucDonVi passwordElastic(String passwordElastic) {
        this.setPasswordElastic(passwordElastic);
        return this;
    }

    public void setPasswordElastic(String passwordElastic) {
        this.passwordElastic = passwordElastic;
    }

    public String getIdNhiemVu() {
        return this.idNhiemVu;
    }

    public DanhMucDonVi idNhiemVu(String idNhiemVu) {
        this.setIdNhiemVu(idNhiemVu);
        return this;
    }

    public void setIdNhiemVu(String idNhiemVu) {
        this.idNhiemVu = idNhiemVu;
    }

    public String getIdLoaiDv() {
        return this.idLoaiDv;
    }

    public DanhMucDonVi idLoaiDv(String idLoaiDv) {
        this.setIdLoaiDv(idLoaiDv);
        return this;
    }

    public void setIdLoaiDv(String idLoaiDv) {
        this.idLoaiDv = idLoaiDv;
    }

    public String getIdCapQl() {
        return this.idCapQl;
    }

    public DanhMucDonVi idCapQl(String idCapQl) {
        this.setIdCapQl(idCapQl);
        return this;
    }

    public void setIdCapQl(String idCapQl) {
        this.idCapQl = idCapQl;
    }

    public Set<CanBoChungThuc> getCanBoChungThucs() {
        return this.canBoChungThucs;
    }

    public void setCanBoChungThucs(Set<CanBoChungThuc> canBoChungThucs) {
        if (this.canBoChungThucs != null) {
            this.canBoChungThucs.forEach(i -> i.setDanhMucDonVi(null));
        }
        if (canBoChungThucs != null) {
            canBoChungThucs.forEach(i -> i.setDanhMucDonVi(this));
        }
        this.canBoChungThucs = canBoChungThucs;
    }

    public DanhMucDonVi canBoChungThucs(Set<CanBoChungThuc> canBoChungThucs) {
        this.setCanBoChungThucs(canBoChungThucs);
        return this;
    }

    public DanhMucDonVi addCanBoChungThuc(CanBoChungThuc canBoChungThuc) {
        this.canBoChungThucs.add(canBoChungThuc);
        canBoChungThuc.setDanhMucDonVi(this);
        return this;
    }

    public DanhMucDonVi removeCanBoChungThuc(CanBoChungThuc canBoChungThuc) {
        this.canBoChungThucs.remove(canBoChungThuc);
        canBoChungThuc.setDanhMucDonVi(null);
        return this;
    }

    public Set<CanBoQuyen> getCanBoQuyens() {
        return this.canBoQuyens;
    }

    public void setCanBoQuyens(Set<CanBoQuyen> canBoQuyens) {
        if (this.canBoQuyens != null) {
            this.canBoQuyens.forEach(i -> i.setDanhMucDonVi(null));
        }
        if (canBoQuyens != null) {
            canBoQuyens.forEach(i -> i.setDanhMucDonVi(this));
        }
        this.canBoQuyens = canBoQuyens;
    }

    public DanhMucDonVi canBoQuyens(Set<CanBoQuyen> canBoQuyens) {
        this.setCanBoQuyens(canBoQuyens);
        return this;
    }

    public DanhMucDonVi addCanBoQuyen(CanBoQuyen canBoQuyen) {
        this.canBoQuyens.add(canBoQuyen);
        canBoQuyen.setDanhMucDonVi(this);
        return this;
    }

    public DanhMucDonVi removeCanBoQuyen(CanBoQuyen canBoQuyen) {
        this.canBoQuyens.remove(canBoQuyen);
        canBoQuyen.setDanhMucDonVi(null);
        return this;
    }

    public CapQuanLy getCapQuanLy() {
        return this.capQuanLy;
    }

    public void setCapQuanLy(CapQuanLy capQuanLy) {
        this.capQuanLy = capQuanLy;
    }

    public DanhMucDonVi capQuanLy(CapQuanLy capQuanLy) {
        this.setCapQuanLy(capQuanLy);
        return this;
    }

    public LoaiDonVi getLoaiDonVi() {
        return this.loaiDonVi;
    }

    public void setLoaiDonVi(LoaiDonVi loaiDonVi) {
        this.loaiDonVi = loaiDonVi;
    }

    public DanhMucDonVi loaiDonVi(LoaiDonVi loaiDonVi) {
        this.setLoaiDonVi(loaiDonVi);
        return this;
    }

    public NhiemVu getNhiemVu() {
        return this.nhiemVu;
    }

    public void setNhiemVu(NhiemVu nhiemVu) {
        this.nhiemVu = nhiemVu;
    }

    public DanhMucDonVi nhiemVu(NhiemVu nhiemVu) {
        this.setNhiemVu(nhiemVu);
        return this;
    }

    public Set<LanhDao> getLanhDaos() {
        return this.lanhDaos;
    }

    public void setLanhDaos(Set<LanhDao> lanhDaos) {
        if (this.lanhDaos != null) {
            this.lanhDaos.forEach(i -> i.setDanhMucDonVi(null));
        }
        if (lanhDaos != null) {
            lanhDaos.forEach(i -> i.setDanhMucDonVi(this));
        }
        this.lanhDaos = lanhDaos;
    }

    public DanhMucDonVi lanhDaos(Set<LanhDao> lanhDaos) {
        this.setLanhDaos(lanhDaos);
        return this;
    }

    public DanhMucDonVi addLanhDao(LanhDao lanhDao) {
        this.lanhDaos.add(lanhDao);
        lanhDao.setDanhMucDonVi(this);
        return this;
    }

    public DanhMucDonVi removeLanhDao(LanhDao lanhDao) {
        this.lanhDaos.remove(lanhDao);
        lanhDao.setDanhMucDonVi(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DanhMucDonVi)) {
            return false;
        }
        return getIdDonVi() != null && getIdDonVi().equals(((DanhMucDonVi) o).getIdDonVi());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DanhMucDonVi{" +
            "idDonVi=" + getIdDonVi() +
            ", tenDonVi='" + getTenDonVi() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", nguoiDaiDien='" + getNguoiDaiDien() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", idDonViQl=" + getIdDonViQl() +
            ", ngayKhaiBao='" + getNgayKhaiBao() + "'" +
            ", trangThai=" + getTrangThai() +
            ", soNha='" + getSoNha() + "'" +
            ", maSoThue='" + getMaSoThue() + "'" +
            ", hoaDonDt=" + getHoaDonDt() +
            ", maDonViIgate='" + getMaDonViIgate() + "'" +
            ", maCoQuanIgate='" + getMaCoQuanIgate() + "'" +
            ", kySo=" + getKySo() +
            ", qrScan=" + getQrScan() +
            ", verifyIdCard=" + getVerifyIdCard() +
            ", isVerifyFace=" + getIsVerifyFace() +
            ", isElastic=" + getIsElastic() +
            ", apikeyCccd='" + getApikeyCccd() + "'" +
            ", apikeyFace='" + getApikeyFace() + "'" +
            ", verifyCodeCccd='" + getVerifyCodeCccd() + "'" +
            ", usernameElastic='" + getUsernameElastic() + "'" +
            ", passwordElastic='" + getPasswordElastic() + "'" +
            ", idNhiemVu='" + getIdNhiemVu() + "'" +
            ", idLoaiDv='" + getIdLoaiDv() + "'" +
            ", idCapQl='" + getIdCapQl() + "'" +
            "}";
    }
}
