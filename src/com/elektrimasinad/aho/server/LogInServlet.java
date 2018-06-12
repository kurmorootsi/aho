package com.elektrimasinad.aho.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class LogInServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {

    UserService userService = UserServiceFactory.getUserService();
    if (userService.isUserLoggedIn()) {
      // Save the relevant profile info and store it in the session.
      User user = userService.getCurrentUser();
      req.getSession().setAttribute("userEmail", user.getEmail());
      req.getSession().setAttribute("userId", user.getUserId());

      String destination = (String) req.getSession().getAttribute("loginDestination");
      if (destination == null) {
        destination = "/Index.html";
      }

      resp.sendRedirect(destination);
    } else {
      resp.sendRedirect(userService.createLoginURL("/login"));
    }
  }
}
