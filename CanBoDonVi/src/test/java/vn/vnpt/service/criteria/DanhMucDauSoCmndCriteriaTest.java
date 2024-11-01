package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhMucDauSoCmndCriteriaTest {

    @Test
    void newDanhMucDauSoCmndCriteriaHasAllFiltersNullTest() {
        var danhMucDauSoCmndCriteria = new DanhMucDauSoCmndCriteria();
        assertThat(danhMucDauSoCmndCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhMucDauSoCmndCriteriaFluentMethodsCreatesFiltersTest() {
        var danhMucDauSoCmndCriteria = new DanhMucDauSoCmndCriteria();

        setAllFilters(danhMucDauSoCmndCriteria);

        assertThat(danhMucDauSoCmndCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhMucDauSoCmndCriteriaCopyCreatesNullFilterTest() {
        var danhMucDauSoCmndCriteria = new DanhMucDauSoCmndCriteria();
        var copy = danhMucDauSoCmndCriteria.copy();

        assertThat(danhMucDauSoCmndCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucDauSoCmndCriteria)
        );
    }

    @Test
    void danhMucDauSoCmndCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhMucDauSoCmndCriteria = new DanhMucDauSoCmndCriteria();
        setAllFilters(danhMucDauSoCmndCriteria);

        var copy = danhMucDauSoCmndCriteria.copy();

        assertThat(danhMucDauSoCmndCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucDauSoCmndCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhMucDauSoCmndCriteria = new DanhMucDauSoCmndCriteria();

        assertThat(danhMucDauSoCmndCriteria).hasToString("DanhMucDauSoCmndCriteria{}");
    }

    private static void setAllFilters(DanhMucDauSoCmndCriteria danhMucDauSoCmndCriteria) {
        danhMucDauSoCmndCriteria.idDauSo();
        danhMucDauSoCmndCriteria.dauSo();
        danhMucDauSoCmndCriteria.tinhThanh();
        danhMucDauSoCmndCriteria.idLoai();
        danhMucDauSoCmndCriteria.distinct();
    }

    private static Condition<DanhMucDauSoCmndCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdDauSo()) &&
                condition.apply(criteria.getDauSo()) &&
                condition.apply(criteria.getTinhThanh()) &&
                condition.apply(criteria.getIdLoai()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhMucDauSoCmndCriteria> copyFiltersAre(
        DanhMucDauSoCmndCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdDauSo(), copy.getIdDauSo()) &&
                condition.apply(criteria.getDauSo(), copy.getDauSo()) &&
                condition.apply(criteria.getTinhThanh(), copy.getTinhThanh()) &&
                condition.apply(criteria.getIdLoai(), copy.getIdLoai()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
