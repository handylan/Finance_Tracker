package console;

import java.time.LocalDate;
import java.util.Scanner;

public enum TransactionType {
    INCOME,
    EXPENSE
}

public class Transaction {
    //fields
    private int id = 0;
    private int nextId = 1;
    private double amount;
    private TransactionType type;
    private String detail;
    private String category;
    private LocalDate date;

    //constructor
    public Transaction() {
        this.id += nextId;
    }
    // getters

    //toString() method
}