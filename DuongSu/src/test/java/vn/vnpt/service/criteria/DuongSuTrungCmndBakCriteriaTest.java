package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DuongSuTrungCmndBakCriteriaTest {

    @Test
    void newDuongSuTrungCmndBakCriteriaHasAllFiltersNullTest() {
        var duongSuTrungCmndBakCriteria = new DuongSuTrungCmndBakCriteria();
        assertThat(duongSuTrungCmndBakCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void duongSuTrungCmndBakCriteriaFluentMethodsCreatesFiltersTest() {
        var duongSuTrungCmndBakCriteria = new DuongSuTrungCmndBakCriteria();

        setAllFilters(duongSuTrungCmndBakCriteria);

        assertThat(duongSuTrungCmndBakCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void duongSuTrungCmndBakCriteriaCopyCreatesNullFilterTest() {
        var duongSuTrungCmndBakCriteria = new DuongSuTrungCmndBakCriteria();
        var copy = duongSuTrungCmndBakCriteria.copy();

        assertThat(duongSuTrungCmndBakCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(duongSuTrungCmndBakCriteria)
        );
    }

    @Test
    void duongSuTrungCmndBakCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var duongSuTrungCmndBakCriteria = new DuongSuTrungCmndBakCriteria();
        setAllFilters(duongSuTrungCmndBakCriteria);

        var copy = duongSuTrungCmndBakCriteria.copy();

        assertThat(duongSuTrungCmndBakCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(duongSuTrungCmndBakCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var duongSuTrungCmndBakCriteria = new DuongSuTrungCmndBakCriteria();

        assertThat(duongSuTrungCmndBakCriteria).hasToString("DuongSuTrungCmndBakCriteria{}");
    }

    private static void setAllFilters(DuongSuTrungCmndBakCriteria duongSuTrungCmndBakCriteria) {
        duongSuTrungCmndBakCriteria.id();
        duongSuTrungCmndBakCriteria.tenDuongSu();
        duongSuTrungCmndBakCriteria.diaChi();
        duongSuTrungCmndBakCriteria.trangThai();
        duongSuTrungCmndBakCriteria.thongTinDs();
        duongSuTrungCmndBakCriteria.ngayThaoTac();
        duongSuTrungCmndBakCriteria.nguoiThaoTac();
        duongSuTrungCmndBakCriteria.idDsGoc();
        duongSuTrungCmndBakCriteria.idMaster();
        duongSuTrungCmndBakCriteria.idDonVi();
        duongSuTrungCmndBakCriteria.strSearch();
        duongSuTrungCmndBakCriteria.soGiayTo();
        duongSuTrungCmndBakCriteria.duongSuId();
        duongSuTrungCmndBakCriteria.distinct();
    }

    private static Condition<DuongSuTrungCmndBakCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
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
                condition.apply(criteria.getDuongSuId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DuongSuTrungCmndBakCriteria> copyFiltersAre(
        DuongSuTrungCmndBakCriteria copy,
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
                condition.apply(criteria.getDuongSuId(), copy.getDuongSuId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
