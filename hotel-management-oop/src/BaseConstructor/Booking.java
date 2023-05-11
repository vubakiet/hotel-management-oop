package BaseConstructor;

import HandleList.ListCustomer;
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
    private String fromTime;
    private String toTime;

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

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
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

        do {
            System.out.print("Nhap ho ten khach hang: ");
            setCustomerName(sc.nextLine());
            String s = "[^0-9]";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(getCustomerName());
        } while (!matcher.find());

        do {
            System.out.print("Nhap ho ten nhan vien: ");
            setEmployeeName(sc.nextLine());
            String s = "[^0-9]";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(getEmployeeName());
        } while (!matcher.find());


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
                        String keyStatusRV = roomVIP.getStatusRoomVIP().toLowerCase();
                        String statusRVAvailable = "available";
                        if (key.contentEquals(roomVIPId) && keyStatusRV.contentEquals(statusRVAvailable.toLowerCase())) {
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
                                Matcher matcherTheTime;

                                switch (selectTypeTime) {
                                    case 1:
                                        System.out.println("Ban chon theo gio");
                                        setTypeTime("theo gio");
                                        System.out.print("Nhap so gio: ");
                                        setTime(Integer.parseInt(sc.nextLine()));
                                        System.out.print("Nhap gio nhan phong: ");
                                        setFromTime(sc.nextLine());
                                        System.out.print("Nhap gio tra phong: ");
                                        setToTime(sc.nextLine());
                                        roomVIP.setStatusRoomVIP("booking");
                                        listRoomVIP.updateListRoomVIP();
                                        setTotalPrice(getTime() * Double.parseDouble(roomVIP.getPricePerHour()));
                                        break;
                                    case 2:
                                        System.out.println("Ban chon theo dem");
                                        setTypeTime("theo dem");
                                        setTime(1);
                                        String theTimeTemp;

                                        do {
                                            System.out.print("Nhap ngay nhan phong (ex: **/**/****): ");
                                            theTimeTemp = sc.nextLine();
                                            String s = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
                                            Pattern pattern = Pattern.compile(s);
                                            matcherTheTime = pattern.matcher(theTimeTemp);
                                        } while (!matcherTheTime.find());
                                        setFromTime(theTimeTemp);
                                        setToTime("10 gio trua hom sau");
                                        roomVIP.setStatusRoomVIP("booking");
                                        listRoomVIP.updateListRoomVIP();
                                        setTotalPrice(getTime() * Double.parseDouble(roomVIP.getPricePerNight()));
                                        break;
                                    case 3:
                                        System.out.println("Ban chon theo ngay");
                                        setTypeTime("theo ngay");
                                        System.out.print("Nhap so ngay: ");
                                        setTime(Integer.parseInt(sc.nextLine()));
                                        String fromTimeTemp;
                                        String toTimeTemp;

                                        do {
                                            System.out.print("Nhap ngay nhan phong (ex: **/**/****): ");
                                            fromTimeTemp = sc.nextLine();
                                            String s = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
                                            Pattern pattern = Pattern.compile(s);
                                            matcherTheTime = pattern.matcher(fromTimeTemp);
                                        } while (!matcherTheTime.find());
                                        setFromTime(fromTimeTemp);

                                        do {
                                            System.out.print("Nhap ngay tra phong (ex: **/**/****): ");
                                            toTimeTemp = sc.nextLine();
                                            String s = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
                                            Pattern pattern = Pattern.compile(s);
                                            matcherTheTime = pattern.matcher(toTimeTemp);
                                        } while (!matcherTheTime.find());
                                        setToTime(toTimeTemp);
                                        roomVIP.setStatusRoomVIP("booking");
                                        listRoomVIP.updateListRoomVIP();
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

                    if (!check) {
                        System.out.printf("\u001B[41m| Khong tim thay ma phong %s Hoac phong %s da duoc dat |\u001B[0m \n", roomVIPId, roomVIPId);
                        break;
                    }
                    else{
                        do {
                            System.out.print("Nhap ngay dat phong (ex: **/**/****): ");
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

                        } while (selectPaymentMethod < 1 || selectPaymentMethod > 2);

                        ListCustomer listCustomer = new ListCustomer();
                        listCustomer.readListCustomer();
                        for (int i = 0; i < listCustomer.countCustomer(); i++) {
                            String name = listCustomer.getCustomerName(i);
                            String type = listCustomer.getTypeCustomer(i);
                            String customerNameTemp = getCustomerName().toLowerCase();
                            if (customerNameTemp.equals(name.toLowerCase()) && type.equals("VIP")) {
                                System.out.println("Giam 10% cho Khach hang VIP");
                                totalPrice = totalPrice - totalPrice * 0.1;
                                break;
                            }
                        }

                        break;
                    }




// ket thuc case 1 --------------------------------------------------------------------------------------

                case 2:
                    System.out.println("Ban chon phong Standard");
                    listRoomStandard.readListRoomStandard();
                    listRoomStandard.display();

                    RoomStandard[] roomStandards = listRoomStandard.getListRoom();
                    String roomStandardId;
                    do {
                        System.out.print("Nhap ma phong: ");
                        roomStandardId = sc.nextLine();
                        String s = "RS[0-9]{3}";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(roomStandardId);
                    } while (!matcher.find());

                    boolean check2 = false;

                    //Tim phong trong database
                    for (RoomStandard roomStandard : roomStandards) {
                        String key = roomStandard.getRoomStandardId();
                        String keyStatusRS = roomStandard.getStatusRoomStandard().toLowerCase();
                        String statusRSAvailable = "available";
                        if (key.contentEquals(roomStandardId) && keyStatusRS.contentEquals(statusRSAvailable.toLowerCase())) {
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
                                Matcher matcherTheTime;

                                switch (selectTypeTime) {
                                    case 1:
                                        System.out.println("Ban chon theo gio");
                                        setTypeTime("theo gio");
                                        System.out.print("Nhap so gio: ");
                                        setTime(Integer.parseInt(sc.nextLine()));
                                        System.out.print("Nhap gio nhan phong: ");
                                        setFromTime(sc.nextLine());
                                        System.out.print("Nhap gio tra phong: ");
                                        setToTime(sc.nextLine());
                                        roomStandard.setStatusRoomStandard("booking");
                                        listRoomStandard.updateListRoomStandard();
                                        setTotalPrice(getTime() * Double.parseDouble(roomStandard.getPricePerHour()));
                                        break;
                                    case 2:
                                        System.out.println("Ban chon theo dem");
                                        setTypeTime("theo dem");
                                        setTime(1);
                                        String theTimeTemp;

                                        do {
                                            System.out.print("Nhap ngay nhan phong (ex: **/**/****): ");
                                            theTimeTemp = sc.nextLine();
                                            String s = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
                                            Pattern pattern = Pattern.compile(s);
                                            matcherTheTime = pattern.matcher(theTimeTemp);
                                        } while (!matcherTheTime.find());
                                        setFromTime(theTimeTemp);
                                        setToTime("10 gio trua hom sau");
                                        roomStandard.setStatusRoomStandard("booking");
                                        listRoomStandard.updateListRoomStandard();
                                        setTotalPrice(getTime() * Double.parseDouble(roomStandard.getPricePerNight()));
                                        break;
                                    case 3:
                                        System.out.println("Ban chon theo ngay");
                                        setTypeTime("theo ngay");
                                        System.out.print("Nhap so ngay: ");
                                        setTime(Integer.parseInt(sc.nextLine()));
                                        String fromTimeTemp;
                                        String toTimeTemp;

                                        do {
                                            System.out.print("Nhap ngay nhan phong (ex: **/**/****): ");
                                            fromTimeTemp = sc.nextLine();
                                            String s = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
                                            Pattern pattern = Pattern.compile(s);
                                            matcherTheTime = pattern.matcher(fromTimeTemp);
                                        } while (!matcherTheTime.find());
                                        setFromTime(fromTimeTemp);

                                        do {
                                            System.out.print("Nhap ngay tra phong (ex: **/**/****): ");
                                            toTimeTemp = sc.nextLine();
                                            String s = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
                                            Pattern pattern = Pattern.compile(s);
                                            matcherTheTime = pattern.matcher(toTimeTemp);
                                        } while (!matcherTheTime.find());
                                        setToTime(toTimeTemp);
                                        roomStandard.setStatusRoomStandard("booking");
                                        listRoomStandard.updateListRoomStandard();
                                        setTotalPrice(getTime() * Double.parseDouble(roomStandard.getPricePerDay()));
                                        break;
//                                        case 0:
//                                            System.out.println("Tro lai");
//                                            break;
//                                        default:
//                                            System.out.println("Loi lua chon! Vui long nhap lai");
//                                            break;
                                }

                            } while (selectTypeTime < 1 || selectTypeTime > 3);

                            check2 = true;
                        }
                    }

                    if (!check2){
                        System.out.printf("\u001B[41m| Khong tim thay ma phong %s Hoac phong %s da duoc dat |\u001B[0m \n", roomStandardId, roomStandardId);
                        break;
                    }else{
                        do {
                            System.out.print("Nhap ngay dat phong (ex: **/**/****): ");
                            setCreateAt(sc.nextLine());
                            String s = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
                            Pattern pattern = Pattern.compile(s);
                            matcher = pattern.matcher(getCreateAt());
                        } while (!matcher.find());

                        int selectPaymentMethod2;
                        String selectPaymentMethodTemp2;
                        Matcher matcherPaymentMethod2;
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
                                selectPaymentMethodTemp2 = sc.nextLine();
                                String s = "^[0-9]{1}";
                                Pattern pattern = Pattern.compile(s);
                                matcherPaymentMethod2 = pattern.matcher(selectPaymentMethodTemp2);
                            } while (!matcherPaymentMethod2.find());
                            selectPaymentMethod2 = Integer.parseInt(selectPaymentMethodTemp2);

                            switch (selectPaymentMethod2) {
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

                        } while (selectPaymentMethod2 < 1 || selectPaymentMethod2 > 2);

                        ListCustomer listCustomer = new ListCustomer();
                        listCustomer.readListCustomer();
                        for (int i = 0; i < listCustomer.countCustomer(); i++) {
                            String name = listCustomer.getCustomerName(i);
                            String type = listCustomer.getTypeCustomer(i);
                            String customerNameTemp = getCustomerName().toLowerCase();
                            if (customerNameTemp.equals(name.toLowerCase()) && type.equals("VIP")) {
                                System.out.println("Giam 10% cho Khach hang VIP");
                                totalPrice = totalPrice - totalPrice * 0.1;
                                break;
                            }
                        }

                        break;
                    }
