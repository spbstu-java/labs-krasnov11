package ru.spbstu.edu.krasnov2.lab3;

import java.util.HashMap;
import java.util.Scanner;

public class DictionaryReader {

    public static HashMap<String, String> Read(Scanner scanner) throws Lab3FormatException {
        var result = new HashMap<String, String>();

        var buffer = new StringBuffer();

        var lineNum = 0;
        while (scanner.hasNext()){

            lineNum++;
            var line = scanner.nextLine();

            //System.out.printf(line);
            var delPos = line.indexOf('|');

            if (delPos < 0)
                throw new Lab3FormatException(String.format("Line %d. Delimiter '|' is not found. String value: '%s'", lineNum, line));

            var value = line.substring(0, delPos);
            fillNormalized(value, buffer);

            if (buffer.isEmpty())
                throw new Lab3FormatException(String.format("Line %d. There no word to translate. String value: '%s'", lineNum, line));

            var key = buffer.toString().toLowerCase();
            if (result.containsKey(key))
                throw new Lab3DuplicateException(String.format("Line %d. Duplicate word. String value: '%s'", lineNum, line));

            value = line.substring(delPos + 1);
            fillNormalized(value, buffer);

            if (buffer.isEmpty())
                throw new Lab3FormatException(String.format("Line %d. There no translation text. String value: '%s'", lineNum, line));

            result.put(key, buffer.toString());
        }

        return result;
    }

    private static void fillNormalized(String value, StringBuffer buffer) {
        var parts = value.split("\\s+");

        buffer.setLength(0);

        for (var p : parts){
            if (!buffer.isEmpty())
                buffer.append(' ');

            buffer.append(p);
        }
    }
}
