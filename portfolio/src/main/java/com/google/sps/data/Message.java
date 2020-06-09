package com.google.sps.data;

/** A message on a message board. */
public final class Message {

    private final long id;
    private final String name;
    private final String email;
    private final String text;

  public Message(long id,String name, String email, String text) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.text = text;
  }
}
