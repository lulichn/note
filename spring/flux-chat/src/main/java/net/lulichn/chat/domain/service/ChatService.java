package net.lulichn.chat.domain.service;

import io.lettuce.core.api.reactive.RedisReactiveCommands;
import net.lulichn.chat.domain.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.Record;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamReceiver;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ChatService {
  private static final String STREAM  = "main";

  @Autowired
  ReactiveRedisTemplate<String, Message> template;

  @Autowired
  StreamReceiver<String, ObjectRecord<String, Message>> receiver;

  public Mono<Long> post(Message message) {
    var record = ObjectRecord.create(STREAM, message);
    return template.opsForStream().add(record).map(RecordId::getSequence);
  }

  public Flux<Message> stream() {
    return receiver.receive(StreamOffset.fromStart(STREAM))
        .map(Record::getValue);
  }
}
