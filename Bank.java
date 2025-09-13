import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User u) {
        users.put(u.getUserId(), u);
    }

    public User authenticateUser(String userId, String pin) {
        User u = users.get(userId);
        if (u != null && u.validatePin(pin)) {
            return u;
        }
        return null;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}