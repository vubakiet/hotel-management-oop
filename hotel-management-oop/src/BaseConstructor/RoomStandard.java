package BaseConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import MainCore.Room;

public class RoomStandard extends Room {
    private String roomStandardId;
    private String pricePerHour, pricePerNight, pricePerDay;
    private Room roomDetails = new Room();
    public RoomStandard() {
    }
    public String getRoomStandardId() {
        return roomStandardId;
    }
    public void setRoomStandardId(String roomStandardId) {
        this.roomStandardId = roomStandardId;
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
            System.out.print("Nhap ma phong Standard (ex: RS***): ");
            setRoomStandardId(sc.nextLine());
            String check = "^RS[0-9]{3}$";
            Pattern pattern = Pattern.compile(check);
            matcher = pattern.matcher(getRoomStandardId());
        } while (!matcher.find());

        roomDetails.inputForRoom();
        System.out.print("Nhap gia roomStandard theo gio (per hour): ");
        setPricePerHour(sc.nextLine());
        System.out.print("Nhap gia roomStandard theo dem (per night): ");
        setPricePerNight(sc.nextLine());
        System.out.print("Nhap gia roomStandard theo ngay (per day): ");
        setPricePerDay(sc.nextLine());
    }
    @Override
    public void output() {
        System.out.printf("\n| %-20s %-25s %-50s %-28s %-28s %-28s %-28s |",
                getRoomStandardId(), roomDetails.getName(), roomDetails.getRoomDetails(), getPricePerHour(), getPricePerNight(), getPricePerDay(), roomDetails.getStatus());
    }
    public void getLineFromFile(String line){
        String[] str = line.split(";");
        setRoomStandardId(str[0]);
        roomDetails.setName(str[1]);
        roomDetails.setRoomDetails(str[2]);
        setPricePerHour(str[3]);
        setPricePerNight(str[4]);
        setPricePerDay(str[5]);
        roomDetails.setStatus(str[6]);
    }

    public String mergeInformationToFile(){
        return getRoomStandardId() + ";" + roomDetails.getName() + ";" + roomDetails.getRoomDetails() + ";"
                + getPricePerHour() + ";" + getPricePerNight() + ";" + getPricePerDay() + ";" + roomDetails.getStatus();
    }

    public String getRoomStandardName(){
        return roomDetails.getName();
    }

    public String getStatusRoomStandard(){
        return roomDetails.getStatus();
    }

    public void setStatusRoomStandard(String statusStandard){
        roomDetails.setStatus(statusStandard);
    }

}