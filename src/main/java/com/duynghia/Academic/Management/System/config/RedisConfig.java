package com.duynghia.Academic.Management.System.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.BatchStrategies;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {

    // 1. Cấu hình ObjectMapper để hỗ trợ dịch LocalDate (Ngày sinh, Ngày tạo...) sang JSON chuẩn
    private ObjectMapper customObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // Giúp Redis nhớ được tên Class của Object để lúc lấy ra tự ép kiểu lại được
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        return objectMapper;
    }

    // 2. CẤU HÌNH THỦ CÔNG: Dùng cho các nghiệp vụ phức tạp (Đếm số lượng, Check tín chỉ)
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        // StringRedisTemplate thay vì RedisTemplate<Object, Object>
        // vì nó lưu thẳng chuỗi Text, rất an toàn và tốc độ cao.
        return new StringRedisTemplate(connectionFactory);
    }

    // 3. CẤU HÌNH TỰ ĐỘNG: Dùng cho Spring Cache (@Cacheable)
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer(customObjectMapper());

        // Tạo luật chung cho mọi loại Cache
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) // Key là String
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer))            // Value là JSON
                .entryTtl(Duration.ofHours(2))       // Cache sống 2 tiếng rồi tự hủy để tránh rác
                .disableCachingNullValues();

        return RedisCacheManager.builder(
                        // Dùng BatchStrategies.scan để quét xóa cache theo lô 1000 record/lần, TRÁNH SẬP REDIS
                        RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory, BatchStrategies.scan(1000)))
                .cacheDefaults(defaultConfig)
                // Ví dụ: Môn học thì cả kỳ mới đổi 1 lần -> Cho sống 30 ngày luôn
                .withCacheConfiguration("program_details", defaultConfig.entryTtl(Duration.ofDays(30)))
                .build();
    }
}