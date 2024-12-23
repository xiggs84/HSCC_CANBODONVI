package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhMucXaCriteriaTest {

    @Test
    void newDanhMucXaCriteriaHasAllFiltersNullTest() {
        var danhMucXaCriteria = new DanhMucXaCriteria();
        assertThat(danhMucXaCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhMucXaCriteriaFluentMethodsCreatesFiltersTest() {
        var danhMucXaCriteria = new DanhMucXaCriteria();

        setAllFilters(danhMucXaCriteria);

        assertThat(danhMucXaCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhMucXaCriteriaCopyCreatesNullFilterTest() {
        var danhMucXaCriteria = new DanhMucXaCriteria();
        var copy = danhMucXaCriteria.copy();

        assertThat(danhMucXaCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucXaCriteria)
        );
    }

    @Test
    void danhMucXaCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhMucXaCriteria = new DanhMucXaCriteria();
        setAllFilters(danhMucXaCriteria);

        var copy = danhMucXaCriteria.copy();

        assertThat(danhMucXaCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucXaCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhMucXaCriteria = new DanhMucXaCriteria();

        assertThat(danhMucXaCriteria).hasToString("DanhMucXaCriteria{}");
    }

    private static void setAllFilters(DanhMucXaCriteria danhMucXaCriteria) {
        danhMucXaCriteria.maXa();
        danhMucXaCriteria.tenXa();
        danhMucXaCriteria.maHuyen();
        danhMucXaCriteria.distinct();
    }

    private static Condition<DanhMucXaCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaXa()) &&
                condition.apply(criteria.getTenXa()) &&
                condition.apply(criteria.getMaHuyen()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhMucXaCriteria> copyFiltersAre(DanhMucXaCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaXa(), copy.getMaXa()) &&
                condition.apply(criteria.getTenXa(), copy.getTenXa()) &&
                condition.apply(criteria.getMaHuyen(), copy.getMaHuyen()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
