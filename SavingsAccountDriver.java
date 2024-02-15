import javax.swing.JOptionPane;

public class SavingsAccountDriver {
    public static void main(String[] args) {
        String annualDepositStr;
        double annualDeposit;

        try {
            annualDepositStr = JOptionPane.showInputDialog("Enter the amount to be deposited annually:");
            annualDeposit = Double.parseDouble(annualDepositStr);

            SavingsAccount nelson = new SavingsAccount(2.5, 10000, annualDeposit);
            int ageWhenBalance = nelson.calculateAgeWhenBalance(30, 400000);

            System.out.println("You will be " + ageWhenBalance + " when you have $400,000 in your account.");
        } catch (Exception e) {
            System.out.println("Amount must be a number (no symbols)");
        }
    }
}
