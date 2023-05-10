package OverrideCore;

import AbstractCore.TypeEmployee;
import BaseConstructor.Employee;

public class Cleanner extends Employee implements TypeEmployee {
    @Override
    public void position() {
        super.setPosition("Lao cong");
    }

    @Override
    public void salary() {
        super.setSalary("10.000.000 vnd");
    }
}
