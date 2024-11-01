package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CauHinhThongTinDuongSuCriteriaTest {

    @Test
    void newCauHinhThongTinDuongSuCriteriaHasAllFiltersNullTest() {
        var cauHinhThongTinDuongSuCriteria = new CauHinhThongTinDuongSuCriteria();
        assertThat(cauHinhThongTinDuongSuCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void cauHinhThongTinDuongSuCriteriaFluentMethodsCreatesFiltersTest() {
        var cauHinhThongTinDuongSuCriteria = new CauHinhThongTinDuongSuCriteria();

        setAllFilters(cauHinhThongTinDuongSuCriteria);

        assertThat(cauHinhThongTinDuongSuCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void cauHinhThongTinDuongSuCriteriaCopyCreatesNullFilterTest() {
        var cauHinhThongTinDuongSuCriteria = new CauHinhThongTinDuongSuCriteria();
        var copy = cauHinhThongTinDuongSuCriteria.copy();

        assertThat(cauHinhThongTinDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(cauHinhThongTinDuongSuCriteria)
        );
    }

    @Test
    void cauHinhThongTinDuongSuCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var cauHinhThongTinDuongSuCriteria = new CauHinhThongTinDuongSuCriteria();
        setAllFilters(cauHinhThongTinDuongSuCriteria);

        var copy = cauHinhThongTinDuongSuCriteria.copy();

        assertThat(cauHinhThongTinDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(cauHinhThongTinDuongSuCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var cauHinhThongTinDuongSuCriteria = new CauHinhThongTinDuongSuCriteria();

        assertThat(cauHinhThongTinDuongSuCriteria).hasToString("CauHinhThongTinDuongSuCriteria{}");
    }

    private static void setAllFilters(CauHinhThongTinDuongSuCriteria cauHinhThongTinDuongSuCriteria) {
        cauHinhThongTinDuongSuCriteria.idCauHinh();
        cauHinhThongTinDuongSuCriteria.noiDung();
        cauHinhThongTinDuongSuCriteria.javascript();
        cauHinhThongTinDuongSuCriteria.css();
        cauHinhThongTinDuongSuCriteria.idLoaiDs();
        cauHinhThongTinDuongSuCriteria.idDonVi();
        cauHinhThongTinDuongSuCriteria.trangThai();
        cauHinhThongTinDuongSuCriteria.distinct();
    }

    private static Condition<CauHinhThongTinDuongSuCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCauHinh()) &&
                condition.apply(criteria.getNoiDung()) &&
                condition.apply(criteria.getJavascript()) &&
                condition.apply(criteria.getCss()) &&
                condition.apply(criteria.getIdLoaiDs()) &&
                condition.apply(criteria.getIdDonVi()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<CauHinhThongTinDuongSuCriteria> copyFiltersAre(
        CauHinhThongTinDuongSuCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCauHinh(), copy.getIdCauHinh()) &&
                condition.apply(criteria.getNoiDung(), copy.getNoiDung()) &&
                condition.apply(criteria.getJavascript(), copy.getJavascript()) &&
                condition.apply(criteria.getCss(), copy.getCss()) &&
                condition.apply(criteria.getIdLoaiDs(), copy.getIdLoaiDs()) &&
                condition.apply(criteria.getIdDonVi(), copy.getIdDonVi()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
