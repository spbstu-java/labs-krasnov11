package ru.spbstu.edu.krasnov2.lab2;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.InvocationTargetException;

public class Caller {

    private final ValueProvider valueProvider;

    public Caller(ValueProvider valueProvider){
        if (valueProvider == null)
            throw new IllegalArgumentException("valueProvider is null");
        this.valueProvider = valueProvider;
    }

    public void ExecuteCalls(Object obj) throws InvocationTargetException, IllegalAccessException, OperationNotSupportedException {
        if (obj == null)
            throw new IllegalArgumentException("Argument obj is null");

        var objClass = obj.getClass();

        for (var m : objClass.getDeclaredMethods()){
            System.out.printf("Process method: %s%n", m);

            for (var a : m.getAnnotations()){

                if (a.annotationType().isAssignableFrom(CallCount.class)){

                    System.out.println(a);

                    var callCount = (CallCount)a;
                    if (callCount.value() > 0){
                        var prmClasses = m.getParameterTypes();
                        var args = new Object[prmClasses.length];

                        // arguments
                        for (int i = 0; i < prmClasses.length; i++) {
                            args[i] = this.valueProvider.getValue(prmClasses[i]);
                        }

                        // call
                        m.setAccessible(true);
                        for (int i = 0; i<callCount.value(); ++i){
                            m.invoke(obj, args);
                        }
                    }

                    break;
                }
            }
        }
    }
}
