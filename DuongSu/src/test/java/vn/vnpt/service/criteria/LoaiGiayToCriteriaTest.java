package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class LoaiGiayToCriteriaTest {

    @Test
    void newLoaiGiayToCriteriaHasAllFiltersNullTest() {
        var loaiGiayToCriteria = new LoaiGiayToCriteria();
        assertThat(loaiGiayToCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void loaiGiayToCriteriaFluentMethodsCreatesFiltersTest() {
        var loaiGiayToCriteria = new LoaiGiayToCriteria();

        setAllFilters(loaiGiayToCriteria);

        assertThat(loaiGiayToCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void loaiGiayToCriteriaCopyCreatesNullFilterTest() {
        var loaiGiayToCriteria = new LoaiGiayToCriteria();
        var copy = loaiGiayToCriteria.copy();

        assertThat(loaiGiayToCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(loaiGiayToCriteria)
        );
    }

    @Test
    void loaiGiayToCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var loaiGiayToCriteria = new LoaiGiayToCriteria();
        setAllFilters(loaiGiayToCriteria);

        var copy = loaiGiayToCriteria.copy();

        assertThat(loaiGiayToCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(loaiGiayToCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var loaiGiayToCriteria = new LoaiGiayToCriteria();

        assertThat(loaiGiayToCriteria).hasToString("LoaiGiayToCriteria{}");
    }

    private static void setAllFilters(LoaiGiayToCriteria loaiGiayToCriteria) {
        loaiGiayToCriteria.idLoaiGiayTo();
        loaiGiayToCriteria.tenLoaiGiayTo();
        loaiGiayToCriteria.idLoaiGiayToId();
        loaiGiayToCriteria.thongTinCapNhatDuongSuId();
        loaiGiayToCriteria.distinct();
    }

    private static Condition<LoaiGiayToCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdLoaiGiayTo()) &&
                condition.apply(criteria.getTenLoaiGiayTo()) &&
                condition.apply(criteria.getIdLoaiGiayToId()) &&
                condition.apply(criteria.getThongTinCapNhatDuongSuId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<LoaiGiayToCriteria> copyFiltersAre(LoaiGiayToCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdLoaiGiayTo(), copy.getIdLoaiGiayTo()) &&
                condition.apply(criteria.getTenLoaiGiayTo(), copy.getTenLoaiGiayTo()) &&
                condition.apply(criteria.getIdLoaiGiayToId(), copy.getIdLoaiGiayToId()) &&
                condition.apply(criteria.getThongTinCapNhatDuongSuId(), copy.getThongTinCapNhatDuongSuId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
