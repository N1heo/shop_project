package functions;

import java.util.Scanner;

public class RoleDirector {
     // Авторизация директора
     public static void directorInputLgPw() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print("Введите логин: ");
            String input_lg = sc.next();
            sc.nextLine();
            System.out.print("Введите пароль: ");
            String input_pw = sc.next();
            sc.nextLine();

            boolean haveInArray = FileReader.authorization(0, input_lg, input_pw);
            
            if (haveInArray) {
                System.out.println("Директор, вы успешно вошли!");
                // directorActions();
                break;
            } else {
                System.out.println("Попытайтесь снова");
            }
        } while (true);
    }

}
