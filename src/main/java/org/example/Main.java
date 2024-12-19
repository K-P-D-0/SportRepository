package org.example;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<SportsObject> sportsObjects = CSVParser.Parsing("Объекты спорта.csv");
        DataBaseHandler.Conn();
        DataBaseHandler.CreateTable("SportsObjects", sportsObjects);
        Task1();
//        Task2();
//        Task3();
        DataBaseHandler.CloseDB();
    }

    public static void Task1() throws SQLException, ClassNotFoundException {
        var results = DataBaseHandler.SelectDB(
                "select subject, count(subject) as 'count'\n" +
                        "from SportsObjects\n" +
                        "group by subject\n" +
                        "order by count(subject) desc");
    }

    public static void Task2() throws SQLException, ClassNotFoundException {
        DataBaseHandler.SelectDB("select * from SportsObjects");
    }

    public static void Task3() throws SQLException, ClassNotFoundException {
        System.out.println("Задача 3:");
        var results = DataBaseHandler.SelectDB(
                "select subject, count(subject) as 'count'\n" +
                "from SportsObjects\n" +
                "group by subject\n" +
                "order by count(subject) desc\n" +
                "limit 3");
        for (var subject: results.keySet())
            System.out.println("\t" + subject + ": " + results.get(subject));
    }
}
