package com.home.retry.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BusinessRepository {

    private Logger LOG = LoggerFactory.getLogger(BusinessRepository.class);

    public String saveToRepo() {
        LOG.info("Saving to repo");
        return "Saved";
    }
}
