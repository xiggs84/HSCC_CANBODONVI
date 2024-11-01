package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DonViScanQrCriteriaTest {

    @Test
    void newDonViScanQrCriteriaHasAllFiltersNullTest() {
        var donViScanQrCriteria = new DonViScanQrCriteria();
        assertThat(donViScanQrCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void donViScanQrCriteriaFluentMethodsCreatesFiltersTest() {
        var donViScanQrCriteria = new DonViScanQrCriteria();

        setAllFilters(donViScanQrCriteria);

        assertThat(donViScanQrCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void donViScanQrCriteriaCopyCreatesNullFilterTest() {
        var donViScanQrCriteria = new DonViScanQrCriteria();
        var copy = donViScanQrCriteria.copy();

        assertThat(donViScanQrCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(donViScanQrCriteria)
        );
    }

    @Test
    void donViScanQrCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var donViScanQrCriteria = new DonViScanQrCriteria();
        setAllFilters(donViScanQrCriteria);

        var copy = donViScanQrCriteria.copy();

        assertThat(donViScanQrCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(donViScanQrCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var donViScanQrCriteria = new DonViScanQrCriteria();

        assertThat(donViScanQrCriteria).hasToString("DonViScanQrCriteria{}");
    }

    private static void setAllFilters(DonViScanQrCriteria donViScanQrCriteria) {
        donViScanQrCriteria.idLuotQuet();
        donViScanQrCriteria.idCongDan();
        donViScanQrCriteria.ngayThaoTac();
        donViScanQrCriteria.distinct();
    }

    private static Condition<DonViScanQrCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdLuotQuet()) &&
                condition.apply(criteria.getIdCongDan()) &&
                condition.apply(criteria.getNgayThaoTac()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DonViScanQrCriteria> copyFiltersAre(DonViScanQrCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdLuotQuet(), copy.getIdLuotQuet()) &&
                condition.apply(criteria.getIdCongDan(), copy.getIdCongDan()) &&
                condition.apply(criteria.getNgayThaoTac(), copy.getNgayThaoTac()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
