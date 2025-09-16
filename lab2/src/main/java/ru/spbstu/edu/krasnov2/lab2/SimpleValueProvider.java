package ru.spbstu.edu.krasnov2.lab2;

import java.util.HashMap;

public class SimpleValueProvider implements ValueProvider{

    private final HashMap<Class<?>, Object> _values;
    private final HashMap<Class<?>, Class<?>> _mapToWrappers;

    public SimpleValueProvider(){
        _values = new HashMap<>();
        _mapToWrappers = new HashMap<>();

        _mapToWrappers.put(byte.class, Byte.class);
        _mapToWrappers.put(short.class, Short.class);
        _mapToWrappers.put(int.class, Integer.class);
        _mapToWrappers.put(long.class, Long.class);
        _mapToWrappers.put(float.class, Float.class);
        _mapToWrappers.put(double.class, Double.class);
        _mapToWrappers.put(char.class, Character.class);
        _mapToWrappers.put(boolean.class, Boolean.class);
    }

    public void addValue(Object obj){
        if (obj == null)
            throw new IllegalArgumentException("obj is null");

        _values.put(obj.getClass(), obj);
    }

    @Override
    public Object getValue(Class<?> c) throws ValueProviderException {

        if (_values.containsKey(c))
            return _values.get(c);

        if (_mapToWrappers.containsKey(c)){
            var wrapper = _mapToWrappers.get(c);
            if (_values.containsKey(wrapper))
                return _values.get(wrapper);
        }

        throw new ValueProviderException("Type " + c.getCanonicalName() + " is not supported");
    }
}
