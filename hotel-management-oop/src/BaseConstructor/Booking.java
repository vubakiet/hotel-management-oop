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
    private Room[] listRoom;

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

    public Booking(String bookingId, String customerName, String employeeName, double totalPrice, String typeTime, int time, String createAt, String paymentMethod, Room[] listRoom) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.employeeName = employeeName;
        this.totalPrice = totalPrice;
        this.typeTime = typeTime;
        this.time = time;
        this.createAt = createAt;
        this.paymentMethod = paymentMethod;
        this.listRoom = listRoom;
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

    public Room[] getListRoom() {
        return listRoom;
    }

    public void setListRoom(Room[] listRoom) {
        this.listRoom = listRoom;
    }

    public void inputForBooking() {
        Matcher matcher;

        do {
            System.out.print("Nhap ma hoa don (ex: HD**): ");
            setBookingId(sc.nextLine());
            String s = "HD[0-9]{2}";
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
            System.out.println("| 0. Tro lai                   |");
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
                    int existingRoomVIP = listRoomVIP.countRoomVIP();

                    for (int i = 0; i < existingRoomVIP; ) {
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
                                    System.out.println("| 0. Tro lai                   |");
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
                                        case 0:
                                            System.out.println("Tro lai");
                                            break;
                                        default:
                                            System.out.println("Loi lua chon! Vui long nhap lai");
                                            break;
                                    }

                                } while (selectTypeTime != 0);

                                check = true;
                            }
                        }

                        if (!check)
                            System.out.printf("\u001B[41m| Khong tim thay ma phong %s |\u001B[0m \n", roomVIPId);
                        else
                            i++;
                    }

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
//                    do {
//                        System.out.println("+-------------------------------------------+");
//                        System.out.println("|        Chon phuong thuc thanh toan        |");
//                        System.out.println("| ------------------=====-------------------|");
//                        System.out.println("| 1. Theo gio                               |");
//                        System.out.println("| 2. Theo dem                               |");
//                        System.out.println("| 3. Theo ngay                              |");
//                        System.out.println("| 0. Tro lai                                |");
//                        System.out.println("+-------------------------------------------+");
//                    }


// ket thuc case 1 --------------------------------------------------------------------------------------

                case 2:
                    System.out.println("Ban chon phong VIP");
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
//------------Ket thuc input----------------------------------------------------------------------------------

    public void getLineFromFile(String line){
        String[] str = line.split(";");
        setBookingId(str[0]);
        setCustomerName(str[1]);
        setEmployeeName(str[2]);
        setTypeTime(str[3]);
        setTime(Integer.parseInt(str[4]));
        setCreateAt(str[5]);
        setPaymentMethod(str[6]);

    }

    public static void main(String[] args) {
        Booking booking = new Booking();
        booking.inputForBooking();
    }
}
