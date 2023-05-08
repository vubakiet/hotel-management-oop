package OverrideCore;

import AbstractCore.TypeCustomer;
import BaseConstructor.Customer;

public class CustomerNormal extends Customer implements TypeCustomer {
    @Override
    public void typeCustomer(){
        super.setTypeCustomer("Normal");
    }
}
