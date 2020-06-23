package com.google.sps.servlets;

import com.google.cloud.language.v1.AnalyzeEntitySentimentRequest;
import com.google.cloud.language.v1.AnalyzeEntitySentimentResponse;
import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.EncodingType;
import com.google.cloud.language.v1.Entity;
import com.google.cloud.language.v1.EntityMention;
import com.google.cloud.language.v1.LanguageServiceClient;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/entityAnalysis")
public class EntityAnalysisServlet extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
      throws IOException {
    // Instantiate the Language client com.google.cloud.language.v1.LanguageServiceClient
    try (LanguageServiceClient language = LanguageServiceClient.create()) {
      String message = servletRequest.getParameter("entityMessage");
      Document doc = Document.newBuilder().setContent(message).setType(Type.PLAIN_TEXT).build();
      AnalyzeEntitySentimentRequest request = AnalyzeEntitySentimentRequest.newBuilder()
                                                  .setDocument(doc)
                                                  .setEncodingType(EncodingType.UTF16)
                                                  .build();
      // detect entity sentiments in the given string
      AnalyzeEntitySentimentResponse response = language.analyzeEntitySentiment(request);
      // Print the response
      servletResponse.setContentType("text/html;");

      for (Entity entity : response.getEntitiesList()) {
        servletResponse.getWriter().println("<p>Entity Name: " + entity.getName() + "</p>");
        servletResponse.getWriter().println("<p>Salience: " + entity.getSalience() + "</p>");
        servletResponse.getWriter().println("<p>Sentiment: " + entity.getSentiment() + "</p>");
        for (EntityMention mention : entity.getMentionsList()) {
          servletResponse.getWriter().println(
              "<p>Begin Offset: " + mention.getText().getBeginOffset() + "</p>");
          servletResponse.getWriter().println(
              "<p>Content: " + mention.getText().getContent() + "</p>");
          servletResponse.getWriter().println(
              "<p>Magnitude: " + mention.getSentiment().getMagnitude() + "</p>");
          servletResponse.getWriter().println(
              "<p>Sentiment Score: " + mention.getSentiment().getScore() + "</p>");
          servletResponse.getWriter().println("<p>Type: " + mention.getType() + "</p>");
        }
      }
    }
  }
}
