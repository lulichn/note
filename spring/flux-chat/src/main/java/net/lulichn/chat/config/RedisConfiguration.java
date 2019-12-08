package net.lulichn.chat.config;

import net.lulichn.chat.domain.model.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.stream.StreamReceiver;

@Configuration
public class RedisConfiguration {
  @Bean
  public StreamReceiver<String, ObjectRecord<String, Message>> streamReceiver(ReactiveRedisConnectionFactory factory) {
    return StreamReceiver.create(factory, StreamReceiver.StreamReceiverOptions.builder().targetType(Message.class).build());
  }

  @Bean
  public ReactiveRedisTemplate<String, Message> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
    var keySerializer = new StringRedisSerializer();
    var valueSerializer = new Jackson2JsonRedisSerializer<>(Message.class);

    var builder = RedisSerializationContext.<String, Message>newSerializationContext(keySerializer);
    var context = builder.value(valueSerializer).build();

    return new ReactiveRedisTemplate<>(factory, context);
  }
}
