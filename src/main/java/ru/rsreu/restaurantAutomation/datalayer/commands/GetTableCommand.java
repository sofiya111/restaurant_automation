package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.db.MyReservationDAO;
import ru.rsreu.restaurantAutomation.datalayer.dto.Reservation;
import ru.rsreu.restaurantAutomation.datalayer.dto.Table;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;
import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GetTableCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";
    private static final String TABLES_ATTRIBUTE_NAME = "tables";
    private static final String ORDERS_ATTRIBUTE_NAME = "orders";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession(false);
            List<Reservation> reservations = (List<Reservation>) session.getAttribute(ORDERS_ATTRIBUTE_NAME);
            int id = Integer.parseInt(req.getParameter("reservation"));
            List<Table> tables = new ArrayList<>();
            for (Reservation reservation : reservations) {
                if (reservation.getId() == id) {
                    tables = reservation.getTables();
                    break;
                }
            }
            req.setAttribute(TABLES_ATTRIBUTE_NAME, tables);
            req.getRequestDispatcher(ORDERS_EMPLOYEE_PATH).forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
