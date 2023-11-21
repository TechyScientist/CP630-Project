package net.johnnyconsole.cp630.project.web.servlet;

import net.johnnyconsole.cp630.project.web.util.ApplicationSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ApplicationSession.clear();
            response.sendRedirect("/temperature-web?action=logout");
    }
}
