package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.db.MyReservationDAO;
import ru.rsreu.restaurantAutomation.datalayer.dto.Table;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;
import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class GetFreeTableCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";
    private static final String TABLES_ATTRIBUTE_NAME = "tables";
    private static final String MILLISECONDS = ":00.000";
    private static final String SPLIT_ELEMENT = "T";
    private static final String SPACE_ELEMENT = " ";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MyReservationDAO myReservationDAO = (MyReservationDAO) req.getServletContext()
                    .getAttribute(ContextAttributeName.RESERVATION_DAO_ATTRIBUTE_NAME);
            String time = req.getParameter(ContextAttributeName.DATE_ATTRIBUTE_NAME)
                    .concat(MILLISECONDS).replace(SPLIT_ELEMENT, SPACE_ELEMENT);
            List<Table> tables = myReservationDAO.getFreeTables(Timestamp.valueOf(time));
            HttpSession session = req.getSession(false);
            session.setAttribute(ContextAttributeName.PERSON_NUMBER_ATTRIBUTE_NAME,
                    req.getParameter(ContextAttributeName.PERSON_NUMBER_ATTRIBUTE_NAME));
            session.setAttribute(ContextAttributeName.DATE_ATTRIBUTE_NAME,
                    req.getParameter(ContextAttributeName.DATE_ATTRIBUTE_NAME));
            session.setAttribute(TABLES_ATTRIBUTE_NAME, tables);
            if (req.getRequestURI().equals(Command.RESERVATION_FOOD_ORDER_PAGE_REDIRECT_PATH)) {
                req.getRequestDispatcher(RESERVATION_FOOD_ORDER_PATH).forward(req, resp);
            } else {
                req.getRequestDispatcher(RESERVATION_PATH).forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
