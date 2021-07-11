import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class MayTinh extends Thread{
    private int tenMay;
    private String status = "Disable";
    private int timeSuDung = 0;
    static private int tienTheoGio = 4000;
    private long tienDichVu = 0;
    private Date startTime;
    private Date endTime;

    public MayTinh(int tenMay) {
        this.tenMay = tenMay;
    }

    public MayTinh(int tenMay, String status) {
        this.tenMay = tenMay;
        this.status = status;
    }

    public MayTinh(int tenMay, int timeSuDung, Date startTime, Date endTime) {
        this.tenMay = tenMay;
        this.timeSuDung = timeSuDung;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getTenMay() {
        return tenMay;
    }

    public void setTenMay(int tenMay) {
        this.tenMay = tenMay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTimeSuDung() {
        return timeSuDung;
    }

    public void setTimeSuDung(int timeSuDung) {
        this.timeSuDung = timeSuDung;
    }

    public int getTienTheoGio() {
        return tienTheoGio;
    }

    public static void setTienTheoGio(int ttg) {
        tienTheoGio = ttg;
    }

    public long getTienDichVu() {
        return tienDichVu;
    }

    public void setTienDichVu(long tienDichVu) {
        this.tienDichVu = tienDichVu;
    }

    public long tongSoTien() {
        return this.getTienTheoGio() * this.getTimeSuDung() + this.getTienDichVu();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "May" + tenMay + '\'' +
                ", status='" + status + '\'' +
                ", timeSuDung=" + timeSuDung +
                '}';
    }

    public String toGhiGD() {
        return tenMay + ", " + timeSuDung  + ", " + tongSoTien() + ", " + startTime + ", " + endTime;
    }

    public String toGhi() {
        return tenMay + ", " + getStatus();
    }


    @Override
    public void run() {
        while (status.equals("Available")) {
            try {
                Thread.sleep(6000);
                this.timeSuDung++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
