package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<SportsObject> sportsObjects = CSVParser.Parsing("Объекты спорта.csv");
        DataBaseHandler.Conn();
        DataBaseHandler.CreateTable("SportsObjects", sportsObjects);
//        Task1();
//        Task2();
        Task3();
        DataBaseHandler.CloseDB();
    }

    public static void Task1() throws SQLException, ClassNotFoundException {
        DataBaseHandler.SelectDB("select * from SportsObjects");
    }

    public static void Task2() throws SQLException, ClassNotFoundException {
        DataBaseHandler.SelectDB("select * from SportsObjects");
    }

    public static void Task3() throws SQLException, ClassNotFoundException {
        var results = DataBaseHandler.SelectDB("select * from SportsObjects");
        for (var subject: results.keySet())
            System.out.println(subject + ": " + results.get(subject));
    }
}
