package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class NoiCapGtttCriteriaTest {

    @Test
    void newNoiCapGtttCriteriaHasAllFiltersNullTest() {
        var noiCapGtttCriteria = new NoiCapGtttCriteria();
        assertThat(noiCapGtttCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void noiCapGtttCriteriaFluentMethodsCreatesFiltersTest() {
        var noiCapGtttCriteria = new NoiCapGtttCriteria();

        setAllFilters(noiCapGtttCriteria);

        assertThat(noiCapGtttCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void noiCapGtttCriteriaCopyCreatesNullFilterTest() {
        var noiCapGtttCriteria = new NoiCapGtttCriteria();
        var copy = noiCapGtttCriteria.copy();

        assertThat(noiCapGtttCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(noiCapGtttCriteria)
        );
    }

    @Test
    void noiCapGtttCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var noiCapGtttCriteria = new NoiCapGtttCriteria();
        setAllFilters(noiCapGtttCriteria);

        var copy = noiCapGtttCriteria.copy();

        assertThat(noiCapGtttCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(noiCapGtttCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var noiCapGtttCriteria = new NoiCapGtttCriteria();

        assertThat(noiCapGtttCriteria).hasToString("NoiCapGtttCriteria{}");
    }

    private static void setAllFilters(NoiCapGtttCriteria noiCapGtttCriteria) {
        noiCapGtttCriteria.idNoiCap();
        noiCapGtttCriteria.dienGiai();
        noiCapGtttCriteria.trangThai();
        noiCapGtttCriteria.distinct();
    }

    private static Condition<NoiCapGtttCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdNoiCap()) &&
                condition.apply(criteria.getDienGiai()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<NoiCapGtttCriteria> copyFiltersAre(NoiCapGtttCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdNoiCap(), copy.getIdNoiCap()) &&
                condition.apply(criteria.getDienGiai(), copy.getDienGiai()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
