package net.lulichn.chat.dto;

import javax.validation.constraints.Size;
import java.util.Date;

public class TweetDTO {
  private String id;

  @Size(max = 140)
  private String text;

  private Date createdAt = new Date();

  public TweetDTO() {}

  public TweetDTO(String id, String text, Date date) {
    this.id = id;
    this.text = text;
    this.createdAt = date;
  }

  public TweetDTO(String text) {
    this.text = text;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
