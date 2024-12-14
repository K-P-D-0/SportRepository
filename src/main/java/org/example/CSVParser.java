package org.example;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public static List<SportsObject> Parsing(String file) {
        List<SportsObject> sportsObjects = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);

            var csvParser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withQuoteChar('"')
                    .withIgnoreQuotations(true)
                    .build();

            try (CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withCSVParser(csvParser)
                    .withSkipLines(1)
                    .build()) {

                List<String[]> records = csvReader.readAll();

                for (String[] record : records) {
                    String name = record[1];
                    String subject = record[2];
                    String address = record[3];
                    Data data = new Data();
                    for (int i = 4; i < record.length; i++) {
                        if (i != record.length - 1)
                            address += ", " + record[i];
                        else if (record[i] != "") {
                            var dataSplit = record[i].split("\\.");
                            var day = Integer.parseInt(dataSplit[0]);
                            var month = Integer.parseInt(dataSplit[1]);
                            var year = Integer.parseInt(dataSplit[2]);
                            data = new Data(day, month, year);
                        }
                    }
                    sportsObjects.add(new SportsObject(name, subject, address, data));
                }
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sportsObjects;
    }
}
