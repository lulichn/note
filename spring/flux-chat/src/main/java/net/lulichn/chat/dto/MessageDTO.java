package net.lulichn.chat.dto;

import javax.validation.constraints.Size;

public class MessageDTO {
  private String name;

  @Size(max = 140)
  private String text;

  public MessageDTO() {}

  public MessageDTO(String name, String text) {
    this.name = name;
    this.text = text;
  }

  public MessageDTO(String text) {
    this.text = text;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
