package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;
import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetEstablishmentCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher(ESTABLISHMENT_PAGE_PATH).forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
