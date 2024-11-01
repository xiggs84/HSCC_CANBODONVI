package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class MenuQuyenCriteriaTest {

    @Test
    void newMenuQuyenCriteriaHasAllFiltersNullTest() {
        var menuQuyenCriteria = new MenuQuyenCriteria();
        assertThat(menuQuyenCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void menuQuyenCriteriaFluentMethodsCreatesFiltersTest() {
        var menuQuyenCriteria = new MenuQuyenCriteria();

        setAllFilters(menuQuyenCriteria);

        assertThat(menuQuyenCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void menuQuyenCriteriaCopyCreatesNullFilterTest() {
        var menuQuyenCriteria = new MenuQuyenCriteria();
        var copy = menuQuyenCriteria.copy();

        assertThat(menuQuyenCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(menuQuyenCriteria)
        );
    }

    @Test
    void menuQuyenCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var menuQuyenCriteria = new MenuQuyenCriteria();
        setAllFilters(menuQuyenCriteria);

        var copy = menuQuyenCriteria.copy();

        assertThat(menuQuyenCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(menuQuyenCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var menuQuyenCriteria = new MenuQuyenCriteria();

        assertThat(menuQuyenCriteria).hasToString("MenuQuyenCriteria{}");
    }

    private static void setAllFilters(MenuQuyenCriteria menuQuyenCriteria) {
        menuQuyenCriteria.id();
        menuQuyenCriteria.listMenu();
        menuQuyenCriteria.distinct();
    }

    private static Condition<MenuQuyenCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) && condition.apply(criteria.getListMenu()) && condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<MenuQuyenCriteria> copyFiltersAre(MenuQuyenCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getListMenu(), copy.getListMenu()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
