package OverrideCore;

import AbstractCore.TypeCustomer;
import BaseConstructor.Customer;

public class CustomerVIP extends Customer implements TypeCustomer {
    @Override
    public void typeCustomer(){
        super.setTypeCustomer("VIP");
    }
}
