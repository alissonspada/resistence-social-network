package org.example.rules;

public class TradeFailureException extends Exception {
    public TradeFailureException(String message) {
        super(message);
    }
}