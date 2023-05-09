package MainCore;

import HandleList.ListBooking;
import HandleList.ListCustomer;
import HandleList.ListEmployee;
import HandleList.ListRoomVIP;
import HandleList.ListRoomStandard;

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
                    listBooking();
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
                    listCustomer();
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

    public void listBooking() {
        Matcher matcher;
        String selectTemp;
        int select;

        do {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|              Danh sach don dat phong             |");
            System.out.println("| -----------------------=====---------------------|");
            System.out.println("| 1. Them don dat phong                            |");
            System.out.println("| 2. Sua thong tin don dat phong                   |");
            System.out.println("| 3. Xoa thong tin don dat phong                   |");
            System.out.println("| 4. Tim kiem don dat phong                        |");
            System.out.println("| 5. Xuat danh sach don dat phong                  |");
            System.out.println("| 6. Xem chi tiet don dat phong                    |");
            System.out.println("| 0. Tro ve                                        |");
            System.out.println("+--------------------------------------------------+");

            do {
                System.out.print("Nhap lua chon: ");
                selectTemp = sc.nextLine();
                String s = "^[0-9]{1}";
                Pattern pattern = Pattern.compile(s);
                matcher = pattern.matcher(selectTemp);
            } while (!matcher.find());
            select = Integer.parseInt(selectTemp);
            ListBooking listBooking = new ListBooking();
            listBooking.readListBooking();

            switch (select) {
                case 1:
                    System.out.println("Ban chon Them don dat phong");
                    listBooking.add();
                    break;
                case 2:
                    System.out.println("Ban chon Sua thong tin don dat phong");
                    listBooking.edit();
                    break;
                case 3:
                    System.out.println("Ban chon Xoa thong tin don dat phong");
                    listBooking.remove();
                    break;
                case 4:
                    System.out.println("Ban chon Tim kiem don dat phong");
                    listBooking.find();
                    break;
                case 5:
                    System.out.println("Ban chon Xuat danh sach don dat phong");
                    listBooking.display();
                    break;
                case 6:
                    System.out.println("Ban chon Xem chi tiet don dat phong");
                    listBooking.bookingDetails();
                    break;
                case 0:
                    System.out.println("Tro ra");
                    break;
                default:
                    System.out.println("Loi lua chon! Vui long chon lai");
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
                                listRoomVIP.edit();
                                break;
                            case 3:
                                System.out.println("Ban chon xoa thong tin phong ");
                                listRoomVIP.remove();
                                break;
                            case 4:
                                System.out.println("Ban chon tim kiem thong tin phong ");
                                listRoomVIP.find();
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
                    String selectTempSD;
                    int selectSD;
                    do {
                        System.out.println("+------------------------------------------+");
                        System.out.println("|       Danh sach phong Standard           |");
                        System.out.println("| -------------------=====-----------------|");
                        System.out.println("| 1. Them thong tin phong                  |");
                        System.out.println("| 2. Sua thong tin phong                   |");
                        System.out.println("| 3. Xoa thong tin phong                   |");
                        System.out.println("| 4. Tim kiem thong tin phong              |");
                        System.out.println("| 5. Xuat danh sach phong                  |");
                        System.out.println("| 0. Tro ve                                |");
                        System.out.println("+------------------------------------------+");

                        do {
                            System.out.println("Nhap lua chon: ");
                            selectTempSD = sc.nextLine();
                            String s = "^[0-9]{1}";
                            Pattern pattern = Pattern.compile(s);
                            matcher = pattern.matcher(selectTempSD);
                        } while (!matcher.find());
                        selectSD = Integer.parseInt(selectTempSD);
                        ListRoomStandard listRoomStandard = new ListRoomStandard();
                        listRoomStandard.readListRoomStandard();

                        switch (selectSD) {
                            case 1:
                                System.out.println("Ban chon them thong tin phong ");
                                listRoomStandard.add();
                                break;
                            case 2:
                                System.out.println("Ban chon sua thong tin phong ");
                                listRoomStandard.edit();
                                break;
                            case 3:
                                System.out.println("Ban chon xoa thong tin phong ");
                                listRoomStandard.remove();
                                break;
                            case 4:
                                System.out.println("Ban chon tim kiem thong tin phong ");
                                listRoomStandard.find();
                                break;
                            case 5:
                                System.out.println("Ban chon xuat thong tin phong ");
                                listRoomStandard.display();
                                break;
                            case 0:
                                System.out.println("Tro ve");
                                break;
                            default:
                                System.out.println("Lua chon loi! Vui long chon lai");
                                break;
                        }
                    } while (selectSD != 0);
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
                    listEmployee.edit();
                    break;
                case 3:
                    System.out.println("Ban chon xoa thong tin nhan vien");
                    listEmployee.remove();
                    break;
                case 4:
                    System.out.println("Ban chon tim kiem thong tin nhan vien");
                    listEmployee.find();
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
    public void listCustomer() {
        Matcher matcher;
        String selectTemp;
        int select;

        do {
            System.out.println("+---------------------------------------------+");
            System.out.println("|              Danh sach Khach Hang           |");
            System.out.println("| -------------------=====--------------------|");
            System.out.println("| 1. Them thong tin khach hang                |");
            System.out.println("| 2. Sua thong tin khach hang                 |");
            System.out.println("| 3. Xoa thong tin khach hang                 |");
            System.out.println("| 4. Tim kiem thong tin khach hang            |");
            System.out.println("| 5. Xuat thong tin khach hang                |");
            System.out.println("| 0. Tro ve                                   |");
            System.out.println("+---------------------------------------------+");

            do {
                System.out.print("Nhap lua chon: ");
                selectTemp = sc.nextLine();
                String check = "^[0-9]{1}";
                Pattern pattern = Pattern.compile(check);
                matcher = pattern.matcher(selectTemp);
            } while (!matcher.find());
            select = Integer.parseInt(selectTemp);
            ListCustomer listCustomer = new ListCustomer();
            listCustomer.readListCustomer();

            switch (select) {
                case 1:
                    System.out.println("Ban chon them thong tin khach hang");
                    listCustomer.add();
                    break;
                case 2:
                    System.out.println("Ban chon sua thong tin khach hang");
                    listCustomer.edit();
                    break;
                case 3:
                    System.out.println("Ban chon xoa thong tin khach hang");
                    listCustomer.remove();
                    break;
                case 4:
                    System.out.println("Ban chon tim kiem thong tin khach hang");
                    listCustomer.find();
                    break;
                case 5:
                    System.out.println("Ban chon xuat thong tin khach hang");
                    listCustomer.display();
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
