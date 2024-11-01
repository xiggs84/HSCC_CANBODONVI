package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DuongSuTrungCmndCriteriaTest {

    @Test
    void newDuongSuTrungCmndCriteriaHasAllFiltersNullTest() {
        var duongSuTrungCmndCriteria = new DuongSuTrungCmndCriteria();
        assertThat(duongSuTrungCmndCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void duongSuTrungCmndCriteriaFluentMethodsCreatesFiltersTest() {
        var duongSuTrungCmndCriteria = new DuongSuTrungCmndCriteria();

        setAllFilters(duongSuTrungCmndCriteria);

        assertThat(duongSuTrungCmndCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void duongSuTrungCmndCriteriaCopyCreatesNullFilterTest() {
        var duongSuTrungCmndCriteria = new DuongSuTrungCmndCriteria();
        var copy = duongSuTrungCmndCriteria.copy();

        assertThat(duongSuTrungCmndCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(duongSuTrungCmndCriteria)
        );
    }

    @Test
    void duongSuTrungCmndCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var duongSuTrungCmndCriteria = new DuongSuTrungCmndCriteria();
        setAllFilters(duongSuTrungCmndCriteria);

        var copy = duongSuTrungCmndCriteria.copy();

        assertThat(duongSuTrungCmndCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(duongSuTrungCmndCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var duongSuTrungCmndCriteria = new DuongSuTrungCmndCriteria();

        assertThat(duongSuTrungCmndCriteria).hasToString("DuongSuTrungCmndCriteria{}");
    }

    private static void setAllFilters(DuongSuTrungCmndCriteria duongSuTrungCmndCriteria) {
        duongSuTrungCmndCriteria.id();
        duongSuTrungCmndCriteria.tenDuongSu();
        duongSuTrungCmndCriteria.diaChi();
        duongSuTrungCmndCriteria.trangThai();
        duongSuTrungCmndCriteria.thongTinDs();
        duongSuTrungCmndCriteria.ngayThaoTac();
        duongSuTrungCmndCriteria.nguoiThaoTac();
        duongSuTrungCmndCriteria.idDsGoc();
        duongSuTrungCmndCriteria.idMaster();
        duongSuTrungCmndCriteria.idDonVi();
        duongSuTrungCmndCriteria.strSearch();
        duongSuTrungCmndCriteria.soGiayTo();
        duongSuTrungCmndCriteria.idDuongSuMin();
        duongSuTrungCmndCriteria.idMasterMin();
        duongSuTrungCmndCriteria.idDuongSuMax();
        duongSuTrungCmndCriteria.idMasterMax();
        duongSuTrungCmndCriteria.duongSuId();
        duongSuTrungCmndCriteria.distinct();
    }

    private static Condition<DuongSuTrungCmndCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
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
                condition.apply(criteria.getIdDuongSuMin()) &&
                condition.apply(criteria.getIdMasterMin()) &&
                condition.apply(criteria.getIdDuongSuMax()) &&
                condition.apply(criteria.getIdMasterMax()) &&
                condition.apply(criteria.getDuongSuId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DuongSuTrungCmndCriteria> copyFiltersAre(
        DuongSuTrungCmndCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
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
                condition.apply(criteria.getIdDuongSuMin(), copy.getIdDuongSuMin()) &&
                condition.apply(criteria.getIdMasterMin(), copy.getIdMasterMin()) &&
                condition.apply(criteria.getIdDuongSuMax(), copy.getIdDuongSuMax()) &&
                condition.apply(criteria.getIdMasterMax(), copy.getIdMasterMax()) &&
                condition.apply(criteria.getDuongSuId(), copy.getDuongSuId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
