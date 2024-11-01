package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class LoaiDuongSuCriteriaTest {

    @Test
    void newLoaiDuongSuCriteriaHasAllFiltersNullTest() {
        var loaiDuongSuCriteria = new LoaiDuongSuCriteria();
        assertThat(loaiDuongSuCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void loaiDuongSuCriteriaFluentMethodsCreatesFiltersTest() {
        var loaiDuongSuCriteria = new LoaiDuongSuCriteria();

        setAllFilters(loaiDuongSuCriteria);

        assertThat(loaiDuongSuCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void loaiDuongSuCriteriaCopyCreatesNullFilterTest() {
        var loaiDuongSuCriteria = new LoaiDuongSuCriteria();
        var copy = loaiDuongSuCriteria.copy();

        assertThat(loaiDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(loaiDuongSuCriteria)
        );
    }

    @Test
    void loaiDuongSuCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var loaiDuongSuCriteria = new LoaiDuongSuCriteria();
        setAllFilters(loaiDuongSuCriteria);

        var copy = loaiDuongSuCriteria.copy();

        assertThat(loaiDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(loaiDuongSuCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var loaiDuongSuCriteria = new LoaiDuongSuCriteria();

        assertThat(loaiDuongSuCriteria).hasToString("LoaiDuongSuCriteria{}");
    }

    private static void setAllFilters(LoaiDuongSuCriteria loaiDuongSuCriteria) {
        loaiDuongSuCriteria.idLoaiDuongSu();
        loaiDuongSuCriteria.tenLoaiDuongSu();
        loaiDuongSuCriteria.idLoaiDuongSuId();
        loaiDuongSuCriteria.thongTinCapNhatDuongSuId();
        loaiDuongSuCriteria.distinct();
    }

    private static Condition<LoaiDuongSuCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdLoaiDuongSu()) &&
                condition.apply(criteria.getTenLoaiDuongSu()) &&
                condition.apply(criteria.getIdLoaiDuongSuId()) &&
                condition.apply(criteria.getThongTinCapNhatDuongSuId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<LoaiDuongSuCriteria> copyFiltersAre(LoaiDuongSuCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdLoaiDuongSu(), copy.getIdLoaiDuongSu()) &&
                condition.apply(criteria.getTenLoaiDuongSu(), copy.getTenLoaiDuongSu()) &&
                condition.apply(criteria.getIdLoaiDuongSuId(), copy.getIdLoaiDuongSuId()) &&
                condition.apply(criteria.getThongTinCapNhatDuongSuId(), copy.getThongTinCapNhatDuongSuId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
