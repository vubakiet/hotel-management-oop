package MainCore;

import java.util.Scanner;

public abstract class Person {
    private String name;
    private String age;
    private String phone;
    private String address;

    public static Scanner sc = new Scanner(System.in);

    public Person() {
    }

    public Person(String name, String age, String phone, String address) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public abstract void output();
    public abstract void getLineFromFile(String line);
    public abstract String mergeInformationToFile();
}
