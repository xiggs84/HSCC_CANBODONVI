package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhMucHuyenCriteriaTest {

    @Test
    void newDanhMucHuyenCriteriaHasAllFiltersNullTest() {
        var danhMucHuyenCriteria = new DanhMucHuyenCriteria();
        assertThat(danhMucHuyenCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhMucHuyenCriteriaFluentMethodsCreatesFiltersTest() {
        var danhMucHuyenCriteria = new DanhMucHuyenCriteria();

        setAllFilters(danhMucHuyenCriteria);

        assertThat(danhMucHuyenCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhMucHuyenCriteriaCopyCreatesNullFilterTest() {
        var danhMucHuyenCriteria = new DanhMucHuyenCriteria();
        var copy = danhMucHuyenCriteria.copy();

        assertThat(danhMucHuyenCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucHuyenCriteria)
        );
    }

    @Test
    void danhMucHuyenCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhMucHuyenCriteria = new DanhMucHuyenCriteria();
        setAllFilters(danhMucHuyenCriteria);

        var copy = danhMucHuyenCriteria.copy();

        assertThat(danhMucHuyenCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucHuyenCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhMucHuyenCriteria = new DanhMucHuyenCriteria();

        assertThat(danhMucHuyenCriteria).hasToString("DanhMucHuyenCriteria{}");
    }

    private static void setAllFilters(DanhMucHuyenCriteria danhMucHuyenCriteria) {
        danhMucHuyenCriteria.maHuyen();
        danhMucHuyenCriteria.tenHuyen();
        danhMucHuyenCriteria.maTinh();
        danhMucHuyenCriteria.distinct();
    }

    private static Condition<DanhMucHuyenCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaHuyen()) &&
                condition.apply(criteria.getTenHuyen()) &&
                condition.apply(criteria.getMaTinh()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhMucHuyenCriteria> copyFiltersAre(
        DanhMucHuyenCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaHuyen(), copy.getMaHuyen()) &&
                condition.apply(criteria.getTenHuyen(), copy.getTenHuyen()) &&
                condition.apply(criteria.getMaTinh(), copy.getMaTinh()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
