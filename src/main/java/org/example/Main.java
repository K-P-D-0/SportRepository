package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<SportsObject> sportsObjects = CSVParser.Parsing("Объекты спорта.csv");
    }
}