package BaseConstructor;

import HandleList.ListRoomStandard;
import HandleList.ListRoomVIP;
import MainCore.Room;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Booking {
    private String bookingId;
    private String customerName;
    private String employeeName;
    private double totalPrice;
    private String typeTime;
    private int time;
    private String createAt;
    private String paymentMethod;
    private Room room;
    private String roomId;

    Scanner sc = new Scanner(System.in);

    public static ListRoomVIP listRoomVIP = new ListRoomVIP();
    public static ListRoomStandard listRoomStandard = new ListRoomStandard();

    public void printLine() {
        System.out.println();
        for (int i = 0; i < 130; i++) {
            System.out.println("=");
        }
        System.out.println();
    }

    public Booking() {

    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTypeTime() {
        return typeTime;
    }

    public void setTypeTime(String typeTime) {
        this.typeTime = typeTime;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void inputForBooking() {
        Matcher matcher;

        do {
            System.out.print("Nhap ma don dat phong (ex: BK**): ");
            setBookingId(sc.nextLine());
            String s = "^BK[0-9]{2}$";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(getBookingId());
        } while (!matcher.find());

        System.out.print("Nhap ho ten khach hang: ");
        setCustomerName(sc.nextLine());

        System.out.print("Nhap ho ten nhan vien: ");
        setEmployeeName(sc.nextLine());


        String selectTemp;
        int select;
        Matcher matcherType;
        do {
            System.out.println("+------------------------------+");
            System.out.println("|           Chon phong         |");
            System.out.println("| ------------=====------------|");
            System.out.println("| 1. Phong VIP                 |");
            System.out.println("| 2. Phong Standard            |");
//            System.out.println("| 0. Tro lai                   |");
            System.out.println("+------------------------------+");

            do {
                System.out.print("Nhap lua chon: ");
                selectTemp = sc.nextLine();
                String s = "^[0-9]{1}";
                Pattern pattern = Pattern.compile(s);
                matcherType = pattern.matcher(selectTemp);
            } while (!matcherType.find());
            select = Integer.parseInt(selectTemp);

            switch (select) {
                case 1:
                    System.out.println("Ban chon phong VIP");
                    listRoomVIP.readListRoomVIP();
                    listRoomVIP.display();

                    RoomVIP[] roomVIPs = listRoomVIP.getListRoom();
                    String roomVIPId;
                    do {
                        System.out.print("Nhap ma phong: ");
                        roomVIPId = sc.nextLine();
                        String s = "RV[0-9]{3}";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(roomVIPId);
                    } while (!matcher.find());

                    boolean check = false;

                    //Tim phong trong database
                    for (RoomVIP roomVIP : roomVIPs) {
                        String key = roomVIP.getRoomVIPId();
                        if (key.contentEquals(roomVIPId)) {
                            setRoomId(key);
                            int selectTypeTime;
                            String selectTypeTimeTemp;
                            Matcher matcherTypeTime;

                            do {
                                System.out.println("+------------------------------+");
                                System.out.println("|        Chon hinh thuc        |");
                                System.out.println("| ------------=====------------|");
                                System.out.println("| 1. Theo gio                  |");
                                System.out.println("| 2. Theo dem                  |");
                                System.out.println("| 3. Theo ngay                 |");
//                                    System.out.println("| 0. Tro lai                   |");
                                System.out.println("+------------------------------+");

                                do {
                                    System.out.print("Nhap lua chon: ");
                                    selectTypeTimeTemp = sc.nextLine();
                                    String s = "^[0-9]{1}";
                                    Pattern pattern = Pattern.compile(s);
                                    matcherTypeTime = pattern.matcher(selectTypeTimeTemp);
                                } while (!matcherTypeTime.find());
                                selectTypeTime = Integer.parseInt(selectTypeTimeTemp);

                                switch (selectTypeTime) {
                                    case 1:
                                        System.out.println("Ban chon theo gio");
                                        setTypeTime("theo gio");
                                        System.out.print("Nhap so gio: ");
                                        setTime(Integer.parseInt(sc.nextLine()));
                                        setTotalPrice(getTime() * Double.parseDouble(roomVIP.getPricePerHour()));
                                        break;
                                    case 2:
                                        System.out.println("Ban chon theo dem");
                                        setTypeTime("theo dem");
                                        setTime(1);
                                        setTotalPrice(getTime() * Double.parseDouble(roomVIP.getPricePerNight()));
                                        break;
                                    case 3:
                                        System.out.println("Ban chon theo ngay");
                                        setTypeTime("theo ngay");
                                        System.out.print("Nhap so ngay: ");
                                        setTime(Integer.parseInt(sc.nextLine()));
                                        setTotalPrice(getTime() * Double.parseDouble(roomVIP.getPricePerDay()));
                                        break;
//                                        case 0:
//                                            System.out.println("Tro lai");
//                                            break;
//                                        default:
//                                            System.out.println("Loi lua chon! Vui long nhap lai");
//                                            break;
                                }

                            } while (selectTypeTime < 1 || selectTypeTime > 3);

                            check = true;
                        }
                    }

                    if (!check)
                        System.out.printf("\u001B[41m| Khong tim thay ma phong %s |\u001B[0m \n", roomVIPId);

                    do {
                        System.out.print("Nhap ngay dat phong: ");
                        setCreateAt(sc.nextLine());
                        String s = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(getCreateAt());
                    } while (!matcher.find());

                    int selectPaymentMethod;
                    String selectPaymentMethodTemp;
                    Matcher matcherPaymentMethod;
                    do {
                        System.out.println("+-------------------------------------------+");
                        System.out.println("|        Chon phuong thuc thanh toan        |");
                        System.out.println("| ------------------=====-------------------|");
                        System.out.println("| 1. Bang tien mat                          |");
                        System.out.println("| 2. Bang the                               |");
//                        System.out.println("| 0. Tro lai                                |");
                        System.out.println("+-------------------------------------------+");

                        do {
                            System.out.print("Vui long chon: ");
                            selectPaymentMethodTemp = sc.nextLine();
                            String s = "^[0-9]{1}";
                            Pattern pattern = Pattern.compile(s);
                            matcherPaymentMethod = pattern.matcher(selectPaymentMethodTemp);
                        } while (!matcherPaymentMethod.find());
                        selectPaymentMethod = Integer.parseInt(selectPaymentMethodTemp);

                        switch (selectPaymentMethod) {
                            case 1:
                                System.out.println("Ban chon tra bang tien mat");
                                setPaymentMethod("tien mat");
                                break;
                            case 2:
                                System.out.println("Ban chon tra bang the");
                                setPaymentMethod("the");
                                break;
//                            case 0:
//                                System.out.println("Tro lai");
//                                break;
//                            default:
//                                System.out.println("Loi lua chon! Vui long chon lai");
//                                break;

                        }

                    } while (selectPaymentMethod <1 || selectPaymentMethod >2);


// ket thuc case 1 --------------------------------------------------------------------------------------

                case 2:
                    System.out.println("Ban chon phong VIP");
                    break;

//                case 0:
//                    System.out.println("Tro ra");
//                    break;
//                default:
//                    System.out.println("Loi lua chon! Vui long chon lai");
//                    break;
            }


        } while (select <1 || select >2);

    }
//------------Ket thuc input----------------------------------------------------------------------------------


    public void output() {
        System.out.printf("\n| %-15s %-20s %-20s %-20s %-25s %-15s %-30s %-20s %-23s |",
                getBookingId(), getCustomerName(), getEmployeeName(), getRoomId(), getTypeTime(), getTime(), getCreateAt(), getPaymentMethod(), getTotalPrice());
    }

    public void getLineFromFile(String line) {
        String[] str = line.split(";");
        setBookingId(str[0]);
        setCustomerName(str[1]);
        setEmployeeName(str[2]);
        listRoomVIP.readListRoomVIP();
        RoomVIP[] roomVIPs = listRoomVIP.getListRoom();
        for (RoomVIP roomVIP : roomVIPs) {
            String key = roomVIP.getRoomVIPId();
            if (key.contentEquals(str[3])) {
                setRoom(roomVIP);
                setRoomId(key);
            }
        }
        setTypeTime(str[4]);
        setTime(Integer.parseInt(str[5]));
        setCreateAt(str[6]);
        setPaymentMethod(str[7]);
        setTotalPrice(Double.parseDouble(str[8]));
    }

    public String mergeInformationToFile() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s",
                getBookingId(), getCustomerName(), getEmployeeName(), getRoomId(), getTypeTime(), getTime(), getCreateAt(), getPaymentMethod(), getTotalPrice());
    }

    public void roomDetails() {
//        printLine();
        System.out.printf("\u001B[44m| %-20s %-25s %-50s %-28s %-28s %-28s |\u001B[0m",
                "Ma phong", "Ten phong", "Chi tiet phong", "Gia moi gio", "Gia moi dem", "Gia moi ngay");
        getRoom().output();
//        printLine();
    }

}
