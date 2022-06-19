package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.db.MyOrderDAO;
import ru.rsreu.restaurantAutomation.datalayer.dto.*;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;
import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrdersCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";
    private static final String ORDERS_ATTRIBUTE_NAME = "orders";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MyOrderDAO myOrderDAO = (MyOrderDAO) req.getServletContext()
                    .getAttribute(ContextAttributeName.ORDER_DAO_ATTRIBUTE_NAME);
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME);
            List<Reservation> reservation = myOrderDAO.getReservationsEmployee(user.getId());
            session.setAttribute(ORDERS_ATTRIBUTE_NAME,reservation);
            req.getRequestDispatcher(ORDERS_EMPLOYEE_PATH).forward(req, resp);
        } catch (IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
