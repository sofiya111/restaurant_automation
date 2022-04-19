package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;
import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmOrderCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Command command;
            if (req.getParameter(ContextAttributeName.ORDER_TYPE_ATTRIBUTE_NAME)
                    .equals(ContextAttributeName.FOOD_ORDER_ATTRIBUTE_NAME)) {
                command = new AddFoodOrderCommand();
            } else if (req.getParameter(ContextAttributeName.ORDER_TYPE_ATTRIBUTE_NAME)
                    .equals(ContextAttributeName.RESERVATION_ATTRIBUTE_NAME)) {
                command = new AddReservationCommand();
            } else {
                command = new AddReservationFoodOrderCommand();
            }
            command.execute(req, resp);
            req.getRequestDispatcher(CONFIRM_ORDER_PATH).forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
