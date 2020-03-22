package com.home.retry.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class Retryer {

    private Logger LOG = LoggerFactory.getLogger(Retryer.class);

    private final BusinessRepository businessRepository;

    public Retryer(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Retryable(value = {RuntimeException.class},
            maxAttempts = 4,
            backoff = @Backoff(value = 500L, multiplier = 2))
    public String methodWithRetry(String param) {
        LOG.info("Attempting to execute business service method");
        String res = businessRepository.saveToRepo();
        LOG.info("Finished executing business service method");
        return res;
    }

    @Recover
    public String fallBackMethod(RuntimeException exception, String param) {
        LOG.error("Method failed after 4 attempts. The parameter was: " + param, exception);
        return "Recovered";
    }
}
