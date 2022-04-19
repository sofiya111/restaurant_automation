package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.dto.Role;
import ru.rsreu.restaurantAutomation.datalayer.dto.User;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;
import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PersonalAccountCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME);
            req.setAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME, user);
            if (user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.MODER) || user.getRole().equals(Role.EMPLOYEE)) {
                req.getRequestDispatcher(PERSONAL_ACCOUNT_PATH).forward(req, resp);
            } else {
                req.getRequestDispatcher(PERSONAL_ACCOUNT_USER_PATH).forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
