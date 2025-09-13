import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String pin;
    private double balance;
    private List<Transaction> history;

    public User(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
        this.history = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        history.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.add(new Transaction("Withdraw", amount));
            return true;
        }
        return false;
    }

    public void addTransaction(Transaction t) {
        history.add(t);
    }

    public List<Transaction> getHistory() {
        return history;
    }
}