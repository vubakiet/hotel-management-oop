package OverrideCore;

import AbstractCore.TypeEmployee;
import BaseConstructor.Employee;

public class Manager extends Employee implements TypeEmployee {
    @Override
    public void position() {
        super.setPosition("Quan ly");
    }

    @Override
    public void salary() {
        super.setSalary("20.000.000 vnd");
    }
}
