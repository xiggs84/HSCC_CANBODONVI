package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DmNoiCapGpdkxCriteriaTest {

    @Test
    void newDmNoiCapGpdkxCriteriaHasAllFiltersNullTest() {
        var dmNoiCapGpdkxCriteria = new DmNoiCapGpdkxCriteria();
        assertThat(dmNoiCapGpdkxCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void dmNoiCapGpdkxCriteriaFluentMethodsCreatesFiltersTest() {
        var dmNoiCapGpdkxCriteria = new DmNoiCapGpdkxCriteria();

        setAllFilters(dmNoiCapGpdkxCriteria);

        assertThat(dmNoiCapGpdkxCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void dmNoiCapGpdkxCriteriaCopyCreatesNullFilterTest() {
        var dmNoiCapGpdkxCriteria = new DmNoiCapGpdkxCriteria();
        var copy = dmNoiCapGpdkxCriteria.copy();

        assertThat(dmNoiCapGpdkxCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(dmNoiCapGpdkxCriteria)
        );
    }

    @Test
    void dmNoiCapGpdkxCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dmNoiCapGpdkxCriteria = new DmNoiCapGpdkxCriteria();
        setAllFilters(dmNoiCapGpdkxCriteria);

        var copy = dmNoiCapGpdkxCriteria.copy();

        assertThat(dmNoiCapGpdkxCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(dmNoiCapGpdkxCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dmNoiCapGpdkxCriteria = new DmNoiCapGpdkxCriteria();

        assertThat(dmNoiCapGpdkxCriteria).hasToString("DmNoiCapGpdkxCriteria{}");
    }

    private static void setAllFilters(DmNoiCapGpdkxCriteria dmNoiCapGpdkxCriteria) {
        dmNoiCapGpdkxCriteria.idNoiCap();
        dmNoiCapGpdkxCriteria.dienGiai();
        dmNoiCapGpdkxCriteria.trangThai();
        dmNoiCapGpdkxCriteria.distinct();
    }

    private static Condition<DmNoiCapGpdkxCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdNoiCap()) &&
                condition.apply(criteria.getDienGiai()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DmNoiCapGpdkxCriteria> copyFiltersAre(
        DmNoiCapGpdkxCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
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
