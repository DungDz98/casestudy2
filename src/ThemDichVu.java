import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ThemDichVu {
    static Scanner sc = new Scanner(System.in);
    static File saveDV = new File("./src/saveDV.csv");
    static ArrayList<DichVu> listDV = docDichVu();

    public static void themDV() {
        System.out.println("\n-------Thêm dịch vụ--------");
        String tenDichVu = getTenDichVu();
        System.out.println("Nhập đơn giá dịch vụ: ");
        int donGia = Integer.parseInt(sc.nextLine());
        System.out.println("---------------------------");
        System.out.println("Thêm dịch vụ thành công!");
        listDV.add(new DichVu(tenDichVu, donGia));

        try {
            ghiDichVu();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String getTenDichVu() {
        while (true) {
            try {
                System.out.println("Nhập tên dịch vụ: ");
                String tenDichVu = sc.nextLine();
                for (DichVu dv : listDV) {
                    if (dv.getTenDichVu().equals(tenDichVu))
                        throw new InterruptedException();
                }
                return tenDichVu;
            } catch (InterruptedException e) {
                System.out.println("\nDịch vụ này đã có! Nhập dịch vụ khác\n");
            }

        }

    }

    public static void xemDV() {
        if (listDV.size() < 1)
            System.out.println("Hiện tại chưa có dịch vụ nào!");
        else {
            for (DichVu dv : listDV)
                System.out.println(dv + " VNĐ");
        }
    }

    private static ArrayList<DichVu> docDichVu() {
        ArrayList<DichVu> listDVnew = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(saveDV));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                if (str.length >= 2) {
                    listDVnew.add(new DichVu(str[0].trim(), Integer.parseInt(str[1].trim())));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            return listDVnew;
    }

    private static void ghiDichVu() throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(saveDV));
            for (DichVu dv : listDV) {
                bw.write(dv.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bw.close();
        }
    }
}
