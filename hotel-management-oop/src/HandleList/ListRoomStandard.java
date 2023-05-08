package HandleList;
import AbstractCore.TypeList;
import BaseConstructor.RoomStandard;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ListRoomStandard implements TypeList {
    private int n;
    private RoomStandard[] listRoom;
    Scanner sc = new Scanner(System.in);

    public ListRoomStandard() {
        n = 0;
    }

    public ListRoomStandard(int n, RoomStandard[] listRoom) {
        this.n = n;
        this.listRoom = listRoom;
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
            FileInputStream fileInputStream = new FileInputStream("./database/ListRoomStanDard.txt");
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

    public void readListRoomStanDard() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./database/ListRoomStanDard.txt");
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
    public void updateListRoomStanDard() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("./database/ListRoomStanDard.txt");
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
    public void addToListRoomStanDard(RoomStandard roomStandard) {
        listRoom = Arrays.copyOf(listRoom, n + 1);
        for (int i = 0; i < n + 1; i++) {
            if (i == n) {
                listRoom[i] = roomStandard;
            }
        }
        n++;
        updateListRoomStanDard();
    }
    @Override
    public void add() {
        RoomStandard roomStandard = new RoomStandard();
        roomStandard.input();
        addToListRoomStanDard(roomStandard);
    }

    @Override
    public void edit() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void find() {

    }

    @Override
    public void display() {
        printLine();
        System.out.printf("| %-20s %-25s %-50s %-28s %-28s %-28s |", "Ma phong", "Ten phong", "Chi tiet phong", "Gia moi gio", "Gia moi dem", "Gia moi ngay");
        for (int i = 0; i < getN(); i++) {
            listRoom[i].output();
        }
        printLine();
    }
}
