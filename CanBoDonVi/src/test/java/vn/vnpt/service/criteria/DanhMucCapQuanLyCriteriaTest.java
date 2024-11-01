package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhMucCapQuanLyCriteriaTest {

    @Test
    void newDanhMucCapQuanLyCriteriaHasAllFiltersNullTest() {
        var danhMucCapQuanLyCriteria = new DanhMucCapQuanLyCriteria();
        assertThat(danhMucCapQuanLyCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhMucCapQuanLyCriteriaFluentMethodsCreatesFiltersTest() {
        var danhMucCapQuanLyCriteria = new DanhMucCapQuanLyCriteria();

        setAllFilters(danhMucCapQuanLyCriteria);

        assertThat(danhMucCapQuanLyCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhMucCapQuanLyCriteriaCopyCreatesNullFilterTest() {
        var danhMucCapQuanLyCriteria = new DanhMucCapQuanLyCriteria();
        var copy = danhMucCapQuanLyCriteria.copy();

        assertThat(danhMucCapQuanLyCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucCapQuanLyCriteria)
        );
    }

    @Test
    void danhMucCapQuanLyCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhMucCapQuanLyCriteria = new DanhMucCapQuanLyCriteria();
        setAllFilters(danhMucCapQuanLyCriteria);

        var copy = danhMucCapQuanLyCriteria.copy();

        assertThat(danhMucCapQuanLyCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucCapQuanLyCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhMucCapQuanLyCriteria = new DanhMucCapQuanLyCriteria();

        assertThat(danhMucCapQuanLyCriteria).hasToString("DanhMucCapQuanLyCriteria{}");
    }

    private static void setAllFilters(DanhMucCapQuanLyCriteria danhMucCapQuanLyCriteria) {
        danhMucCapQuanLyCriteria.idCapQl();
        danhMucCapQuanLyCriteria.dienGiai();
        danhMucCapQuanLyCriteria.distinct();
    }

    private static Condition<DanhMucCapQuanLyCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCapQl()) &&
                condition.apply(criteria.getDienGiai()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhMucCapQuanLyCriteria> copyFiltersAre(
        DanhMucCapQuanLyCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCapQl(), copy.getIdCapQl()) &&
                condition.apply(criteria.getDienGiai(), copy.getDienGiai()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
