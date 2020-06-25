package com.google.sps.data;

import com.google.auto.value.AutoValue;

/** A Book */
@AutoValue
public abstract class Book {
  public static Book create(int id,String name, String author, int rating) {
    return new AutoValue_Book(id, name, author,rating);
  }

  abstract int id();
  abstract String name();
  abstract String author();
  abstract int rating();
}
