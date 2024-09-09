package vn.vnpt.domain.enumeration;

/**
 * The LoaiGiayTo enumeration.
 */
public enum LoaiGiayTo {
    Cmnd("Chứng minh nhân dân"),
    Cccd("Căn cước công dân"),
    Cc("Căn cước"),
    Hc("Hộ chiếu"),
    K("Giấy tờ hợp lệ khác");

    private final String value;

    LoaiGiayTo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
