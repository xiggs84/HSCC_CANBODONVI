package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class QuanHeDuongSuCriteriaTest {

    @Test
    void newQuanHeDuongSuCriteriaHasAllFiltersNullTest() {
        var quanHeDuongSuCriteria = new QuanHeDuongSuCriteria();
        assertThat(quanHeDuongSuCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void quanHeDuongSuCriteriaFluentMethodsCreatesFiltersTest() {
        var quanHeDuongSuCriteria = new QuanHeDuongSuCriteria();

        setAllFilters(quanHeDuongSuCriteria);

        assertThat(quanHeDuongSuCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void quanHeDuongSuCriteriaCopyCreatesNullFilterTest() {
        var quanHeDuongSuCriteria = new QuanHeDuongSuCriteria();
        var copy = quanHeDuongSuCriteria.copy();

        assertThat(quanHeDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(quanHeDuongSuCriteria)
        );
    }

    @Test
    void quanHeDuongSuCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var quanHeDuongSuCriteria = new QuanHeDuongSuCriteria();
        setAllFilters(quanHeDuongSuCriteria);

        var copy = quanHeDuongSuCriteria.copy();

        assertThat(quanHeDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(quanHeDuongSuCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var quanHeDuongSuCriteria = new QuanHeDuongSuCriteria();

        assertThat(quanHeDuongSuCriteria).hasToString("QuanHeDuongSuCriteria{}");
    }

    private static void setAllFilters(QuanHeDuongSuCriteria quanHeDuongSuCriteria) {
        quanHeDuongSuCriteria.idQuanHe();
        quanHeDuongSuCriteria.idDuongSuQh();
        quanHeDuongSuCriteria.trangThai();
        quanHeDuongSuCriteria.duongSuId();
        quanHeDuongSuCriteria.distinct();
    }

    private static Condition<QuanHeDuongSuCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdQuanHe()) &&
                condition.apply(criteria.getIdDuongSuQh()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getDuongSuId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<QuanHeDuongSuCriteria> copyFiltersAre(
        QuanHeDuongSuCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdQuanHe(), copy.getIdQuanHe()) &&
                condition.apply(criteria.getIdDuongSuQh(), copy.getIdDuongSuQh()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getDuongSuId(), copy.getDuongSuId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
