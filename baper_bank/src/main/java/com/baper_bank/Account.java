package com.baper_bank;

public class Account {
    private int accountId;
    private double balance = 0;
    private int customerId;

    public int getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Account(owner: Customer) |

    // | + getAccountId(): int |
    // | + getBalance(): double |
    // | + setBalance(balance: double): |
    // | void |
    // | + getOwner(): Customer |
    // | + setOwner(owner: Customer): void
}
