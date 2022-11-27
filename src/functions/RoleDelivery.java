package functions;

import java.io.IOException;
import java.util.Scanner;

import static functions.DB.*;
import java.sql.ResultSet;

public class RoleDelivery {
    // Авторизация курьера
    public static void deliverymanInputLgPw() throws IOException {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Введите логин: ");
            String input_lg = sc.next();
            sc.nextLine();
            System.out.print("Введите пароль: ");
            String input_pw = sc.next();
            sc.nextLine();

            boolean haveInArray = FileReader.authorization(2, input_lg, input_pw);

            if (haveInArray) {
                System.out.println("Приветствую дорогой доставщик!");
                menu();
                break;
            } else {
                System.out.println("Попытайтесь снова.");
            }
        } while (true);
    }

    public static String menu() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Меню пользователя (Доставщик):");
        System.out.println("(1) Показать список заказанных товаров");
        System.out.println("(2) Показать доставленные товары");
        System.out.println("(3) Доставить");
        System.out.println("(4) Показать количество доставленных товаров");
        System.out.println("(5) Показать количество заказанных товаров");
        System.out.println("(6) Показать мой заработок ");
        System.out.println("(7) Выход");
        do {
            System.out.print("Ваш выбор: ");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    System.out.println("");
                    show_delivery();
                    break;
                case "2":
                    System.out.println("");
                    break;
                case "3":
                    System.out.println("");
                    break;
                case "4":
                    System.out.println("");
                    break;
                case "5":
                    System.out.println("");
                    break;
                case "6":
                    System.out.println("");
                    break;
                case "7":
                    System.out.println("Выход из системы");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ошибка ввода");
                    System.out.print("Желаете выйти[1] или повторить[0]?");
                    int ex = sc.nextInt();
                    if (ex == 0){
                        menu();
                    }
                    else if(ex == 1){
                        System.exit(0);
                    }
            }
            break;
        } while (true);
        return "";
    }

    public static void show_delivery() {
        try {
           stmt = c.createStatement();
           ResultSet rs = stmt.executeQuery( "SELECT * FROM ORDERED;" );
           while ( rs.next() ) {
              int id = rs.getInt("id");
              String  name = rs.getString("name");
              int amount  = rs.getInt("amount");
              String  category = rs.getString("category");
              System.out.println( "ID = " + id );
              System.out.println( "NAME = " + name );
              System.out.println( "AMOUNT = " + amount );
              System.out.println( "CATEGORY = " + category );
              System.out.println();
           }
           rs.close();
           stmt.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
           System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}
