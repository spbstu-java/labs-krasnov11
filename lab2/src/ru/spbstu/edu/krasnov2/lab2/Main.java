package ru.spbstu.edu.krasnov2.lab2;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {

        var valueProvider = new SimpleValueProvider();
        valueProvider.addValue(12);
        valueProvider.addValue("Hello");

        var caller = new Caller(valueProvider);

        try {
            caller.ExecuteCalls(new TestCallClass());
        } catch (InvocationTargetException | IllegalAccessException | OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
