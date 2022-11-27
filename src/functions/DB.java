package functions;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB {

   static Connection c;
   static Statement stmt;

   public DB(){
      c = null;
      stmt = null;
   }

   public static void open_db() {
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/db_oop",
            "postgres", "iruves45");
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   }

   public static void create_table() {
      try {
         stmt = c.createStatement();
         String sql = "CREATE TABLE PRODUCT " +
            "(ID            INT               PRIMARY KEY   NOT NULL," +
            " NAME          TEXT              NOT NULL, " +
            " PRICE         DOUBLE PRECISION  NOT NULL, " +
            " AMOUNT        INT, " +
            " CATEGORY      TEXT              NOT NULL, " +
            " SALE          BOOLEAN           NOT NULL, " +
            " SALE_AMOUNT   DOUBLE PRECISION  NOT NULL, " +
            " BUY_DATE      TIMESTAMP         DEFAULT NOW())";
         stmt.executeUpdate(sql);

         String sql2 = "CREATE TABLE ORDERED " +
            "(ID            INT               PRIMARY KEY   NOT NULL," +
            " NAME          TEXT              NOT NULL, " +
            " PRICE         DOUBLE PRECISION  NOT NULL, " +
            " AMOUNT        INT, " +
            " CATEGORY      TEXT              NOT NULL, " +
            " SALE          BOOLEAN           NOT NULL, " +
            " SALE_AMOUNT   DOUBLE PRECISION  NOT NULL, " +
            " ORDER_DATE    TIMESTAMP         DEFAULT NOW())";
         stmt.executeUpdate(sql2);

         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
   }
}