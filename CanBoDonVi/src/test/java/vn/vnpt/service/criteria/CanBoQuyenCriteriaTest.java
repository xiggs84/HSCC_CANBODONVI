package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CanBoQuyenCriteriaTest {

    @Test
    void newCanBoQuyenCriteriaHasAllFiltersNullTest() {
        var canBoQuyenCriteria = new CanBoQuyenCriteria();
        assertThat(canBoQuyenCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void canBoQuyenCriteriaFluentMethodsCreatesFiltersTest() {
        var canBoQuyenCriteria = new CanBoQuyenCriteria();

        setAllFilters(canBoQuyenCriteria);

        assertThat(canBoQuyenCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void canBoQuyenCriteriaCopyCreatesNullFilterTest() {
        var canBoQuyenCriteria = new CanBoQuyenCriteria();
        var copy = canBoQuyenCriteria.copy();

        assertThat(canBoQuyenCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(canBoQuyenCriteria)
        );
    }

    @Test
    void canBoQuyenCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var canBoQuyenCriteria = new CanBoQuyenCriteria();
        setAllFilters(canBoQuyenCriteria);

        var copy = canBoQuyenCriteria.copy();

        assertThat(canBoQuyenCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(canBoQuyenCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var canBoQuyenCriteria = new CanBoQuyenCriteria();

        assertThat(canBoQuyenCriteria).hasToString("CanBoQuyenCriteria{}");
    }

    private static void setAllFilters(CanBoQuyenCriteria canBoQuyenCriteria) {
        canBoQuyenCriteria.id();
        canBoQuyenCriteria.danhMucDonViId();
        canBoQuyenCriteria.distinct();
    }

    private static Condition<CanBoQuyenCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getDanhMucDonViId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<CanBoQuyenCriteria> copyFiltersAre(CanBoQuyenCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getDanhMucDonViId(), copy.getDanhMucDonViId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
