package ru.spbstu.edu.krasnov2.lab2;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CallCount {
    int value();
}
