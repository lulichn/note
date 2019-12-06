package net.lulichn.chat.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.lulichn.chat.dto.TweetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamReceiver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class SampleController {
  @Autowired
  ReactiveRedisTemplate<String, String> template;

  @Autowired
  StreamReceiver<String, MapRecord<String, String, String>> receiver;

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Push data")
  })
  @RequestMapping(path = "/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public Mono<Long> post(@RequestBody TweetDTO dto) {
    var recordIdMono = template.opsForStream().add("main", Map.of("message", dto.getText()));
    return recordIdMono.map(RecordId::getSequence);
  }

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Pop data")
  })
  @GetMapping("/stream")
  Flux<ServerSentEvent<String>> stream() {
    return receiver.receive(StreamOffset.fromStart("main"))
        .map(e -> e.getValue().get("message"))
        .map(m -> ServerSentEvent.builder(m).event("stream").build());
  }
}
