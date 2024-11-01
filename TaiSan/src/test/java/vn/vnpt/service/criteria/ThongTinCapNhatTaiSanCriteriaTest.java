package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class ThongTinCapNhatTaiSanCriteriaTest {

    @Test
    void newThongTinCapNhatTaiSanCriteriaHasAllFiltersNullTest() {
        var thongTinCapNhatTaiSanCriteria = new ThongTinCapNhatTaiSanCriteria();
        assertThat(thongTinCapNhatTaiSanCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void thongTinCapNhatTaiSanCriteriaFluentMethodsCreatesFiltersTest() {
        var thongTinCapNhatTaiSanCriteria = new ThongTinCapNhatTaiSanCriteria();

        setAllFilters(thongTinCapNhatTaiSanCriteria);

        assertThat(thongTinCapNhatTaiSanCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void thongTinCapNhatTaiSanCriteriaCopyCreatesNullFilterTest() {
        var thongTinCapNhatTaiSanCriteria = new ThongTinCapNhatTaiSanCriteria();
        var copy = thongTinCapNhatTaiSanCriteria.copy();

        assertThat(thongTinCapNhatTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(thongTinCapNhatTaiSanCriteria)
        );
    }

    @Test
    void thongTinCapNhatTaiSanCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var thongTinCapNhatTaiSanCriteria = new ThongTinCapNhatTaiSanCriteria();
        setAllFilters(thongTinCapNhatTaiSanCriteria);

        var copy = thongTinCapNhatTaiSanCriteria.copy();

        assertThat(thongTinCapNhatTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(thongTinCapNhatTaiSanCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var thongTinCapNhatTaiSanCriteria = new ThongTinCapNhatTaiSanCriteria();

        assertThat(thongTinCapNhatTaiSanCriteria).hasToString("ThongTinCapNhatTaiSanCriteria{}");
    }

    private static void setAllFilters(ThongTinCapNhatTaiSanCriteria thongTinCapNhatTaiSanCriteria) {
        thongTinCapNhatTaiSanCriteria.idCapNhat();
        thongTinCapNhatTaiSanCriteria.tenTaiSan();
        thongTinCapNhatTaiSanCriteria.ngayCapNhat();
        thongTinCapNhatTaiSanCriteria.taiSanId();
        thongTinCapNhatTaiSanCriteria.danhMucLoaiTaiSanId();
        thongTinCapNhatTaiSanCriteria.distinct();
    }

    private static Condition<ThongTinCapNhatTaiSanCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCapNhat()) &&
                condition.apply(criteria.getTenTaiSan()) &&
                condition.apply(criteria.getNgayCapNhat()) &&
                condition.apply(criteria.getTaiSanId()) &&
                condition.apply(criteria.getDanhMucLoaiTaiSanId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<ThongTinCapNhatTaiSanCriteria> copyFiltersAre(
        ThongTinCapNhatTaiSanCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCapNhat(), copy.getIdCapNhat()) &&
                condition.apply(criteria.getTenTaiSan(), copy.getTenTaiSan()) &&
                condition.apply(criteria.getNgayCapNhat(), copy.getNgayCapNhat()) &&
                condition.apply(criteria.getTaiSanId(), copy.getTaiSanId()) &&
                condition.apply(criteria.getDanhMucLoaiTaiSanId(), copy.getDanhMucLoaiTaiSanId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
