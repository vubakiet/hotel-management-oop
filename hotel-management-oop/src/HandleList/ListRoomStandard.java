package HandleList;
import AbstractCore.TypeList;
import BaseConstructor.RoomStandard;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ListRoomStandard implements TypeList {
    private int n;
    private RoomStandard[] listRoom;
    Scanner sc = new Scanner(System.in);

    public ListRoomStandard() {
        n = 0;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public RoomStandard[] getListRoom() {
        return listRoom;
    }

    public void setListRoom(RoomStandard[] listRoom) {
        this.listRoom = listRoom;
    }

    public static void printLine() {
        System.out.println();
        for (int i = 0; i < 188; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public int countRoomStandard() {
        int count = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream("./database/ListRoomStandard.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    count++;
                }
                n = count;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void readListRoomStandard() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./database/ListRoomStandard.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;

            try {
                n = countRoomStandard();
                listRoom = new RoomStandard[n];
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    listRoom[i] = new RoomStandard();
                    listRoom[i].getLineFromFile(line);
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void updateListRoomStandard() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("./database/ListRoomStandard.txt");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (listRoom[i] != null) {
                    String line = listRoom[i].mergeInformationToFile();
                    stringBuilder.append(line);
                    stringBuilder.append(System.lineSeparator());
                }
            }
            try {
                byte[] info = stringBuilder.toString().getBytes("utf8");
                try {
                    fileOutputStream.write(info);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println("Da cap nhat danh sach!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void addToListRoomStandard(RoomStandard roomStandard) {
        listRoom = Arrays.copyOf(listRoom, n + 1);
        for (int i = 0; i < n + 1; i++) {
            if (i == n) {
                listRoom[i] = roomStandard;
            }
        }
        n++;
        updateListRoomStandard();
    }
    @Override
    public void add() {
        RoomStandard roomStandard = new RoomStandard();
        roomStandard.input();
        addToListRoomStandard(roomStandard);
    }

    @Override
    public void edit() {
        Matcher matcher;
        String temp, selectTemp;
        int select;
        display();
        do {
            System.out.print("Nhap ma phong Standard can thay doi: ");
            temp = sc.nextLine();
            String s = "^SD[0-9]{3}$";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(temp);
        } while (!matcher.find());

        boolean check = false;
        for (int i = 0; i < n; i++) {
            String key = listRoom[i].getRoomStandardId();
            if (key.contentEquals(temp)) {
                check = true;
                RoomStandard Stan1 = new RoomStandard();
                System.out.println("Nhap thong tin phong Standard!");
                Stan1.input();
                listRoom[i] = Stan1;
                break;
            }
        }
        if(check) updateListRoomStandard();
        else System.out.println("Khong tim thay ma khach hang");
    }

    @Override
    public void remove() {
        Matcher matcher;
        String temp;
        display();
        do {
            System.out.print("Nhap ma Phong Standard can xoa: ");
            temp = sc.nextLine();
            String s = "^RS[0-9]{3}$";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(temp);
        }while(!matcher.find());
        boolean check = false;
        for(int i = 0; i< n; i++){
            String key = listRoom[i].getRoomStandardId();
            if(key.contentEquals(temp)) {
                check = true;
                for (int j = i; j < n - 1; j++) {
                    listRoom[j] = listRoom[j + 1];
                }
                n--;
                listRoom = Arrays.copyOf(listRoom, n);
            }
        }
        if(check) updateListRoomStandard();
        else System.out.println("Khong tin thay ma khach hang!");
    }

    @Override
    public void find() {
        Matcher matcher;
        String temp;
        String selectTemp;
        int select;
        do {
            System.out.println();
            System.out.println("+---------------------------------------------+");
            System.out.println("        Tim kiem trong DS phong               ");
            System.out.println("| -------------------=====--------------------|");
            System.out.println("| 1. Tim kiem theo ma phong                   |");
            System.out.println("| 2. Tim kiem theo Ten phong                  |");
            System.out.println("| 3. Tim kiem theo gia mot gio                |");
            System.out.println("| 4. Tim kiem theo gia mot dem                |");
            System.out.println("| 5. Tim kiem theo gia mot ngay               |");
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
                    System.out.println("Ban chon Tim kiem theo ma phong");
                    do {
                        System.out.print("Nhap ma phong: ");
                        temp = sc.nextLine();
                        String s = "^RS[0-9]{3}$";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(temp);
                    } while (!matcher.find());
                    printLine();
                    System.out.printf("\u001B[44m| %-20s %-25s %-50s %-28s %-28s %-28s %-28s |\u001B[0m",
                            "Ma phong", "Ten phong", "Chi tiet phong", "Gia moi gio", "Gia moi dem", "Gia moi ngay", "Trang Thai");
                    for (int i = 0; i < n; i++) {
                        String key = listRoom[i].getRoomStandardId();
                        if (key.contentEquals(temp)) {
                            listRoom[i].output();
                        }
                    }
                    printLine();
                    break;

                case 2:
                    System.out.println("Ban chon Tim kiem theo Ten phong");
                    do {
                        System.out.print("Nhap Ten phong: ");
                        temp = sc.nextLine();
                        String s = "[^0-9]";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(temp);
                    } while (!matcher.find());
                    printLine();
                    System.out.printf("\u001B[44m| %-20s %-25s %-50s %-28s %-28s %-28s %-28s |\u001B[0m",
                            "Ma phong", "Ten phong", "Chi tiet phong", "Gia moi gio", "Gia moi dem", "Gia moi ngay", "Trang Thai");
                    for (int i = 0; i < n; i++) {
                        String key = listRoom[i].getRoomStandardName().toLowerCase();
                        if (key.contentEquals(temp.toLowerCase())) {
                            listRoom[i].output();
                        }
                    }
                    printLine();
                    break;
                case 3:
                    System.out.println("Ban chon Tim kiem theo gia mot gio");
                    do {
                        System.out.print("Nhap gia mot gio can tim: ");
                        temp = sc.nextLine();
                        String s = "^[0-9]";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(temp);
                    } while (!matcher.find());

                    printLine();
                    System.out.printf("\u001B[44m| %-20s %-25s %-50s %-28s %-28s %-28s %-28s |\u001B[0m",
                            "Ma phong", "Ten phong", "Chi tiet phong", "Gia moi gio", "Gia moi dem", "Gia moi ngay", "Trang Thai");
                    for (int i = 0; i < n; i++) {
                        String key = listRoom[i].getPricePerHour();
                        if (key.contentEquals(temp)) {
                            listRoom[i].output();
                        }
                    }
                    printLine();
                    break;
                case 4:
                    System.out.println("Ban chon Tim kiem theo gia mot dem");
                    do {
                        System.out.print("Nhap gia mot dem can tim: ");
                        temp = sc.nextLine();
                        String s = "^[0-9]";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(temp);
                    } while (!matcher.find());

                    printLine();
                    System.out.printf("\u001B[44m| %-20s %-25s %-50s %-28s %-28s %-28s %-28s |\u001B[0m",
                            "Ma phong", "Ten phong", "Chi tiet phong", "Gia moi gio", "Gia moi dem", "Gia moi ngay", "Trang Thai");
                    for (int i = 0; i < n; i++) {
                        String key = listRoom[i].getPricePerNight();
                        if (key.contentEquals(temp)) {
                            listRoom[i].output();
                        }
                    }
                    printLine();
                    break;
                case 5:
                    System.out.println("Ban chon Tim kiem theo gia mot ngay");
                    do {
                        System.out.print("Nhap gia mot ngay can tim: ");
                        temp = sc.nextLine();
                        String s = "^[0-9]";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(temp);
                    } while (!matcher.find());

                    printLine();
                    System.out.printf("\u001B[44m| %-20s %-25s %-50s %-28s %-28s %-28s %-28s |\u001B[0m",
                            "Ma phong", "Ten phong", "Chi tiet phong", "Gia moi gio", "Gia moi dem", "Gia moi ngay", "Trang Thai");
                    for (int i = 0; i < n; i++) {
                        String key = listRoom[i].getPricePerDay();
                        if (key.contentEquals(temp)) {
                            listRoom[i].output();
                        }
                    }
                    printLine();
                    break;

                case 0:
                    System.out.println("Tro lai");
                    break;
                default:
                    System.out.println("Loi lua chon! Vui long chon lai");
                    break;
            }
        }while (select != 0) ;
    }

    @Override
    public void display() {
        printLine();
        System.out.printf("\u001B[44m| %-20s %-25s %-50s %-28s %-28s %-28s %-28s |\u001B[0m",
                "Ma phong", "Ten phong", "Chi tiet phong", "Gia moi gio", "Gia moi dem", "Gia moi ngay", "Trang Thai");
        for (int i = 0; i < getN(); i++) {
            listRoom[i].output();
        }
        printLine();
    }
}
