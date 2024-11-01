package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class ThongTinCapNhatDuongSuCriteriaTest {

    @Test
    void newThongTinCapNhatDuongSuCriteriaHasAllFiltersNullTest() {
        var thongTinCapNhatDuongSuCriteria = new ThongTinCapNhatDuongSuCriteria();
        assertThat(thongTinCapNhatDuongSuCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void thongTinCapNhatDuongSuCriteriaFluentMethodsCreatesFiltersTest() {
        var thongTinCapNhatDuongSuCriteria = new ThongTinCapNhatDuongSuCriteria();

        setAllFilters(thongTinCapNhatDuongSuCriteria);

        assertThat(thongTinCapNhatDuongSuCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void thongTinCapNhatDuongSuCriteriaCopyCreatesNullFilterTest() {
        var thongTinCapNhatDuongSuCriteria = new ThongTinCapNhatDuongSuCriteria();
        var copy = thongTinCapNhatDuongSuCriteria.copy();

        assertThat(thongTinCapNhatDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(thongTinCapNhatDuongSuCriteria)
        );
    }

    @Test
    void thongTinCapNhatDuongSuCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var thongTinCapNhatDuongSuCriteria = new ThongTinCapNhatDuongSuCriteria();
        setAllFilters(thongTinCapNhatDuongSuCriteria);

        var copy = thongTinCapNhatDuongSuCriteria.copy();

        assertThat(thongTinCapNhatDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(thongTinCapNhatDuongSuCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var thongTinCapNhatDuongSuCriteria = new ThongTinCapNhatDuongSuCriteria();

        assertThat(thongTinCapNhatDuongSuCriteria).hasToString("ThongTinCapNhatDuongSuCriteria{}");
    }

    private static void setAllFilters(ThongTinCapNhatDuongSuCriteria thongTinCapNhatDuongSuCriteria) {
        thongTinCapNhatDuongSuCriteria.idCapNhat();
        thongTinCapNhatDuongSuCriteria.tenDuongSu();
        thongTinCapNhatDuongSuCriteria.soGiayTo();
        thongTinCapNhatDuongSuCriteria.ngayCapNhat();
        thongTinCapNhatDuongSuCriteria.loaiDuongSuId();
        thongTinCapNhatDuongSuCriteria.loaiGiayToId();
        thongTinCapNhatDuongSuCriteria.duongSuId();
        thongTinCapNhatDuongSuCriteria.distinct();
    }

    private static Condition<ThongTinCapNhatDuongSuCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCapNhat()) &&
                condition.apply(criteria.getTenDuongSu()) &&
                condition.apply(criteria.getSoGiayTo()) &&
                condition.apply(criteria.getNgayCapNhat()) &&
                condition.apply(criteria.getLoaiDuongSuId()) &&
                condition.apply(criteria.getLoaiGiayToId()) &&
                condition.apply(criteria.getDuongSuId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<ThongTinCapNhatDuongSuCriteria> copyFiltersAre(
        ThongTinCapNhatDuongSuCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCapNhat(), copy.getIdCapNhat()) &&
                condition.apply(criteria.getTenDuongSu(), copy.getTenDuongSu()) &&
                condition.apply(criteria.getSoGiayTo(), copy.getSoGiayTo()) &&
                condition.apply(criteria.getNgayCapNhat(), copy.getNgayCapNhat()) &&
                condition.apply(criteria.getLoaiDuongSuId(), copy.getLoaiDuongSuId()) &&
                condition.apply(criteria.getLoaiGiayToId(), copy.getLoaiGiayToId()) &&
                condition.apply(criteria.getDuongSuId(), copy.getDuongSuId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
