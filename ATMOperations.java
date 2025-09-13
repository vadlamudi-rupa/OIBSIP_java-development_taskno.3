import java.util.Scanner;

public class ATMOperations {

    public static void showHistory(User user) {
        System.out.println("\n=== Transaction History for " + user.getUserId() + " ===");
        if (user.getHistory().isEmpty()) {
            System.out.println("No transactions to show.");
        } else {
            for (Transaction t : user.getHistory()) {
                System.out.println(t);
            }
        }
        System.out.printf("Current Balance: $%.2f%n", user.getBalance());
    }

    public static void withdraw(Scanner sc, User user) {
        double amount = readDouble(sc, "Enter amount to withdraw: ");
        if (amount <= 0) {
            System.out.println("Enter an amount greater than 0.");
            return;
        }
        if (user.withdraw(amount)) {
            System.out.printf("Withdraw successful. New balance: $%.2f%n", user.getBalance());
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    public static void deposit(Scanner sc, User user) {
        double amount = readDouble(sc, "Enter amount to deposit: ");
        if (amount <= 0) {
            System.out.println("Enter an amount greater than 0.");
            return;
        }
        user.deposit(amount);
        System.out.printf("Deposit successful. New balance: $%.2f%n", user.getBalance());
    }

    public static void transfer(Scanner sc, User sender, Bank bank) {
        System.out.print("Enter recipient User ID: ");
        String recipientId = sc.nextLine().trim();
        User receiver = bank.getUser(recipientId);
        if (receiver == null) {
            System.out.println("Recipient not found.");
            return;
        }
        double amount = readDouble(sc, "Enter amount to transfer: ");
        if (amount <= 0) {
            System.out.println("Enter an amount greater than 0.");
            return;
        }
        if (sender.withdraw(amount)) {
            // add transaction for both
            receiver.deposit(amount);
            sender.addTransaction(new Transaction("Transfer to " + receiver.getUserId(), amount));
            receiver.addTransaction(new Transaction("Transfer from " + sender.getUserId(), amount));
            System.out.printf("Transfer successful. Your new balance: $%.2f%n", sender.getBalance());
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    // Helper to read double safely
    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (e.g. 100 or 50.25).");
            }
        }
    }
}