//                case 0:
//                    System.out.println("Tro ra");
//                    break;
//                default:
//                    System.out.println("Loi lua chon! Vui long chon lai");
//                    break;
            }


        } while (select < 1 || select > 2);



    }
//------------Ket thuc input----------------------------------------------------------------------------------


    public void output() {
        System.out.printf("\n| %-15s %-20s %-20s %-20s %-25s %-15s %-30s %-30s %-30s %-20s %-23s |",
                getBookingId(), getCustomerName(), getEmployeeName(), getRoomId(), getTypeTime(), getTime(), getFromTime(), getToTime(), getCreateAt(), getPaymentMethod(), getTotalPrice());
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

        listRoomStandard.readListRoomStandard();
        RoomStandard[] roomStandards = listRoomStandard.getListRoom();
        for (RoomStandard roomStandard : roomStandards) {
            String key = roomStandard.getRoomStandardId();
            if (key.contentEquals(str[3])) {
                setRoom(roomStandard);
                setRoomId(key);
            }
        }

        setTypeTime(str[4]);
        setTime(Integer.parseInt(str[5]));
        setFromTime(str[6]);
        setToTime(str[7]);
        setCreateAt(str[8]);
        setPaymentMethod(str[9]);
        setTotalPrice(Double.parseDouble(str[10]));
    }

    public String mergeInformationToFile() {
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s",
                getBookingId(), getCustomerName(), getEmployeeName(), getRoomId(), getTypeTime(), getTime(), getFromTime(), getToTime(), getCreateAt(), getPaymentMethod(), getTotalPrice());
    }

    public void roomDetails() {
//        printLine();
        System.out.printf("\u001B[44m| %-20s %-25s %-50s %-28s %-28s %-28s %-28s |\u001B[0m",
                "Ma phong", "Ten phong", "Chi tiet phong", "Gia moi gio", "Gia moi dem", "Gia moi ngay", "Trang thai");
        getRoom().output();
//        printLine();
    }

}
