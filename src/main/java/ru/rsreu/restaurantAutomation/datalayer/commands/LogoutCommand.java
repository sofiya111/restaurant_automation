package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getSession().invalidate();
            resp.sendRedirect(START_PAGE_REDIRECT_PATH);
        } catch (IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
