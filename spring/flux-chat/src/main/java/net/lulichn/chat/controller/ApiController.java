package net.lulichn.chat.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.lulichn.chat.domain.model.Message;
import net.lulichn.chat.domain.service.ChatService;
import net.lulichn.chat.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api")
public class ApiController {
  @Autowired
  ChatService chatService;

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Push data")
  })
  @RequestMapping(path = "/", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public Mono<Long> post(@RequestBody MessageDTO dto) {
    var message = new Message(dto.getName(), dto.getText());
    return chatService.post(message);
  }

  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Pop data")
  })
  @GetMapping("/stream")
  Flux<ServerSentEvent<MessageDTO>> stream() {
    return chatService.stream()
        .map(m -> ServerSentEvent.builder(new MessageDTO(m.getName(), m.getText())).event("stream").build());
  }
}
