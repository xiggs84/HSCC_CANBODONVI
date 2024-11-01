package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class QuanHeMasterCriteriaTest {

    @Test
    void newQuanHeMasterCriteriaHasAllFiltersNullTest() {
        var quanHeMasterCriteria = new QuanHeMasterCriteria();
        assertThat(quanHeMasterCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void quanHeMasterCriteriaFluentMethodsCreatesFiltersTest() {
        var quanHeMasterCriteria = new QuanHeMasterCriteria();

        setAllFilters(quanHeMasterCriteria);

        assertThat(quanHeMasterCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void quanHeMasterCriteriaCopyCreatesNullFilterTest() {
        var quanHeMasterCriteria = new QuanHeMasterCriteria();
        var copy = quanHeMasterCriteria.copy();

        assertThat(quanHeMasterCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(quanHeMasterCriteria)
        );
    }

    @Test
    void quanHeMasterCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var quanHeMasterCriteria = new QuanHeMasterCriteria();
        setAllFilters(quanHeMasterCriteria);

        var copy = quanHeMasterCriteria.copy();

        assertThat(quanHeMasterCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(quanHeMasterCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var quanHeMasterCriteria = new QuanHeMasterCriteria();

        assertThat(quanHeMasterCriteria).hasToString("QuanHeMasterCriteria{}");
    }

    private static void setAllFilters(QuanHeMasterCriteria quanHeMasterCriteria) {
        quanHeMasterCriteria.id();
        quanHeMasterCriteria.idDuongSu();
        quanHeMasterCriteria.idDuongSuQh();
        quanHeMasterCriteria.distinct();
    }

    private static Condition<QuanHeMasterCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdDuongSu()) &&
                condition.apply(criteria.getIdDuongSuQh()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<QuanHeMasterCriteria> copyFiltersAre(
        QuanHeMasterCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdDuongSu(), copy.getIdDuongSu()) &&
                condition.apply(criteria.getIdDuongSuQh(), copy.getIdDuongSuQh()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
