package org.example;

import org.jfree.ui.RefineryUtilities;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<SportsObject> sportsObjects = CSVParser.Parsing("Объекты спорта.csv");
        DataBaseHandler.Conn();
        DataBaseHandler.CreateTable("SportsObjects", sportsObjects);
//        Task1();
        Task2();
//        Task3();
        DataBaseHandler.CloseDB();
    }

    public static void Task1() throws SQLException {
        var results = DataBaseHandler.SelectDB(
                """
                        select subject, count(subject) as 'count'
                        from SportsObjects
                        group by subject
                        order by count(subject) desc""");
        Graph createGraph = new Graph("Задача 1", results);
        createGraph.pack();
        RefineryUtilities.centerFrameOnScreen(createGraph);
        createGraph.setVisible(true);
    }

    public static void Task2() throws SQLException {
        System.out.println("Задача 2:");
        var results = DataBaseHandler.SelectDB("""
                SELECT subject,
                       round(1.0 * COUNT(subject) * 100 / (SELECT COUNT(*) FROM SportsObjects), 2) AS count
                FROM SportsObjects
                GROUP BY subject
                order by count(subject) desc""");
        for (var subject: results.keySet())
            System.out.println("\t" + subject + ": " + results.get(subject) + "%");
    }

    public static void Task3() throws SQLException {
        System.out.println("Задача 3:");
        var results = DataBaseHandler.SelectDB(
                """
                        select subject, count(subject) as 'count'
                        from SportsObjects
                        group by subject
                        order by count(subject) desc
                        limit 3""");
        for (var subject: results.keySet())
            System.out.println("\t" + subject + ": " + results.get(subject));
    }
}
