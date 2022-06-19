package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPageCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher(LOGIN_PATH).forward(req,resp);
        } catch (IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
