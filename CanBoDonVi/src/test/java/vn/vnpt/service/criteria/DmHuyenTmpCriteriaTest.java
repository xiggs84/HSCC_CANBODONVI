package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DmHuyenTmpCriteriaTest {

    @Test
    void newDmHuyenTmpCriteriaHasAllFiltersNullTest() {
        var dmHuyenTmpCriteria = new DmHuyenTmpCriteria();
        assertThat(dmHuyenTmpCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void dmHuyenTmpCriteriaFluentMethodsCreatesFiltersTest() {
        var dmHuyenTmpCriteria = new DmHuyenTmpCriteria();

        setAllFilters(dmHuyenTmpCriteria);

        assertThat(dmHuyenTmpCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void dmHuyenTmpCriteriaCopyCreatesNullFilterTest() {
        var dmHuyenTmpCriteria = new DmHuyenTmpCriteria();
        var copy = dmHuyenTmpCriteria.copy();

        assertThat(dmHuyenTmpCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(dmHuyenTmpCriteria)
        );
    }

    @Test
    void dmHuyenTmpCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dmHuyenTmpCriteria = new DmHuyenTmpCriteria();
        setAllFilters(dmHuyenTmpCriteria);

        var copy = dmHuyenTmpCriteria.copy();

        assertThat(dmHuyenTmpCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(dmHuyenTmpCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dmHuyenTmpCriteria = new DmHuyenTmpCriteria();

        assertThat(dmHuyenTmpCriteria).hasToString("DmHuyenTmpCriteria{}");
    }

    private static void setAllFilters(DmHuyenTmpCriteria dmHuyenTmpCriteria) {
        dmHuyenTmpCriteria.maHuyen();
        dmHuyenTmpCriteria.tenHuyen();
        dmHuyenTmpCriteria.distinct();
    }

    private static Condition<DmHuyenTmpCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaHuyen()) &&
                condition.apply(criteria.getTenHuyen()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DmHuyenTmpCriteria> copyFiltersAre(DmHuyenTmpCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMaHuyen(), copy.getMaHuyen()) &&
                condition.apply(criteria.getTenHuyen(), copy.getTenHuyen()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
