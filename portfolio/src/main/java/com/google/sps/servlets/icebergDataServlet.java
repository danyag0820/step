package com.google.sps.servlets;

import com.google.common.collect.ImmutableList; 
import com.google.common.collect.ImmutableList.Builder;
import com.google.gson.Gson;
import com.google.sps.data.Iceberg;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.*; 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/** Returns iceberg data as a JSON array, e.g. [{"lat": 38.4404675, "lng": -122.7144313,'size': MED}] */
@WebServlet("/iceberg-data")
public class icebergDataServlet extends HttpServlet {

  private Collection<Iceberg> icebergSightings;


  @Override
  public void init() throws ServletException {

    ImmutableList.Builder<Iceberg> icebergSightings = ImmutableList.builder();

   try (Scanner scanner = new Scanner(getServletContext().getResourceAsStream("/WEB-INF/icebergSightings.csv"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] cells = line.split(",");

            double lat = Double.parseDouble(cells[0]);
            double lng = Double.parseDouble(cells[1]);
            String size = cells[2];

            Iceberg iceberg = Iceberg.create(lat,lng,size);
            icebergSightings.add(iceberg);
            }
        this.icebergSightings = icebergSightings.build(); 
    } catch (Exception e) {
        throw new ServletException("Error reading input file", e);
    }
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    Gson gson = new Gson();
    String json = gson.toJson(icebergSightings);
    response.getWriter().println(json);
  }
}
