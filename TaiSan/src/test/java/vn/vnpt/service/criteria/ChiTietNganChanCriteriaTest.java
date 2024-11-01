package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class ChiTietNganChanCriteriaTest {

    @Test
    void newChiTietNganChanCriteriaHasAllFiltersNullTest() {
        var chiTietNganChanCriteria = new ChiTietNganChanCriteria();
        assertThat(chiTietNganChanCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void chiTietNganChanCriteriaFluentMethodsCreatesFiltersTest() {
        var chiTietNganChanCriteria = new ChiTietNganChanCriteria();

        setAllFilters(chiTietNganChanCriteria);

        assertThat(chiTietNganChanCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void chiTietNganChanCriteriaCopyCreatesNullFilterTest() {
        var chiTietNganChanCriteria = new ChiTietNganChanCriteria();
        var copy = chiTietNganChanCriteria.copy();

        assertThat(chiTietNganChanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(chiTietNganChanCriteria)
        );
    }

    @Test
    void chiTietNganChanCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var chiTietNganChanCriteria = new ChiTietNganChanCriteria();
        setAllFilters(chiTietNganChanCriteria);

        var copy = chiTietNganChanCriteria.copy();

        assertThat(chiTietNganChanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(chiTietNganChanCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var chiTietNganChanCriteria = new ChiTietNganChanCriteria();

        assertThat(chiTietNganChanCriteria).hasToString("ChiTietNganChanCriteria{}");
    }

    private static void setAllFilters(ChiTietNganChanCriteria chiTietNganChanCriteria) {
        chiTietNganChanCriteria.id();
        chiTietNganChanCriteria.idDoiTuong();
        chiTietNganChanCriteria.ngayThaoTac();
        chiTietNganChanCriteria.loaiDoiTuong();
        chiTietNganChanCriteria.soHsCv();
        chiTietNganChanCriteria.soCc();
        chiTietNganChanCriteria.soVaoSo();
        chiTietNganChanCriteria.moTa();
        chiTietNganChanCriteria.ngayNganChan();
        chiTietNganChanCriteria.ngayBdNganChan();
        chiTietNganChanCriteria.ngayKtNganChan();
        chiTietNganChanCriteria.trangThai();
        chiTietNganChanCriteria.nguoiThaoTac();
        chiTietNganChanCriteria.loaiNganChan();
        chiTietNganChanCriteria.ngayCongVan();
        chiTietNganChanCriteria.distinct();
    }

    private static Condition<ChiTietNganChanCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getIdDoiTuong()) &&
                condition.apply(criteria.getNgayThaoTac()) &&
                condition.apply(criteria.getLoaiDoiTuong()) &&
                condition.apply(criteria.getSoHsCv()) &&
                condition.apply(criteria.getSoCc()) &&
                condition.apply(criteria.getSoVaoSo()) &&
                condition.apply(criteria.getMoTa()) &&
                condition.apply(criteria.getNgayNganChan()) &&
                condition.apply(criteria.getNgayBdNganChan()) &&
                condition.apply(criteria.getNgayKtNganChan()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getNguoiThaoTac()) &&
                condition.apply(criteria.getLoaiNganChan()) &&
                condition.apply(criteria.getNgayCongVan()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<ChiTietNganChanCriteria> copyFiltersAre(
        ChiTietNganChanCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getIdDoiTuong(), copy.getIdDoiTuong()) &&
                condition.apply(criteria.getNgayThaoTac(), copy.getNgayThaoTac()) &&
                condition.apply(criteria.getLoaiDoiTuong(), copy.getLoaiDoiTuong()) &&
                condition.apply(criteria.getSoHsCv(), copy.getSoHsCv()) &&
                condition.apply(criteria.getSoCc(), copy.getSoCc()) &&
                condition.apply(criteria.getSoVaoSo(), copy.getSoVaoSo()) &&
                condition.apply(criteria.getMoTa(), copy.getMoTa()) &&
                condition.apply(criteria.getNgayNganChan(), copy.getNgayNganChan()) &&
                condition.apply(criteria.getNgayBdNganChan(), copy.getNgayBdNganChan()) &&
                condition.apply(criteria.getNgayKtNganChan(), copy.getNgayKtNganChan()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getNguoiThaoTac(), copy.getNguoiThaoTac()) &&
                condition.apply(criteria.getLoaiNganChan(), copy.getLoaiNganChan()) &&
                condition.apply(criteria.getNgayCongVan(), copy.getNgayCongVan()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
