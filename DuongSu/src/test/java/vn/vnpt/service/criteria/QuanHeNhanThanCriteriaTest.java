package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class QuanHeNhanThanCriteriaTest {

    @Test
    void newQuanHeNhanThanCriteriaHasAllFiltersNullTest() {
        var quanHeNhanThanCriteria = new QuanHeNhanThanCriteria();
        assertThat(quanHeNhanThanCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void quanHeNhanThanCriteriaFluentMethodsCreatesFiltersTest() {
        var quanHeNhanThanCriteria = new QuanHeNhanThanCriteria();

        setAllFilters(quanHeNhanThanCriteria);

        assertThat(quanHeNhanThanCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void quanHeNhanThanCriteriaCopyCreatesNullFilterTest() {
        var quanHeNhanThanCriteria = new QuanHeNhanThanCriteria();
        var copy = quanHeNhanThanCriteria.copy();

        assertThat(quanHeNhanThanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(quanHeNhanThanCriteria)
        );
    }

    @Test
    void quanHeNhanThanCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var quanHeNhanThanCriteria = new QuanHeNhanThanCriteria();
        setAllFilters(quanHeNhanThanCriteria);

        var copy = quanHeNhanThanCriteria.copy();

        assertThat(quanHeNhanThanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(quanHeNhanThanCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var quanHeNhanThanCriteria = new QuanHeNhanThanCriteria();

        assertThat(quanHeNhanThanCriteria).hasToString("QuanHeNhanThanCriteria{}");
    }

    private static void setAllFilters(QuanHeNhanThanCriteria quanHeNhanThanCriteria) {
        quanHeNhanThanCriteria.idQuanHe();
        quanHeNhanThanCriteria.dienGiai();
        quanHeNhanThanCriteria.idQuanHeDoiUng();
        quanHeNhanThanCriteria.distinct();
    }

    private static Condition<QuanHeNhanThanCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdQuanHe()) &&
                condition.apply(criteria.getDienGiai()) &&
                condition.apply(criteria.getIdQuanHeDoiUng()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<QuanHeNhanThanCriteria> copyFiltersAre(
        QuanHeNhanThanCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdQuanHe(), copy.getIdQuanHe()) &&
                condition.apply(criteria.getDienGiai(), copy.getDienGiai()) &&
                condition.apply(criteria.getIdQuanHeDoiUng(), copy.getIdQuanHeDoiUng()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
