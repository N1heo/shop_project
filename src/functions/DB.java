package functions;

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
         String sql = "CREATE TABLE IF NOT EXISTS PRODUCT " +
            "(ID            SERIAL            PRIMARY KEY," +
            " NAME          TEXT              NOT NULL, " +
            " PRICE         DOUBLE PRECISION  NOT NULL, " +
            " AMOUNT        INT, " +
            " CATEGORY      TEXT              NOT NULL, " +
            " SALE          BOOLEAN           NOT NULL, " +
            " SALE_AMOUNT   DOUBLE PRECISION  NOT NULL, " +
            " BUY_DATE      TIMESTAMP         DEFAULT NOW())";
         stmt.executeUpdate(sql);

         String sql2 = "CREATE TABLE IF NOT EXISTS ORDERED " +
            "(ID            SERIAL            PRIMARY KEY," +
            " NAME          TEXT              NOT NULL, " +
            " PRICE         DOUBLE PRECISION  NOT NULL, " +
            " AMOUNT        INT, " +
            " CATEGORY      TEXT              NOT NULL, " +
            " ORDER_FEE     DOUBLE PRECISION, " +
            " ORDER_DATE    TIMESTAMP         DEFAULT NOW())";
         stmt.executeUpdate(sql2);

         String sql3 = " CREATE OR REPLACE FUNCTION SET_DEFAULT_FEE() RETURNS TRIGGER AS $$ " +
                        " BEGIN " +
                        "   IF NEW.ORDER_FEE IS NULL THEN " + 
                        "      NEW.ORDER_FEE := NEW.PRICE * 0.1; " +
                        "   END IF; " +
                        "   RETURN NEW; "+
                        "END; " +
                        "$$ LANGUAGE PLPGSQL; " +

                        "DROP TRIGGER IF EXISTS TRIG_SET_DEFAULT_FEE " +
                        "ON PUBLIC.ORDERED;" +

                        "CREATE TRIGGER TRIG_SET_DEFAULT_FEE " +
                        " BEFORE INSERT " +
                        " ON ORDERED " +
                        " FOR EACH ROW " +
                        " EXECUTE PROCEDURE SET_DEFAULT_FEE();";
         stmt.executeUpdate(sql3);

         stmt.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
   }
   
   
}