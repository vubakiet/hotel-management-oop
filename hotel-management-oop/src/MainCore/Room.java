package MainCore;

import java.util.Scanner;

public class Room {
    private String name;
    private String roomDetails;
    private String status;
    public static Scanner sc = new Scanner(System.in);

    public Room() {
    }

    public Room(String name, String roomDetails) {
        this.name = name;
        this.roomDetails = roomDetails;
    }

    public String getRoomId(){
        return "";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(String roomDetails) {
        this.roomDetails = roomDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void inputForRoom(){
        System.out.print("Nhap ten phong: ");
        setName(sc.nextLine());
        System.out.println("Mo ta phong (trang thiet bi, view, do rong,...): ");
        setRoomDetails(sc.nextLine());
        setStatus("available");
    }

    public void output(){
        System.out.printf("\n%-25s %-50s\n", getName(), getRoomDetails());
    }
}
