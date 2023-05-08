package OverrideCore;

import AbstractCore.TypeEmployee;
import BaseConstructor.Employee;

public class Receptionist extends Employee implements TypeEmployee {
    @Override
    public void position() {
        super.setPosition("Tiep tan");
    }

    @Override
    public void salary() {
        super.setSalary("15.000.000 vnd");
    }
}
