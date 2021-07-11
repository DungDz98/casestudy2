public class DichVu {
    private String tenDichVu;
    private int donGia;

    public DichVu(String tenDichVu, int donGia) {
        this.tenDichVu = tenDichVu;
        this.donGia = donGia;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return tenDichVu + ", " + donGia;
    }

}
