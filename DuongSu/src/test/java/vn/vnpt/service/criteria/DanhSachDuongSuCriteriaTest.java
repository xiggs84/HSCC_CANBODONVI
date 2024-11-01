package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhSachDuongSuCriteriaTest {

    @Test
    void newDanhSachDuongSuCriteriaHasAllFiltersNullTest() {
        var danhSachDuongSuCriteria = new DanhSachDuongSuCriteria();
        assertThat(danhSachDuongSuCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhSachDuongSuCriteriaFluentMethodsCreatesFiltersTest() {
        var danhSachDuongSuCriteria = new DanhSachDuongSuCriteria();

        setAllFilters(danhSachDuongSuCriteria);

        assertThat(danhSachDuongSuCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhSachDuongSuCriteriaCopyCreatesNullFilterTest() {
        var danhSachDuongSuCriteria = new DanhSachDuongSuCriteria();
        var copy = danhSachDuongSuCriteria.copy();

        assertThat(danhSachDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhSachDuongSuCriteria)
        );
    }

    @Test
    void danhSachDuongSuCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhSachDuongSuCriteria = new DanhSachDuongSuCriteria();
        setAllFilters(danhSachDuongSuCriteria);

        var copy = danhSachDuongSuCriteria.copy();

        assertThat(danhSachDuongSuCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhSachDuongSuCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhSachDuongSuCriteria = new DanhSachDuongSuCriteria();

        assertThat(danhSachDuongSuCriteria).hasToString("DanhSachDuongSuCriteria{}");
    }

    private static void setAllFilters(DanhSachDuongSuCriteria danhSachDuongSuCriteria) {
        danhSachDuongSuCriteria.id();
        danhSachDuongSuCriteria.tenDuongSu();
        danhSachDuongSuCriteria.diaChi();
        danhSachDuongSuCriteria.trangThai();
        danhSachDuongSuCriteria.ngayThaoTac();
        danhSachDuongSuCriteria.nguoiThaoTac();
        danhSachDuongSuCriteria.idDsGoc();
        danhSachDuongSuCriteria.idMaster();
        danhSachDuongSuCriteria.idDonVi();
        danhSachDuongSuCriteria.strSearch();
        danhSachDuongSuCriteria.soGiayTo();
        danhSachDuongSuCriteria.idLoaiNganChan();
        danhSachDuongSuCriteria.duongSuId();
        danhSachDuongSuCriteria.distinct();
    }

    private static Condition<DanhSachDuongSuCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getTenDuongSu()) &&
                condition.apply(criteria.getDiaChi()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getNgayThaoTac()) &&
                condition.apply(criteria.getNguoiThaoTac()) &&
                condition.apply(criteria.getIdDsGoc()) &&
                condition.apply(criteria.getIdMaster()) &&
                condition.apply(criteria.getIdDonVi()) &&
                condition.apply(criteria.getStrSearch()) &&
                condition.apply(criteria.getSoGiayTo()) &&
                condition.apply(criteria.getIdLoaiNganChan()) &&
                condition.apply(criteria.getDuongSuId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhSachDuongSuCriteria> copyFiltersAre(
        DanhSachDuongSuCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getTenDuongSu(), copy.getTenDuongSu()) &&
                condition.apply(criteria.getDiaChi(), copy.getDiaChi()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getNgayThaoTac(), copy.getNgayThaoTac()) &&
                condition.apply(criteria.getNguoiThaoTac(), copy.getNguoiThaoTac()) &&
                condition.apply(criteria.getIdDsGoc(), copy.getIdDsGoc()) &&
                condition.apply(criteria.getIdMaster(), copy.getIdMaster()) &&
                condition.apply(criteria.getIdDonVi(), copy.getIdDonVi()) &&
                condition.apply(criteria.getStrSearch(), copy.getStrSearch()) &&
                condition.apply(criteria.getSoGiayTo(), copy.getSoGiayTo()) &&
                condition.apply(criteria.getIdLoaiNganChan(), copy.getIdLoaiNganChan()) &&
                condition.apply(criteria.getDuongSuId(), copy.getDuongSuId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
