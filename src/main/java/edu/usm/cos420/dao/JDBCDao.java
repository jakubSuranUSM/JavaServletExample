package edu.usm.cos420.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDao {
    public void connect() throws ClassNotFoundException {
        
        Class.forName("org.postgresql.Driver");
     
        try (
            Connection conn = DriverManager.getConnection(
              "jdbc:postgresql://localhost:5432/ebookshop",
              "ebookapp", "heslo45");   // For MySQL
            Statement stmt = conn.createStatement();
        ) {
            String sqlStr = "select * from books where author = "
              + "'" + "Kumar" + "'"   // Single-quote SQL string
              + " and qty > 0 order by price desc";
            
            ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server

            int count = 0;
            while(rset.next()) {
                System.out.println("<p>" + rset.getString("author")
                 + ", " + rset.getString("title")
                 + ", $" + rset.getDouble("price") + "</p>");
           count++;
            }
        } catch(Exception ex) {
            System.out.println("<p>Error: " + ex.getMessage() + "</p>");
            System.out.println("<p>Check Tomcat console for details.</p>");
            ex.printStackTrace();
        }  
    } 

    public static void main(String[] args) {
        JDBCDao dao = new JDBCDao();
        try {
            dao.connect();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        }
    }
}
