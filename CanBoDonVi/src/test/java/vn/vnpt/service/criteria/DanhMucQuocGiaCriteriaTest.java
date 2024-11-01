package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhMucQuocGiaCriteriaTest {

    @Test
    void newDanhMucQuocGiaCriteriaHasAllFiltersNullTest() {
        var danhMucQuocGiaCriteria = new DanhMucQuocGiaCriteria();
        assertThat(danhMucQuocGiaCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhMucQuocGiaCriteriaFluentMethodsCreatesFiltersTest() {
        var danhMucQuocGiaCriteria = new DanhMucQuocGiaCriteria();

        setAllFilters(danhMucQuocGiaCriteria);

        assertThat(danhMucQuocGiaCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhMucQuocGiaCriteriaCopyCreatesNullFilterTest() {
        var danhMucQuocGiaCriteria = new DanhMucQuocGiaCriteria();
        var copy = danhMucQuocGiaCriteria.copy();

        assertThat(danhMucQuocGiaCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucQuocGiaCriteria)
        );
    }

    @Test
    void danhMucQuocGiaCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhMucQuocGiaCriteria = new DanhMucQuocGiaCriteria();
        setAllFilters(danhMucQuocGiaCriteria);

        var copy = danhMucQuocGiaCriteria.copy();

        assertThat(danhMucQuocGiaCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucQuocGiaCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhMucQuocGiaCriteria = new DanhMucQuocGiaCriteria();

        assertThat(danhMucQuocGiaCriteria).hasToString("DanhMucQuocGiaCriteria{}");
    }

    private static void setAllFilters(DanhMucQuocGiaCriteria danhMucQuocGiaCriteria) {
        danhMucQuocGiaCriteria.idQuocGia();
        danhMucQuocGiaCriteria.tenQuocGia();
        danhMucQuocGiaCriteria.tenTiengAnh();
        danhMucQuocGiaCriteria.distinct();
    }

    private static Condition<DanhMucQuocGiaCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdQuocGia()) &&
                condition.apply(criteria.getTenQuocGia()) &&
                condition.apply(criteria.getTenTiengAnh()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhMucQuocGiaCriteria> copyFiltersAre(
        DanhMucQuocGiaCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdQuocGia(), copy.getIdQuocGia()) &&
                condition.apply(criteria.getTenQuocGia(), copy.getTenQuocGia()) &&
                condition.apply(criteria.getTenTiengAnh(), copy.getTenTiengAnh()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
