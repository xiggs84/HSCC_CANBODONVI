package vn.vnpt.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class DanhMucCanBoCriteriaTest {

    @Test
    void newDanhMucCanBoCriteriaHasAllFiltersNullTest() {
        var danhMucCanBoCriteria = new DanhMucCanBoCriteria();
        assertThat(danhMucCanBoCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void danhMucCanBoCriteriaFluentMethodsCreatesFiltersTest() {
        var danhMucCanBoCriteria = new DanhMucCanBoCriteria();

        setAllFilters(danhMucCanBoCriteria);

        assertThat(danhMucCanBoCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void danhMucCanBoCriteriaCopyCreatesNullFilterTest() {
        var danhMucCanBoCriteria = new DanhMucCanBoCriteria();
        var copy = danhMucCanBoCriteria.copy();

        assertThat(danhMucCanBoCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucCanBoCriteria)
        );
    }

    @Test
    void danhMucCanBoCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var danhMucCanBoCriteria = new DanhMucCanBoCriteria();
        setAllFilters(danhMucCanBoCriteria);

        var copy = danhMucCanBoCriteria.copy();

        assertThat(danhMucCanBoCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(danhMucCanBoCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var danhMucCanBoCriteria = new DanhMucCanBoCriteria();

        assertThat(danhMucCanBoCriteria).hasToString("DanhMucCanBoCriteria{}");
    }

    private static void setAllFilters(DanhMucCanBoCriteria danhMucCanBoCriteria) {
        danhMucCanBoCriteria.idCanBo();
        danhMucCanBoCriteria.tenCanBo();
        danhMucCanBoCriteria.diaChi();
        danhMucCanBoCriteria.namSinh();
        danhMucCanBoCriteria.email();
        danhMucCanBoCriteria.soDienThoai();
        danhMucCanBoCriteria.soGiayToTuyThan();
        danhMucCanBoCriteria.tenDangNhap();
        danhMucCanBoCriteria.matKhau();
        danhMucCanBoCriteria.trangThai();
        danhMucCanBoCriteria.clientId();
        danhMucCanBoCriteria.clientSecret();
        danhMucCanBoCriteria.usernameKyso();
        danhMucCanBoCriteria.passwordKyso();
        danhMucCanBoCriteria.userLogin();
        danhMucCanBoCriteria.distinct();
    }

    private static Condition<DanhMucCanBoCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCanBo()) &&
                condition.apply(criteria.getTenCanBo()) &&
                condition.apply(criteria.getDiaChi()) &&
                condition.apply(criteria.getNamSinh()) &&
                condition.apply(criteria.getEmail()) &&
                condition.apply(criteria.getSoDienThoai()) &&
                condition.apply(criteria.getSoGiayToTuyThan()) &&
                condition.apply(criteria.getTenDangNhap()) &&
                condition.apply(criteria.getMatKhau()) &&
                condition.apply(criteria.getTrangThai()) &&
                condition.apply(criteria.getClientId()) &&
                condition.apply(criteria.getClientSecret()) &&
                condition.apply(criteria.getUsernameKyso()) &&
                condition.apply(criteria.getPasswordKyso()) &&
                condition.apply(criteria.getUserLogin()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<DanhMucCanBoCriteria> copyFiltersAre(
        DanhMucCanBoCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getIdCanBo(), copy.getIdCanBo()) &&
                condition.apply(criteria.getTenCanBo(), copy.getTenCanBo()) &&
                condition.apply(criteria.getDiaChi(), copy.getDiaChi()) &&
                condition.apply(criteria.getNamSinh(), copy.getNamSinh()) &&
                condition.apply(criteria.getEmail(), copy.getEmail()) &&
                condition.apply(criteria.getSoDienThoai(), copy.getSoDienThoai()) &&
                condition.apply(criteria.getSoGiayToTuyThan(), copy.getSoGiayToTuyThan()) &&
                condition.apply(criteria.getTenDangNhap(), copy.getTenDangNhap()) &&
                condition.apply(criteria.getMatKhau(), copy.getMatKhau()) &&
                condition.apply(criteria.getTrangThai(), copy.getTrangThai()) &&
                condition.apply(criteria.getClientId(), copy.getClientId()) &&
                condition.apply(criteria.getClientSecret(), copy.getClientSecret()) &&
                condition.apply(criteria.getUsernameKyso(), copy.getUsernameKyso()) &&
                condition.apply(criteria.getPasswordKyso(), copy.getPasswordKyso()) &&
                condition.apply(criteria.getUserLogin(), copy.getUserLogin()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
