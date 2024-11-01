package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DmXaTmpCriteriaTest {

    @Test
    void newDmXaTmpCriteriaHasAllFiltersNullTest() {
        var dmXaTmpCriteria = new DmXaTmpCriteria();
        assertThat(dmXaTmpCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void dmXaTmpCriteriaFluentMethodsCreatesFiltersTest() {
        var dmXaTmpCriteria = new DmXaTmpCriteria();

        setAllFilters(dmXaTmpCriteria);

        assertThat(dmXaTmpCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void dmXaTmpCriteriaCopyCreatesNullFilterTest() {
        var dmXaTmpCriteria = new DmXaTmpCriteria();
        var copy = dmXaTmpCriteria.copy();

        assertThat(dmXaTmpCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(dmXaTmpCriteria)
        );
    }

    @Test
    void dmXaTmpCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dmXaTmpCriteria = new DmXaTmpCriteria();
        setAllFilters(dmXaTmpCriteria);

        var copy = dmXaTmpCriteria.copy();

        assertThat(dmXaTmpCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(dmXaTmpCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dmXaTmpCriteria = new DmXaTmpCriteria();

        assertThat(dmXaTmpCriteria).hasToString("DmXaTmpCriteria{}");
    }

    private static void setAllFilters(DmXaTmpCriteria dmXaTmpCriteria) {
        dmXaTmpCriteria.maXa();
        dmXaTmpCriteria.tenXa();
        dmXaTmpCriteria.distinct();
    }

    private static Condition<DmXaTmpCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaXa()) && condition.apply(criteria.getTenXa()) && condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DmXaTmpCriteria> copyFiltersAre(DmXaTmpCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaXa(), copy.getMaXa()) &&
                condition.apply(criteria.getTenXa(), copy.getTenXa()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
