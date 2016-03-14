package com.sky.customer.service;

import com.google.common.collect.ImmutableMap;
import com.sky.customer.exception.UnknownCustomer;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * This mock service it does not provide any real data.
 * We can decide later if we call another REST endpoint or invoke repository.
 */
@Service
public final class CustomerLocationServiceStub implements CustomerLocationService {

    private static final Map<String, String> CUSTOMER_LOCATION_MOCK = ImmutableMap.<String, String>builder()
            .put("123", "London")
            .put("321", "Liverpool")
            .build();

    @Override
    public String findLocationId(String customerID) {
        String locationId = CUSTOMER_LOCATION_MOCK.get(customerID);
        if (locationId == null) {
            throw new UnknownCustomer();
        }
        return locationId;
    }

}
