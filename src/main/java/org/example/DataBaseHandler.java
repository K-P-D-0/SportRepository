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

    public static void CreateTable(String name, List<SportsObject> sportsObjects) throws SQLException
    {
        statmt = conn.createStatement();
        DatabaseMetaData metaData = conn.getMetaData();
        var createdTable = true;
        ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        while (rs.next()) {
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

    public static Map<String, Integer> SelectDB(String query) throws SQLException
    {
        resSet = statmt.executeQuery(query);
        Map<String, Integer> result = new LinkedHashMap<>();
        while(resSet.next()) {
            var key = resSet.getString("subject");
            var value = resSet.getString("count");
            result.put(key, Integer.parseInt(value));
        }
        return result;
    }

    public static void CloseDB() throws SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();
    }

}