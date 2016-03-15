package com.sky.customer.exception;

public final class UnknownCustomer extends RuntimeException {

    public UnknownCustomer() {
        super("There was a problem retrieving the customer information");
    }

}
