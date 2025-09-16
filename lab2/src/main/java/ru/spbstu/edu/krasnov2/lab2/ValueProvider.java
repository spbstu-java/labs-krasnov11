package ru.spbstu.edu.krasnov2.lab2;

public interface ValueProvider {
    Object getValue(Class<?> c) throws ValueProviderException;
}
