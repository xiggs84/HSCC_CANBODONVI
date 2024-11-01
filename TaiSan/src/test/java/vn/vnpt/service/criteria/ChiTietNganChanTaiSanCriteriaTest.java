package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class ChiTietNganChanTaiSanCriteriaTest {

    @Test
    void newChiTietNganChanTaiSanCriteriaHasAllFiltersNullTest() {
        var chiTietNganChanTaiSanCriteria = new ChiTietNganChanTaiSanCriteria();
        assertThat(chiTietNganChanTaiSanCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void chiTietNganChanTaiSanCriteriaFluentMethodsCreatesFiltersTest() {
        var chiTietNganChanTaiSanCriteria = new ChiTietNganChanTaiSanCriteria();

        setAllFilters(chiTietNganChanTaiSanCriteria);

        assertThat(chiTietNganChanTaiSanCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void chiTietNganChanTaiSanCriteriaCopyCreatesNullFilterTest() {
        var chiTietNganChanTaiSanCriteria = new ChiTietNganChanTaiSanCriteria();
        var copy = chiTietNganChanTaiSanCriteria.copy();

        assertThat(chiTietNganChanTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(chiTietNganChanTaiSanCriteria)
        );
    }

    @Test
    void chiTietNganChanTaiSanCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var chiTietNganChanTaiSanCriteria = new ChiTietNganChanTaiSanCriteria();
        setAllFilters(chiTietNganChanTaiSanCriteria);

        var copy = chiTietNganChanTaiSanCriteria.copy();

        assertThat(chiTietNganChanTaiSanCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(chiTietNganChanTaiSanCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var chiTietNganChanTaiSanCriteria = new ChiTietNganChanTaiSanCriteria();

        assertThat(chiTietNganChanTaiSanCriteria).hasToString("ChiTietNganChanTaiSanCriteria{}");
    }

    private static void setAllFilters(ChiTietNganChanTaiSanCriteria chiTietNganChanTaiSanCriteria) {
        chiTietNganChanTaiSanCriteria.idNganChan();
        chiTietNganChanTaiSanCriteria.ngayThaoTac();
        chiTietNganChanTaiSanCriteria.soHsCv();
        chiTietNganChanTaiSanCriteria.soCc();
        chiTietNganChanTaiSanCriteria.soVaoSo();
        chiTietNganChanTaiSanCriteria.moTa();
        chiTietNganChanTaiSanCriteria.ngayNganChan();
        chiTietNganChanTaiSanCriteria.ngayBdNganChan();
        chiTietNganChanTaiSanCriteria.ngayKtNganChan();
        chiTietNganChanTaiSanCriteria.trangThai();
        chiTietNganChanTaiSanCriteria.nguoiThaoTac();
        chiTietNganChanTaiSanCriteria.loaiNganChan();
        chiTietNganChanTaiSanCriteria.ngayCongVan();
        chiTietNganChanTaiSanCriteria.taiSanId();
        chiTietNganChanTaiSanCriteria.distinct();
    }

    private static Condition<ChiTietNganChanTaiSanCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdNganChan()) &&
                condition.apply(criteria.getNgayThaoTac()) &&
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
                condition.apply(criteria.getTaiSanId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<ChiTietNganChanTaiSanCriteria> copyFiltersAre(
        ChiTietNganChanTaiSanCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdNganChan(), copy.getIdNganChan()) &&
                condition.apply(criteria.getNgayThaoTac(), copy.getNgayThaoTac()) &&
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
                condition.apply(criteria.getTaiSanId(), copy.getTaiSanId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
