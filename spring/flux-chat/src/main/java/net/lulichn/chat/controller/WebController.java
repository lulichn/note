package net.lulichn.chat.controller;

import net.lulichn.chat.domain.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class WebController {
  @Autowired
  ChatService chatService;

  @RequestMapping(method = RequestMethod.GET)
  public String index(Model model) {
    var stream = chatService.stream();

    model.addAttribute("items", new ReactiveDataDriverContextVariable(stream, 1));

    return "index";
  }
}
