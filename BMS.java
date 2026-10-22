import java.util.Scanner;
import java.sql.SQLException;
import java.math.BigDecimal;

public class BMS {

    public static void main(String[] args) {

        Scanner abi = new Scanner(System.in);

        try {

            System.out.println("===== BANKING MANAGEMENT SYSTEM =====");

            System.out.print("Enter Account Number: ");
            String acno = abi.nextLine();

            if (!acno.matches("[0-9]+") || acno.length() > 20) {
                System.out.println("Invalid Account Number!");
                return;
            }

            // Check Account
            String acname = DBConnection.getAccountName(acno);

            if (acname == null) {

                System.out.print("Enter Account Holder Name: ");
                acname = abi.nextLine();

                if (!acname.matches("[a-zA-Z ]+")
                        || acname.trim().isEmpty()) {
                    System.out.println("Invalid Account Name!");
                    return;
                }

                DBConnection.saveAccount(acno, acname);

            } else {
                System.out.println("Welcome Back, " + acname);
            }

            // Banking Menu
            while (true) {

                System.out.println("\n===== BANKING MENU =====");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");

                System.out.print("Enter Your Choice: ");

                int choice;

                try {
                    choice = Integer.parseInt(abi.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Choice!");
                    continue;
                }

                switch (choice) {

                    case 1:

                        System.out.println("Current Balance: Rs. "
                                + DBConnection.getBalance(acno));
                        break;

                    case 2:

                        System.out.print("Enter Deposit Amount: ");

                        BigDecimal da = readAmount(abi);

                        if (da != null) {
                            DBConnection.deposit(acno, da);
                        }
                        break;

                    case 3:

                        System.out.print("Enter Withdraw Amount: ");

                        BigDecimal wa = readAmount(abi);

                        if (wa != null) {
                            DBConnection.withdraw(acno, wa);
                        }
                        break;

                    case 4:

                        System.out.println("Thank You For Using Our Bank!");
                        return;

                    default:

                        System.out.println("Invalid Operation!");
                }
            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());

        } finally {
            abi.close();
        }
    }

    // Amount Validation
    static BigDecimal readAmount(Scanner abi) {

        try {
            BigDecimal amount =
                    new BigDecimal(abi.nextLine().trim());

            if (amount.compareTo(BigDecimal.ZERO) <= 0
                    || amount.scale() > 2) {

                System.out.println("Invalid Amount!");
                return null;
            }

            return amount;

        } catch (NumberFormatException e) {

            System.out.println("Enter Numbers Only!");
            return null;
        }
    }
}