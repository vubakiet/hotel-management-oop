package BaseConstructor;

import MainCore.Room;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomVIP extends Room {
    private String roomVIPId;
    private String pricePerHour, pricePerNight, pricePerDay;
    private Room roomDetails = new Room();


    public RoomVIP() {
    }

    public RoomVIP(String roomVIPId, String pricePerHour, String pricePerNight, String pricePerDay, Room roomDetails) {
        this.roomVIPId = roomVIPId;
        this.pricePerHour = pricePerHour;
        this.pricePerNight = pricePerNight;
        this.pricePerDay = pricePerDay;
        this.roomDetails = roomDetails;
    }

    public RoomVIP(String name, String roomDetails, String roomVIPId, String pricePerHour, String pricePerNight, String pricePerDay, Room roomDetails1) {
        super(name, roomDetails);
        this.roomVIPId = roomVIPId;
        this.pricePerHour = pricePerHour;
        this.pricePerNight = pricePerNight;
        this.pricePerDay = pricePerDay;
        this.roomDetails = roomDetails1;
    }

    public String getRoomVIPId() {
        return roomVIPId;
    }

    public void setRoomVIPId(String roomVIPId) {
        this.roomVIPId = roomVIPId;
    }

    public String getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(String pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(String pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void input() {
        Matcher matcher;
        do {
            System.out.print("Nhap ma phong VIP (ex: RV***): ");
            setRoomVIPId(sc.nextLine());
            String check = "^RV[0-9]{3}$";
            Pattern pattern = Pattern.compile(check);
            matcher = pattern.matcher(getRoomVIPId());
        } while (!matcher.find());

        roomDetails.inputForRoom();
        System.out.print("Nhap gia roomVIP theo gio (per hour): ");
        setPricePerHour(sc.nextLine());
        System.out.print("Nhap gia roomVIP theo dem (per night): ");
        setPricePerNight(sc.nextLine());
        System.out.print("Nhap gia roomVIP theo ngay (per day): ");
        setPricePerDay(sc.nextLine());
    }

    @Override
    public void output() {
        System.out.printf("\n| %-20s %-25s %-50s %-28s %-28s %-28s |",
                getRoomVIPId(), roomDetails.getName(), roomDetails.getRoomDetails(), getPricePerHour(), getPricePerNight(), getPricePerDay());
    }

    public void getLineFromFile(String line){
        String[] str = line.split(";");
        setRoomVIPId(str[0]);
        roomDetails.setName(str[1]);
        roomDetails.setRoomDetails(str[2]);
        setPricePerHour(str[3]);
        setPricePerNight(str[4]);
        setPricePerDay(str[5]);
    }

    public String mergeInformationToFile(){
        return getRoomVIPId() + ";" + roomDetails.getName() + ";" + roomDetails.getRoomDetails() + ";"
                + getPricePerHour() + ";" + getPricePerNight() + ";" + getPricePerDay();
    }

    public String getRoomVIPName(){
        return roomDetails.getName();
    }


}
