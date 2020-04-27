package w.g.util;

import com.longruan.ark.common.db.service.IDataBaseCache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@EnableCaching
public class DBCacheConfig implements IDataBaseCache {
    public static CacheManager cacheManager;

    @Bean
    @Override
    public CacheManager getCacheManager() {
        return Optional.ofNullable(cacheManager).orElse(cacheManager = new ConcurrentMapCacheManager());
    }
}
