package BaseConstructor;

import MainCore.Person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee extends Person {
     private String employeeId;
     protected String position;
     protected String salary;

    public Employee() {
    }


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void inputForEmployee(){
        Matcher matcher;
        do{
            System.out.print("Nhap ma nhan vien: ");
            setEmployeeId(sc.nextLine());
            String s = "^NV[0-9]{2}$";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(getEmployeeId());
        }while (!matcher.find());

        do {
            System.out.print("Nhap ten nhan vien: ");
            super.setName(sc.nextLine());
            String s = "[^0-9]";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(super.getName());
        }while (!matcher.find());

        System.out.print("Nhap dia chi nhan vien: ");
        super.setAddress(sc.nextLine());

        do{
            System.out.print("Nhap tuoi nhan vien: ");
            super.setAge(sc.nextLine());
            String s = "^[0-9]{2}";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(super.getAge());
        }while (!matcher.find());

        do{
            System.out.print("Nhap so dien thoai nhan vien: ");
            super.setPhone(sc.nextLine());
            String s = "^[0-9]{10,11}";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(super.getPhone());
        }while (!matcher.find());
    }

    public void position(){
        setPosition("none");
    }
    public void salary(){
        setSalary("none");
    }

    public void receptionist(){
        setPosition("Tiep tan");
        setSalary("15.000.000 vnd");
    }
    public void cleanner(){
        setPosition("Lao cong");
        setSalary("10.000.000 vnd");
    }
    public void manager(){
        setPosition("Quan ly");
        setSalary("20.000.000 vnd");
    }

    @Override
    public String toString() {
        return String.format("| %-10s %-20s %-30s %-10s %-15s %-15s %-20s |",
                getEmployeeId(), super.getName(), super.getAddress(), super.getAge(), super.getPhone(), getPosition(), getSalary());
    }

    @Override
    public void output() {
        System.out.println(toString());
    }

    @Override
    public void getLineFromFile(String line) {
        String[] str = line.split(";");
        setEmployeeId(str[0]);
        super.setName(str[1]);
        super.setAddress(str[2]);
        super.setAge(str[3]);
        super.setPhone(str[4]);
        setPosition(str[5]);
        setSalary(str[6]);
    }

    @Override
    public String mergeInformationToFile() {
        return String.format("%s;%s;%s;%s;%s;%s;%s",getEmployeeId(), super.getName(), super.getAddress(), super.getAge(),super.getPhone(), getPosition(), getSalary());
    }
}
