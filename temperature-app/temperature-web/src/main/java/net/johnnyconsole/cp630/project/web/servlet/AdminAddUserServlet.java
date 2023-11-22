package net.johnnyconsole.cp630.project.web.servlet;

import at.favre.lib.crypto.bcrypt.BCrypt;
import net.johnnyconsole.cp630.project.ejb.interfaces.TemperatureStatelessLocal;
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

@WebServlet("/AdminAddUserServlet")
public class AdminAddUserServlet extends HttpServlet {
    @EJB
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(ApplicationSession.username != null) {
                String username = request.getParameter("username"),
                        name = request.getParameter("name"),
                        password = BCrypt.withDefaults().hashToString(12, request.getParameter("password").toCharArray());
                int accessLevel = Integer.parseInt(request.getParameter("accessLevel"));

                User user = new User(username, name, password, accessLevel);
                if(userDao.addUser(user)) response.sendRedirect("/temperature-web/dashboard.jsp?user=added");
                else response.sendRedirect("/temperature-web/?error=useradd");
            } else response.sendRedirect("/temperature-web/?error=notlogedin");
        } catch(Exception e) {
            response.sendRedirect("/temperature-web/dashboard.jsp?error=prediction");
        }

    }

    private String getMonthName(int month) {
        switch(month) {
            case 1: return " January ";
            case 2: return " February ";
            case 3: return " March ";
            case 4: return " April ";
            case 5: return " May ";
            case 6: return " June ";
            case 7: return " July ";
            case 8: return " August ";
            case 9: return " September ";
            case 10: return " October ";
            case 11: return " November ";
            default: return " December ";
        }
    }
}
