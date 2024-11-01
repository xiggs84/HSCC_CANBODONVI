package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhMucDonViCriteriaTest {

    @Test
    void newDanhMucDonViCriteriaHasAllFiltersNullTest() {
        var danhMucDonViCriteria = new DanhMucDonViCriteria();
        assertThat(danhMucDonViCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhMucDonViCriteriaFluentMethodsCreatesFiltersTest() {
        var danhMucDonViCriteria = new DanhMucDonViCriteria();

        setAllFilters(danhMucDonViCriteria);

        assertThat(danhMucDonViCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhMucDonViCriteriaCopyCreatesNullFilterTest() {
        var danhMucDonViCriteria = new DanhMucDonViCriteria();
        var copy = danhMucDonViCriteria.copy();

        assertThat(danhMucDonViCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucDonViCriteria)
        );
    }

    @Test
    void danhMucDonViCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhMucDonViCriteria = new DanhMucDonViCriteria();
        setAllFilters(danhMucDonViCriteria);

        var copy = danhMucDonViCriteria.copy();

        assertThat(danhMucDonViCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucDonViCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhMucDonViCriteria = new DanhMucDonViCriteria();

        assertThat(danhMucDonViCriteria).hasToString("DanhMucDonViCriteria{}");
    }

    private static void setAllFilters(DanhMucDonViCriteria danhMucDonViCriteria) {
        danhMucDonViCriteria.idDonVi();
        danhMucDonViCriteria.tenDonVi();
        danhMucDonViCriteria.diaChi();
        danhMucDonViCriteria.nguoiDaiDien();
        danhMucDonViCriteria.soDienThoai();
        danhMucDonViCriteria.idDonViQl();
        danhMucDonViCriteria.ngayKhaiBao();
        danhMucDonViCriteria.trangThai();
        danhMucDonViCriteria.soNha();
        danhMucDonViCriteria.maSoThue();
        danhMucDonViCriteria.hoaDonDt();
        danhMucDonViCriteria.maDonViIgate();
        danhMucDonViCriteria.maCoQuanIgate();
        danhMucDonViCriteria.kySo();
        danhMucDonViCriteria.qrScan();
        danhMucDonViCriteria.verifyIdCard();
        danhMucDonViCriteria.isVerifyFace();
        danhMucDonViCriteria.isElastic();
        danhMucDonViCriteria.apikeyCccd();
        danhMucDonViCriteria.apikeyFace();
        danhMucDonViCriteria.verifyCodeCccd();
        danhMucDonViCriteria.usernameElastic();
        danhMucDonViCriteria.passwordElastic();
        danhMucDonViCriteria.idNhiemVu();
        danhMucDonViCriteria.idLoaiDv();
        danhMucDonViCriteria.idCapQl();
        danhMucDonViCriteria.canBoChungThucId();
        danhMucDonViCriteria.canBoQuyenId();
        danhMucDonViCriteria.capQuanLyId();
        danhMucDonViCriteria.loaiDonViId();
        danhMucDonViCriteria.nhiemVuId();
        danhMucDonViCriteria.lanhDaoId();
        danhMucDonViCriteria.distinct();
    }

    private static Condition<DanhMucDonViCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdDonVi()) &&
                condition.apply(criteria.getTenDonVi()) &&
                condition.apply(criteria.getDiaChi()) &&
                condition.apply(criteria.getNguoiDaiDien()) &&
                condition.apply(criteria.getSoDienThoai()) &&
                condition.apply(criteria.getIdDonViQl()) &&
                condition.apply(criteria.getNgayKhaiBao()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getSoNha()) &&
                condition.apply(criteria.getMaSoThue()) &&
                condition.apply(criteria.getHoaDonDt()) &&
                condition.apply(criteria.getMaDonViIgate()) &&
                condition.apply(criteria.getMaCoQuanIgate()) &&
                condition.apply(criteria.getKySo()) &&
                condition.apply(criteria.getQrScan()) &&
                condition.apply(criteria.getVerifyIdCard()) &&
                condition.apply(criteria.getIsVerifyFace()) &&
                condition.apply(criteria.getIsElastic()) &&
                condition.apply(criteria.getApikeyCccd()) &&
                condition.apply(criteria.getApikeyFace()) &&
                condition.apply(criteria.getVerifyCodeCccd()) &&
                condition.apply(criteria.getUsernameElastic()) &&
                condition.apply(criteria.getPasswordElastic()) &&
                condition.apply(criteria.getIdNhiemVu()) &&
                condition.apply(criteria.getIdLoaiDv()) &&
                condition.apply(criteria.getIdCapQl()) &&
                condition.apply(criteria.getCanBoChungThucId()) &&
                condition.apply(criteria.getCanBoQuyenId()) &&
                condition.apply(criteria.getCapQuanLyId()) &&
                condition.apply(criteria.getLoaiDonViId()) &&
                condition.apply(criteria.getNhiemVuId()) &&
                condition.apply(criteria.getLanhDaoId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhMucDonViCriteria> copyFiltersAre(
        DanhMucDonViCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdDonVi(), copy.getIdDonVi()) &&
                condition.apply(criteria.getTenDonVi(), copy.getTenDonVi()) &&
                condition.apply(criteria.getDiaChi(), copy.getDiaChi()) &&
                condition.apply(criteria.getNguoiDaiDien(), copy.getNguoiDaiDien()) &&
                condition.apply(criteria.getSoDienThoai(), copy.getSoDienThoai()) &&
                condition.apply(criteria.getIdDonViQl(), copy.getIdDonViQl()) &&
                condition.apply(criteria.getNgayKhaiBao(), copy.getNgayKhaiBao()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getSoNha(), copy.getSoNha()) &&
                condition.apply(criteria.getMaSoThue(), copy.getMaSoThue()) &&
                condition.apply(criteria.getHoaDonDt(), copy.getHoaDonDt()) &&
                condition.apply(criteria.getMaDonViIgate(), copy.getMaDonViIgate()) &&
                condition.apply(criteria.getMaCoQuanIgate(), copy.getMaCoQuanIgate()) &&
                condition.apply(criteria.getKySo(), copy.getKySo()) &&
                condition.apply(criteria.getQrScan(), copy.getQrScan()) &&
                condition.apply(criteria.getVerifyIdCard(), copy.getVerifyIdCard()) &&
                condition.apply(criteria.getIsVerifyFace(), copy.getIsVerifyFace()) &&
                condition.apply(criteria.getIsElastic(), copy.getIsElastic()) &&
                condition.apply(criteria.getApikeyCccd(), copy.getApikeyCccd()) &&
                condition.apply(criteria.getApikeyFace(), copy.getApikeyFace()) &&
                condition.apply(criteria.getVerifyCodeCccd(), copy.getVerifyCodeCccd()) &&
                condition.apply(criteria.getUsernameElastic(), copy.getUsernameElastic()) &&
                condition.apply(criteria.getPasswordElastic(), copy.getPasswordElastic()) &&
                condition.apply(criteria.getIdNhiemVu(), copy.getIdNhiemVu()) &&
                condition.apply(criteria.getIdLoaiDv(), copy.getIdLoaiDv()) &&
                condition.apply(criteria.getIdCapQl(), copy.getIdCapQl()) &&
                condition.apply(criteria.getCanBoChungThucId(), copy.getCanBoChungThucId()) &&
                condition.apply(criteria.getCanBoQuyenId(), copy.getCanBoQuyenId()) &&
                condition.apply(criteria.getCapQuanLyId(), copy.getCapQuanLyId()) &&
                condition.apply(criteria.getLoaiDonViId(), copy.getLoaiDonViId()) &&
                condition.apply(criteria.getNhiemVuId(), copy.getNhiemVuId()) &&
                condition.apply(criteria.getLanhDaoId(), copy.getLanhDaoId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
