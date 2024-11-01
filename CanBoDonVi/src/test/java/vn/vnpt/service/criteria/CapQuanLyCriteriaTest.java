package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CapQuanLyCriteriaTest {

    @Test
    void newCapQuanLyCriteriaHasAllFiltersNullTest() {
        var capQuanLyCriteria = new CapQuanLyCriteria();
        assertThat(capQuanLyCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void capQuanLyCriteriaFluentMethodsCreatesFiltersTest() {
        var capQuanLyCriteria = new CapQuanLyCriteria();

        setAllFilters(capQuanLyCriteria);

        assertThat(capQuanLyCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void capQuanLyCriteriaCopyCreatesNullFilterTest() {
        var capQuanLyCriteria = new CapQuanLyCriteria();
        var copy = capQuanLyCriteria.copy();

        assertThat(capQuanLyCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(capQuanLyCriteria)
        );
    }

    @Test
    void capQuanLyCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var capQuanLyCriteria = new CapQuanLyCriteria();
        setAllFilters(capQuanLyCriteria);

        var copy = capQuanLyCriteria.copy();

        assertThat(capQuanLyCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(capQuanLyCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var capQuanLyCriteria = new CapQuanLyCriteria();

        assertThat(capQuanLyCriteria).hasToString("CapQuanLyCriteria{}");
    }

    private static void setAllFilters(CapQuanLyCriteria capQuanLyCriteria) {
        capQuanLyCriteria.idCapQl();
        capQuanLyCriteria.tenCapQl();
        capQuanLyCriteria.idCapQlId();
        capQuanLyCriteria.distinct();
    }

    private static Condition<CapQuanLyCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCapQl()) &&
                condition.apply(criteria.getTenCapQl()) &&
                condition.apply(criteria.getIdCapQlId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<CapQuanLyCriteria> copyFiltersAre(CapQuanLyCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCapQl(), copy.getIdCapQl()) &&
                condition.apply(criteria.getTenCapQl(), copy.getTenCapQl()) &&
                condition.apply(criteria.getIdCapQlId(), copy.getIdCapQlId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
