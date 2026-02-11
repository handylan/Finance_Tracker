package console;

import java.time.LocalDate;

public class Transaction {
    //fields
    private final int id;
    private static int nextId = 1;
    private final double amount;
    private final TransactionType type;
    private final String description;
    private final String category;
    private final LocalDate date;

    //constructor
    public Transaction(double amount, String category, String description, LocalDate date) {
        if (amount == 0) {
            throw new IllegalArgumentException("Amount can't be 0.");
        }
        this.id = nextId++;
        this.amount = Math.abs(amount);
        this.type = (amount >= 0) ? TransactionType.INCOME : TransactionType.EXPENSE;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    // getters
    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    //toString() method
    public String toString() {
        String sign = (type == TransactionType.INCOME) ? "+" : "-";
        return String.format("%d | %s%.2f | %s | %s | %s | %s",
                id, sign, amount, type, category, description, date);
    }
}