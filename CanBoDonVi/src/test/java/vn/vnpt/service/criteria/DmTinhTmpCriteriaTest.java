package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DmTinhTmpCriteriaTest {

    @Test
    void newDmTinhTmpCriteriaHasAllFiltersNullTest() {
        var dmTinhTmpCriteria = new DmTinhTmpCriteria();
        assertThat(dmTinhTmpCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void dmTinhTmpCriteriaFluentMethodsCreatesFiltersTest() {
        var dmTinhTmpCriteria = new DmTinhTmpCriteria();

        setAllFilters(dmTinhTmpCriteria);

        assertThat(dmTinhTmpCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void dmTinhTmpCriteriaCopyCreatesNullFilterTest() {
        var dmTinhTmpCriteria = new DmTinhTmpCriteria();
        var copy = dmTinhTmpCriteria.copy();

        assertThat(dmTinhTmpCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(dmTinhTmpCriteria)
        );
    }

    @Test
    void dmTinhTmpCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dmTinhTmpCriteria = new DmTinhTmpCriteria();
        setAllFilters(dmTinhTmpCriteria);

        var copy = dmTinhTmpCriteria.copy();

        assertThat(dmTinhTmpCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(dmTinhTmpCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dmTinhTmpCriteria = new DmTinhTmpCriteria();

        assertThat(dmTinhTmpCriteria).hasToString("DmTinhTmpCriteria{}");
    }

    private static void setAllFilters(DmTinhTmpCriteria dmTinhTmpCriteria) {
        dmTinhTmpCriteria.maTinh();
        dmTinhTmpCriteria.tenTinh();
        dmTinhTmpCriteria.distinct();
    }

    private static Condition<DmTinhTmpCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaTinh()) && condition.apply(criteria.getTenTinh()) && condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DmTinhTmpCriteria> copyFiltersAre(DmTinhTmpCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaTinh(), copy.getMaTinh()) &&
                condition.apply(criteria.getTenTinh(), copy.getTenTinh()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
