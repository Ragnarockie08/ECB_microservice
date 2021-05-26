package org.bochenek.service;

public interface CacheService {

    void cleanCaches();

    void scheduledCacheClean();
}
