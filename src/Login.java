import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    static ArrayList<TaiKhoanHeThong> listTKHT = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void login() {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t------PHẦN MỀM QUẢN LÝ PHÒNG NET------");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t-----------Hệ thống-----------");
        int logChoice = 0;
        while (true) {
            try {
                System.out.println("********** Login **********");
                System.out.println("*  1. Đăng nhập           *");
                System.out.println("*  2. Đăng ký             *");
                System.out.println("*  3. Thoát chương trình  *");
                System.out.println("***************************");
                System.out.println();
                System.out.print("\n---------> Lựa chọn của bạn là: ");
                logChoice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Lựa chọn không hợp lệ. Mời chọn lại!");
            }
            switch (logChoice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }

    }

    private static void signIn() {
        String name, pass;
        while (true) {
            boolean check = false;
            try {
                listTKHT = docFile();
            } catch (Exception e) {
                System.out.println("Hiện tại chưa có tài khoản nào. Xin hãy đăng ký tài khoản trước!");
                break;
            }
            System.out.println();
            System.out.println("---------Đăng nhập---------");
            System.out.println("Nhập tên tài khoản: ");
            name = sc.nextLine();
            System.out.println("Nhập password: ");
            pass = sc.nextLine();
            System.out.println("---------------------------");
            if (name.equals("") || pass.equals(""))
                System.out.println("Tài khoản và Password không được để trống. Nhập lại");
            else {
                for (TaiKhoanHeThong tk : listTKHT) {
                    if (tk.getName().equals(name) && tk.getPass().equals(pass)) {
                        check = true;
                        System.out.println("Đăng nhập thành công!");
                        menu();
                        break;
                    }
                }
                if (!check) System.out.println("Tên đăng nhập hoặc mật khẩu không đúng. Thử lại");
                else break;
            }
        }
    }

    private static void signUp() {
        String name, pass;
        while (true) {
            try {
                try {
                    listTKHT = docFile();
                } catch (Exception e) {
                    System.out.println("Hiện tại chưa có tài khoản nào. Mời bạn đăng ký tài khoản!");
                }
                System.out.println();
                System.out.println("---------Đăng ký---------");
                System.out.println("Nhập tên tài khoản: ");
                name = sc.nextLine();
                System.out.println("Nhập password: ");
                pass = sc.nextLine();
                System.out.println("-------------------------");
                if (name.equals("") || pass.equals(""))
                    System.out.println("Tài khoản và Password không được để trống. Nhập lại");
                else {
                    for (TaiKhoanHeThong tk : listTKHT) {
                        if (tk.getName().equals(name))
                            throw new NameCondition();
                    }
                    listTKHT.add(new TaiKhoanHeThong(name, pass));
                    System.out.println("Đăng ký thành công!");
                    break;
                }
            } catch (NameCondition e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ghiFile();
//        listTKHT = docFile();
    }

    public static ArrayList<TaiKhoanHeThong> docFile() throws Exception {
        ArrayList<TaiKhoanHeThong> listTKHTNew = new ArrayList<>();
        ObjectInputStream ois = null;
        ois = new ObjectInputStream(new FileInputStream("./src/taikhoanhethong.txt"));
        listTKHTNew = (ArrayList<TaiKhoanHeThong>) ois.readObject();
        ois.close();
        return listTKHTNew;
    }

    public static void ghiFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/taikhoanhethong.txt"));
            oos.writeObject(listTKHT);
            oos.close();
        } catch (IOException e) {
            System.out.println("Lỗi ghi file");
        }
    }

    public static void menu() {
        while (true) {
            System.out.println();
            System.out.println("\t\t\t\t\t\t\t##=====================================MENU=====================================##");
            System.out.println("\t\t\t\t\t\t\t|| 1. Hiển thị danh sách máy            || 7. Tính tiền                         ||");
            System.out.println("\t\t\t\t\t\t\t|| 2. Thêm 1 máy mới                    || 8. Quản lý tài khoản đăng nhập       ||");
            System.out.println("\t\t\t\t\t\t\t|| 3. Sửa đổi thông tin máy             || 9. Chốt doanh thu của phiên          ||");
            System.out.println("\t\t\t\t\t\t\t|| 4. Xóa 1 máy                         || 10. Tổng doanh thu từ trước đến nay  ||");
            System.out.println("\t\t\t\t\t\t\t|| 5. Thêm dịch vụ                      || 11. Check doanh thu theo ngày        ||");
            System.out.println("\t\t\t\t\t\t\t|| 6. Chỉnh sửa tính tiền theo giờ      || 12. Đăng xuất                        ||");
            System.out.println("\t\t\t\t\t\t\t##====================================*****=====================================##");
            System.out.println();
            System.out.print("\t\t\t\t\t\t\t---------> Lựa chọn của bạn là: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    QuanLyMayTinh.menuShowMT();
                    break;
                case 2:
                    QuanLyMayTinh.themMayTinh();
                    break;
                case 3:
                    QuanLyMayTinh.suaMayTinh();
                    break;
                case 4:
                    QuanLyMayTinh.xoaMayTinh();
                    break;
                case 5:
                    ThemDichVu.themDV();
                    break;
                case 6:
                    while(true) {
                        try {
                            System.out.println("\n-----Sửa tiền theo giờ-----");
                            System.out.println("Nhập số tiền muốn thay đổi: ");
                            int moneyOfHours = Integer.parseInt(sc.nextLine());
                            MayTinh.setTienTheoGio(moneyOfHours);
                            System.out.println("---------------------------");
                            System.out.println("Thao tác thành công!");
                            break;
                        } catch (Exception e) {
                            System.out.println("\nĐịnh dạng không hợp lệ! Hãy nhập số nguyên");
                        }
                    }
                    break;
                case 7:
                    QuanLyMayTinh.tinhTienMay();
                    break;
                case 8:
                    QuanLyTaiKhoan.quanLyTK();
                    break;
                case 9:
//                    long turnOver = QuanLyMayTinh.tongDoanhThu();
//                    System.out.println("Tổng doanh thu trong ngày là: " + turnOver);
                    QuanLyMayTinh.ghiDT();
                    System.out.println("\n-----Chốt doanh thu-----");
                    System.out.println("=> Đã chốt doanh thu của phiên! <=");
                    System.out.println("---------------------------");
                    break;
                case 10:
//                    try {
//                        for (String x : QuanLyMayTinh.docTime(1))
//                            System.out.println(x);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    QuanLyMayTinh.getDoanhThu();
                    break;
                case 11:
                    QuanLyMayTinh.getDoanhThuTheoNgay();
                    break;
                case 12:
                    login();
                    break;
            }
        }
    }
}
