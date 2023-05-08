package HandleList;

import AbstractCore.TypeList;
import BaseConstructor.Booking;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListBooking implements TypeList {
    private int n;
    private Booking[] listBooking;
    Scanner sc = new Scanner(System.in);

    public ListBooking() {
        n = 0;
    }


    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Booking[] getListBooking() {
        return listBooking;
    }

    public void setListBooking(Booking[] listBooking) {
        this.listBooking = listBooking;
    }

    public static void printLine() {
        System.out.println();
        for (int i = 0; i < 200; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    //    ------------------------------------------------------------------------------------------------------ //

    public int countBooking() {
        int count = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream("./database/ListBooking.txt");
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

    public void readListBooking() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./database/ListBooking.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            try {
                n = countBooking();
                listBooking = new Booking[n];
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    listBooking[i] = new Booking();
                    listBooking[i].getLineFromFile(line);
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBooking() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("./database/ListBooking.txt");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (listBooking[i] != null) {
                    String line = listBooking[i].mergeInformationToFile();
                    stringBuilder.append(line);
                    stringBuilder.append(System.lineSeparator());
                }
            }
            byte[] info = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
            try {
                fileOutputStream.write(info);
            } catch (IOException e) {
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

    public void addToListBooking(Booking booking) {
        listBooking = Arrays.copyOf(listBooking, n + 1);
        for (int i = 0; i < n + 1; i++) {
            if (i == n) {
                listBooking[i] = booking;
            }
        }
        n++;
        updateBooking();
    }


//    ------------------------------------------------------------------------------------------------------ //


    @Override
    public void add() {
        Booking booking = new Booking();
        booking.inputForBooking();
        addToListBooking(booking);
    }

    @Override
    public void edit() {
        Matcher matcher;
        String temp;
        display();
        do {
            System.out.print("Nhap ma don dat phong can thay doi: ");
            temp = sc.nextLine();
            String s = "^BK[0-9]{2}$";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(temp);
        }while (!matcher.find());

        boolean check = false;
        for(int i = 0; i < n; i++){
            String key = listBooking[i].getBookingId();
            if(key.contentEquals(temp)){
                check = true;
                Booking booking = new Booking();
                System.out.println("Nhap thong tin don dat phong!");
                booking.inputForBooking();
                listBooking[i] = booking;
            }
        }

        if(check){
            updateBooking();
        }
        else{
            System.out.println("Khong tim thay ma don dat phong");
        }
    }

    @Override
    public void remove() {
        Matcher matcher;
        String temp;
        display();
        do {
            System.out.print("Nhap ma don dat phong: ");
            temp = sc.nextLine();
            String s = "^BK[0-9]{2}$";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(temp);
        }while(!matcher.find());

        boolean check = false;
        for(int i = 0; i< n; i++){
            String key = listBooking[i].getBookingId();
            if(key.contentEquals(temp)){
                check = true;
                for(int j = i; j < n-1; j++){
                    listBooking[j] = listBooking[j+1];
                }
                n--;
                listBooking = Arrays.copyOf(listBooking, n);
            }
        }
        if(check){
            updateBooking();
        }
        else{
            System.out.println("Khong tin thay ma don dat phong");
        }
    }

    @Override
    public void find() {

    }

    @Override
    public void display() {
        printLine();
        System.out.printf("| %-15s %-20s %-20s %-20s %-25s %-15s %-30s %-20s %-23s |",
                "Ma BK", "Ten KH", "Ten NV", "Ma Phong", "Kieu thoi gian", "Thoi gian", "Ngay dat phong", "PTTT", "Tong tien(VND)");
        for (int i = 0; i < getN(); i++) {
            listBooking[i].output();
        }
        printLine();
        System.out.println();
    }

    public void bookingDetails() {
        Matcher matcher;
        String temp;
        display();
        do {
            System.out.print("Nhap ma don dat phong can xem: ");
            temp = sc.nextLine();
            String s = "^BK[0-9]{2}$";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(temp);
        } while (!matcher.find());

        printLine();
        System.out.printf("\n\u001B[44m| %-15s %-20s %-20s %-20s %-25s %-15s %-30s %-20s %-23s |\u001B[0m",
                "Ma BK", "Ten KH", "Ten NV", "Ma Phong", "Kieu thoi gian", "Thoi gian", "Ngay dat phong", "PTTT", "Tong tien(VND)");
        for (int i = 0; i < n; i++) {
            String key = listBooking[i].getBookingId();
            if (key.contentEquals(temp)) {
                listBooking[i].output();
                printLine();
                listBooking[i].roomDetails();
                System.out.println();
                printLine();
            }
        }
        System.out.println();
        System.out.println();

    }
}
