public class SavingsAccount {
    private double annualInterestRate;
    private double initialBalance;
    private double annualDeposit;

    public SavingsAccount(double annualInterestRate, double initialBalance, double annualDeposit) {
        this.annualInterestRate = annualInterestRate;
        this.initialBalance = initialBalance;
        this.annualDeposit = annualDeposit;
    }

    public double getAnnualDeposit() {
        return annualDeposit;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    public void setAnnualDeposit(double annualDeposit) {
        this.annualDeposit = annualDeposit;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public void setInitialBalance(double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public int calculateAgeWhenBalance(int currentAge, double wantedBalance) {
        double yearsPassed;

        yearsPassed = ((wantedBalance / initialBalance) - 1) / ((annualDeposit / initialBalance) + (annualInterestRate / 100));

        return (int)(currentAge + yearsPassed);
    }
}
