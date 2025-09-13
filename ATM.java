import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // prepare demo bank and users
        Bank bank = new Bank();
        bank.addUser(new User("user1", "1234", 5000.00));
        bank.addUser(new User("user2", "4321", 3000.00));

        System.out.println("=== Welcome to the Java ATM ===");

        User currentUser = null;
        while (currentUser == null) {
            System.out.print("\nEnter User ID: ");
            String id = sc.nextLine().trim();
            System.out.print("Enter PIN: ");
            String pin = sc.nextLine().trim();

            currentUser = bank.authenticateUser(id, pin);
            if (currentUser == null) {
                System.out.println("Invalid credentials — please try again.");
            }
        }

        System.out.println("\nLogin successful. Hello, " + currentUser.getUserId() + "!");

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option (1-5): ");

            String choiceLine = sc.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(choiceLine);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    ATMOperations.showHistory(currentUser);
                    break;
                case 2:
                    ATMOperations.withdraw(sc, currentUser);
                    break;
                case 3:
                    ATMOperations.deposit(sc, currentUser);
                    break;
                case 4:
                    ATMOperations.transfer(sc, currentUser, bank);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Enter 1..5.");
            }
        }

        sc.close();
    }
}