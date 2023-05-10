package BaseConstructor;

import MainCore.Person;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer extends Person {
    private String customerld;
    protected String typeCustomer;
    public Customer(){
    }
    public Customer(String customerld, String typeCustomer) {
        this.customerld = customerld;
        this.typeCustomer = typeCustomer;
    }
    public String getCustomerld() {
        return customerld;
    }
    public void setCustomerld(String customerld) {
        this.customerld = customerld;
    }
    public String getTypeCustomer() {
        return typeCustomer;
    }
    public void setTypeCustomer(String typeCustomer) {
        this.typeCustomer = typeCustomer;
    }
    public void typeCustomer(){
        setTypeCustomer("none");
    }
    public void customerNormal(){
        setTypeCustomer("Normal");
    }
    public void customerVIP(){
        setTypeCustomer("VIP");
    }
    public void inputForCustomer(){
        Matcher matcher;
        do{
            System.out.print("Nhap ma khach hang: ");
            setCustomerld(sc.nextLine());
            String checkID = "^KH[0-9]{2}$";
            Pattern pattern = Pattern.compile(checkID);
            matcher = pattern.matcher(getCustomerld());
        }while (!matcher.find());

        do {
            System.out.print("Nhap ten khach hang: ");
            super.setName(sc.nextLine());
            String s = "[^0-9]";
            Pattern pattern = Pattern.compile(s);
            matcher = pattern.matcher(super.getName());
        }while (!matcher.find());

        System.out.print("Nhap dia chi khach hang: ");
        super.setAddress(sc.nextLine());

        do{
            System.out.print("Nhap tuoi khach hang: ");
            super.setAge(sc.nextLine());
            String checkOld = "^[0-9]{2}";
            Pattern pattern = Pattern.compile(checkOld);
            matcher = pattern.matcher(super.getAge());
        }while (!matcher.find());
        do{
            System.out.print("Nhap so dien thoai khach hang: ");
            super.setPhone(sc.nextLine());
            String checkPhone = "^[0-9]{10,11}";
            Pattern pattern = Pattern.compile(checkPhone);
            matcher = pattern.matcher(super.getPhone());
        }while (!matcher.find());
    }
    @Override
    public String toString(){
        return String.format("| %-10s %-20s %-30s %-10s %-25s %-15s |",
                getCustomerld(), super.getName(), super.getAddress(), super.getAge(), super.getPhone(), getTypeCustomer());
    }

    @Override
    public void output(){
        System.out.println(toString());
    }

    @Override
    public void getLineFromFile(String line){
        String[] str = line.split(";");
        setCustomerld(str[0]);
        super.setName(str[1]);
        super.setAddress(str[2]);
        super.setAge(str[3]);
        super.setPhone(str[4]);
        setTypeCustomer(str[5]);
    }

    @Override
    public String mergeInformationToFile(){
        return String.format("%s;%s;%s;%s;%s;%s", getCustomerld(),super.getName(),super.getAddress(),super.getAge(),super.getPhone(),getTypeCustomer());
    }
}
