package com.baper_bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankSystem {

    List<Customer> customers = new ArrayList<Customer>();
    List<Account> accounts = new ArrayList<Account>();
    // | - customers: List<Customer> |
    // | - accounts: List<Account> |

    protected int addCustomer() {
        int lastCustomerId = 1;
        if (customers.size() > 0)
            lastCustomerId = this.customers.get(this.customers.size() - 1).customerId;
        // Scanner sc = new Scanner(System.in);

        Customer nc = new Customer();
        nc.name = "MD. Musa";
        nc.address = "Dhaka";
        nc.phoneNumber = "01700507265";
        nc.customerId = (lastCustomerId > 0) ? lastCustomerId + 1 : 1;

        customers.add(nc);
        System.out.println("Account created successfully\n");
        System.out.println("A/N: " + nc.customerId);
        return nc.customerId;
    }

    public void removeCustomer(int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customerId == customers.get(i).customerId) {
                customers.remove(i);
                System.out.println("Customer with id: " + customerId + " removed successfully");
                return;
            }
        }
        System.out.println("Customer with id: " + customerId + " does not exist");
    }

    // | + addAccount(account: Account): |
    // | void |
    public void addAccount() {
        int customerId = this.addCustomer();
        int lastAcId = 1;
        if (accounts.size() > 0)
            lastAcId = this.accounts.get(this.accounts.size() - 1).getAccountId();

        Account ac = new Account();
        ac.setAccountId(lastAcId);
        ac.setCustomerId(customerId);
        accounts.add(ac);
    }

    public void deposit(int accountId, double amount) {
        if (amount < 0) {
            System.out.println("Amount must be positive number");
            return;
        }
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountId() == accountId) {
                Account ac = accounts.get(i);
                ac.setBalance(ac.getBalance() + amount);
                System.out.println("Amount " + amount + " is has been deposited\n");
                return;
            }
        }
        System.out.println("Something went wrong");
    }

    public void withdraw(int accountId, double amount) {
        if (amount < 0) {
            System.out.println("Amount must be positive number");
            return;
        }
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountId() == accountId) {
                Account ac = accounts.get(i);
                if (ac.getBalance() < amount) {
                    System.out.println("Not have sufficient balance");
                    return;
                }
                ac.setBalance(ac.getBalance() - amount);
                System.out.println("Amount " + amount + " is has been withdrawn\n");
                return;
            }
        }
        System.out.println("Something went wrong");
    }
    // | + removeAccount(account: Account): |
    // | void |
    // | + getCustomerAccounts(customer: |
    // | Customer): List<Account> |
    // | + deposit(account: Account, |
    // | amount: double): void |
    // | + withdraw(account: Account, |
    // | amount: double): void |
    // | + transfer(source: Account, |
    // | destination: Account, |
    // | amount: double): void |
    // | + displayCustomerInfo(customer: |
    // | Customer): void
}
