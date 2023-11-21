package net.johnnyconsole.cp630.project.web.servlet;

import net.johnnyconsole.cp630.project.persistence.User;
import net.johnnyconsole.cp630.project.persistence.interfaces.UserDao;
import net.johnnyconsole.cp630.project.web.util.ApplicationSession;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @EJB
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").toLowerCase(),
                password = request.getParameter("password");
        if(userDao.verifyUser(username, password)) {
            User user = userDao.getUser(username);
            ApplicationSession.set(username, user.getName(), user.getAccessLevel());
            response.sendRedirect("/temperature-web/dashboard.jsp");
        }
        else {
            response.sendRedirect("/temperature-web?error=login");
        }
    }
}
