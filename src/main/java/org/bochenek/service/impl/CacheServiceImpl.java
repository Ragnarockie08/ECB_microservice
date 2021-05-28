package org.bochenek.service.impl;

import org.bochenek.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private CacheManager cacheManager;

    private Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Override
    public void cleanCaches() {
        cacheManager.getCacheNames()
                .forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
        logger.info("Cache cleared on:" + LocalDateTime.now());
    }

    @Override
    @Scheduled(cron = "0 */1 * * * *")
    public void scheduledCacheClean() {
        cleanCaches();
    }
}
