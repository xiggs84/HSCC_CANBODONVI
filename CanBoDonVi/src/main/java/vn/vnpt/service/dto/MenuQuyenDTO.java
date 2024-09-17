package vn.vnpt.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.vnpt.domain.MenuQuyen} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MenuQuyenDTO implements Serializable {

    private Long id;

    private String listMenu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListMenu() {
        return listMenu;
    }

    public void setListMenu(String listMenu) {
        this.listMenu = listMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuQuyenDTO)) {
            return false;
        }

        MenuQuyenDTO menuQuyenDTO = (MenuQuyenDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, menuQuyenDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MenuQuyenDTO{" +
            "id=" + getId() +
            ", listMenu='" + getListMenu() + "'" +
            "}";
    }
}
