package ru.spbstu.edu.krasnov2.lab3;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class WordDictionary {

    private final static String DELIMITER_REGEX = "(?<=\\s)|(?=\\s)|(?<=\\p{Punct})|(?=\\p{Punct})";

    private final HashMap<String, String> _dictionary;
    private int _maxPartCount;

    public WordDictionary(){
        _dictionary = new HashMap<>();
        _maxPartCount = 0;
    }

    public void Read(InputStreamReader reader) throws Lab3FormatException {

        try(var scanner = new Scanner(reader)){
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
                if (_dictionary.containsKey(key))
                    throw new Lab3DuplicateException(String.format("Line %d. Duplicate word. String value: '%s'", lineNum, line));

                value = line.substring(delPos + 1).trim();

                if (value.isEmpty())
                    throw new Lab3FormatException(String.format("Line %d. There no translation text. String value: '%s'", lineNum, line));

                System.out.printf("'%s' : '%s'%n", key, value);

                _dictionary.put(key, value);
            }
        }
    }

    private void fillNormalized(String value, StringBuffer buffer) {
        var parts = value.split(DELIMITER_REGEX);

        buffer.setLength(0);

        var partCount = 0;
        for (var p : parts){

            if (p.trim().isEmpty())
                continue;

            if (!buffer.isEmpty())
                buffer.append(' ');

            buffer.append(p);
            ++partCount;
        }

        if (_maxPartCount < partCount)
            _maxPartCount = partCount;
    }

    public String translate(String line) {

        if (line == null || line.isEmpty() || _dictionary.isEmpty())
            return line;

        var notWhitespace = new String[_maxPartCount];
        var whitespaceBefore = new boolean[_maxPartCount];

        var result = new StringBuilder();
        var tmpBuffer = new StringBuffer();

        try (var scanner = new Scanner(line)){

            // текст разделяется на токены
            // между токенами: места до и после любых пробелов и пунктуаций
            // т.о. весь текст остается в токенах
            scanner.useDelimiter(DELIMITER_REGEX);

            var fillCount = 0;
            var curIndex = 0;
            while (scanner.hasNext()) {

                var token = scanner.next();
                System.out.println(token);

                var j = (fillCount + curIndex) % _maxPartCount; // циклически

                if (token.trim().isEmpty()){
                    // пробельные символы
                    whitespaceBefore[j] = true;
                    continue;
                }

                notWhitespace[j] = token;
                ++fillCount;

                // заполняем в буфере все места
                if (fillCount < _maxPartCount)
                    continue;

                var found = false;
                for (int partCount = _maxPartCount; partCount > 0 && !found; --partCount) {

                    var key = getKey(tmpBuffer, partCount, curIndex, notWhitespace);

                    if (_dictionary.containsKey(key)){
                        if (whitespaceBefore[curIndex])
                            result.append(' ');

                        for (int i = 0; i < partCount; i++) {
                            j = (i + curIndex) % _maxPartCount; // циклически
                            whitespaceBefore[i] = false;
                        }

                        var translated = _dictionary.get(key);
                        result.append(translated);

                        curIndex = (curIndex + partCount) % _maxPartCount;
                        fillCount -= partCount;

                        found = true;
                    }
                }

                // с текущего слова переводить нечего
                // первое считанное копируем в вывод, переходим к следующему
                if (!found) {

                    if (whitespaceBefore[curIndex])
                        result.append(' ');

                    whitespaceBefore[curIndex] = false;

                    result.append(notWhitespace[curIndex]);

                    curIndex = (curIndex + 1) % _maxPartCount;
                    --fillCount;
                }
            }

            // оставшиеся считанные необработанные
            while (fillCount > 0) {
                --fillCount;
            }
        }

        return result.toString();
    }

    private String getKey(StringBuffer tmpBuffer, int partCount, int curIndex, String[] notWhitespace) {
        // собираем ключ для проверки
        tmpBuffer.setLength(0);
        for (int i = 0; i < partCount; i++) {

            if (i > 0)
                tmpBuffer.append(' ');

            var wordIndex = (curIndex + i) % _maxPartCount; // циклически
            tmpBuffer.append(notWhitespace[wordIndex].trim());
        }

        var key = tmpBuffer.toString();
        return key;
    }
}
