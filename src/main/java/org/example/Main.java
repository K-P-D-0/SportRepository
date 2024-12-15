package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<SportsObject> sportsObjects = CSVParser.Parsing("Объекты спорта.csv");
        DataBaseHandler.Conn();
        DataBaseHandler.CreateTable("SportsObjects", sportsObjects);
        DataBaseHandler.SelectDB("123");
        DataBaseHandler.CloseDB();
    }
}