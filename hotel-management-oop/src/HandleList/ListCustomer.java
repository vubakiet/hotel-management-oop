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
            System.out.println("|                Them Khach hang               |");
            System.out.println("| -------------------=====---------------------|");
            System.out.println("| 1. Them khach hang                           |");
            System.out.println("| 2. Them khach VIP                            |");
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

    }
    @Override
    public void remove(){

    }
    @Override
    public void find(){

    }
    @Override
    public void display(){
        printLine();
        System.out.printf("\n|%-10s %-20s %-30s %-10s %-25s %-15s |\n",
        " Ma KH", " Ho Ten", " Dia Chi", " Tuoi", " So Dien Thoai", " Phan Loai Khach");
        for (int i =0; i < n ;i++){
            listCustomer[i].output();
        }
        printLine();
        System.out.println("");
    }
}
