package com.google.sps.data;

import com.google.auto.value.AutoValue;

/** A message on a message board. */
@AutoValue
public abstract class Message {
    public static Message create(long id, String name, String email, String text) {
        return new AutoValue_Message(id,name,email,text);
    }

    abstract long id();
    abstract String name();
    abstract String email();
    abstract String text();
}
