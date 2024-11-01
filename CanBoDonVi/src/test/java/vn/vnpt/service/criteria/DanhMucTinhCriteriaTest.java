package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhMucTinhCriteriaTest {

    @Test
    void newDanhMucTinhCriteriaHasAllFiltersNullTest() {
        var danhMucTinhCriteria = new DanhMucTinhCriteria();
        assertThat(danhMucTinhCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhMucTinhCriteriaFluentMethodsCreatesFiltersTest() {
        var danhMucTinhCriteria = new DanhMucTinhCriteria();

        setAllFilters(danhMucTinhCriteria);

        assertThat(danhMucTinhCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhMucTinhCriteriaCopyCreatesNullFilterTest() {
        var danhMucTinhCriteria = new DanhMucTinhCriteria();
        var copy = danhMucTinhCriteria.copy();

        assertThat(danhMucTinhCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucTinhCriteria)
        );
    }

    @Test
    void danhMucTinhCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhMucTinhCriteria = new DanhMucTinhCriteria();
        setAllFilters(danhMucTinhCriteria);

        var copy = danhMucTinhCriteria.copy();

        assertThat(danhMucTinhCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucTinhCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhMucTinhCriteria = new DanhMucTinhCriteria();

        assertThat(danhMucTinhCriteria).hasToString("DanhMucTinhCriteria{}");
    }

    private static void setAllFilters(DanhMucTinhCriteria danhMucTinhCriteria) {
        danhMucTinhCriteria.maTinh();
        danhMucTinhCriteria.tenTinh();
        danhMucTinhCriteria.distinct();
    }

    private static Condition<DanhMucTinhCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaTinh()) && condition.apply(criteria.getTenTinh()) && condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhMucTinhCriteria> copyFiltersAre(DanhMucTinhCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaTinh(), copy.getMaTinh()) &&
                condition.apply(criteria.getTenTinh(), copy.getTenTinh()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
