package ru.spbstu.edu.krasnov2.lab4;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // метод, возвращающий среднее значение списка целых чисел
        System.out.println("---AVG---");
        var integers = new Integer[]{ 1, 2, 2, 3, -18, 45, 1024, 1024, -333};
        System.out.printf("Origin array: %s%n", arrayAsString(List.of(integers)));
        var avg = intAvg(List.of(integers));
        System.out.printf("AVG = %f%n%n", avg);

        // метод, приводящий все строки в списке в верхний регистр
        // и добавляющий к ним префикс «_new_»
        System.out.println("---Upper New---");
        var strings = new String[]{
                "hello", " ", "Привет", "Respect"
        };
        System.out.printf("Origin array: %s%n", arrayAsString(List.of(strings)));
        var str = upperConcatWithPrefix(List.of(strings));
        System.out.printf("Upper New: %s%n%n", arrayAsString(str));

        // метод, возвращающий список квадратов всех встречающихся только один раз элементов списка
        System.out.println("---Distinct squares---");
        System.out.printf("Origin array: %s%n", arrayAsString(List.of(integers)));
        var sqtrList = sumDistinctSquare(List.of(integers));
        System.out.printf("Distinct squares: %s%n%n", arrayAsString(sqtrList));

        // метод, принимающий на вход коллекцию и возвращающий ее последний элемент
        // или кидающий исключение, если коллекция пуста
        System.out.println("---Last element---");
        System.out.printf("Origin array: %s%n", arrayAsString(List.of(integers)));
        System.out.printf("Last: %s%n", getLastOrThrow(List.of(integers)));
        try {
            getLastOrThrow(List.of(new Integer[0]));
        }
        catch (NoSuchElementException ex) {
            System.out.println(ex.toString());
        }
        System.out.println();

        // метод, принимающий на вход массив целых чисел, возвращающий сумму
        // чётных чисел или 0, если чётных чисел нет
        System.out.println("---Even Integers---");
        System.out.printf("Origin array: %s%n", arrayAsString(List.of(integers)));
        System.out.printf("Even sum: %s%n", sumEven(integers));
        var empty = new Integer[0];
        System.out.printf("Origin array: %s%n", arrayAsString(List.of(empty)));
        System.out.printf("Even sum: %s%n%n", sumEven(empty));

        // метод, преобразовывающий все строки в списке в Map,
        // где первый символ – ключ, оставшиеся – значение
        System.out.println("---Map---");
        System.out.printf("Origin array: %s%n", arrayAsString(List.of(strings)));
        var map = asSuperMap(List.of(strings));
        for (var key : map.keySet()){
            System.out.printf("'%c' : '%s'%n", key, map.get(key));
        }
    }

    // вывод массива
    private static String arrayAsString(Iterable<?> list){

        var afterFirst = false;
        var sb = new StringBuilder();
        sb.append("[");

        for (var item : list){
            if (afterFirst)
                sb.append(", ");
            else {
                afterFirst = true;
            }

            if (item instanceof String)
                sb.append('\"').append(item).append('\"');
            else
                sb.append(item);
        }
        sb.append("]");

        return sb.toString();
    }

    // метод, возвращающий среднее значение списка целых чисел
    private static double intAvg(List<Integer> list){
        return list
                .stream()
                .mapToInt(x -> x)
                .average()
                .orElse(0);
    }

    // метод, приводящий все строки в списке в верхний регистр
    // и добавляющий к ним префикс «_new_»
    private static List<String> upperConcatWithPrefix(List<String> strings){
        return strings
                .stream()
                .map(s -> "_new_" + s.toUpperCase())
                .toList();
    }

    // метод, возвращающий список квадратов всех встречающихся
    // только один раз элементов списка
    private static List<Integer> sumDistinctSquare(List<Integer> numbers){
        return numbers
                .stream()
                .distinct()
                .map(n -> n * n)
                .toList();
    }

    // метод, принимающий на вход коллекцию и возвращающий ее последний элемент
    // или кидающий исключение, если коллекция пуста
    private static <T> T getLastOrThrow(Collection<T> items){
        return items
                .stream()
                .reduce((n1, n2) -> n2)
                .orElseThrow();
    }

    // метод, принимающий на вход массив целых чисел, возвращающий сумму
    // чётных чисел или 0, если чётных чисел нет
    private static Integer sumEven(Integer[] array){
        return Arrays.stream(array)
                .filter(n -> n % 2 == 0)
                .reduce(Integer::sum)
                .orElse(0);
    }

    // метод, преобразовывающий все строки в списке в Map,
    // где первый символ – ключ, оставшиеся – значение
    private static Map<Character, String> asSuperMap(List<String> list){
        return list
                .stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1)
                ));
    }
}
