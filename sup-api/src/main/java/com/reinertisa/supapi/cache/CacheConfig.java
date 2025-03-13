package com.reinertisa.supapi.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {

    @Bean(name = {"userLoginCache"})
    public CacheStore<String, Integer> userCache() {
        return new CacheStore<>(900, TimeUnit.SECONDS);
    }

    // you can create different cache. We do not use it but this is just example cache
    @Bean(name = {"registrationCache"})
    public CacheStore<Long, Integer> registrationCache() {
        return new CacheStore<>(1800, TimeUnit.MILLISECONDS);
    }
}
