package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhMucLoaiTaiSanCriteriaTest {

    @Test
    void newDanhMucLoaiTaiSanCriteriaHasAllFiltersNullTest() {
        var danhMucLoaiTaiSanCriteria = new DanhMucLoaiTaiSanCriteria();
        assertThat(danhMucLoaiTaiSanCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhMucLoaiTaiSanCriteriaFluentMethodsCreatesFiltersTest() {
        var danhMucLoaiTaiSanCriteria = new DanhMucLoaiTaiSanCriteria();

        setAllFilters(danhMucLoaiTaiSanCriteria);

        assertThat(danhMucLoaiTaiSanCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhMucLoaiTaiSanCriteriaCopyCreatesNullFilterTest() {
        var danhMucLoaiTaiSanCriteria = new DanhMucLoaiTaiSanCriteria();
        var copy = danhMucLoaiTaiSanCriteria.copy();

        assertThat(danhMucLoaiTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucLoaiTaiSanCriteria)
        );
    }

    @Test
    void danhMucLoaiTaiSanCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhMucLoaiTaiSanCriteria = new DanhMucLoaiTaiSanCriteria();
        setAllFilters(danhMucLoaiTaiSanCriteria);

        var copy = danhMucLoaiTaiSanCriteria.copy();

        assertThat(danhMucLoaiTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucLoaiTaiSanCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhMucLoaiTaiSanCriteria = new DanhMucLoaiTaiSanCriteria();

        assertThat(danhMucLoaiTaiSanCriteria).hasToString("DanhMucLoaiTaiSanCriteria{}");
    }

    private static void setAllFilters(DanhMucLoaiTaiSanCriteria danhMucLoaiTaiSanCriteria) {
        danhMucLoaiTaiSanCriteria.idLoaiTs();
        danhMucLoaiTaiSanCriteria.dienGiai();
        danhMucLoaiTaiSanCriteria.trangThai();
        danhMucLoaiTaiSanCriteria.loaiTaiSanId();
        danhMucLoaiTaiSanCriteria.danhSachTaiSanId();
        danhMucLoaiTaiSanCriteria.taiSanDgcId();
        danhMucLoaiTaiSanCriteria.taiSanDatNhaId();
        danhMucLoaiTaiSanCriteria.thongTinCapNhatTaiSanId();
        danhMucLoaiTaiSanCriteria.distinct();
    }

    private static Condition<DanhMucLoaiTaiSanCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdLoaiTs()) &&
                condition.apply(criteria.getDienGiai()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getLoaiTaiSanId()) &&
                condition.apply(criteria.getDanhSachTaiSanId()) &&
                condition.apply(criteria.getTaiSanDgcId()) &&
                condition.apply(criteria.getTaiSanDatNhaId()) &&
                condition.apply(criteria.getThongTinCapNhatTaiSanId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhMucLoaiTaiSanCriteria> copyFiltersAre(
        DanhMucLoaiTaiSanCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdLoaiTs(), copy.getIdLoaiTs()) &&
                condition.apply(criteria.getDienGiai(), copy.getDienGiai()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getLoaiTaiSanId(), copy.getLoaiTaiSanId()) &&
                condition.apply(criteria.getDanhSachTaiSanId(), copy.getDanhSachTaiSanId()) &&
                condition.apply(criteria.getTaiSanDgcId(), copy.getTaiSanDgcId()) &&
                condition.apply(criteria.getTaiSanDatNhaId(), copy.getTaiSanDatNhaId()) &&
                condition.apply(criteria.getThongTinCapNhatTaiSanId(), copy.getThongTinCapNhatTaiSanId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
