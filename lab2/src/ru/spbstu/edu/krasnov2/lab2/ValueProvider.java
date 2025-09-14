package ru.spbstu.edu.krasnov2.lab2;

import javax.naming.OperationNotSupportedException;

public interface ValueProvider {
    Object getValue(Class<?> c) throws OperationNotSupportedException;
}
