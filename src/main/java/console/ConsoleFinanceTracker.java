package console;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleFinanceTracker {

    private static final List<Transaction> transactions = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- Personal Finance Tracker (Demo) ---");

        while (true) {
            printMenu();
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Choice input should be numbers.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    viewTransactions();
                    break;
                case 3:
                    viewSummary();
                    break;
                case 4:
                    System.out.println("Goodbye :)");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add Transaction");
        System.out.println("2. View Transaction List");
        System.out.println("3. View Transaction Summary");
        System.out.println("4. Exit");
        System.out.print("Choose Number: ");
    }

    private static void addTransaction() {
        boolean success = false;
        while (!success) {
            System.out.print("Amount(insert negative number if Expense): ");
            double curAmount;
            try {
                curAmount = scanner.nextDouble();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Amount should be numbers.");
                scanner.nextLine();
                continue;
            }

            System.out.print("Description: ");
            String curDescription = scanner.nextLine().trim();

            System.out.print("Category: ");
            String curCategory = scanner.nextLine().trim();

            LocalDate curDate = LocalDate.now();

            try {
                Transaction newTransaction = new Transaction(curAmount, curCategory, curDescription, curDate);
                transactions.add(newTransaction);
                System.out.println("Transaction [" + newTransaction + "] added successfully!");
                success = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("ID  |       Amount | Type     | Description                        | Category     | Date");
            System.out.println("-".repeat(90));
            for (Transaction t : transactions) {
                System.out.println(t.toString());
            }
        }
    }

    private static void viewSummary() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet");
        } else {
            double totalIncome = 0;
            double totalExpense = 0;

            for (Transaction t : transactions) {
                if (t.getType() == TransactionType.INCOME) {
                    totalIncome += t.getAmount();
                } else {
                    totalExpense += t.getAmount();
                }
            }

            double balance = totalIncome - totalExpense;
            String balanceSign = balance >= 0 ? "+" : "";

            System.out.println("INCOMES      | EXPENSES      | BALANCE     ");
            System.out.println("-".repeat(80));
            System.out.printf("+%.2f | -%.2f | %s%.2f", totalIncome, totalExpense, balanceSign, Math.abs(balance));
        }
    }
}