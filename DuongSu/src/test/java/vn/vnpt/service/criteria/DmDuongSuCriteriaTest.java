package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DmDuongSuCriteriaTest {

    @Test
    void newDmDuongSuCriteriaHasAllFiltersNullTest() {
        var dmDuongSuCriteria = new DmDuongSuCriteria();
        assertThat(dmDuongSuCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void dmDuongSuCriteriaFluentMethodsCreatesFiltersTest() {
        var dmDuongSuCriteria = new DmDuongSuCriteria();

        setAllFilters(dmDuongSuCriteria);

        assertThat(dmDuongSuCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void dmDuongSuCriteriaCopyCreatesNullFilterTest() {
        var dmDuongSuCriteria = new DmDuongSuCriteria();
        var copy = dmDuongSuCriteria.copy();

        assertThat(dmDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(dmDuongSuCriteria)
        );
    }

    @Test
    void dmDuongSuCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var dmDuongSuCriteria = new DmDuongSuCriteria();
        setAllFilters(dmDuongSuCriteria);

        var copy = dmDuongSuCriteria.copy();

        assertThat(dmDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(dmDuongSuCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var dmDuongSuCriteria = new DmDuongSuCriteria();

        assertThat(dmDuongSuCriteria).hasToString("DmDuongSuCriteria{}");
    }

    private static void setAllFilters(DmDuongSuCriteria dmDuongSuCriteria) {
        dmDuongSuCriteria.idDuongSu();
        dmDuongSuCriteria.tenDuongSu();
        dmDuongSuCriteria.diaChi();
        dmDuongSuCriteria.trangThai();
        dmDuongSuCriteria.thongTinDs();
        dmDuongSuCriteria.ngayThaoTac();
        dmDuongSuCriteria.nguoiThaoTac();
        dmDuongSuCriteria.idDsGoc();
        dmDuongSuCriteria.idMaster();
        dmDuongSuCriteria.idDonVi();
        dmDuongSuCriteria.strSearch();
        dmDuongSuCriteria.soGiayTo();
        dmDuongSuCriteria.idLoaiNganChan();
        dmDuongSuCriteria.distinct();
    }

    private static Condition<DmDuongSuCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdDuongSu()) &&
                condition.apply(criteria.getTenDuongSu()) &&
                condition.apply(criteria.getDiaChi()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getThongTinDs()) &&
                condition.apply(criteria.getNgayThaoTac()) &&
                condition.apply(criteria.getNguoiThaoTac()) &&
                condition.apply(criteria.getIdDsGoc()) &&
                condition.apply(criteria.getIdMaster()) &&
                condition.apply(criteria.getIdDonVi()) &&
                condition.apply(criteria.getStrSearch()) &&
                condition.apply(criteria.getSoGiayTo()) &&
                condition.apply(criteria.getIdLoaiNganChan()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DmDuongSuCriteria> copyFiltersAre(DmDuongSuCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdDuongSu(), copy.getIdDuongSu()) &&
                condition.apply(criteria.getTenDuongSu(), copy.getTenDuongSu()) &&
                condition.apply(criteria.getDiaChi(), copy.getDiaChi()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getThongTinDs(), copy.getThongTinDs()) &&
                condition.apply(criteria.getNgayThaoTac(), copy.getNgayThaoTac()) &&
                condition.apply(criteria.getNguoiThaoTac(), copy.getNguoiThaoTac()) &&
                condition.apply(criteria.getIdDsGoc(), copy.getIdDsGoc()) &&
                condition.apply(criteria.getIdMaster(), copy.getIdMaster()) &&
                condition.apply(criteria.getIdDonVi(), copy.getIdDonVi()) &&
                condition.apply(criteria.getStrSearch(), copy.getStrSearch()) &&
                condition.apply(criteria.getSoGiayTo(), copy.getSoGiayTo()) &&
                condition.apply(criteria.getIdLoaiNganChan(), copy.getIdLoaiNganChan()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
