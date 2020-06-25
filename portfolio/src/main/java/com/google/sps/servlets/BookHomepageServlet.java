// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;
 
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import com.google.sps.data.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns a message from the user's form submission*/
@WebServlet("/bookHomepage")
public class BookHomepageServlet extends HttpServlet {
  private ArrayList<Book> bookList = new ArrayList<>();
    // TODO:call database
  private static String toJSON(ArrayList<Book> book) {

    Gson gson = new Gson();
    return gson.toJson(book);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    bookList.add(Book.create(1,"Great Gatsby","F Scott Fitzgerald",3));
    bookList.add(Book.create(2,"book2","author2",4));
    bookList.add(Book.create(3,"book3","author3",1));

    String json = toJSON(bookList);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
 
}