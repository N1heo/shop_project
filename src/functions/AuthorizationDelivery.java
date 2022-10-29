package functions;

import java.io.IOException;
import java.util.Scanner;

public class AuthorizationDelivery {
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
                // deliverymanActions();
                break;
            } else {
                System.out.println("Попытайтесь снова.");
            }
        } while (true);
    }
}
