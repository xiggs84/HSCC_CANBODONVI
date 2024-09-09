package vn.vnpt.domain.enumeration;

/**
 * The LoaiDuongSu enumeration.
 */
public enum LoaiDuongSu {
    CaNhan("Cá nhân"),
    ToChuc("Tổ chức"),
    ToChucTinDung("Tổ chức tín dụng");

    private final String value;

    LoaiDuongSu(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LoaiDuongSu fromString(String value) {
        for (LoaiDuongSu loai : LoaiDuongSu.values()) {
            if (loai.name().equalsIgnoreCase(value)) {
                return loai;
            }
        }
        throw new IllegalArgumentException("Không tìm thấy giá trị enum cho: " + value);
    }
}
