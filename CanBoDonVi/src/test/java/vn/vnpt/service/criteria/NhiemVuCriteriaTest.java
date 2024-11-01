package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class NhiemVuCriteriaTest {

    @Test
    void newNhiemVuCriteriaHasAllFiltersNullTest() {
        var nhiemVuCriteria = new NhiemVuCriteria();
        assertThat(nhiemVuCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void nhiemVuCriteriaFluentMethodsCreatesFiltersTest() {
        var nhiemVuCriteria = new NhiemVuCriteria();

        setAllFilters(nhiemVuCriteria);

        assertThat(nhiemVuCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void nhiemVuCriteriaCopyCreatesNullFilterTest() {
        var nhiemVuCriteria = new NhiemVuCriteria();
        var copy = nhiemVuCriteria.copy();

        assertThat(nhiemVuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(nhiemVuCriteria)
        );
    }

    @Test
    void nhiemVuCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var nhiemVuCriteria = new NhiemVuCriteria();
        setAllFilters(nhiemVuCriteria);

        var copy = nhiemVuCriteria.copy();

        assertThat(nhiemVuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(nhiemVuCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var nhiemVuCriteria = new NhiemVuCriteria();

        assertThat(nhiemVuCriteria).hasToString("NhiemVuCriteria{}");
    }

    private static void setAllFilters(NhiemVuCriteria nhiemVuCriteria) {
        nhiemVuCriteria.idNhiemVu();
        nhiemVuCriteria.tenNhiemVu();
        nhiemVuCriteria.idNhiemVuId();
        nhiemVuCriteria.distinct();
    }

    private static Condition<NhiemVuCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdNhiemVu()) &&
                condition.apply(criteria.getTenNhiemVu()) &&
                condition.apply(criteria.getIdNhiemVuId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<NhiemVuCriteria> copyFiltersAre(NhiemVuCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdNhiemVu(), copy.getIdNhiemVu()) &&
                condition.apply(criteria.getTenNhiemVu(), copy.getTenNhiemVu()) &&
                condition.apply(criteria.getIdNhiemVuId(), copy.getIdNhiemVuId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
