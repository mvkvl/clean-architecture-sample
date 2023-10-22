package ws.slink.ca.domain.entity;

import ws.slink.ca.domain.exception.InvalidAccountIdException;
import ws.slink.ca.domain.exception.NegativeAmountException;

public class Account {

    Long id;
    double balance;

    public Account(long accountId, double balance) {
        checkAccountId(accountId);
        checkBalance(balance);
        this.id = accountId;
        this.balance = balance;
    }
    public Account(double balance) {
        checkBalance(balance);
        this.balance = balance;
    }

    public long getId() {
        return id == null ? -1 : id;
    }
    public void setId(long value) {
        checkAccountId(value);
        this.id = value;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double value) {
        checkBalance(value);
        this.balance = value;
    }

    private static void checkBalance(double balance) {
        if (balance < 0) {
            throw new NegativeAmountException();
        }
    }
    private static void checkAccountId(long id) {
        if (id <= 0) {
            throw new InvalidAccountIdException();
        }
    }

}
