package ar.edu.unq.desapp.grupoB.backenddesappapi.config

import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import java.time.Duration


@Configuration
@EnableCaching
class CacheConfig {

    /*@Bean
    fun cacheConfiguration(): RedisCacheConfiguration? {
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofHours(3))
            .disableCachingNullValues()
            .serializeValuesWith(SerializationPair.fromSerializer<Any>(GenericJackson2JsonRedisSerializer()))
    }*/

    @Bean
    fun cacheManager(connectionFactory: RedisConnectionFactory?): RedisCacheManager? {
        val cacheConfiguration = RedisCacheConfiguration
            .defaultCacheConfig()
            .entryTtl(Duration.ZERO)
            .disableCachingNullValues()
        return RedisCacheManager
            .builder(connectionFactory!!)
            .cacheDefaults(cacheConfiguration)
            .build()
    }

    /*
   @Bean
   fun ehCacheCacheManager(): EhCacheManagerFactoryBean {
       val ehCacheManagerFactoryBean = EhCacheManagerFactoryBean()
       ehCacheManagerFactoryBean.setConfigLocation(ClassPathResource("ehcache.xml"))
       ehCacheManagerFactoryBean.setShared(true)
       return ehCacheManagerFactoryBean
   }

 @Bean
   fun cacheManager(): CacheManager? {
       return EhCacheCacheManager(ehCacheCacheManager().getObject()!!)
   }*/

}