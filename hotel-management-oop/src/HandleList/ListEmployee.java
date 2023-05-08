package HandleList;

import AbstractCore.TypeList;
import BaseConstructor.Booking;
import BaseConstructor.Employee;
import OverrideCore.Cleanner;
import OverrideCore.Manager;
import OverrideCore.Receptionist;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListEmployee implements TypeList {
    private int n;
    private Employee[] listEmployee;

    Scanner sc = new Scanner(System.in);

    public ListEmployee() {
        n = 0;
    }

    public ListEmployee(int n, Employee[] listEmployee) {
        this.n = n;
        this.listEmployee = listEmployee;
    }

    public static void printLine() {
        for (int i = 0; i < 130; i++) {
            System.out.print("=");
        }
    }

    public int countEmployee() {
        int count = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream("./database/ListEmployee.txt");
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

    public void readListEmployee() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./database/ListEmployee.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            try {
                n = countEmployee();
                listEmployee = new Employee[n];
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    listEmployee[i] = new Employee();
                    listEmployee[i].getLineFromFile(line);
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateListEmployee() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("./database/ListEmployee.txt");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (listEmployee[i] != null) {
                    String line = listEmployee[i].mergeInformationToFile();
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

    public void addToListEmployee(Employee employee) {
        listEmployee = Arrays.copyOf(listEmployee, n + 1);
        for (int i = 0; i < n + 1; i++) {
            if (i == n) {
                listEmployee[i] = employee;
            }
        }
        n++;
        updateListEmployee();
    }


    @Override
    public void add() {
        Matcher matcher;
        String selectTemp;
        int select;

        do {
            System.out.println("+---------------------------------------------+");
            System.out.println("|                Them nhan vien               |");
            System.out.println("| -------------------=====--------------------|");
            System.out.println("| 1. Them nhan vien Tiep tan                  |");
            System.out.println("| 2. Them nhan vien Lao cong                  |");
            System.out.println("| 3. Them nhan vien Quan ly                   |");
            System.out.println("| 0. Tro ve                                   |");
            System.out.println("+---------------------------------------------+");

            do {
                System.out.print("Nhap lua chon: ");
                selectTemp = sc.nextLine();
                String s = "^[0-9]{1}";
                Pattern pattern = Pattern.compile(s);
                matcher = pattern.matcher(selectTemp);
            }while (!matcher.find());
            select = Integer.parseInt(selectTemp);

            switch (select){
                case 1:
                    Employee emp1 = new Receptionist();
                    emp1.inputForEmployee();
                    emp1.position();
                    emp1.salary();
                    addToListEmployee(emp1);
                    break;
                case 2:
                    Employee emp2 = new Cleanner();
                    emp2.inputForEmployee();
                    emp2.position();
                    emp2.salary();
                    addToListEmployee(emp2);
                    break;
                case 3:
                    Employee emp3 = new Manager();
                    emp3.inputForEmployee();
                    emp3.position();
                    emp3.salary();
                    addToListEmployee(emp3);
                    break;
                case 0:
                    System.out.println("Tro ve");
                    break;
                default:
                    System.out.println("Lua chon loi! Vui long chon lai");
                    break;
            }

        }while (select != 0);
    }

    @Override
    public void edit() {
        Matcher matcher;
        String temp, selectTemp;
        int select;
        display();
        do {
            System.out.print("Nhap ma nhan vien can thay doi: ");
            temp = sc.nextLine();
            String s = "^NV[0-9]{2}$";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(temp);
        } while (!matcher.find());
        boolean check = false;
        for (int i = 0; i < n; i++) {
            String key = listEmployee[i].getEmployeeId();
            if (key.contentEquals(temp)) {
                check = true;
                do {
                    System.out.println("+---------------------------------------------+");
                    System.out.println("|                Loai nhan vien               |");
                    System.out.println("| -------------------=====--------------------|");
                    System.out.println("| 1. Nhan vien Tiep tan                       |");
                    System.out.println("| 2. Nhan vien Lao cong                       |");
                    System.out.println("| 3. Nhan vien Quan ly                        |");
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
                            Employee emp1 = new Receptionist();
                            System.out.println("Nhap thong tin nhan vien!");
                            emp1.inputForEmployee();
                            emp1.position();
                            emp1.salary();
                            listEmployee[i] = emp1;
                            break;
                        case 2:
                            Employee emp2 = new Cleanner();
                            System.out.println("Nhap thong tin nhan vien!");
                            emp2.inputForEmployee();
                            emp2.position();
                            emp2.salary();
                            listEmployee[i] = emp2;
                            break;
                        case 3:
                            Employee emp3 = new Manager();
                            System.out.println("Nhap thong tin nhan vien!");
                            emp3.inputForEmployee();
                            emp3.position();
                            emp3.salary();
                            listEmployee[i] = emp3;
                            break;
                        case 0:
                            System.out.println("Tro ve");
                            break;
                        default:
                            System.out.println("Lua chon loi! Vui long chon lai");
                            break;
                    }

                } while (select != 0);
            }
        }
        if(check) updateListEmployee();
        else System.out.println("Khong tim thay ma khach hang");
    }

    @Override
    public void remove() {
        Matcher matcher;
        String temp;
        display();
        do {
            System.out.print("Nhap ma khach hang: ");
            temp = sc.nextLine();
            String s = "^NV[0-9]{2}$";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(temp);
        }while(!matcher.find());
        boolean check = false;
        for(int i = 0; i< n; i++)

        {
            String key = listEmployee[i].getEmployeeId();
            if (key.contentEquals(temp)) {
                check = true;
                for (int j = i; j < n - 1; j++) {
                    listEmployee[j] = listEmployee[j + 1];
                }
                n--;
                listEmployee = Arrays.copyOf(listEmployee, n);
            }
        }
        if(check) updateListEmployee();
        else System.out.println("Khong tin thay ma khach hang!");
    }
    @Override
    public void find() {

    }

    @Override
    public void display() {
        printLine();
        System.out.printf("\n| %-10s %-20s %-30s %-10s %-15s %-15s %-20s |\n",
                "Ma NV", "Ho Ten", "Dia Chi", "Tuoi", "So dien thoai", "Chuc vu", "Luong");
        for (int i = 0; i < n; i++) {
            listEmployee[i].output();
        }
        printLine();
        System.out.println("");
    }
}
