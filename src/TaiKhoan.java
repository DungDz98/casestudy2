import java.io.Serializable;

public class TaiKhoan implements Serializable {
    private String tenTK;
    private String passTK;

    public TaiKhoan(String tenTK, String passTK) {
        this.tenTK = tenTK;
        this.passTK = passTK;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getPassTK() {
        return passTK;
    }

    public void setPassTK(String passTK) {
        this.passTK = passTK;
    }

    @Override
    public String toString() {
        return tenTK;
    }
}
