package ru.spbstu.edu.krasnov2.lab3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, Lab3FileNotFound, Lab3FormatException {

        var dictionary = readDictionary(args.length > 0 ? args[0] : "");

        System.out.println("Dictionary:");
        for (var key : dictionary.keySet()){
            System.out.printf("  '%s' : '%s'%n", key, dictionary.get(key));
        }

        System.out.println();
        System.out.println("Enter text for a translation:");

        String line;
        try (var scanner = new Scanner(System.in)){
            line = scanner.nextLine();
        }

        var translated = getTranslated(dictionary, line);

        System.out.println();
        System.out.println("Translated text:");
        System.out.println(translated);
    }

    private static String getTranslated(HashMap<String, String> dictionary, String line) {
        return line;
    }

    private static HashMap<String, String> readDictionary(String path) throws IOException, Lab3FileNotFound, Lab3FormatException {

        var fileName = (path != null && !path.isEmpty())
            ? path
            : "./data/dictionary.txt";

        var file = new File(fileName);
        System.out.printf("Used dictionary file: '%s'%n", file.getCanonicalPath());

        if (!file.exists())
            throw new Lab3FileNotFound("File not found: " + fileName);

        try (var fis = new FileInputStream(file);
             var isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             var scanner = new Scanner(isr)){

            return DictionaryReader.Read(scanner);
        }
    }
}
