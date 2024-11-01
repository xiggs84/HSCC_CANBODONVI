package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class LoaiDonViCriteriaTest {

    @Test
    void newLoaiDonViCriteriaHasAllFiltersNullTest() {
        var loaiDonViCriteria = new LoaiDonViCriteria();
        assertThat(loaiDonViCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void loaiDonViCriteriaFluentMethodsCreatesFiltersTest() {
        var loaiDonViCriteria = new LoaiDonViCriteria();

        setAllFilters(loaiDonViCriteria);

        assertThat(loaiDonViCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void loaiDonViCriteriaCopyCreatesNullFilterTest() {
        var loaiDonViCriteria = new LoaiDonViCriteria();
        var copy = loaiDonViCriteria.copy();

        assertThat(loaiDonViCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(loaiDonViCriteria)
        );
    }

    @Test
    void loaiDonViCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var loaiDonViCriteria = new LoaiDonViCriteria();
        setAllFilters(loaiDonViCriteria);

        var copy = loaiDonViCriteria.copy();

        assertThat(loaiDonViCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(loaiDonViCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var loaiDonViCriteria = new LoaiDonViCriteria();

        assertThat(loaiDonViCriteria).hasToString("LoaiDonViCriteria{}");
    }

    private static void setAllFilters(LoaiDonViCriteria loaiDonViCriteria) {
        loaiDonViCriteria.idLoaiDv();
        loaiDonViCriteria.tenLoaiDv();
        loaiDonViCriteria.idLoaiDvId();
        loaiDonViCriteria.distinct();
    }

    private static Condition<LoaiDonViCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdLoaiDv()) &&
                condition.apply(criteria.getTenLoaiDv()) &&
                condition.apply(criteria.getIdLoaiDvId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<LoaiDonViCriteria> copyFiltersAre(LoaiDonViCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdLoaiDv(), copy.getIdLoaiDv()) &&
                condition.apply(criteria.getTenLoaiDv(), copy.getTenLoaiDv()) &&
                condition.apply(criteria.getIdLoaiDvId(), copy.getIdLoaiDvId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
