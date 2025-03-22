import java.util.Scanner;

// BankAccount class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false; // Insufficient funds
        }
    }

    public void deposit(double amount) {
        balance += amount;
    }
}

// ATM class to represent the ATM machine interface
class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void showMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("Please choose an option:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void withdraw(double amount) {
        if (bankAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful! You withdrew $" + amount);
        } else {
            System.out.println("Error: Insufficient balance for withdrawal.");
        }
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
        System.out.println("Deposit successful! You deposited $" + amount);
    }

    public void checkBalance() {
        System.out.println("Your current balance is: $" + bankAccount.getBalance());
    }
}

// ATMInterface class to handle user input and interface with the ATM machine
public class ATMInterface {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initial account balance for testing
        BankAccount userAccount = new BankAccount(500.00);
        ATM atm = new ATM(userAccount);

        boolean exit = false;
        while (!exit) {
            // Show ATM menu
            atm.showMenu();
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();

            // Handle user choice
            switch (choice) {
                case 1: // Withdraw
                    System.out.print("Enter the amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAmount <= 0) {
                        System.out.println("Error: Amount must be greater than zero.");
                    } else {
                        atm.withdraw(withdrawAmount);
                    }
                    break;

                case 2: // Deposit
                    System.out.print("Enter the amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    if (depositAmount <= 0) {
                        System.out.println("Error: Amount must be greater than zero.");
                    } else {
                        atm.deposit(depositAmount);
                    }
                    break;

                case 3: // Check Balance
                    atm.checkBalance();
                    break;

                case 4: // Exit
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }

            System.out.println(); // Blank line for readability
        }

        scanner.close();
    }
}

