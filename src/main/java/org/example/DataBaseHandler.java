package org.example;

import java.sql.*;
import java.util.*;


public class DataBaseHandler {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:SportsObjects.s3db");
    }

    public static void CreateTable(String name, List<SportsObject> sportsObjects) throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        DatabaseMetaData metaData = conn.getMetaData();
        var createdTable = true;
        ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (rs.next()) {
            var a = rs.getString("TABLE_NAME");
            if (Objects.equals(rs.getString("TABLE_NAME"), name))
                createdTable = false;
        }
        if (createdTable) {
            statmt.execute(String.format("CREATE TABLE %s ('ID' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "'name' text," +
                    "'subject' text," +
                    "'address' text," +
                    "'date' text);", name));
            for(var sportsObject: sportsObjects) {
                statmt.execute(String.format("INSERT INTO %s ('name', 'subject', 'address', 'date') VALUES ('%s', '%s', '%s', '%s');",
                        name, sportsObject.getName(), sportsObject.getSubject(), sportsObject.getAddress(), sportsObject.getDate()));
            }
        }
    }

    public static void SelectDB(String query) throws ClassNotFoundException, SQLException
    {
// доделать
//        resSet = statmt.executeQuery(query);
//        Dictionary<String, Integer> result = new Hashtable<>();
//        while(resSet.next()) {
//
//        }
    }

    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();
    }

}