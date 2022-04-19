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

public class StartPageCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Forwarding error";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession(true);
            User user = (User) session.getAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME);
            if (user.getRole().equals(Role.USER)) {
                req.getRequestDispatcher(START_PAGE_PATH).forward(req, resp);
            } else if (user.getRole().equals(Role.ADMIN)) {
                Command command = new GetUsersCommand();
                command.execute(req, resp);
            } else if (user.getRole().equals(Role.MODER)) {
                Command command = new GetUsersModerCommand();
                command.execute(req, resp);
            } else if (user.getRole().equals(Role.EMPLOYEE)) {
                Command command = new GetMenuEmployeeCommand();
                command.execute(req, resp);
            }
        } catch (IOException | ServletException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
