package com.baper_bank;

import java.sql.Timestamp;
import java.util.UUID;

public class Transaction {
    String transactionId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    double amount;
    int senderAN;
    int receiverAN;
}
