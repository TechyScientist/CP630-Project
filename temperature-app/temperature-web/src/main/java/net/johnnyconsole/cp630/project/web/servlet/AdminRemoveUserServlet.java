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

@WebServlet("/AdminRemoveUserServlet")
public class AdminRemoveUserServlet extends HttpServlet {
    @EJB
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(ApplicationSession.username != null) {
                String username = request.getParameter("user");
                User user = userDao.getUser(username);
                if(userDao.removeUser(user, ApplicationSession.username)) response.sendRedirect("/temperature-web/dashboard.jsp?user=removed");
                else response.sendRedirect("/temperature-web/dashboard.jsp?error=userremove1");
            } else response.sendRedirect("/temperature-web/?error=notloggedin");
        } catch(Exception e) {
            System.out.println(e);
            response.sendRedirect("/temperature-web/dashboard.jsp?error=userremove");
        }

    }
}
