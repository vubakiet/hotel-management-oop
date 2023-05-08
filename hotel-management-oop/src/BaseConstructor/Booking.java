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
    private int quantity;
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

    public Booking(String bookingId, String customerName, String employeeName, double totalPrice, String typeTime, int time, String createAt, int quantity, String paymentMethod, Room[] listRoom) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.employeeName = employeeName;
        this.totalPrice = totalPrice;
        this.typeTime = typeTime;
        this.time = time;
        this.createAt = createAt;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
                    int existingRoom = listRoomVIP.countRoomVIP();
                    listRoom = new Room[existingRoom];

                    for(int i = 0; i< existingRoom;){
                        RoomVIP[] roomVIPs = listRoomVIP.getListRoom();
                        String roomVIPId;
                        do {
                            System.out.print("Nhap ma phong: ");
                            roomVIPId = sc.nextLine();
                            String s = "RV[0-9]{3}";
                            Pattern pattern = Pattern.compile(s);
                            matcher = pattern.matcher(roomVIPId);
                        }while (!matcher.find());

                        Boolean check = false;
                        for(int j = 0; j < roomVIPs.length; j++){
                            String key = roomVIPs[j].getRoomVIPId();
                            if(key.contentEquals(roomVIPId)){
                                listRoom[i] = roomVIPs[j];

                            }
                        }
                    }
            }

        } while (select != 0);


    }

    public static void main(String[] args) {
        Booking booking = new Booking();
        booking.inputForBooking();
    }
}
