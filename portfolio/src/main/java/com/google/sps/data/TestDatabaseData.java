package com.google.sps.data;

import java.util.HashMap;


public class TestDatabaseData {
    private TestDatabaseData(){}

    private static HashMap<Integer,Book> ALL_BOOKS = new HashMap<Integer,Book>();

    public static HashMap<Integer,Book> getTestBookData() {
        ALL_BOOKS.put(1,Book.create(1,"book1","author1",4));
        ALL_BOOKS.put(2,Book.create(2,"book2","author2",3));
        ALL_BOOKS.put(3,Book.create(3,"book3","author3",5));
        ALL_BOOKS.put(4,Book.create(4,"book4","author4",1));

        return ALL_BOOKS;
    }

} 