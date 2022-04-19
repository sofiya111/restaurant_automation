package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FoodOrderCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Forwarding error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher(FOOD_ORDER_PATH).forward(req, resp);
        } catch (IOException | ServletException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
