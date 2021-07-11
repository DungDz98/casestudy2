import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyTaiKhoan {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<TaiKhoan> listTK = new ArrayList<>();

    public static void quanLyTK() {
        while (true) {
            menuTK();
            int accChoice = Integer.parseInt(sc.nextLine());
            switch (accChoice) {
                case 1:
                    themTK();
                    break;
                case 2:
                    suaTK();
                    break;
                case 3:
                    xoaTK();
                    break;
                case 4:
                    showListTK();
                    break;
            }
            if (accChoice == 5)
                break;
        }
    }

    private static void menuTK() {
        System.out.println("\n/-----Quản lý tài khoản-----\\");
        System.out.println("|  1. Thêm mới tài khoản    |");
        System.out.println("|  2. Sửa tài khoản         |");
        System.out.println("|  3. Xóa tài khoản         |");
        System.out.println("|  4. Danh sách tài khoản   |");
        System.out.println("|  5. Trở về                |");
        System.out.println("|___________________________|");
        System.out.println();
        System.out.print("---------->Mời bạn chọn lựa: ");
    }

    public static void themTK() {
        listTK = docTK();
        String tenTK, passTK;
        while (true) {
            try {
                System.out.println("\nNhập tên tài khoản: ");
                tenTK = sc.nextLine();
                System.out.println("Nhập password: ");
                passTK = sc.nextLine();
                if (tenTK.equals("") || passTK.equals(""))
                    System.out.println("Tài khoản và Password không được để trống. Nhập lại");
                else {
                    for (TaiKhoan tk : listTK) {
                        if (tk.getTenTK().equals(tenTK))
                            throw new NameCondition();
                    }
                    listTK.add(new TaiKhoan(tenTK, passTK));
                    break;
                }
            } catch (NameCondition e) {
                System.out.println(e.getMessage());
            }

        }
        ghiTK();
        showListTK();
    }

    private static void showListTK() {
        listTK = docTK();
        System.out.println();
        if (listTK.size() > 0) {
            System.out.println("---------------------------");
            System.out.printf("%-10s %-20s \n", "Số thứ tự", "Tên tài khoản");
            for (int i = 0; i < listTK.size(); i++)
                System.out.printf("%5d %14s \n", (i + 1), listTK.get(i));
            System.out.println("---------------------------");
        } else {
            System.out.println("Chưa có tài khoản nào!");
        }
    }

    public static void suaTK() {
        showListTK();
        if (listTK.size() > 0) {
            System.out.println("\nNhập số của tài khoản muốn sửa: ");
            int ID = Integer.parseInt(sc.nextLine());
            System.out.println("\n----------------------------------------");
            System.out.println("Tên đăng nhập: " + listTK.get((ID - 1)) + ", Mật khẩu: " + listTK.get((ID - 1)).getPassTK());
            System.out.println("----------------------------------------\n");

            while (true) {
                try {
                    System.out.println("Nhập tên tài khoản mới: ");
                    String tenTK = sc.nextLine();
                    System.out.println("Nhập password mới: ");
                    String passTK = sc.nextLine();
                    if (tenTK.equals("") || passTK.equals(""))
                        System.out.println("Tài khoản hoặc Password không được để trống. Nhập lại");
                    else {
                        for (TaiKhoan tk : listTK) {
                            if (tk.getTenTK().equals(tenTK))
                                throw new NameCondition();
                        }
                        listTK.set((ID - 1), new TaiKhoan(tenTK, passTK));
                        break;
                    }
                } catch (NameCondition e) {
                    System.out.println(e.getMessage());
                }
            }
            ghiTK();
        } else {
            System.out.println("Thao tác thất bại do chưa có tài khoản nào!");
        }
    }

    public static void xoaTK() {
        showListTK();
        if (listTK.size() > 0) {
            System.out.println("\nNhập số của tài khoản muốn xóa: ");
            int ID = Integer.parseInt(sc.nextLine());
            while (true) {
                System.out.println("Bạn có chắc chắn muốn xóa?");
                System.out.println("1. Xóa");
                System.out.println("2. Không Xóa");
                int delChoice = Integer.parseInt(sc.nextLine());
                if (delChoice == 1) {
                    listTK.remove(ID - 1);
                    break;
                } else if (delChoice == 2) {
                    break;
                } else {
                    System.out.println("SỐ không hợp lệ, nhập lại");
                }
            }
            ghiTK();
        } else {
            System.out.println("Thao tác thất bại do chưa có tài khoản nào!");
        }

    }

    public static ArrayList<TaiKhoan> docTK() {
        ArrayList<TaiKhoan> listTK = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/saveTK.txt"));
            listTK = (ArrayList<TaiKhoan>) ois.readObject();
            ois.close();
        } catch (EOFException e) {
            System.out.println("Hiện tại chưa có tài khoản nào. Vui lòng thêm tài khoản!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTK;
    }

    public static void ghiTK() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/saveTK.txt"));
            oos.writeObject(listTK);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
