package vn.vnpt.domain.enumeration;

/**
 * The GioiTinh enumeration.
 */
public enum GioiTinh {
    Nam,
    Nu("Nữ");

    private String value;

    GioiTinh() {}

    GioiTinh(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
