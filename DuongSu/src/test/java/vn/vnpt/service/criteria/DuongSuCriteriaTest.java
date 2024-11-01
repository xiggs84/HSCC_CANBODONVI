package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DuongSuCriteriaTest {

    @Test
    void newDuongSuCriteriaHasAllFiltersNullTest() {
        var duongSuCriteria = new DuongSuCriteria();
        assertThat(duongSuCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void duongSuCriteriaFluentMethodsCreatesFiltersTest() {
        var duongSuCriteria = new DuongSuCriteria();

        setAllFilters(duongSuCriteria);

        assertThat(duongSuCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void duongSuCriteriaCopyCreatesNullFilterTest() {
        var duongSuCriteria = new DuongSuCriteria();
        var copy = duongSuCriteria.copy();

        assertThat(duongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(duongSuCriteria)
        );
    }

    @Test
    void duongSuCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var duongSuCriteria = new DuongSuCriteria();
        setAllFilters(duongSuCriteria);

        var copy = duongSuCriteria.copy();

        assertThat(duongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(duongSuCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var duongSuCriteria = new DuongSuCriteria();

        assertThat(duongSuCriteria).hasToString("DuongSuCriteria{}");
    }

    private static void setAllFilters(DuongSuCriteria duongSuCriteria) {
        duongSuCriteria.idDuongSu();
        duongSuCriteria.tenDuongSu();
        duongSuCriteria.diaChi();
        duongSuCriteria.soDienThoai();
        duongSuCriteria.email();
        duongSuCriteria.fax();
        duongSuCriteria.website();
        duongSuCriteria.trangThai();
        duongSuCriteria.ngayThaoTac();
        duongSuCriteria.nguoiThaoTac();
        duongSuCriteria.idDsGoc();
        duongSuCriteria.idMaster();
        duongSuCriteria.idDonVi();
        duongSuCriteria.strSearch();
        duongSuCriteria.soGiayTo();
        duongSuCriteria.ghiChu();
        duongSuCriteria.idLoaiNganChan();
        duongSuCriteria.syncStatus();
        duongSuCriteria.thongTinCapNhatId();
        duongSuCriteria.taiSanDuongSuId();
        duongSuCriteria.quanHeDuongSuId();
        duongSuCriteria.danhSachDuongSuId();
        duongSuCriteria.duongSuTrungCmndId();
        duongSuCriteria.duongSuTrungCmndBakId();
        duongSuCriteria.loaiDuongSuId();
        duongSuCriteria.loaiGiayToId();
        duongSuCriteria.distinct();
    }

    private static Condition<DuongSuCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdDuongSu()) &&
                condition.apply(criteria.getTenDuongSu()) &&
                condition.apply(criteria.getDiaChi()) &&
                condition.apply(criteria.getSoDienThoai()) &&
                condition.apply(criteria.getEmail()) &&
                condition.apply(criteria.getFax()) &&
                condition.apply(criteria.getWebsite()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getNgayThaoTac()) &&
                condition.apply(criteria.getNguoiThaoTac()) &&
                condition.apply(criteria.getIdDsGoc()) &&
                condition.apply(criteria.getIdMaster()) &&
                condition.apply(criteria.getIdDonVi()) &&
                condition.apply(criteria.getStrSearch()) &&
                condition.apply(criteria.getSoGiayTo()) &&
                condition.apply(criteria.getGhiChu()) &&
                condition.apply(criteria.getIdLoaiNganChan()) &&
                condition.apply(criteria.getSyncStatus()) &&
                condition.apply(criteria.getThongTinCapNhatId()) &&
                condition.apply(criteria.getTaiSanDuongSuId()) &&
                condition.apply(criteria.getQuanHeDuongSuId()) &&
                condition.apply(criteria.getDanhSachDuongSuId()) &&
                condition.apply(criteria.getDuongSuTrungCmndId()) &&
                condition.apply(criteria.getDuongSuTrungCmndBakId()) &&
                condition.apply(criteria.getLoaiDuongSuId()) &&
                condition.apply(criteria.getLoaiGiayToId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DuongSuCriteria> copyFiltersAre(DuongSuCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdDuongSu(), copy.getIdDuongSu()) &&
                condition.apply(criteria.getTenDuongSu(), copy.getTenDuongSu()) &&
                condition.apply(criteria.getDiaChi(), copy.getDiaChi()) &&
                condition.apply(criteria.getSoDienThoai(), copy.getSoDienThoai()) &&
                condition.apply(criteria.getEmail(), copy.getEmail()) &&
                condition.apply(criteria.getFax(), copy.getFax()) &&
                condition.apply(criteria.getWebsite(), copy.getWebsite()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getNgayThaoTac(), copy.getNgayThaoTac()) &&
                condition.apply(criteria.getNguoiThaoTac(), copy.getNguoiThaoTac()) &&
                condition.apply(criteria.getIdDsGoc(), copy.getIdDsGoc()) &&
                condition.apply(criteria.getIdMaster(), copy.getIdMaster()) &&
                condition.apply(criteria.getIdDonVi(), copy.getIdDonVi()) &&
                condition.apply(criteria.getStrSearch(), copy.getStrSearch()) &&
                condition.apply(criteria.getSoGiayTo(), copy.getSoGiayTo()) &&
                condition.apply(criteria.getGhiChu(), copy.getGhiChu()) &&
                condition.apply(criteria.getIdLoaiNganChan(), copy.getIdLoaiNganChan()) &&
                condition.apply(criteria.getSyncStatus(), copy.getSyncStatus()) &&
                condition.apply(criteria.getThongTinCapNhatId(), copy.getThongTinCapNhatId()) &&
                condition.apply(criteria.getTaiSanDuongSuId(), copy.getTaiSanDuongSuId()) &&
                condition.apply(criteria.getQuanHeDuongSuId(), copy.getQuanHeDuongSuId()) &&
                condition.apply(criteria.getDanhSachDuongSuId(), copy.getDanhSachDuongSuId()) &&
                condition.apply(criteria.getDuongSuTrungCmndId(), copy.getDuongSuTrungCmndId()) &&
                condition.apply(criteria.getDuongSuTrungCmndBakId(), copy.getDuongSuTrungCmndBakId()) &&
                condition.apply(criteria.getLoaiDuongSuId(), copy.getLoaiDuongSuId()) &&
                condition.apply(criteria.getLoaiGiayToId(), copy.getLoaiGiayToId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
