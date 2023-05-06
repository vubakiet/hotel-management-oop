package MainCore;

import HandleList.ListEmployee;
import HandleList.ListRoomVIP;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    Scanner sc = new Scanner(System.in);

    public void mainMenu() {
        Matcher matcher;
        String selectTemp;
        int select;

        do {
            System.out.println("+---------------------------------------------+");
            System.out.println("|        Quan li khach san                    |");
            System.out.println("| -------------------=====--------------------|");
            System.out.println("| 1. Danh sach dat phong                      |");
            System.out.println("| 2. Danh sach phong                          |");
            System.out.println("| 3. Danh sach nhan vien                      |");
            System.out.println("| 4. Danh sach khach hang                     |");
            System.out.println("| 0. Thoat chuong trinh                       |");
            System.out.println("+---------------------------------------------+");

            do {
                System.out.print("Nhap lua chon: ");
                selectTemp = sc.nextLine();
                String s = "^[0-9]{1}";
                Pattern pattern = Pattern.compile(s);
                matcher = pattern.matcher(selectTemp);
            } while (!matcher.find());
            select = Integer.parseInt(selectTemp);

            switch (select) {
                case 1:
                    System.out.println("Ban chon danh sach dat phong");
                    break;
                case 2:
                    System.out.println("Ban chon danh sach phong");
                    listRoom();
                    break;
                case 3:
                    System.out.println("Ban chon danh sach nhan vien");
                    listEmployee();
                    break;
                case 4:
                    System.out.println("Ban chon danh sach khach hang");
                    break;

                case 0:
                    System.out.println("\nThoat chuong trinh");
                    break;

                default:
                    System.out.println("Lua chon loi! Vui long lua chon lai ");
                    break;
            }

        } while (select != 0);
    }

    public void listRoom() {
        Matcher matcher;
        String selectTemp;
        int select;

        do {
            System.out.println("+---------------------------------------------+");
            System.out.println("|              Danh sach phong                |");
            System.out.println("| -------------------=====--------------------|");
            System.out.println("| 1. Danh sach phong VIP                      |");
            System.out.println("| 2. Danh sach phong Standard                 |");
            System.out.println("| 0. Tro ve                                   |");
            System.out.println("+---------------------------------------------+");

            do {
                System.out.print("Nhap lua chon: ");
                selectTemp = sc.nextLine();
                String s = "^[0-9]{1}";
                Pattern pattern = Pattern.compile(s);
                matcher = pattern.matcher(selectTemp);
            } while (!matcher.find());
            select = Integer.parseInt(selectTemp);

            switch (select) {
                case 1:
                    String selectTempRV;
                    int selectRV;

                    do {
                        System.out.println("+------------------------------------------+");
                        System.out.println("|       Danh sach phong VIP                |");
                        System.out.println("| -------------------=====-----------------|");
                        System.out.println("| 1. Them thong tin phong                  |");
                        System.out.println("| 2. Sua thong tin phong                   |");
                        System.out.println("| 3. Xoa thong tin phong                   |");
                        System.out.println("| 4. Tim kiem thong tin phong              |");
                        System.out.println("| 5. Xuat danh sach phong                  |");
                        System.out.println("| 0. Tro ve                                |");
                        System.out.println("+------------------------------------------+");

                        do {
                            System.out.print("Nhap lua chon: ");
                            selectTempRV = sc.nextLine();
                            String s = "^[0-9]{1}";
                            Pattern pattern = Pattern.compile(s);
                            matcher = pattern.matcher(selectTempRV);
                        } while (!matcher.find());
                        selectRV = Integer.parseInt(selectTempRV);
                        ListRoomVIP listRoomVIP = new ListRoomVIP();
                        listRoomVIP.readListRoomVIP();

                        switch (selectRV) {
                            case 1:
                                System.out.println("Ban chon them thong tin phong ");
                                listRoomVIP.add();
                                break;
                            case 2:
                                System.out.println("Ban chon sua thong tin phong ");
                                break;
                            case 3:
                                System.out.println("Ban chon xoa thong tin phong ");
                                break;
                            case 4:
                                System.out.println("Ban chon tim kiem thong tin phong ");
                                break;
                            case 5:
                                System.out.println("Ban chon xuat thong tin phong ");
                                listRoomVIP.display();
                                break;
                            case 0:
                                System.out.println("Tro ve");
                                break;
                            default:
                                System.out.println("Lua chon loi! Vui long chon lai");
                                break;

                        }

                    } while (selectRV != 0);
                    break;
                case 2:
                    System.out.println("Hello");
                    break;
                default:
                    System.out.println("Lua chon loi! Vui long chon lai");
                    break;
            }

        } while (select != 0);

    }

    public void listEmployee() {
        Matcher matcher;
        String selectTemp;
        int select;

        do {
            System.out.println("+---------------------------------------------+");
            System.out.println("|              Danh sach nhan vien            |");
            System.out.println("| -------------------=====--------------------|");
            System.out.println("| 1. Them thong tin nhan vien                 |");
            System.out.println("| 2. Sua thong tin nhan vien                  |");
            System.out.println("| 3. Xoa thong tin nhan vien                  |");
            System.out.println("| 4. Tim kiem thong tin nhan vien             |");
            System.out.println("| 5. Xuat thong tin nhan vien                 |");
            System.out.println("| 0. Tro ve                                   |");
            System.out.println("+---------------------------------------------+");

            do {
                System.out.print("Nhap lua chon: ");
                selectTemp = sc.nextLine();
                String s = "^[0-9]{1}";
                Pattern pattern = Pattern.compile(s);
                matcher = pattern.matcher(selectTemp);
            } while (!matcher.find());
            select = Integer.parseInt(selectTemp);
            ListEmployee listEmployee = new ListEmployee();
            listEmployee.readListEmployee();

            switch (select) {
                case 1:
                    System.out.println("Ban chon them thong tin nhan vien");
                    listEmployee.add();
                    break;
                case 2:
                    System.out.println("Ban chon sua thong tin nhan vien");
                    break;
                case 3:
                    System.out.println("Ban chon xoa thong tin nhan vien");
                    break;
                case 4:
                    System.out.println("Ban chon tim kiem thong tin nhan vien");
                    break;
                case 5:
                    System.out.println("Ban chon xuat thong tin nhan vien");
                    listEmployee.display();
                    break;
                case 0:
                    System.out.println("Tro ve");
                    break;
                default:
                    System.out.println("Lua chon loi! Vui long chon lai");
                    break;
            }

        } while (select != 0);
    }
}
