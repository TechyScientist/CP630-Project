package net.johnnyconsole.cp630.project.web.servlet;

import net.johnnyconsole.cp630.project.ejb.interfaces.TemperatureStatelessLocal;
import net.johnnyconsole.cp630.project.web.util.ApplicationSession;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PredictionServlet")
public class PredictionServlet extends HttpServlet {
    @EJB
    private TemperatureStatelessLocal stateless;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if(ApplicationSession.username != null) {
                String model = request.getParameter("model").toLowerCase();
                int year = Integer.parseInt(request.getParameter("year")),
                        month = Integer.parseInt(request.getParameter("month")),
                        day = Integer.parseInt(request.getParameter("day"));
                double prediction = stateless.predict(model, year, month, day);
                if (prediction == Double.MIN_VALUE) throw new Exception("Missing Model");
                response.sendRedirect("/temperature-web/dashboard.jsp?date=" + day + getMonthName(month) + year + "&prediction=" + prediction);
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
