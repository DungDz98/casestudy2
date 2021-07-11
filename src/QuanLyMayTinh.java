import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuanLyMayTinh {
    static long turnOver = 0;
    static Scanner sc = new Scanner(System.in);
    static File saveMT = new File("./src/saveMT.csv");
    static File doanhthu = new File("./src/doanhthu.csv");
    static File saveGD = new File("./src/saveGD.csv");
    static ArrayList<MayTinh> listMT = docMT();

    public static void menuShowMT() {
        System.out.println("\n----------Xem danh sách máy----------");
        System.out.println("1. Hiển thị máy Online");
        System.out.println("2. Hiển thị máy Offline");
        System.out.print("Mời bạn lựa chọn: ");
        int statusChoice = Integer.parseInt(sc.nextLine());
        if (statusChoice == 1) {
            showOnlineMT();
        } else {
            showOfflineMT();
        }
    }

    private static void showOnlineMT() {
        boolean checkNull = true;
        System.out.println("\n-------------------------------------");
        System.out.printf("%-10s %-20s \n", "Tên máy", "Trạng thái");
        for (MayTinh mt : listMT) {
            if (mt.getStatus().equals("Available")) {
                checkNull = false;
                System.out.printf("%5s %14s \n", "Máy " + mt.getTenMay(), mt.getStatus());
            }
        }
        System.out.println("-------------------------------------\n");
        if (checkNull) System.out.println("Không có máy nào!");
        else {
            System.out.println("Nhập tên máy muốn xem: ");
            int tenMT = Integer.parseInt(sc.nextLine());

            for (MayTinh mt : listMT) {
                if (mt.getTenMay() == tenMT) {
                    if (mt.getStatus().equals("Available")) {
                        System.out.println("\n------------------------------------------");
                        System.out.printf("%-10s %-20s %-30s\n", "Tên máy", "Thời gian chơi(h)", "Tổng tiền");
                        System.out.printf("%5s %14s %16s\n",mt.getTenMay(), mt.getTimeSuDung(), mt.tongSoTien());
                        System.out.println("------------------------------------------\n");
                    } else {
                        System.out.println("Máy này hiện không Online.");
                    }
                }
            }

        }
    }

    private static void showOfflineMT() {
        boolean checkNull = true;
        System.out.println("\n-------------------------------------");
        System.out.printf("%-10s %-20s \n", "Tên máy", "Trạng thái");
        for (MayTinh mt : listMT) {
            if (mt.getStatus().equals("Disable")) {
                checkNull = false;
                System.out.printf("%5s %14s \n", "Máy " + mt.getTenMay(), mt.getStatus());
            }
        }
        System.out.println("-------------------------------------\n");
        if (checkNull) System.out.println("Không có máy nào!");
        else {

            System.out.println("Nhập tên máy muốn xem: ");
            int tenMT = Integer.parseInt(sc.nextLine());

            for (MayTinh mt : listMT) {
                if (mt.getTenMay() == tenMT) {
                    if (mt.getStatus().equals("Disable")) {
                        while (true) {
                            System.out.println("Máy đang Offline. Bạn muốn bật hay không?");
                            System.out.println("1. Bật");
                            System.out.println("2. Không");
                            int choice = Integer.parseInt(sc.nextLine());
                            if (choice == 1) {
                                mt.setStatus("Available");
                                mt.setStartTime(new Date());
                                ghiMT();
//                                ghiTimeStart(mt.getTenMay());
                                mt.start();
                                break;
                            } else if (choice == 2) {
                                break;
                            } else {
                                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại");
                            }
                        }
                    } else System.out.println("Máy này hiện không Offline.");
                }
            }

        }
    }

    public static void themMayTinh() {
        System.out.println("\n----------Thêm máy----------");
        int tenMay = getTenMay();
        System.out.println("----------------------------");
        System.out.println("Thêm máy thành công!");
        listMT.add(new MayTinh(tenMay));
        ghiMT();
    }


    public static void suaMayTinh() {
        System.out.println("\n-------------Sửa thông tin máy tính-----------------");
        System.out.printf("%-10s %-20s \n", "Tên máy", "Trạng thái");
        for (MayTinh mt : listMT)
            System.out.printf("%5s %14s \n", "Máy " + mt.getTenMay(), mt.getStatus());
        System.out.println("----------------------------------------------\n");
        System.out.println("Nhập tên máy muốn sửa: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập tên máy mới: ");
        int idNew = Integer.parseInt(sc.nextLine());
        for (MayTinh mt : listMT)
            if (mt.getTenMay() == id)
                mt.setTenMay(idNew);
            else if (mt.getTenMay() == idNew)
                mt.setTenMay(id);
        ghiMT();
        System.out.println("Sửa thông tin thành công!");
    }

    public static void xoaMayTinh() {
        System.out.println("\n-------------Xóa máy tính-----------------");
        System.out.printf("%-10s %-20s \n", "Tên máy", "Trạng thái");
        for (MayTinh mt : listMT)
            System.out.printf("%5s %14s \n", "Máy " + mt.getTenMay(), mt.getStatus());
        System.out.println("------------------------------------------\n");
        System.out.println("Nhập tên máy muốn xóa: ");
        int id = Integer.parseInt(sc.nextLine());
        while (true) {
            System.out.println("\nBạn có chắc chắn muốn xóa máy " + id + " hay không?");
            System.out.println("1. Xóa");
            System.out.println("2. Không xóa");
            int delChoice = Integer.parseInt(sc.nextLine());
            if (delChoice == 1) {
                for (int i = 0; i < listMT.size(); i++) {
                    if (listMT.get(i).getTenMay() == id)
                        listMT.remove(i);
                }
                ghiMT();
                System.out.println("Xóa máy thành công!");
                break;
            } else if (delChoice == 2) {
                break;
            } else {
                System.out.println("Số không hợp lệ");
            }
        }

    }

    private static String getStatus() {
        while (true) {
            try {
                System.out.println("Nhập trạng thái máy (Available/Disable): ");
                String status = sc.nextLine();
                if (status.equals("Available") || status.equals("Disable"))
                    return status;
                else
                    throw new StatusCondition();
            } catch (StatusCondition e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getTenMay() {
        while (true) {
            try {
                System.out.println("Nhập tên máy: ");
                int tenMay = Integer.parseInt(sc.nextLine());
                for (MayTinh mt : listMT)
                    if (mt.getTenMay() == tenMay)
                        throw new NameCondition();
                return tenMay;
            } catch (NameCondition e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void tinhTienMay() {
        System.out.println("\n----------Tính tiền----------");
        System.out.println("1. Tính tiền");
        System.out.println("2. Thêm dịch vụ");
        System.out.print("Lựa chọn: ");
        int moneyChoice = Integer.parseInt(sc.nextLine());
        if (moneyChoice == 1) {
            showOnlineMT();
            boolean checkNull = true;
            for (MayTinh mt : listMT) {
                if (mt.getStatus().equals("Available")) {
                    checkNull = false;
//                    System.out.println("Máy " + mt.getTenMay() + "\t" + mt.getStatus());
                }
            }
            if (!checkNull) {
                boolean checkStatus = false;
                while (true) {
                    System.out.println("Nhập lại số máy: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("\n-------------------------------------");
                    System.out.println("Bạn muốn thanh toán phải không?");
                    System.out.println("1. Thanh toán");
                    System.out.println("2. Trở về");
                    int moneySubChoice = Integer.parseInt(sc.nextLine());
                    System.out.println("-------------------------------------");
                    if (moneySubChoice == 1) {
                        for (MayTinh mt : listMT) {
                            if (mt.getStatus().equals("Available"))
                                if (mt.getTenMay() == id) {
                                    mt.setEndTime(new Date());
                                    System.out.println("* Đã thanh toán: " + mt.tongSoTien() + " VNĐ");
                                    turnOver += mt.tongSoTien();
                                    checkStatus = true;
                                    mt.setStatus("Disable");
                                    ghiGD(mt);
                                    ghiMT();
//                                    ghiTimeEnd(mt.getTenMay());
                                    mt.setTimeSuDung(0);
                                    mt.setTienDichVu(0);
                                    break;
                                }
                        }
                        if (checkStatus) {
                            System.out.println("Thanh toán thành công!");
                            break;
                        } else
                            System.out.println("Thanh toán thất bại! Máy này hiện không online. Mời nhập lại");
                    } else if (moneySubChoice == 2) {
                        break;
                    } else {
                        System.out.println("Không có thao tác này. Mời nhập lại");
                    }
                }
            }

        } else if (moneyChoice == 2) {
            showOnlineMT();
            boolean checkNull = true;
            for (MayTinh mt : listMT) {
                if (mt.getStatus().equals("Available")) {
                    checkNull = false;
//                    System.out.println("Máy " + mt.getTenMay() + "\t" + mt.getStatus());
                }
            }
            if (!checkNull) {
                System.out.println("Nhập lại số máy: ");
                int id = Integer.parseInt(sc.nextLine());
                if (ThemDichVu.listDV.size() < 1)
                    System.out.println("\nHiện tại chưa có dịch vụ nào!");
                else {
                    System.out.println("\n---------Menu dịch vụ-----------");
                    for (DichVu dv : ThemDichVu.listDV)
                        System.out.println(dv + " VNĐ");
                    System.out.println("--------------------------------\n");
                }
                int dvChoice = 0;
                int bonus = 0;
                boolean checkDV = false;
                while (true) {
                    System.out.println("1. Chọn dịch vụ");
                    System.out.println("2. Dừng lại");
                    System.out.println("Chọn thao tác(Chọn 1 hoặc 2): ");
                    dvChoice = Integer.parseInt(sc.nextLine());
                    if (dvChoice == 2) {
                        System.out.println("Hi vọng quý khách hài lòng với dịch vụ!");
                        break;
                    }
                    System.out.println("\nNhập tên dịch vụ: ");
                    String tenDichVu = sc.nextLine();
                    for (DichVu dv : ThemDichVu.listDV)
                        if (dv.getTenDichVu().equals(tenDichVu)) {
                            checkDV = true;
                            bonus += dv.getDonGia();
                        }
                    if (checkDV) System.out.println("\nThêm dịch vụ thành công!");
                    else System.out.println("\nKhông có dịch vụ này!");
                }
                for (MayTinh mt : listMT) {
                    if (mt.getTenMay() == id) {
                        mt.setTienDichVu(bonus);
                    }
                }

            }
        }
    }

    public static long tongDoanhThu() {
        return turnOver;
    }

    public static ArrayList<MayTinh> docMT() {
        ArrayList<MayTinh> listMTNew = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(saveMT));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                if (str.length >= 2)
                    listMTNew.add(new MayTinh(Integer.parseInt(str[0].trim()), str[1].trim()));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listMTNew;
    }

    public static void ghiGD(MayTinh mt) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(saveGD, true));
            bw.write(mt.toGhiGD());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void ghiMT() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(saveMT));
            for (MayTinh mt : listMT) {
                bw.write(mt.toGhi());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ghiDT() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(doanhthu, true));
            bw.write("Ngày " + new Date().getDate() + "/" + (new Date().getMonth() + 1) + ": " + (int) tongDoanhThu() + " VNĐ");
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public static void ghiTimeStart(int name) {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter("./src/mayTinh.txt", true));
//            bw.write(name + "," + "start"+ "," + LocalDateTime.now().toString());
//            bw.newLine();
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void ghiTimeEnd(int name) {
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter("./src/mayTinh.txt", true));
//            bw.write(name + "," + "end"+ "," + LocalDateTime.now().toString());
//            bw.newLine();
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static ArrayList<String> docTime(int name) throws IOException {
//        String nameX = String.valueOf(name).concat(",");
//        String line = "";
//        BufferedReader br = new BufferedReader(new FileReader("./src/mayTinh.txt"));
//        ArrayList<String> list = new ArrayList<>();
//        while ((line = br.readLine()) != null) {
//            if (line.contains(nameX)){
//                list.add(line);
//            }
//        }
//        return list;
//    }

    public static void getDoanhThu() {
        Pattern p = Pattern.compile("^Ngày [0-9]+/[0-9]+: (.*?) VNĐ$");
        String line = "";
        Matcher m;
        long turnOver = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/doanhthu.csv"));
            while ((line = br.readLine()) != null) {
                m = p.matcher(line);
                while (m.find()) {
                    turnOver += Long.parseLong(m.group(1).trim());
                }

            }
            System.out.println("\n*********************************************************");
            System.out.println("        Tổng doanh thu là : " + turnOver + " VNĐ      ");
            System.out.println("*********************************************************");
        } catch (IOException e) {
            System.out.println("Lỗi File");
        }
    }

    public static void getDoanhThuTheoNgay() {
        System.out.println("\n-----Xem doanh thu theo ngày-----");
        System.out.println("Nhập ngày bắt đầu: ");
        String startDay = sc.nextLine();
        System.out.println("Nhập ngày kết thúc: ");
        String endDay = sc.nextLine();
        System.out.println("---------------------------------");
        Pattern p = Pattern.compile("^Ngày [" + startDay +  "-"  + endDay + "]+/[0-9]+: (.*?) VNĐ$");
        String line = "";
        Matcher m;
        long turnOverPerDay = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/doanhthu.csv"));
            while ((line = br.readLine()) != null) {
                m = p.matcher(line);
                while (m.find()) {
                    turnOverPerDay += Long.parseLong(m.group(1).trim());
                }

            }
            System.out.println("* Tổng doanh thu từ ngày " + startDay + " đến ngày " + endDay + " là : " + turnOverPerDay + " VNĐ *");
        } catch (IOException e) {
            System.out.println("Lỗi File");
        }
    }
}
