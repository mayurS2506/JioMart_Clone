package MainApp;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------Welcome to Jio-Mart----------------------");
        System.out.println("1 : ADMIN");
        System.out.println("2 : USER");
        System.out.print("Enter Your Choice : ");
        int choice = sc.nextInt();
        if (choice==1){
            MainAppAdmin admin= new MainAppAdmin();
            admin.productOperation();
        }else if (choice==2){
            UserMainApp user = new UserMainApp();
                user.operation();
        }else {
            System.out.println("Invalid  Choice");
        }
    }
}
