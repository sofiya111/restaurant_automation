package ru.rsreu.restaurantAutomation.datalayer.commands;

import ru.rsreu.restaurantAutomation.datalayer.db.MyUserDAO;
import ru.rsreu.restaurantAutomation.datalayer.dto.Role;
import ru.rsreu.restaurantAutomation.datalayer.dto.User;
import ru.rsreu.restaurantAutomation.servlets.ContextAttributeName;
import ru.rsreu.restaurantAutomation.servlets.MyServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FindUserCommand implements Command {

    private static final String SERVLET_EXCEPTION_MESSAGE = "Redirect error";
    private static final String USER_ATTRIBUTE_NAME = "users";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            MyUserDAO myUserDAO = (MyUserDAO) req.getServletContext()
                    .getAttribute(ContextAttributeName.USER_DAO_ATTRIBUTE_NAME);
            String search = req.getParameter(ContextAttributeName.SEARCH_ATTRIBUTE_NAME);
            List<User> users = new ArrayList<>();
            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute(ContextAttributeName.USER_ATTRIBUTE_NAME);
            if (user.getRole().equals(Role.ADMIN)) {
                user = myUserDAO.findUser(search);
                users.add(user);
                req.setAttribute(USER_ATTRIBUTE_NAME, users);
                req.getRequestDispatcher(ADMIN_PATH).forward(req, resp);
            } else if (user.getRole().equals(Role.MODER)) {
                user = myUserDAO.findClient(search);
                users.add(user);
                req.setAttribute(USER_ATTRIBUTE_NAME, users);
                req.getRequestDispatcher(MODER_PAGE_PATH).forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            throw new MyServletException(SERVLET_EXCEPTION_MESSAGE, e);
        }
    }
}
