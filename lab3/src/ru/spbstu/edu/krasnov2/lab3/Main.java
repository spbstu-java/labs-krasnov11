package ru.spbstu.edu.krasnov2.lab3;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, Lab3FileNotFound, Lab3FormatException {

        var dictionary = readDictionary(args.length > 0 ? args[0] : "");

        for (var key : dictionary.keySet()){
            System.out.printf("'%s' : '%s'%n", key, dictionary.get(key));
        }
    }

    private static HashMap<String, String> readDictionary(String path) throws IOException, Lab3FileNotFound, Lab3FormatException {

        var fileName = (path != null && path.length() > 0)
            ? path
            : "./data/dictionary.txt";

        var file = new File(fileName);
        System.out.println(file.getCanonicalPath());

        if (!file.exists())
            throw new Lab3FileNotFound("File not found: " + fileName);

        HashMap<String, String> dictionary;

        try (var fis = new FileInputStream(fileName);
             var isr = new InputStreamReader(fis, "utf-8");
             Scanner scanner = new Scanner(isr)){

            return DictionaryReader.Read(scanner);
        }
    }
}
