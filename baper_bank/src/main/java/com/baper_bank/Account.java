package com.baper_bank;

public class Account {
    private int accountNumber;
    private double balance = 0;
    private int userId;
    // public String accountType;

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Account(owner: Customer) |

    // | + getAccountId(): int |
    // | + getBalance(): double |
    // | + setBalance(balance: double): |
    // | void |
    // | + getOwner(): Customer |
    // | + setOwner(owner: Customer): void
}
