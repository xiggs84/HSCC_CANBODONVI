package vn.vnpt.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link vn.vnpt.domain.MenuQuyen} entity. This class is used
 * in {@link vn.vnpt.web.rest.MenuQuyenResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /menu-quyens?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MenuQuyenCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter listMenu;

    private Boolean distinct;

    public MenuQuyenCriteria() {}

    public MenuQuyenCriteria(MenuQuyenCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.listMenu = other.optionalListMenu().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public MenuQuyenCriteria copy() {
        return new MenuQuyenCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getListMenu() {
        return listMenu;
    }

    public Optional<StringFilter> optionalListMenu() {
        return Optional.ofNullable(listMenu);
    }

    public StringFilter listMenu() {
        if (listMenu == null) {
            setListMenu(new StringFilter());
        }
        return listMenu;
    }

    public void setListMenu(StringFilter listMenu) {
        this.listMenu = listMenu;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MenuQuyenCriteria that = (MenuQuyenCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(listMenu, that.listMenu) && Objects.equals(distinct, that.distinct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listMenu, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MenuQuyenCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalListMenu().map(f -> "listMenu=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
