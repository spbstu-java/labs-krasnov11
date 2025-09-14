package ru.spbstu.edu.krasnov2.lab3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, Lab3FileNotFound, Lab3FormatException {

        var dictionary = readDictionary(args.length > 0 ? args[0] : "");

        System.out.println();
        System.out.println("Enter text for a translation:");

        String line;
        try (var scanner = new Scanner(System.in)){
            line = scanner.nextLine();
        }

        var translated = dictionary.translate(line);

        System.out.println();
        System.out.println("Origin text:     " + line);
        System.out.println("Translated text: " + translated);
    }

    private static WordDictionary readDictionary(String path) throws IOException, Lab3FileNotFound, Lab3FormatException {

        var fileName = (path != null && !path.isEmpty())
            ? path
            : "./data/dictionary.txt";

        var file = new File(fileName);
        System.out.printf("Used dictionary file: '%s'%n", file.getCanonicalPath());

        if (!file.exists())
            throw new Lab3FileNotFound("File not found: " + fileName);

        var result = new WordDictionary();

        try (var fis = new FileInputStream(file);
             var reader = new InputStreamReader(fis, StandardCharsets.UTF_8)){

            result.Read(reader);
        }

        return result;
    }
}
