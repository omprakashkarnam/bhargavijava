import java.util.*;

class Account {
    private static int accountNumberCounter = 1;
    private int accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public Account() {
        this.accountNumber = accountNumberCounter++;
        this.balance = 0;
        this.transactionHistory = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            return true;
        } else {
            return false;
        }
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

public class BankingSystem {
    private static Map<Integer, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Banking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        Account newAccount = new Account();
        accounts.put(newAccount.getAccountNumber(), newAccount);
        System.out.println("Account created. Your account number is " + newAccount.getAccountNumber());
    }

    private static void deposit() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposited $" + amount + " into account number " + accountNumber);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        Account account = accounts.get(accountNumber);
        if (account != null) {
            if (account.withdraw(amount)) {
                System.out.println("Withdrew $" + amount + " from account number " + accountNumber);
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account number " + accountNumber + " has a balance of $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewTransactionHistory() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Transaction history for account number " + accountNumber + ":");
            for (String transaction : account.getTransactionHistory()) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Account not found.");
        }
    }
}