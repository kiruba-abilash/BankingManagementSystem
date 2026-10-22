import java.sql.*;
import java.math.BigDecimal;

public class DBConnection {

    // Database Connection
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/banking_system",
            "root",
            "MyNewPass@123"
        );
    }

    // Create Account
    public static void saveAccount(String acno, String acname)
            throws SQLException {

        String sql = "INSERT INTO accounts (acno, acname, balance) VALUES (?, ?, 0)";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, acno);
            ps.setString(2, acname);

            ps.executeUpdate();

            System.out.println("Account Created Successfully!");
        }
    }

    // Check Existing Account
    public static String getAccountName(String acno)
            throws SQLException {

        String sql = "SELECT acname FROM accounts WHERE acno=?";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, acno);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("acname");
                }
            }
        }

        return null;
    }

    // Check Balance
    public static BigDecimal getBalance(String acno)
            throws SQLException {

        String sql = "SELECT balance FROM accounts WHERE acno=?";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, acno);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("balance");
                }
            }
        }

        throw new SQLException("Account not found");
    }

    // Deposit
    public static void deposit(String acno, BigDecimal amount)
            throws SQLException {

        String sql = "UPDATE accounts SET balance=balance+? WHERE acno=?";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBigDecimal(1, amount);
            ps.setString(2, acno);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Amount Deposited Successfully!");
            } else {
                System.out.println("Account Not Found!");
            }
        }
    }

    // Withdraw
    public static void withdraw(String acno, BigDecimal amount)
            throws SQLException {

        String sql = "UPDATE accounts SET balance=balance-? WHERE acno=? AND balance>=?";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBigDecimal(1, amount);
            ps.setString(2, acno);
            ps.setBigDecimal(3, amount);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Amount Withdrawn Successfully!");
            } else {
                System.out.println("Insufficient Balance!");
            }
        }
    }
}