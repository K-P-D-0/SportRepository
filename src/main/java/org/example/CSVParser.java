package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSVParser {
    public static List<SportsObject> Parsing(String file) {
        List<SportsObject> sportsObjects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            var isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                var sportsObject = parseLine(line.substring(line.indexOf(",") + 1));
                var name = sportsObject.getFirst();
                var subject = sportsObject.get(1);
                if (Objects.equals(subject, "г. Москва") || Objects.equals(subject, "Московская область"))
                    subject = "Москва";
                if (Objects.equals(subject, ""))
                    subject = "Нет данных";
                var address = sportsObject.get(2);
                var date = new Date();
                if (sportsObject.get(3).split("\\.").length == 3){
                    var day = Integer.parseInt(sportsObject.get(3).split("\\.")[0]);
                    var month = Integer.parseInt(sportsObject.get(3).split("\\.")[1]);
                    var year = Integer.parseInt(sportsObject.get(3).split("\\.")[2]);
                    date = new Date(day, month, year);
                }
                sportsObjects.add(new SportsObject(name, subject, address, date));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sportsObjects;
    }

    private static List<String> parseLine(String line) {
        StringBuilder field = new StringBuilder();
        List<String> result = new ArrayList<>();
        var index = -1;
        var charArray = line.toCharArray();
        for (char c : charArray) {
            index += 1;
            if ((',' == c && (index == charArray.length - 1 || index == 0)) ||
                    (',' == c && charArray[index + 1] != ' ' && !Character.isDigit(charArray[index + 1]) && !Character.isLetter(charArray[index + 1]) &&
                            !Character.isDigit(charArray[index - 1]) && !Character.isLetter(charArray[index - 1]))) {
                result.add(field.toString());
                field = new StringBuilder();
            } else {
                if (c != '\"')
                    field.append(c);
            }
        }
        result.add(field.toString());
        return result;
    }
}
