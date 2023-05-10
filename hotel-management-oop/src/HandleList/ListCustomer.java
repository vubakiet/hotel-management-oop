package HandleList;

import AbstractCore.TypeList;
import BaseConstructor.Customer;
import OverrideCore.CustomerVIP;
import OverrideCore.CustomerNormal;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCustomer implements TypeList{
    private int n;
    private Customer[] listCustomer;

    Scanner sc = new Scanner(System.in);

    public ListCustomer(){
        n = 0;
    }

    public ListCustomer(int n, Customer[] listCustomer) {
        this.n = n;
        this.listCustomer = listCustomer;

    }

    public String getCustomerName(int i){
        return listCustomer[i].getName();
    }

    public String getTypeCustomer(int i){
        return listCustomer[i].getTypeCustomer();
    }

    public static void printLine(){
        for (int i = 0;i < 119; i++){
            System.out.print("=");
        }
    }

    public int countCustomer(){
        int count = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream("./database/ListCustomer.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    count++;
                }
                n = count;
            } catch(IOException e) {
                e.printStackTrace();
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }
    public void readListCustomer(){
        try {
            FileInputStream fileInputStream = new FileInputStream("./database/ListCustomer.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            try {
                n = countCustomer();
                listCustomer = new Customer[n];
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    listCustomer[i] = new Customer();
                    listCustomer[i].getLineFromFile(line);
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void updateListCustomer(){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("./database/ListCustomer.txt");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (listCustomer[i] != null) {
                    String line = listCustomer[i].mergeInformationToFile();
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
            System.out.println("Da cap nhat danh sach");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void addToListCustomer(Customer customer){
        listCustomer = Arrays.copyOf(listCustomer, n + 1);
        for (int i = 0; i < n + 1; i++) {
            if (i == n) {
                listCustomer[i] = customer;
            }
        }
        n++;
        updateListCustomer();
    }
    @Override
    public void add(){
        Matcher matcher;
        String selectTemp;
        int select;

        do{
            System.out.println("+----------------------------------------------+");
            System.out.println("|                Loai Khach hang               |");
            System.out.println("| -------------------=====---------------------|");
            System.out.println("| 1. Khach hang Normal                         |");
            System.out.println("| 2. Khach hang VIP                            |");
            System.out.println("| 0. Tro ve                                    |");
            System.out.println("+----------------------------------------------+");

            do{
                System.out.print("Nhap lua chon: ");
                selectTemp = sc.nextLine();
                String check = "^[0-9]{1}";
                Pattern pattern = Pattern.compile(check);
                matcher = pattern.matcher(selectTemp);
            }while(!matcher.find());
            select = Integer.parseInt(selectTemp);

            switch(select){
                case 1:
                    Customer cus1 = new CustomerNormal();
                    cus1.inputForCustomer();
                    cus1.typeCustomer();
                    addToListCustomer(cus1);
                    break;
                case 2:
                    Customer cus2 = new CustomerVIP();
                    cus2.inputForCustomer();
                    cus2.typeCustomer();
                    addToListCustomer(cus2);
                    break;
                case 0:
                    break;
            }
        }while( select !=0);
    }
    @Override
    public void edit(){
        Matcher matcher;
        String temp, selectTemp;
        int select;
        display();
        do {
            System.out.print("Nhap ma khach hang can thay doi: ");
            temp = sc.nextLine();
            String s = "^KH[0-9]{2}$";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(temp);
        }while (!matcher.find());

        boolean check = false;
        for(int i = 0; i < n; i++){
            String key = listCustomer[i].getCustomerld();
            if(key.contentEquals(temp)){
                check = true;
                do{
                    System.out.println("+----------------------------------------------+");
                    System.out.println("|                Loai Khach hang               |");
                    System.out.println("| -------------------=====---------------------|");
                    System.out.println("| 1. Khach hang Normal                         |");
                    System.out.println("| 2. Khach hang VIP                            |");
                    System.out.println("| 0. Tro ve                                    |");
                    System.out.println("+----------------------------------------------+");

                    do{
                        System.out.print("Nhap lua chon: ");
                        selectTemp = sc.nextLine();
                        String s = "^[0-9]{1}";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(selectTemp);
                    }while(!matcher.find());
                    select = Integer.parseInt(selectTemp);

                    switch(select){
                        case 1:
                            Customer cus1 = new CustomerNormal();
                            System.out.println("Nhap thong tin khach hang!");
                            cus1.inputForCustomer();
                            cus1.typeCustomer();
                            listCustomer[i] = cus1;
                            break;
                        case 2:
                            Customer cus2 = new CustomerVIP();
                            System.out.println("Nhap thong tin khach hang!");
                            cus2.inputForCustomer();
                            cus2.typeCustomer();
                            listCustomer[i] = cus2;
                            break;
                        case 0:
                            break;
                    }
                }while( select !=0);
            }
        }
        if(check) updateListCustomer();
        else System.out.println("Khong tim thay ma khach hang");
    }

    @Override
    public void remove(){
        Matcher matcher;
        String temp;
        display();
        do {
            System.out.print("Nhap ma khach hang can xoa: ");
            temp = sc.nextLine();
            String s = "^KH[0-9]{2}$";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(temp);
        }while(!matcher.find());
        boolean check = false;
        for(int i = 0; i< n; i++){
            String key = listCustomer[i].getCustomerld();
            if(key.contentEquals(temp)) {
                check = true;
                for (int j = i; j < n - 1; j++) {
                    listCustomer[j] = listCustomer[j + 1];
                }
                n--;
                listCustomer = Arrays.copyOf(listCustomer, n);
            }
        }
        if(check) updateListCustomer();
        else System.out.println("Khong tin thay ma khach hang!");
    }

    @Override
    public void find(){
        Matcher matcher;
        String temp;
        String selectTemp;
        int select;
        do {
            System.out.println();
            System.out.println("+---------------------------------------------+");
            System.out.println("        Tim kiem trong DS khach hang           ");
            System.out.println("| -------------------=====--------------------|");
            System.out.println("| 1. Tim kiem theo ma khach hang              |");
            System.out.println("| 2. Tim kiem theo Ten khach hang             |");
            System.out.println("| 3. Tim kiem theo dia chi khach hang         |");
            System.out.println("| 4. Tim kiem theo tuoi khach hang            |");
            System.out.println("| 5. Tim kiem theo so dien thoai khach hang   |");
            System.out.println("| 6. Tim kiem theo loai khach hang            |");
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
                    System.out.println("Ban chon Tim kiem theo ma khach hang");
                    do {
                        System.out.print("Nhap ma khach hang: ");
                        temp = sc.nextLine();
                        String s = "^KH[0-9]{2}$";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(temp);
                    } while (!matcher.find());
                    printLine();
                    System.out.printf("\n\u001B[44m|%-10s %-20s %-30s %-10s %-25s %-15s |\u001B[0m\n",
                            " Ma KH", " Ho Ten", " Dia Chi", " Tuoi", " So Dien Thoai", " Phan Loai Khach");
                    for (int i = 0; i < n; i++) {
                        String key = listCustomer[i].getCustomerld();
                        if (key.contentEquals(temp)) {
                            listCustomer[i].output();
                        }
                    }
                    printLine();
                    break;
                case 2:
                    System.out.println("Ban chon Tim kiem theo Ten khach hang");
                    do {
                        System.out.print("Nhap Ten khach hang: ");
                        temp = sc.nextLine();
                        String s = "[^0-9]";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(temp);
                    } while (!matcher.find());
                    printLine();
                    System.out.printf("\n\u001B[44m|%-10s %-20s %-30s %-10s %-25s %-15s |\u001B[0m\n",
                            " Ma KH", " Ho Ten", " Dia Chi", " Tuoi", " So Dien Thoai", " Phan Loai Khach");
                    for (int i = 0; i < n; i++) {
                        String key = listCustomer[i].getName().toLowerCase();
                        if (key.contains(temp.toLowerCase())) {
                            listCustomer[i].output();
                        }
                    }
                    printLine();
                    break;
                case 3:
                    System.out.println("Ban chon Tim kiem theo dia chi khach hang");
                    do {
                        System.out.print("Nhap Dia chi khach hang: ");
                        temp = sc.nextLine();
                        String s = "[^0-9]";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(temp);
                    } while (!matcher.find());
                    printLine();
                    System.out.printf("\n\u001B[44m|%-10s %-20s %-30s %-10s %-25s %-15s |\u001B[0m\n",
                            " Ma KH", " Ho Ten", " Dia Chi", " Tuoi", " So Dien Thoai", " Phan Loai Khach");
                    for (int i = 0; i < n; i++) {
                        String key = listCustomer[i].getAddress().toLowerCase();
                        if (key.contains(temp.toLowerCase())) {
                            listCustomer[i].output();
                        }
                    }
                    printLine();
                    break;

                case 4:
                    System.out.println("Ban chon Tim kiem theo tuoi khach hang");
                    do {
                        System.out.print("Nhap tuoi khach hang: ");
                        temp = sc.nextLine();
                        String s = "^[0-9]{2}";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(temp);
                    } while (!matcher.find());

                    printLine();
                    System.out.printf("\n\u001B[44m|%-10s %-20s %-30s %-10s %-25s %-15s |\u001B[0m\n",
                            " Ma KH", " Ho Ten", " Dia Chi", " Tuoi", " So Dien Thoai", " Phan Loai Khach");
                    for (int i = 0; i < n; i++) {
                        String key = listCustomer[i].getAge();
                        if (key.contentEquals(temp)) {
                            listCustomer[i].output();
                        }
                    }
                    printLine();
                    break;

                case 5:
                    System.out.println("Ban chon Tim kiem theo so dien thoai khach hang");
                    do {
                        System.out.print("Nhap so dien thoai khach hang: ");
                        temp = sc.nextLine();
                        String s = "^[0-9]{10,11}";
                        Pattern pattern = Pattern.compile(s);
                        matcher = pattern.matcher(temp);
                    } while (!matcher.find());

                    printLine();
                    System.out.printf("\n\u001B[44m|%-10s %-20s %-30s %-10s %-25s %-15s |\u001B[0m\n",
                            " Ma KH", " Ho Ten", " Dia Chi", " Tuoi", " So Dien Thoai", " Phan Loai Khach");
                    for (int i = 0; i < n; i++) {
                        String key = listCustomer[i].getPhone();
                        if (key.contentEquals(temp)) {
                            listCustomer[i].output();
                        }
                    }
                    printLine();
                    break;

                case 6:
                    System.out.println("Ban chon Tim kiem theo loai khach hang");
                    String selectTyperCustomerTemp;
                    int selectTypeCustomer;

                    do {
                        System.out.println("+-------------------------------------------+");
                        System.out.println("|        Chon loai khach hang               |");
                        System.out.println("| ------------------=====-------------------|");
                        System.out.println("| 1. khach hang Normal                      |");
                        System.out.println("| 2. khach hang VIP                         |");
                        System.out.println("| 0. Tro lai                                |");
                        System.out.println("+-------------------------------------------+");

                        do {
                            System.out.print("Nhap lua chon: ");
                            selectTyperCustomerTemp = sc.nextLine();
                            String s = "^[0-9]{1}";
                            Pattern pattern = Pattern.compile(s);
                            matcher = pattern.matcher(selectTyperCustomerTemp);
                        } while (!matcher.find());
                        selectTypeCustomer = Integer.parseInt(selectTyperCustomerTemp);

                        switch (selectTypeCustomer) {
                            case 1:
                                System.out.println("Ban chon khach hang Normal");
                                String TypeCustomerTemp = "Normal";

                                printLine();
                                System.out.printf("\n\u001B[44m|%-10s %-20s %-30s %-10s %-25s %-15s |\u001B[0m\n",
                                        " Ma KH", " Ho Ten", " Dia Chi", " Tuoi", " So Dien Thoai", " Phan Loai Khach");
                                for (int i = 0; i < n; i++) {
                                    String key = listCustomer[i].getTypeCustomer().toLowerCase();
                                    if (key.contentEquals(TypeCustomerTemp.toLowerCase())) {
                                        listCustomer[i].output();
                                    }
                                }
                                printLine();
                                break;

                            case 2:
                                System.out.println("Ban chon khach hang VIP");
                                String typeCustomerTemp = "VIP";

                                printLine();
                                System.out.printf("\n\u001B[44m|%-10s %-20s %-30s %-10s %-25s %-15s |\u001B[0m\n",
                                        " Ma KH", " Ho Ten", " Dia Chi", " Tuoi", " So Dien Thoai", " Phan Loai Khach");
                                for (int i = 0; i < n; i++) {
                                    String key = listCustomer[i].getTypeCustomer().toLowerCase();
                                    if (key.contentEquals(typeCustomerTemp.toLowerCase())) {
                                        listCustomer[i].output();
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
                    } while (selectTypeCustomer != 0);
                    break;

                case 0:
                    System.out.println("Tro lai");
                    break;
                default:
                    System.out.println("Loi lua chon! Vui long chon lai");
                    break;
            }
        } while (select != 0);

    }
    @Override
    public void display(){
        printLine();
        System.out.printf("\n\u001B[44m|%-10s %-20s %-30s %-10s %-25s %-15s |\u001B[0m\n",
                " Ma KH", " Ho Ten", " Dia Chi", " Tuoi", " So Dien Thoai", " Phan Loai Khach");
        for (int i =0; i < n ;i++){
            listCustomer[i].output();
        }
        printLine();
        System.out.println("");
    }
}
